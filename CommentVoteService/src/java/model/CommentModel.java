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
@XmlRootElement(name = "comment")
@XmlType(propOrder = { "name", "content", "createdAt" })
public final class CommentModel {
    private String name;
    private String content;
    private long created_at;
    
    private final long lifetime = 15000;
    
    public CommentModel() { }
        
    public CommentModel(String name, String content) throws SQLException {
        this.name = name;
	this.content = content;
	this.created_at= System.currentTimeMillis() / 1000;
    }
    
    @XmlElement
    public String getName() {
        return this.name;
    }
    
    @XmlElement
    public String getContent() {
        return this.content;
    }
    
    @XmlElement
    public long getCreatedAt() {
        return this.created_at;
    }
    
    
    public String toXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(CommentModel.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(this, stringWriter);
            return stringWriter.toString();
        }
        catch (JAXBException exception) {
	    System.out.println(exception);
	    return "JANCUK";
        }
    }
    
    public void addToDatabase() throws SQLException {
	try {
	    Connection conn = ConnectDb.connect();
	    Statement stmt = null;
	    String sql = "insert into comments(id, name, content, created_at)"
		       + "values (null, ?, ?, now())";
	    PreparedStatement dbStatement = conn.prepareStatement(sql);
	    dbStatement = conn.prepareStatement(sql);
	    dbStatement.setString(1, this.name);
	    dbStatement.setString(2, this.content);
	    int rs = dbStatement.executeUpdate();
	} catch (Exception ex) {
	    System.out.println("Error Add Comment to Database : " + ex);
	}
    }    
}
