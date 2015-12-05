package org.tusiri.ws.comment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.parser.ParseException;
import org.tusiri.ws.db.DBConnection;
import org.tusiri.ws.token_validity.CheckTokenValidity;
import org.tusiri.ws.token_validity.TokenValidity;

@Path("/comment")
public class Comment {
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public int addComment(@FormParam("access_token") String access_token, @FormParam("id_question") int id_question, @FormParam("content") String comment){
		int result = -1;
		CheckTokenValidity checker = new CheckTokenValidity(access_token);
		try {
			TokenValidity validity = checker.check();
			result = validity.getIsValid();
			if(result == 1){
				//Masukkan ke database
				DBConnection dbc = new DBConnection();
				PreparedStatement stmt = dbc.getDBStmt();
				Connection conn = dbc.getConn();
				try{
					String sql = "INSERT INTO comment(id_user,id_question,comment,comment_time)"
							+ "VALUES(?,?,?,NOW())";
					stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					stmt.setInt(1, validity.getIdUser());
					stmt.setInt(2, id_question);
					stmt.setString(3, comment);
					stmt.executeUpdate();
					stmt.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch(Exception e){
					//Handle errors for Class.forName
					e.printStackTrace();
				} finally {
			      try{
				         if(stmt!=null)
				            stmt.close();
				      } catch(SQLException se2){
				      
				      }
				      try {
				         if(conn!=null)
				            conn.close();
				      } catch(SQLException se){
				         se.printStackTrace();
				      }
				}
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
