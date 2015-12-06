/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authentication;

/**
 *
 * @author FiqieUlya
 */
import Config.DB;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class Auth {
    private final String url = "http://localhost:27300/StackExchangeAuth/validateToken";

    // HTTP POST request
    private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try 
        {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            return doc;
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;
    }
    public String checkToken(String token, String userAgent, String ip) {
            String username="";
            String result="";
            try { 
                String charset = "UTF-8";
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setDoOutput(true);
                con.setRequestMethod("POST");
                con.setRequestProperty("Accept-Charset", charset);
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
                
                String query = String.format("token_string=%s&user_agent=%s&ip",
                        URLEncoder.encode(token, charset),
                        URLEncoder.encode(userAgent, charset),
                        URLEncoder.encode(ip, charset));
                try (OutputStream output = con.getOutputStream()) {
                    output.write(query.getBytes(charset));
                }
                InputStream response = con.getInputStream();
                
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (con.getInputStream())));
                
                String output;

                
                while ((output = br.readLine()) != null) {
                    result+=output;
                }
                Document doc = convertStringToDocument(result);
                String stat = doc.getElementsByTagName("status").item(0).getTextContent();
                if(stat.equals("true")){
                    username= doc.getElementsByTagName("username").item(0).getTextContent();
                }
                else if(stat.equals("user-agent")){
                    username="-998";
                }
                else if(stat.equals("ip")){
                    username="-997";
                }
                else{
                    username="-999";
                    //username=stat;
                }
                
                
                con.disconnect();   
            } catch (MalformedURLException ex) {
                Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            }

            return username;
        }
        
        private boolean isStatus(String s) {
            return s.startsWith("<status>");
        }
        
        private boolean isUsername(String s) {
            return s.startsWith("<username>");
        }
       
       public static void main (String [] args) throws IOException{
            Auth a = new Auth();
            String abc = "key";
            //System.out.println(a.checkToken(abc));
            //a.checkToken(abc);
        }
}
