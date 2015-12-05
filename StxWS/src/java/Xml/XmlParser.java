/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xml;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Asus
 */
public class XmlParser {
    String xmlFile;
    String token;
    String lifetime;
    String email;
    public XmlParser() {
        xmlFile = "";
        token = "";
        lifetime = "";
        email = "";
        
    }
    public XmlParser(String S) {
        xmlFile = S;
        token = "";
        lifetime = "";
        email = "";
        
    }
    public void parse() {     
        try {
            SAXBuilder sb = new SAXBuilder();
            Document doc = sb.build(new StringReader(xmlFile));
            Element elm = doc.getRootElement();
            email = elm.getChildText("email");
            token = elm.getChildText("token");
            lifetime = elm.getChildText("lifetime");
        } catch (JDOMException | IOException ex) {
            
        }
	    
    }
    public String getEmail() {
        return email;
    }
    public String getToken() {
        return token;
    }
    public String getLifeTime() {
        return lifetime;
    }
    @Override
    public String toString() {
        String S;
        S = "Email = " + email + "\n"
                + "Token = "+token+"\n"
                + "Life time = "+lifetime;
        return S;
    }    
}

