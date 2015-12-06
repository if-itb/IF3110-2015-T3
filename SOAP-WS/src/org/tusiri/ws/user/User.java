package org.tusiri.ws.user;

import java.io.IOException;
import java.sql.*;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.parser.ParseException;
import org.tusiri.ws.db.DBConnection;


@WebService(endpointInterface = "org.tusiri.ws.user.User")
public class User {
	
	@WebMethod
	public int createUser(String username, String password, String email, String fullname) throws ClientProtocolException, IOException, ParseException{
		int u_id = 0;
		boolean exist = true;
		
		DBConnection dbc = new DBConnection();
		PreparedStatement stmt = dbc.getDBStmt();
		Connection conn = dbc.getConn();
		try{
			String sql = "SELECT username,email FROM user "
					+ "WHERE email = ? OR username = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.setString(2, username);
			ResultSet rs = stmt.executeQuery();
            if (!rs.next()){
            	
            	exist = false;
            }
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		if (exist){
			u_id = -1;
		} 
		else{
			try{
				String sql = "INSERT INTO user(username,password,email,fullname) "
						+ "VALUES(?,MD5(?),?,?)";
				stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, username);
				stmt.setString(2, password);
				stmt.setString(3, email);
				stmt.setString(4, fullname);
				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
	            while (rs.next()) {
	               u_id = rs.getInt(1);
	            } 	
				System.out.println("u_id = " + u_id);
				stmt.close();
				conn.close();
			} catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			} catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			} finally {
			      try{
				         if(stmt!=null)
				            stmt.close();
				      } catch(SQLException se2){
				      
				      }
				      try {
				         if(conn!=null)
				            conn.close();
				      } catch(SQLException se){
				         se.printStackTrace();
				      }
			}
		}
		return u_id;
	}
	
	@WebMethod
	public UserDetail getUser(int id_user){
		UserDetail A = new UserDetail(id_user);
		return A;
	}
	
}
