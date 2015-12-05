/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Asus
 */
public class XmlResponse {
    private String url;
    private String respon;
    
    public XmlResponse() {
        url = null;
        respon = "";
    }
    public XmlResponse(String _URL) {
        url = _URL;
        respon = "";
    }
    public int sendGET() throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
 
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
 
            // save result
            respon = response.toString();
        } 
        return responseCode;
    }
 
    /**
     *
     * @param POST_PARAMS
     * @return 
     * @throws IOException
     */
    public int sendPOST(String POST_PARAMS) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        byte[] postData = POST_PARAMS.getBytes( StandardCharsets.UTF_8);
        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes(StandardCharsets.UTF_8));
        os.flush();
        os.close();
        // For POST only - END
 
        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
 
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            //save result
            respon = response.toString();
        } 
        return responseCode;
    }
    @Override
    public String toString() {
        return respon;
    }
}
