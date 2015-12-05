/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xml;

import java.io.IOException;

/**
 *
 * @author Muhtar H
 */
public class InformationToken {
    private boolean status;
    public InformationToken() {
        status = false;
    }
    public String getToken(String url, String urlpost) {
        String token = "";
        try {
            XmlResponse xr = new XmlResponse(url);
            int res = xr.sendPOST(urlpost);
            if(res == 200) {
                status = true;
            }
            System.out.println(xr);
            XmlParser xp = new XmlParser(xr.toString());
            xp.parse();
            token = xp.getToken();
        } catch (IOException ex) {
            
        }
        return token;
    }
    public boolean getStatus() {
        return status;
    }
}
