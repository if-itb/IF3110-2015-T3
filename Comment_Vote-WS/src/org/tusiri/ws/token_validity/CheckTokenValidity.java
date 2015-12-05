package org.tusiri.ws.token_validity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CheckTokenValidity {
	private String access_token;
	public CheckTokenValidity(String access_token){
		this.access_token = access_token;
	}
	public TokenValidity check() throws ClientProtocolException, IOException, ParseException{
		TokenValidity tv = new TokenValidity();
		try{
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
				"http://localhost:8081/REST-WS/rest/token-validity/getUserID");
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
			String output;
			System.out.println("Output from REST ..... \n");
			int isTokenValid;
			int id_user;
			
			if ((output = br.readLine()) != null) {
				System.out.println(output);
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject = (JSONObject) jsonParser.parse(output);
				System.out.println("after JSON Parse");
				long isTokenValid_long = (long) jsonObject.get("valid");
				isTokenValid = (int) isTokenValid_long;
				
				tv.setIsValid(isTokenValid);
				
				System.out.println("after JSON Parse 2");
				long id_user_long = (long) jsonObject.get("id_user");
				id_user = (int) id_user_long; //bahaya, tapi asumsi ga ada angka yang besar
				tv.setIdUser(id_user);
				System.out.println(id_user);
				
			}
			 httpClient.getConnectionManager().shutdown();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tv;
	}
}
