package model;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import mysql.ConnectDb;



// ACCESS_TOKEN OBJECT
@XmlRootElement(name = "voteQuestion")
@XmlType(propOrder = { "userId", "content", "createdAt" })
public final class VoteQuestionModel {
    private int userId;
    private int qid;
    private int value;
    private int vote;
    
    public VoteQuestionModel() { }
    
    public VoteQuestionModel(int userId, int qid, int value) throws SQLException {
	this.userId = userId;
    	this.qid = qid;
	this.value = value;
    }
    
    @XmlElement
    public int getQid() {
	return this.qid;
    }
    
    @XmlElement
    public int getValue() {
	return this.value;
    }
    
    @XmlElement
    public int getVote() {
	return this.vote;
    }
    
    public String toXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(VoteQuestionModel.class);
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
    
    public String addToDatabase() throws SQLException {
	try {
	    Connection conn = ConnectDb.connect();
	    String sql = "select * from user_vote_question where id = ?, qid = ?";
	    PreparedStatement dbStatement = conn.prepareStatement(sql);
	    
	    dbStatement.setInt(1, this.userId);
	    dbStatement.setInt(2, this.qid);
	    dbStatement.setInt(3, this.value);
	    
	    ResultSet res = dbStatement.executeQuery();
	    if(res.next()){
		return "error";
	    } else {
		sql = "insert into user_vote_question(id, qid, value)"
			+ "values (?, ?, ?)";
		dbStatement = conn.prepareStatement(sql);
		dbStatement.setInt(1, this.userId);
		dbStatement.setInt(2, this.qid);
		dbStatement.setInt(3, this.value);
		int rs = dbStatement.executeUpdate();
		
		if(this.value == -1) {
		    sql = "update questions set votes = votes - 1 where qid = ?";
		} else {
		    sql = "update questions set votes = votes + 1 where qid = ?";
		}
		
		dbStatement = conn.prepareStatement(sql);
		dbStatement.setInt(1, this.qid);
		rs = dbStatement.executeUpdate();
		
		sql = "select * from questions where=?";
		dbStatement = conn.prepareStatement(sql);
		dbStatement.setInt(1, this.qid);
		
		return "sukses";
	    }
	} catch (Exception ex) {
	    System.out.println("Error Add Vote to Database : " + ex);
	}
	return null;
    }
}
