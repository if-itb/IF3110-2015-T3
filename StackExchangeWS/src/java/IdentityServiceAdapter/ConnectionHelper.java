/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IdentityServiceAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
/**
 *
 * @author user
 */
public class ConnectionHelper {
    public static String executeGET(String targetURL) throws IOException {
        URL url = new URL(targetURL);
        URLConnection uc = url.openConnection();
        
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                    uc.getInputStream()));
        
        String inputLine;
        String res = "";

        while ((inputLine = in.readLine()) != null)
            res += inputLine;

        return res;
    }
}
