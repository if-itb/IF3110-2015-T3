package org.tusiri.ws.question;
import javax.jws.WebMethod;
import javax.jws.WebService;

import java.util.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.*;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.parser.ParseException;
import org.tusiri.ws.db.DBConnection;
import org.tusiri.ws.token_validity.CheckTokenValidity;
import org.tusiri.ws.token_validity.TokenValidity;

//This statement means that class "Bookstore.java" is the root-element of our example

@WebService(endpointInterface = "org.tusiri.ws.question.Question")
public class Question {
	
	@WebMethod
	public int createQuestion(String access_token,String title,String content) throws ClientProtocolException, IOException, ParseException{
		int q_id = 0;
		
		System.out.println(access_token);
		System.out.println(title);
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
					/*String sql = "INSERT INTO question(id_user,content,question_date,topic,num_vote)"
							+ "VALUES("+id_user+",'"+content+"',NOW(),'"+title+"',0)";*/
					String sql = "INSERT INTO question(id_user,content,question_date,topic,num_vote)"
							+ "VALUES(?,?,NOW(),?,0)";
					stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
					stmt.setInt(1, validity.getIdUser());
					stmt.setString(2,content);
					stmt.setString(3,title);
					stmt.executeUpdate();
					ResultSet rs = stmt.getGeneratedKeys();
		            while (rs.next()) {
		               q_id = rs.getInt(1);
		            } 	
					System.out.println("q_id = " + q_id);
					//res = 1;
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return q_id;
	}
	
	@WebMethod
	public int editQuestion(String access_token,String title,String content, int id_question) throws ClientProtocolException, IOException, ParseException{
		int status = 0;
		
		System.out.println(access_token);
		System.out.println(title);
		System.out.println(content);
		System.out.println(id_question);
		
		try {
			CheckTokenValidity checker = new CheckTokenValidity(access_token);
			TokenValidity validity = checker.check();
			
			if(validity.getIsValid() == 1){
				DBConnection dbc = new DBConnection();
				PreparedStatement stmt = dbc.getDBStmt();
				Connection conn = dbc.getConn();
				
				//Check apakah user_id sesuai dengan user_id question yang bersangkutan. 
				String sqlCheck = "SELECT id_user FROM question WHERE id_question = ?";
				try {
					stmt = conn.prepareStatement(sqlCheck);
					stmt.setInt(1,id_question);
					ResultSet rsCheck = stmt.executeQuery();
					if (rsCheck.next()){
						if(rsCheck.getInt("id_user") == validity.getIdUser()){//jika id sesuai
							try{
								//Update database
								String sql = "UPDATE question SET content = ?, topic = ?, question_date = NOW() WHERE id_question = ?";
								stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
								stmt.setString(1,content);
								stmt.setString(2,title);
								stmt.setInt(3, id_question);
								stmt.executeUpdate();
								ResultSet rs = stmt.getGeneratedKeys();
								status = 1;
							} catch(Exception e){
								//Handle errors for Class.forName
								e.printStackTrace();
							}
						}
					}
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	@WebMethod
	public int deleteQuestion(String access_token,int id_question) throws ClientProtocolException, IOException, ParseException{
		
		System.out.println("call deleteQuestion");
		int status = 0;
		
		System.out.println(access_token);
		System.out.println(id_question);
		
		try {
			CheckTokenValidity checker = new CheckTokenValidity(access_token);
			TokenValidity validity = checker.check();
			
			if(validity.getIsValid() == 1){
				DBConnection dbc = new DBConnection();
				PreparedStatement stmt = dbc.getDBStmt();
				Connection conn = dbc.getConn();
				
				//Check apakah user_id sesuai dengan user_id question yang bersangkutan. 
				String sqlCheck = "SELECT id_user FROM question WHERE id_question = ?";
				try {
					stmt = conn.prepareStatement(sqlCheck);
					stmt.setInt(1,id_question);
					ResultSet rsCheck = stmt.executeQuery();
					if (rsCheck.next()){
						if(rsCheck.getInt("id_user") == validity.getIdUser()){//jika id sesuai
							//Delete question dan answer dari database
							try{
								String sql ="DELETE FROM answer_vote WHERE id_answer IN (SELECT num_answer as id_answer FROM answer WHERE id_question = ?);"
										+ "DELETE FROM answer WHERE id_question = ?;" 
										+"DELETE FROM question_vote WHERE id_question = ?;"
										+ "DELETE FROM question WHERE id_question = ?";
								stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
								stmt.setInt(1, id_question);
								stmt.setInt(2, id_question);
								stmt.setInt(3, id_question);
								stmt.setInt(4, id_question);
								System.out.println(stmt);
								stmt.executeUpdate();
								ResultSet rs = stmt.getGeneratedKeys();
								status = 1;
								System.out.println("status = " + status);
								//res = 1;
							} catch(Exception e){
								//Handle errors for Class.forName
								e.printStackTrace();
							}
						}
					}
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	@WebMethod
	public ArrayList<QuestionItem> getQuestionList() {
		ArrayList<QuestionItem> questionItemList = new ArrayList();
		DBConnection dbc = new DBConnection();
		PreparedStatement stmt = dbc.getDBStmt();
		Connection conn = dbc.getConn();
		try{
			String sql = "SELECT * FROM question NATURAL JOIN user";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			// Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int id_question  = rs.getInt("id_question");
				int id_user  = rs.getInt("id_user");
				String content = rs.getString("content");
				String question_date = rs.getDate("question_date").toString();
				String topic = rs.getString("topic");
				int num_vote = rs.getInt("num_vote");
				String username = rs.getString("username");
				int num_answer = rs.getInt("num_answer");
				
				QuestionItem q = new QuestionItem();
				q.setIDQuestion(id_question);
				q.setIDUser(id_user);
				q.setContent(content);
				q.setQuestionDate(question_date);
				q.setTopic(topic);
				q.setNumVote(num_vote);
				q.setUsername(username);
				q.setNumAnswer(num_answer);
				
				questionItemList.add(q);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  catch(Exception e){
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
		return questionItemList;
	}

	@WebMethod
	public QuestionItem getQuestionInfo(String access_token, int id_question)throws ClientProtocolException, IOException, ParseException {
		QuestionItem q = new QuestionItem();
		
		try {
			CheckTokenValidity checker = new CheckTokenValidity(access_token);
			TokenValidity validity = checker.check();
			
			int user;
			
			if(validity.getIsValid() == 1){
				user = validity.getIdUser();
			}
			else{
				user = 0;
			}
				
			DBConnection dbc = new DBConnection();
			PreparedStatement stmt = dbc.getDBStmt();
			Connection conn = dbc.getConn();
	
			try{
				String sql = "SELECT * FROM (SELECT * FROM question LEFT OUTER JOIN (SELECT status FROM question_vote WHERE id_user=? AND id_question=?) as GOO ON id_question=?) as GOO2 NATURAL JOIN user WHERE id_question = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, validity.getIdUser());
				stmt.setInt(2, id_question);
				stmt.setInt(3, id_question);
				stmt.setInt(4, id_question);
				ResultSet rs = stmt.executeQuery();
				if(rs.next()){
					int id_user  = rs.getInt("id_user");
					String content = rs.getString("content");
					String question_date = rs.getDate("question_date").toString();
					String topic = rs.getString("topic");
					String username = rs.getString("username");
					int num_vote = rs.getInt("num_vote");
					int num_answer = rs.getInt("num_answer");
					int status = rs.getInt("status");
					System.out.println("Status = " + status);
					
					q.setIDQuestion(id_question);
					q.setIDUser(id_user);
					q.setContent(content);
					q.setQuestionDate(question_date);
					q.setTopic(topic);
					q.setUsername(username);
					q.setNumVote(num_vote);
					q.setNumAnswer(num_answer);
					q.setStatus(status);
					System.out.println(q.getStatus());
				}
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
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return q;
	}
	
	@WebMethod
	public int QuestionVoteUp(int id_question, String access_token)throws ClientProtocolException, IOException, ParseException{
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
					String sql = "SELECT * FROM question_vote WHERE id_question = ? AND id_user = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1,id_question);
					stmt.setInt(2,id_user);
					ResultSet rs = stmt.executeQuery();
					//Jika tidak pernah melakukan vote
					if(!rs.next()){
						try{
							System.out.println("Buat baru");
							String sql1 = "INSERT INTO question_vote(id_question,id_user,status) VALUES (?,?,0)";
							stmt = conn.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
							stmt.setInt(1,id_question);
							stmt.setInt(2,id_user);
							stmt.executeUpdate();
							ResultSet rs1 = stmt.getGeneratedKeys();
							
						} catch(SQLException se){
							//Handle errors for JDBC
							se.printStackTrace();
						} catch (Exception e) {
							//Handle errors for Class.forName
							e.printStackTrace();
						}
					}
					
					//mendapatkan status dan vote_value
					String sql2 = " SELECT num_vote, status FROM question_vote INNER JOIN question ON question.id_question=? AND question_vote.id_user=? AND question_vote.id_question=?";
					stmt = conn.prepareStatement(sql2);
					stmt.setInt(1,id_question);
					stmt.setInt(2,id_user);
					stmt.setInt(3,id_question);
					ResultSet rs2 = stmt.executeQuery();
					if(rs2.next()){
						//System.out.println("Halo3");	
						vote = rs2.getInt("num_vote");
						status = rs2.getInt("status");
						//System.out.println("Halo4");
						//System.out.println("Sebelum tambah : "+vote + "Status = "+status);
						
						if(status == 0){
							//System.out.println("Masuk1");
							vote = vote+1;
							status = 1;
						}
						else if(status == 1){
							//System.out.println("Masuk2");
							vote = vote-1;
							status = 0;
						}
						else{ //status = -1
							//System.out.println("Masuk3");
							vote = vote +2;
							status = 1;
						}
						//System.out.println("status = "+status);
						//System.out.println("vote = "+vote);
						
						String sql3 = " UPDATE question_vote, question SET question_vote.status = ? , num_vote = ? WHERE question_vote.id_question=? AND question_vote.id_user=? and question.id_question=?";
						stmt = conn.prepareStatement(sql3,Statement.RETURN_GENERATED_KEYS);
						stmt.setInt(1,status);
						stmt.setInt(2,vote);
						stmt.setInt(3,id_question);
						stmt.setInt(4,id_user);
						stmt.setInt(5,id_question);
						stmt.executeUpdate();
						ResultSet rs3 = stmt.getGeneratedKeys();
					}
					stmt.close();
					conn.close();
					
				} catch(SQLException se){
					//Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e) {
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
		//System.out.println("status = "+status);
		//System.out.println("vote = "+vote);
		return vote;
	}
	
	@WebMethod
	public int QuestionVoteDown(int id_question, String access_token)throws ClientProtocolException, IOException, ParseException{
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
					String sql = "SELECT * FROM question_vote WHERE id_question = ? AND id_user = ?";
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1,id_question);
					stmt.setInt(2,id_user);
					ResultSet rs = stmt.executeQuery();
					//Jika tidak pernah melakukan vote
					if(!rs.next()){
						try{
							System.out.println("Buat baru");
							String sql1 = "INSERT INTO question_vote(id_question,id_user,status) VALUES (?,?,0)";
							stmt = conn.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
							stmt.setInt(1,id_question);
							stmt.setInt(2,id_user);
							stmt.executeUpdate();
							ResultSet rs1 = stmt.getGeneratedKeys();
						} catch(SQLException se){
							//Handle errors for JDBC
							se.printStackTrace();
						} catch (Exception e) {
							//Handle errors for Class.forName
							e.printStackTrace();
						}
					}
					
					//mendapatkan status dan vote_value
					String sql2 = " SELECT num_vote, status FROM question_vote INNER JOIN question ON question.id_question=? AND question_vote.id_user=? AND question_vote.id_question=?";
					stmt = conn.prepareStatement(sql2);
					stmt.setInt(1,id_question);
					stmt.setInt(2,id_user);
					stmt.setInt(3,id_question);
					ResultSet rs2 = stmt.executeQuery();
					if(rs2.next()){
						//System.out.println("Halo3");	
						vote = rs2.getInt("num_vote");
						status = rs2.getInt("status");
						//System.out.println("Sebelum tambah : "+vote + "Status = "+status);
						
						if(status == 0){
							//System.out.println("Masuk1");
							vote = vote-1;
							status = -1;
						}
						else if(status == -1){
							//System.out.println("Masuk2");
							vote = vote+1;
							status = 0;
						}
						else{ //status = 1
							//System.out.println("Masuk3");
							vote = vote -2;
							status = -1;
						}
						//System.out.println("status = "+status);
						//System.out.println("vote = "+vote);
						
						String sql3 = " UPDATE question_vote, question SET question_vote.status = ? , num_vote = ? WHERE question_vote.id_question=? AND question_vote.id_user=? and question.id_question=?";
						stmt = conn.prepareStatement(sql3,Statement.RETURN_GENERATED_KEYS);
						stmt.setInt(1,status);
						stmt.setInt(2,vote);
						stmt.setInt(3,id_question);
						stmt.setInt(4,id_user);
						stmt.setInt(5,id_question);
						stmt.executeUpdate();
						ResultSet rs3 = stmt.getGeneratedKeys();
					}
					stmt.close();
					conn.close();
					
				} catch(SQLException se){
					//Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e) {
					//Handle errors for Class.forName
					e.printStackTrace();
				}  finally {
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
		//System.out.println("status = "+status);
		//System.out.println("vote = "+vote);
		return vote;
	}
	
	
	@WebMethod
	public ArrayList<QuestionItem> searchQuestion(String keyword) {
		ArrayList<QuestionItem> questionItemList = new ArrayList();
		DBConnection dbc = new DBConnection();
		PreparedStatement stmt = dbc.getDBStmt();
		Connection conn = dbc.getConn();
		try{
			String like="%"+keyword+"%";
			String sql = "SELECT * FROM question NATURAL JOIN user WHERE question.topic LIKE ? OR question.content LIKE ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, like);
			stmt.setString(2, like);
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			
			// Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int id_question  = rs.getInt("id_question");
				int id_user  = rs.getInt("id_user");
				String content = rs.getString("content");
				String question_date = rs.getDate("question_date").toString();
				String topic = rs.getString("topic");
				int num_vote = rs.getInt("num_vote");
				String username = rs.getString("username");
				int num_answer = rs.getInt("num_answer");
				
				QuestionItem q = new QuestionItem();
				q.setIDQuestion(id_question);
				q.setIDUser(id_user);
				q.setContent(content);
				q.setQuestionDate(question_date);
				q.setTopic(topic);
				q.setNumVote(num_vote);
				q.setUsername(username);
				q.setNumAnswer(num_answer);
				
				questionItemList.add(q);
			}
			stmt.close();
			conn.close();
		} catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
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
		return questionItemList;
	}
	
}
