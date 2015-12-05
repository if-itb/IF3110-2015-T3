package org.tusiri.ws.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.bind.annotation.XmlElement;

import org.tusiri.ws.db.DBConnection;

public class UserDetail {
	private int id_user=-1;
	private String username;
	private String email;
	private String fullname;
	
	public UserDetail(int _id_user){
		DBConnection dbc = new DBConnection();
		PreparedStatement stmt = dbc.getDBStmt();
		Connection conn = dbc.getConn();
		try{
			String sql = "SELECT * FROM user "
					+ "WHERE id_user = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, _id_user);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				id_user = rs.getInt("id_user");
				username = rs.getString("username");
				email = rs.getString("email");
				fullname = rs.getString("fullname");
			}
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
	}
	
	@XmlElement(name = "id_user")
	public int getId_user(){
		return id_user;
	}
	
	@XmlElement(name = "username")
	public String getUsername(){
		return username;
	}
	
	@XmlElement(name = "email")
	public String getEmail(){
		return email;
	}
	
	@XmlElement(name = "fullname")
	public String getFullname(){
		return fullname;
	}
}