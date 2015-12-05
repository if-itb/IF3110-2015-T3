package org.stackexchange.webservice.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionHelper {
    public static String executeGET(String targetURL) throws IOException {
        URL yahoo = new URL(targetURL);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;
        String res = "";

        while ((inputLine = in.readLine()) != null)
            res += inputLine;

        return res;
    }
}
