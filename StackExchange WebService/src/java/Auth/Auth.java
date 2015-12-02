/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author acel
 */
public class Auth {
    String token = "";
    String id = "";
    
    public Auth(String token, String id){
        this.token = token;
        this.id = id;
    }
    
    public boolean getResponse(String url){
        String response = "";
        try {
            response = servletResponse(url);
        } catch (IOException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.equals("Successful");
    }
    
    public String servletResponse(String url)throws MalformedURLException, IOException{
        String link = url + "?token=" + token + "&id=" + id;
        StringBuffer response1 = new StringBuffer();
        String USER_AGENT = "Chrome/46.0.2490.86";
        URL obj = new URL(link);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
            String inputLine;
            
            while ((inputLine = in.readLine()) != null) {
                    response1.append(inputLine);
            }
            in.close();

        } catch (Exception e){

        }
        return(response1.toString());
    }
}
