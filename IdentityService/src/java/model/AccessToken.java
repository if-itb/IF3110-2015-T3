package model;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import mysql.ConnectDb;



// ACCESS_TOKEN OBJECT
@XmlRootElement(name = "accessToken")
@XmlType(propOrder = { "id", "token", "username" })
public final class AccessToken {
    private String token;
    private long expirationDate;
    private String username;
    private long id;
    private String useragent;
    private String ip;
    
    private final long lifetime = 15000;
    
    public AccessToken() {}
    
    public AccessToken(String email, String username, long id, String useragent, String ip) throws SQLException {
        long now = System.currentTimeMillis() / 1000;
        this.token =  email + Long.toString(now);
        this.expirationDate = now + lifetime;
	this.username = username;
	this.id = id;
        this.useragent = useragent;
        this.ip = ip;
        System.out.println(useragent + " " + ip);
    }
    
    @XmlElement
    public String getToken() {
        return this.token;
    }
    
    @XmlElement
    public String getUsername() {
        return this.username;
    }
    
    public long getExpirationDate() {
        return this.expirationDate;
    }
    
    @XmlElement
    public long getId() {
	return this.id;
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
	    System.out.println(exception);
	    return "ADH";
        }
    }
    
    public void addToDatabase() throws SQLException {
	try {
	    Connection conn = ConnectDb.connect();
	    Statement stmt = null;
	    String sql = "insert into access_token(access_token, expiration_date, user_id, user_agent, ip)"
		       + "values (?, ?, ?, ?, ?)";
	    PreparedStatement dbStatement = conn.prepareStatement(sql);
	    dbStatement = conn.prepareStatement(sql);
	    dbStatement.setString(1, this.getToken());
	    dbStatement.setInt(2, (int) (long) this.getExpirationDate());
            dbStatement.setLong(3, this.getId());
            dbStatement.setString(4, this.useragent);
            dbStatement.setString(5, this.ip);
	    int rs = dbStatement.executeUpdate();
	} catch (Exception ex) {
	    System.out.println("Error add Token : " + ex);
	}
    }    
}
