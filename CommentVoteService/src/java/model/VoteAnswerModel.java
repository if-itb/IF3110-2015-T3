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
@XmlRootElement(name = "voteAnswer")
@XmlType(propOrder = { "aid", "vote" })
public final class VoteAnswerModel {
    private int userId;
    private int aid;
    private int value;
    private int vote;
    
    public VoteAnswerModel() { }
    
    public VoteAnswerModel(int userId, int aid, int value) throws SQLException {
	this.userId = userId;
    	this.aid = aid;
	this.value = value;
    }
    
    public VoteAnswerModel(int userId, int aid, int value ,int vote) throws SQLException {
	this.userId = userId;
	this.aid = aid;
	this.vote = vote;
    }
    
    @XmlElement
    public int getAid() {
	return this.aid;
    }
    
    public int getValue() {
	return this.value;
    }
    
    @XmlElement
    public int getVote() {
	return this.vote;
    }
    
    public String toXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(VoteAnswerModel.class);
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
	    String sql = "select * from user_vote_answer where id = ? and aid = ?";
	    PreparedStatement dbStatement = conn.prepareStatement(sql);
	    
	    dbStatement.setInt(1, this.userId);
	    dbStatement.setInt(2, this.aid);
	    
	    ResultSet res = dbStatement.executeQuery();
	    if(res.next()){
		return "error";
	    } else {
		sql = "insert into user_vote_answer(id, aid, value)"
			+ "values (?, ?, ?)";
		dbStatement = conn.prepareStatement(sql);
		dbStatement.setInt(1, this.userId);
		dbStatement.setInt(2, this.aid);
		dbStatement.setInt(3, this.value);
		int rs = dbStatement.executeUpdate();
		
		if(this.value == -1) {
		    System.out.println("-----------");
		    sql = "update answers set votes = votes - 1 where aid = ?";
		} else {
		    System.out.println("+++++++++++");
		    sql = "update answers set votes = votes + 1 where aid = ?";
		}
		
		dbStatement = conn.prepareStatement(sql);
		dbStatement.setInt(1, this.aid);
		rs = dbStatement.executeUpdate();
		
		sql = "select * from answers where aid=?";
		dbStatement = conn.prepareStatement(sql);
		dbStatement.setInt(1, this.aid);
		
		res = dbStatement.executeQuery();
		if(res.next()) {
		    this.vote = res.getInt("votes");
		}
		return "sukses";
	    }
	} catch ( SQLException ex) {
	    System.out.println("Error Add Vote to Database : " + ex);
	}
	return null;
    }
}
