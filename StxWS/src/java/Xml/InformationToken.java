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
    private int status;
    public InformationToken() {
        status = 0;
    }
    public String getEmail(String token, String ua, String ip) {
        String email = "";
        try {
            XmlResponse xr = new XmlResponse("http://localhost:8082/StxIS/Handler?token="+token+"&user-agent="+ua+"&ip="+ip);
            status = xr.sendGET();
            
            System.out.println(xr);
            XmlParser xp = new XmlParser(xr.toString());
            xp.parse();
            email = xp.getEmail();
        } catch (IOException ex) {
            
        }
        return email;
    }
    public int getStatus() {
        return status;
    }
}
