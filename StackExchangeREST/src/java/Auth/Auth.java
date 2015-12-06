/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auth;

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
            HttpURLConnection connection = null;
            URL url = new URL("http://localhost:8082/WBD_IS/testrestservlet");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
        InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
            String line;
            while((line = rd.readLine()) != null) {
              response.append(line);
            }
            rd.close();
            if (response.toString().equals("valid"))
            { ret= 1;}
            else if (response.toString().equals("expired"))
            {ret= -1;}
            else
            {ret= 0;}
            connection.disconnect();

                } catch (MalformedURLException e) {

                      e.printStackTrace();

                } catch (IOException e) {

                      e.printStackTrace();

          }
        
        return ret;
    }
}
