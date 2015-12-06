/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Token;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author User
 */

@XmlRootElement(name = "tokenModel")
@XmlType(propOrder = { "token" })
public class TokenModel {
    @XmlElement(name="token", required=true)
    private String token;
    
    /*public TokenModel() {
       TokenGenerator tgin = new TokenGenerator(6);
       this.token = tgin.getToken();
    }*/
    
    public TokenModel(String userAgent, String ip) {
       TokenGenerator tgin = new TokenGenerator(6, userAgent, ip);
       this.token = tgin.getToken();
    }
    
    /*public TokenModel(int lifetime) {
       TokenGenerator tgin = new TokenGenerator(lifetime);
       this.token = tgin.getToken();
    }*/
    
    @XmlElement
    public String getToken() {
        return this.token;
    }
}
