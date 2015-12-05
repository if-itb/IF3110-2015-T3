/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author Vanji
 */
public class Connect {
    public static String connect(String url) throws MalformedURLException, IOException {
        String res = "";
        URL con = new URL (url);
        URLConnection ucon = con.openConnection();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(ucon.getInputStream()));
        String input;
        
        while ((input = in.readLine()) != null){
            res = res + input;
        }
        
        return res;
    }
}
