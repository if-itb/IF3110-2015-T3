package org.tusiri.ws.vote;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.json.simple.parser.ParseException;
import org.tusiri.ws.db.DBConnection;
import org.tusiri.ws.token_validity.CheckTokenValidity;
import org.tusiri.ws.token_validity.TokenValidity;

@Path("/voteanswer")
public class VoteAnswer {
	
	@POST
	@Path("/voteup")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public int VoteUp(@FormParam("access_token") String access_token, @FormParam("id_answer") int id_answer) throws ParseException{
		int vote =-9999;
		int status = 0;
		try {
			CheckTokenValidity checker = new CheckTokenValidity(access_token);
			TokenValidity validity = checker.check();
			
			DBConnection dbc = new DBConnection();
			PreparedStatement stmt = dbc.getDBStmt();
			Connection conn = dbc.getConn();
			
			if(validity.getIsValid() == 1){
				//mendapatkan id_user dari REST
				int id_user = validity.getIdUser();
				try{
					//Lihat apakah untuk pertanyaan tersebut, user pernah melakukan vote
					String sql = "SELECT * FROM answer_vote WHERE id_answer = ? AND id_user = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1,id_answer);
					stmt.setInt(2,id_user);
					System.out.println("Halo");
					ResultSet rs = stmt.executeQuery();
					//Jika tidak pernah melakukan vote
					if(!rs.next()){
						try{
							System.out.println("Buat baru");
							String sql1 = "INSERT INTO answer_vote(id_answer,id_user,status) VALUES (?,?,0)";
							stmt = conn.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
							stmt.setInt(1,id_answer);
							stmt.setInt(2,id_user);
							stmt.executeUpdate();
							System.out.println("Halo1");
							ResultSet rs1 = stmt.getGeneratedKeys();
						} catch(SQLException se){
							//Handle errors for JDBC
							se.printStackTrace();
						} catch(Exception e){
							//Handle errors for Class.forName
							e.printStackTrace();
						}
					}
					
					//mendapatkan status dan vote_value
					String sql2 = " SELECT num_vote, status FROM answer_vote INNER JOIN answer ON id_answer=? AND answer_vote.id_user=? AND num_answer = ?";
					stmt = conn.prepareStatement(sql2);
					stmt.setInt(1,id_answer);
					stmt.setInt(2,id_user);
					stmt.setInt(3,id_answer);
					ResultSet rs2 = stmt.executeQuery();
					if(rs2.next()){		
						vote = rs2.getInt("num_vote");
						status = rs2.getInt("status");
						System.out.println("Sebelum tambah : "+vote + "Status = "+status);
						
						if(status == 0){
							System.out.println("Masuk1");
							vote = vote+1;
							status = 1;
						}
						else if(status == 1){
							System.out.println("Masuk2");
							vote = vote-1;
							status = 0;
						}
						else{ //status = -1
							System.out.println("Masuk3");
							vote = vote +2;
							status = 1;
						}
						System.out.println("status = "+status);
						System.out.println("vote = "+vote);
						
						String sql3 = " UPDATE answer_vote, answer SET answer_vote.status = ? , num_vote = ? WHERE answer_vote.id_answer=? AND answer_vote.id_user=? and answer.num_answer=?";
						stmt = conn.prepareStatement(sql3,Statement.RETURN_GENERATED_KEYS);
						stmt.setInt(1,status);
						stmt.setInt(2,vote);
						stmt.setInt(3,id_answer);
						stmt.setInt(4,id_user);
						stmt.setInt(5,id_answer);
						stmt.executeUpdate();
						ResultSet rs3 = stmt.getGeneratedKeys();
					}
					stmt.close();
				    conn.close();
				} catch(SQLException se){
					//Handle errors for JDBC
					se.printStackTrace();
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
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("status = "+status);
		System.out.println("vote = "+vote);
		return vote;
		
	}
	
	@POST
	@Path("/votedown")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public int VoteDown(@FormParam("access_token") String access_token, @FormParam("id_answer") int id_answer) throws ParseException{
		int vote =-9999;
		int status = 0;
		try {
			CheckTokenValidity checker = new CheckTokenValidity(access_token);
			TokenValidity validity = checker.check();
			
			DBConnection dbc = new DBConnection();
			PreparedStatement stmt = dbc.getDBStmt();
			Connection conn = dbc.getConn();
			
			if(validity.getIsValid() == 1){
				//mendapatkan id_user dari REST
				int id_user = validity.getIdUser();
				try{
					//Lihat apakah untuk pertanyaan tersebut, user pernah melakukan vote
					String sql = "SELECT * FROM answer_vote WHERE id_answer = ? AND id_user = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1,id_answer);
					stmt.setInt(2,id_user);
					System.out.println("Halo");
					ResultSet rs = stmt.executeQuery();
					//Jika tidak pernah melakukan vote
					if(!rs.next()){
						try{
							System.out.println("Buat baru");
							String sql1 = "INSERT INTO answer_vote(id_answer,id_user,status) VALUES (?,?,0)";
							stmt = conn.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
							stmt.setInt(1,id_answer);
							stmt.setInt(2,id_user);
							stmt.executeUpdate();
							System.out.println("Halo1");
							ResultSet rs1 = stmt.getGeneratedKeys();
						} catch(SQLException se){
							//Handle errors for JDBC
							se.printStackTrace();
						} catch(Exception e){
							//Handle errors for Class.forName
							e.printStackTrace();
						}
					}
					
					//mendapatkan status dan vote_value
					String sql2 = " SELECT num_vote, status FROM answer_vote INNER JOIN answer ON id_answer=? AND answer_vote.id_user=? AND num_answer = ?";
					stmt = conn.prepareStatement(sql2);
					stmt.setInt(1,id_answer);
					stmt.setInt(2,id_user);
					stmt.setInt(3,id_answer);
					ResultSet rs2 = stmt.executeQuery();
					System.out.println("Halo2");
					if(rs2.next()){
						System.out.println("Halo3");	
						vote = rs2.getInt("num_vote");
						status = rs2.getInt("status");
						System.out.println("Halo4");
						System.out.println("Sebelum tambah : "+vote + "Status = "+status);
						
						if(status == 0){
							System.out.println("Masuk1");
							vote = vote-1;
							status = -1;
						}
						else if(status == -1){
							System.out.println("Masuk2");
							vote = vote+1;
							status = 0;
						}
						else{ //status = 1
							System.out.println("Masuk3");
							vote = vote -2;
							status = -1;
						}
						System.out.println("status = "+status);
						System.out.println("vote = "+vote);
						
						String sql3 = " UPDATE answer_vote, answer SET answer_vote.status = ? , num_vote = ? WHERE answer_vote.id_answer=? AND answer_vote.id_user=? and answer.num_answer=?";
						stmt = conn.prepareStatement(sql3,Statement.RETURN_GENERATED_KEYS);
						stmt.setInt(1,status);
						stmt.setInt(2,vote);
						stmt.setInt(3,id_answer);
						stmt.setInt(4,id_user);
						stmt.setInt(5,id_answer);
						stmt.executeUpdate();
						ResultSet rs3 = stmt.getGeneratedKeys();
					}
					stmt.close();
					conn.close();
				} catch(SQLException se){
					//Handle errors for JDBC
					se.printStackTrace();
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
		}catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("status = "+status);
		System.out.println("vote = "+vote);
		return vote;
		
	}
}
