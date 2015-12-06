package model;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
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
    private String created_at;
    private int qid;
        
    public CommentModel() { }
    
    public CommentModel(String name, String content, int qid, String createdAt) throws SQLException {
	this.name = name;
	this.content = content;
	this.created_at = createdAt;
	this.qid = qid;
    }
    
    public CommentModel(String name, String content,int qid) throws SQLException {
        this.name = name;
	this.content = content;
	Timestamp stamp = new Timestamp(System.currentTimeMillis());
	Date date = new Date(stamp.getTime());
	SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	sdf2.setTimeZone(TimeZone.getTimeZone("Asia/Jakarta"));
	
	
	this.created_at= sdf2.format(date);
	this.qid = qid;
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
    public String getCreatedAt() {
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
	    String sql = "insert into comments(id, name, content, created_at, qid)"
		       + "values (null, ?, ?, now(), ?)";
	    PreparedStatement dbStatement = conn.prepareStatement(sql);
	    dbStatement = conn.prepareStatement(sql);
	    dbStatement.setString(1, this.name);
	    dbStatement.setString(2, this.content);
	    dbStatement.setInt(3, this.qid);
	    int rs = dbStatement.executeUpdate();
	} catch (Exception ex) {
	    System.out.println("Error Add Comment to Database : " + ex);
	}
    }
}
