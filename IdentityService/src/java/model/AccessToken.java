/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author dancinggrass
 */

@XmlRootElement(name = "accessToken")
@XmlType(propOrder = { "token","username", "expirationDate", "lifetime" })
public class AccessToken {
    private String token;
    private long expirationDate;
    private String username;
    
    public final long lifetime = 3600;
    
    public AccessToken() {}
    
    public AccessToken(String email, String username) {
        long now = System.currentTimeMillis() / 1000;
        this.token =  email + Long.toString(now);
        this.expirationDate = now + lifetime;
	this.username = username;
    }
    
    @XmlElement
    public String getToken() {
        return this.token;
    }
    
    @XmlElement
    public String getUsername() {
        return this.username;
    }
    
    @XmlElement
    public long getExpirationDate() {
        return this.expirationDate;
    }
    
    public String toXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(AccessToken.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(this, stringWriter);
            return stringWriter.toString();
        }
        catch (JAXBException exception) {
        }
        return null;
    }
}
