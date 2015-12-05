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

    public InformationToken() {
        
    }
    public String getEmail(String token) {
        String email = "";
        try {
            XmlResponse xr = new XmlResponse("http://localhost:8082/StxIS1/Handler?token="+token);
            xr.sendGET();
            System.out.println(xr);
            XmlParser xp = new XmlParser(xr.toString());
            xp.parse();
            email = xp.getEmail();
        } catch (IOException ex) {
            
        }
        return email;
    }
}
