package com.wbd.tokenChecker;



import com.wbd.tokenChecker.TokenIdentity;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class TokenChecker{
        private int valid;
        private int IDUser;
        private int expired;
        public TokenChecker(){
            valid = 0;
            IDUser = 0;
            expired = 0;
        }
        
        public int getValid(){
		return valid;	
	}
	
	public void setValid(int _valid){
		this.valid = _valid;
	}
	
	public int getIDUser(){
		return IDUser;
	}
	
	public void setIDUser(int _IDUser){
		this.IDUser = _IDUser;
	}
        
        public int getExpired(){
            return expired;
        }
        
        public void setExpired(int expired){
            this.expired = expired;
        }

        public static void deleteToken(String access_token){
            Connection conn = null;
            try {
                //Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
                Statement stmt = conn.createStatement();
                String sql = "DELETE FROM token WHERE access_token = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, access_token);
                dbStatement.executeUpdate();
                stmt.close();
            } catch (SQLException ex){
                System.out.println(ex);
            }
        }
    
        
	public void check(String access_token) throws ParseException{
                System.out.println("TOken Checker Start");
                try {
                    //this.valid = -1;
                    String req = "access_token=" + access_token;
                    byte[] postData       = req.getBytes( StandardCharsets.UTF_8 );
                    int    postDataLength = postData.length;
                    String request        = "http://localhost:8082/StackExchange_IS/rest/tokenValidate";
                    URL    url;
                    try {
                        url = new URL( request );
                        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                        conn.setDoOutput( true );
                        conn.setInstanceFollowRedirects( false );
                        conn.setRequestMethod( "POST" );
                        conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
                        conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
                        conn.setUseCaches( false );
                        
                        try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                            wr.write( postData );
                        } catch (IOException ex) {
                            //Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        String response = "";
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response += line;
                        }
                           System.out.println("Response : "   + response);
                        int tokenValidity;
			int user_id;
                        
                        JSONParser jsonParser = new JSONParser();
                        JSONObject jsonObject = (JSONObject) jsonParser.parse(response);

                        long tokenValid_long = (long) jsonObject.get("valid");
                        tokenValidity = (int) tokenValid_long;
                        System.out.println("Valid : " + tokenValidity);
                        setValid(tokenValidity);

                        long user_id_long = (long) jsonObject.get("userID");
                        user_id = (int) user_id_long;
                        System.out.println("USer ID : " + user_id);
                        setIDUser(user_id);
                        if (user_id != 0 && valid == 0){ // Kondisi expired
                            deleteToken(access_token);
                            setExpired(1);
                        }
                    } catch (MalformedURLException ex) {
                        //Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        //Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch(Exception e){
                    e.printStackTrace();
                }

                /*		try{
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
				"http://localhost:8082/StackExchange_IS/rest/tokenValidate");
			
			StringEntity input = new StringEntity("{\"access_token\":\""+access_token+"\"}");
			
			input.setContentType("application/json");
			postRequest.setEntity(input);
			
			HttpResponse response = httpClient.execute(postRequest);
	
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
				new InputStreamReader((response.getEntity().getContent())));
			
			String output_response;
			int tokenValidity;
			int user_id;
			
			if ((output_response = br.readLine()) != null) {

				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(output_response);

				long tokenValid_long = (long) jsonObject.get("valid");
				tokenValidity = (int) tokenValid_long;
				
				ti.setValid(tokenValidity);
				
				long user_id_long = (long) jsonObject.get("userID");
				user_id = (int) user_id_long; 
				ti.setIDUser(user_id);
				
			}
			 httpClient.getConnectionManager().shutdown();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
           
                System.out.println("TOken Checker Stop");
		System.out.println("Token Identity :" + ti);
		return ti;*/
        }
        
        
}