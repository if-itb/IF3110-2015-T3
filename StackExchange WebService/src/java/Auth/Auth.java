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
    private String token = "";
    private String id = "";
    private String ip = "";
    private String user_agent = "";
    private final String path = "jdbc:mysql://localhost:3306/stack_exchange";
    
    public Auth(String token, String id, String ip, String user_agent){
        this.token = token;
        this.id = id;
        this.ip = ip;
        this.user_agent = user_agent;
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
        String link = url + "?token=" + token + "&id=" + id + "&ip=" + ip;
        StringBuffer response1 = new StringBuffer();
        
        URL obj = new URL(link);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", user_agent);
        // optional default is GET
        con.setRequestMethod("GET");
        
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
