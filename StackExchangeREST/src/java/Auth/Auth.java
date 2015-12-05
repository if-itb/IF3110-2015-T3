/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auth;

import Connection.Connect;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author Vanji
 */
public class Auth {
    
    private String resturl = "http://localhost:8082/WBD_IS/testrestservlet";
    private String charset = "UTF-8";
    
    public int validation  (String token){
        int ret = -1;
        JSONParser parser = new JSONParser();
        
        try {
            URL url = new URL(resturl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Charset", charset);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);

            String query = String.format("token=%s", 
                                          URLEncoder.encode(token, charset));

            try (OutputStream output = con.getOutputStream()) {
                output.write(query.getBytes(charset));
            }

            InputStream res = con.getInputStream();
            
            System.out.println(res);
            BufferedReader br = new BufferedReader(new InputStreamReader(
                (con.getInputStream())));

            String output;      
            Object obj;
            JSONObject jobj;

            while ((output = br.readLine()) != null) {
              obj = parser.parse(output);
              jobj = (JSONObject) obj;

              ret = -1;

              if (((String)jobj.get("message")).equals("valid")) {
                ret = 1;
              } else if (((String)jobj.get("message")).equals("expired")) {
                ret = 0;
              } else {
                ret = -1;
              }
            }

            con.disconnect();

                } catch (MalformedURLException e) {

                      e.printStackTrace();

                } catch (IOException e) {

                      e.printStackTrace();

          } catch (ParseException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
          }
        
        
        return ret;
    }
}
