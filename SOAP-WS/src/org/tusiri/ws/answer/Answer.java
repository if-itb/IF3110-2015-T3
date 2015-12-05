package org.tusiri.ws.answer;
import javax.jws.WebMethod;
import javax.jws.WebService;

import java.util.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.parser.ParseException;
import org.tusiri.ws.db.DBConnection;
import org.tusiri.ws.question.QuestionItem;
import org.tusiri.ws.token_validity.CheckTokenValidity;
import org.tusiri.ws.token_validity.TokenValidity;

@WebService(endpointInterface = "org.tusiri.ws.answer.Answer")
public class Answer {
	@WebMethod
	public ArrayList<AnswerItem> getAnswerList(String access_token,int id_question)throws ClientProtocolException, IOException, ParseException {
		ArrayList<AnswerItem> questionItemList = new ArrayList<AnswerItem>();
		
		try {
			CheckTokenValidity checker = new CheckTokenValidity(access_token);
			TokenValidity validity = checker.check();
			
			int user = 0;
			if(validity.getIsValid() == 1){
				user = validity.getIdUser();
			}
			else{
				user= 0;
			}
			DBConnection dbc = new DBConnection();
			PreparedStatement stmt = dbc.getDBStmt();
			Connection conn = dbc.getConn();
			try{
				String sql = "SELECT * FROM user NATURAL JOIN(SELECT * FROM answer LEFT OUTER JOIN (SELECT id_answer,status FROM answer_vote WHERE id_user=?) AS GOO ON num_answer=id_answer WHERE id_question = ?) AS GOO2";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, user);
				stmt.setInt(2, id_question);
				ResultSet rs = stmt.executeQuery();
			
				// Extract data from result set
				while(rs.next()){
					//Retrieve by column name
					
					int num_answer = rs.getInt("num_answer");
					int id_user = rs.getInt("id_user");
					String content = rs.getString("content");
					String answer_date = rs.getString("answer_date");
					int num_votes = rs.getInt("num_vote");
					String username = rs.getString("username");
					int status = rs.getInt("status");
					
					AnswerItem a = new AnswerItem(num_answer,id_question,id_user,content,answer_date,num_votes,username,status);
					questionItemList.add(a);
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return questionItemList;
	}
	
	@WebMethod
	public int createAnswer(String access_token,int id_question,String content) throws ClientProtocolException, IOException, ParseException{
		int status = 0;
		
		System.out.println(access_token);
		System.out.println(id_question);
		System.out.println(content);
		
		try {
			CheckTokenValidity checker = new CheckTokenValidity(access_token);
			TokenValidity validity = checker.check();
			
			if(validity.getIsValid() == 1){
				//Masukkan ke database
				DBConnection dbc = new DBConnection();
				PreparedStatement stmt = dbc.getDBStmt();
				Connection conn = dbc.getConn();
				try{
					
					String sql = "INSERT INTO answer(id_question,id_user,content,answer_date,num_vote)"
							+ "VALUES(?,?,?,NOW(),0);"
							+ "UPDATE question SET num_answer = num_answer+1 WHERE id_question = ?";
					stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					stmt.setInt(1, id_question);
					stmt.setInt(2, validity.getIdUser());
					stmt.setString(3,content);
					stmt.setInt(4, id_question);
					stmt.executeUpdate();
					ResultSet rs = stmt.getGeneratedKeys();
		            while (rs.next()) {
		            	status = 1;
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
			} else {
				status = -1;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	@WebMethod
	public int AnswerVoteUp(int id_answer, String access_token)throws ClientProtocolException, IOException, ParseException{
		int vote =0;
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
	
	@WebMethod
	public int AnswerVoteDown(int id_answer, String access_token)throws ClientProtocolException, IOException, ParseException{
		int vote =0;
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

