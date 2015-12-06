//tokenGenerate.java


package com.wbd.service;

import com.wbd.rest.Token;
import MD5Hashing.MD5Hashing;

import com.wbd.db.DBConnection;
import com.wbd.rest.UserAgent;
import com.wbd.rest.UserIP;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/token")
public class tokenGenerate {
        
	public static Token generateToken(String email, String password, String user_agent, String user_ipaddress){
		Token token = new Token();

		DBConnection dbc = new DBConnection();
		PreparedStatement stmt = dbc.getStatement();
		Connection conn = dbc.getConnection();
                String availablecookies = "";
		try{
			String sql = "SELECT * FROM user WHERE Email = ? AND Password = ?";
			
			//System.out.println(email);
			//System.out.println(password);
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, password);
			
			//System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			//System.out.println(sql);
			//System.out.println("Luminto homo");
			if(rs.next()){
                            //User is not unique
                            MD5Hashing md5 = new MD5Hashing();

                        // Pemeriksaan Security Browser dan IP agent saat login
                            String sql2 = "SELECT * FROM token NATURAL JOIN user WHERE Email = ? AND Password = ?";
                                PreparedStatement stmt2 = dbc.getStatement();
                                stmt2 = conn.prepareStatement(sql2);
                                stmt2.setString(1, email);
                                stmt2.setString(2, password);
                                ResultSet rs2 = stmt2.executeQuery();
                            if (rs2.next()){
                                availablecookies = rs2.getString("access_token");
                                System.out.println("LELAH");
                                String[] temp = URLDecoder.decode(availablecookies,"UTF-8").split("#");
                                if (!temp[1].equals(user_agent)){
                                    token.errMessage = "Browser Beda dengan akun yang sama";
                                }
                                else if (!temp[2].equals(user_ipaddress)){
                                    token.errMessage = "IP Address Beda dengan akun yang sama";
                                }
                                sql = "DELETE FROM token WHERE access_token = ?";
                                PreparedStatement dbStatement = conn.prepareStatement(sql);
                                dbStatement.setString(1, availablecookies);
                                dbStatement.executeUpdate();
                                System.out.println("SUPER LELAH HIKS");
                                return token;
                            } else {
                                System.out.println("SAMPAI DISINI SAJA");
                                //System.out.println("Sebelum encode : " + ("a" + md5.Hash(password) + "#" + user_agent +"#" + user_ipaddress));
                                token.access_token = URLEncoder.encode( (md5.Hash(password) + "#" + user_agent +"#" + user_ipaddress), "UTF-8");
                                token.errMessage = "";
                                //token.access_token = md5.Hash(password) + "#" + userip.getIPAddress() + "#" + useragent.getAgent();
                                System.out.println("TOken : " + token.access_token);

                                token.lifetime = 5;
                                sql = "DELETE FROM token WHERE access_token = ?";
                                PreparedStatement dbStatement = conn.prepareStatement(sql);
                                dbStatement.setString(1, token.access_token);
                                dbStatement.executeUpdate();
                                System.out.println("SUPER LELAH");
                                sql = "INSERT INTO token(access_token,IDUser) VALUES (?,?)";
                                dbStatement = conn.prepareStatement(sql);
                                dbStatement.setString(1, token.access_token);
                                dbStatement.setInt(2, rs.getInt("IDUser"));
                                dbStatement.executeUpdate();
                            }  
                
            }

		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}		
		return token;
	}

	public static Token requestToken(String access_token){
		Token token = new Token();
      
		DBConnection dbc = new DBConnection();
		PreparedStatement stmt = dbc.getStatement();
		Connection conn = dbc.getConnection();
		try{
			//Get all the token as a sign of user that is logged in
			String sql = "SELECT * FROM token";
			
			//System.out.println(email);
			//System.out.println(password);
			stmt = conn.prepareStatement(sql);
			
			//System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			//System.out.println(sql);
			//System.out.println("Luminto homo");
			boolean found = false;

			while (rs.next() && !found){
                //User is not unique
                String tokenLoggedIn = rs.getString("access_token");
                if (tokenLoggedIn.equals(access_token)){
                	found = true;
                }
            }

        	if (found){
        		//User got the access to create question, etc
        		Date current = new Date();
        		String sql2 = "UPDATE SET created_at= ? WHERE access_token = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql2);
                dbStatement.setTimestamp(1, new Timestamp(current.getTime()));
                dbStatement.setString(2, access_token);
                dbStatement.executeUpdate();
                token.access_token = access_token;
                token.lifetime = 5;  
        	}
        	else{
        		token.access_token = "";
        		token.lifetime = 5;
        		//User  cannot get the access
        	}

        	return token;

		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}		
		return token;
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Token post(@FormParam("email") String email,@FormParam("password") String password,@FormParam("user_agent") String user_agent,@FormParam("user_ipaddress") String user_ipaddress) {
		Token token = generateToken(email,password,user_agent,user_ipaddress);
                //System.out.println("Nino Homo" + token.access_token);
		return token;
	}

	@POST
	@Path("/tokenRequest")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Token requestTokenREST(@FormParam("access_token") String access_token) {
		System.out.println("call regenerateToken");
		Token token = requestToken(access_token);
		System.out.println(token.access_token);
		return token;
	}

	@POST
	@Path("/signout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void signOut(@FormParam("access_token") String access_token) {
	}

        
        
}

