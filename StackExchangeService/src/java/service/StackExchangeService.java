package service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import mysql.ConnectDb;
import model.Question;
import java.sql.*;
import java.util.ArrayList;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import model.Answer;
import model.User;


@WebService(serviceName = "StackExchangeService")
public class StackExchangeService {
    
    
    @WebMethod(operationName = "getAllQuestion")
    public ArrayList<Question> getAllQuestion() throws Exception {  
        ArrayList<Question> questions = new ArrayList<>();        

        Connection conn = ConnectDb.connect();
        Statement stmt;
        stmt = conn.createStatement();
        String sql = "select * from questions Order By qid desc";
        PreparedStatement dbStatement = conn.prepareStatement(sql);

        ResultSet rs = dbStatement.executeQuery();
        while(rs.next()){
            questions.add(new Question(
                rs.getInt("qid"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getString("qtopic"),
                rs.getString("qcontent"),
                rs.getInt("votes"),
                rs.getInt("answer_count"),
                rs.getString("created_at"))
            );
        }
        return questions;
    }

    @WebMethod(operationName = "createQuestion")
    public String createQuestion(
            @WebParam(name = "name") String name,
            @WebParam(name = "qtopic") String qtopic,
            @WebParam(name = "qcontent") String qcontent,
            @WebParam(name = "token") String token
        ) throws Exception {
	
	String tokenStatus = isValidToken(token).trim();
	if ("valid".equals(tokenStatus)) {
	    Connection conn = ConnectDb.connect();
            Statement stmt;
            stmt = conn.createStatement();
	    String sql = "select * from users where name = ? ";
	    PreparedStatement dbStatement = conn.prepareStatement(sql);
	    dbStatement.setString(1, name);
	    ResultSet res = dbStatement.executeQuery();
	    
	    String email = null;
	    if(res.next()){
		email = res.getString("email");
	    }
            
            stmt = conn.createStatement();
            sql = "insert into questions(qid, name, email, qtopic, qcontent, votes, answer_count, created_at)" +
            "values (null, ?,?,?,?,0,0,Now())";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, name);
            dbStatement.setString(2, email);
            dbStatement.setString(3, qtopic);
            dbStatement.setString(4, qcontent);
            int rs = dbStatement.executeUpdate();
            return "success";
        }
	else if ("invalid".equals(tokenStatus)) {
	    System.out.println("INVALID TOKEN");
	    return "invalid";
	} else {
	    System.out.println("EXPIRED_TOKEN");
	    return "expired";
	}
    }

    @WebMethod(operationName = "deleteQuestion")
    public String deleteQuestion(
            @WebParam(name = "qid") int qid,
            @WebParam(name = "token") String token
        ) throws Exception {
	
	String tokenStatus = isValidToken(token).trim();
        if ("valid".equals(tokenStatus)) {
	    System.out.println("VALID_TOKEN");
            Connection conn = ConnectDb.connect();
            Statement stmt;
            stmt = conn.createStatement();

            String sql = "delete from answers where qid = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            int rs = dbStatement.executeUpdate();

            sql = "delete from questions where qid = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
	    
            rs = dbStatement.executeUpdate();
            return "success";
        }
	else if("invalid".equals(tokenStatus)) {
	    System.out.println("INVALID TOKEN");
	    return "invalid";
	}
	else {
	    System.out.println("EXPIRED_TOKEN");
	    return "expired";
	}
    }

    @WebMethod(operationName = "editQuestion")
    public String editQuestion(
            @WebParam(name = "qid") int qid,
            @WebParam(name = "name") String name,
            @WebParam(name = "qtopic") String qtopic,
            @WebParam(name = "qcontent") String qcontent,
            @WebParam(name = "token") String token,
            @WebParam(name = "expirationDate") long expirationDate
        ) throws Exception {
        
	String tokenStatus = isValidToken(token).trim();
        if ("valid".equals(tokenStatus)) {

	    Connection conn = ConnectDb.connect();
            Statement stmt;
            stmt = conn.createStatement();
	    String sql = "select * from users where name = ? ";
	    PreparedStatement dbStatement = conn.prepareStatement(sql);
	    dbStatement.setString(1, name);
	    ResultSet res = dbStatement.executeQuery();
	    
	    String email = null;
	    if(res.next()){
		email = res.getString("email");
	    }
	    
            conn = ConnectDb.connect();
            stmt = conn.createStatement();
            sql = "UPDATE questions SET name = ?, email = ?, qtopic = ?, qcontent = ?" +
            "WHERE qid = ?;";

            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, name);
            dbStatement.setString(2, email);
            dbStatement.setString(3, qtopic);
            dbStatement.setString(4, qcontent);
            dbStatement.setInt(5, qid);

            int rs = dbStatement.executeUpdate();

	    return "success";
        }
	else if ("invalid".equals(tokenStatus)) {
	    System.out.println("INVALID TOKEN");
	    return "invalid";
	} else {
	    System.out.println("EXPIRED_TOKEN");
	    return "expired";
	}
    }

    @WebMethod(operationName = "getQuestion")
    public Question getQuestion(
	    @WebParam(name = "token") String token,
            @WebParam(name = "qid") int qid
        ) throws Exception {
	
	String tokenStatus = isValidToken(token).trim();
	if ("valid".equals(tokenStatus)) {
	    Connection conn = ConnectDb.connect();
	    Statement stmt;
	    stmt = conn.createStatement();
	    String sql = "select * from questions where qid = ?";

	    PreparedStatement dbStatement = conn.prepareStatement(sql);
	    dbStatement.setInt(1, qid);

	    ResultSet rs = dbStatement.executeQuery();
	    while (rs.next()) {
		return new Question(
			rs.getInt("qid"),
			rs.getString("name"),
			rs.getString("email"),
			rs.getString("qtopic"),
			rs.getString("qcontent"),
			rs.getInt("votes"),
			rs.getInt("answer_count"),
			rs.getString("created_at"));
	    }
	}
	
	return null;
	
    }
    
    @WebMethod(operationName = "getQuestionWithoutValidation")
    public Question getQuestionWithoutValidation(
	    @WebParam(name = "qid") int qid
    ) throws Exception {

	Connection conn = ConnectDb.connect();
	Statement stmt;
	stmt = conn.createStatement();
	String sql = "select * from questions where qid = ?";

	PreparedStatement dbStatement = conn.prepareStatement(sql);
	dbStatement.setInt(1, qid);

	ResultSet rs = dbStatement.executeQuery();
	while (rs.next()) {
	    return new Question(
		    rs.getInt("qid"),
		    rs.getString("name"),
		    rs.getString("email"),
		    rs.getString("qtopic"),
		    rs.getString("qcontent"),
		    rs.getInt("votes"),
		    rs.getInt("answer_count"),
		    rs.getString("created_at"));
	}
	
	return null;

    }
    
    @WebMethod(operationName = "getAnswers")
    public ArrayList<Answer> getAnswers(
            @WebParam(name = "qid") int qid
        ) throws Exception {
        
        ArrayList<Answer> answers = new ArrayList<>();

        Connection conn = ConnectDb.connect();
        Statement stmt;
        stmt = conn.createStatement();
        String sql = "SELECT * FROM answers WHERE qid = ?  ORDER BY  aid DESC";
        PreparedStatement dbStatement = conn.prepareStatement(sql);
        dbStatement.setInt(1, qid);

        ResultSet rs = dbStatement.executeQuery();
        while (rs.next()) {
            answers.add(new Answer(
                    rs.getInt("aid"),
                    rs.getInt("qid"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("content"),
                    rs.getInt("votes"),
                    rs.getString("created_at"))
            );
        }
        return answers;
    }
    
    @WebMethod(operationName = "createAnswer")
    public String createAnswer(
        @WebParam(name = "qid") int qid,
        @WebParam(name = "name") String name,
        @WebParam(name = "content") String content,
        @WebParam(name = "token") String token
    ) throws Exception {
        String tokenStatus = isValidToken(token).trim();
	if ("valid".equals(tokenStatus)) {
	    Connection conn = ConnectDb.connect();
	    Statement stmt;
	    stmt = conn.createStatement();
	    String sql = "select * from users where name = ? ";
	    PreparedStatement dbStatement = conn.prepareStatement(sql);
	    dbStatement.setString(1, name);
	    ResultSet res = dbStatement.executeQuery();

	    String email = null;
	    if (res.next()) {
		email = res.getString("email");
	    }
	    
	 
	    
	    
            conn = ConnectDb.connect();
            stmt = conn.createStatement();
            sql = "insert into answers(aid, qid, name, email, content, votes, created_at)"
                    + "values (null, ?, ?, ?, ?, 0, now())";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            dbStatement.setString(2, name);
            dbStatement.setString(3, email);
            dbStatement.setString(4, content);
            int rs = dbStatement.executeUpdate();

            sql = "UPDATE questions SET answer_count = answer_count + 1 WHERE qid = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            rs = dbStatement.executeUpdate();
            return "success";
        }
	else if ("invalid".equals(tokenStatus)) {
	    System.out.println("INVALID TOKEN");
	    return "invalid";
	} else {
	    System.out.println("EXPIRED_TOKEN");
	    return "expired";
	}
    }
    
    
    @WebMethod(operationName = "voteQuestion")
    public int voteQuestion(
        @WebParam(name = "qid") int qid,
        @WebParam(name = "operation") String operation,
        @WebParam(name = "token") String token,
	@WebParam(name = "id") int id
    ) throws Exception {
        String tokenStatus = isValidToken(token).trim();
        if ("valid".equals(tokenStatus)) {
            Connection conn = ConnectDb.connect();
            Statement stmt = conn.createStatement();
            PreparedStatement dbStatement;
            String sql = null, sql2=null;
	    
	    boolean canVote = false;
	    sql = "SELECT * from user_vote_question where id = ? and qid = ? and value = ?";
	    dbStatement = conn.prepareStatement(sql);
	    dbStatement.setInt(1, id);
	    dbStatement.setInt(2, qid);
	    if("up".equals(operation)){
		dbStatement.setInt(3, 1);
	    } else {
		dbStatement.setInt(3, -1);
	    }
	    
	    ResultSet r = dbStatement.executeQuery();
	    if(r.next()) {
		canVote = false;
	    } else {
		canVote = true;
	    }
	    
	    if(canVote){
		if ("up".equals(operation)) {
		    sql = "UPDATE questions SET votes=votes + 1 WHERE qid = ?";
		    sql2 = "INSERT INTO user_vote_question (id,qid,value) values(?,?,1)";
		} else if ("down".equals(operation)) {
		    sql = "UPDATE questions SET votes=votes-1 WHERE qid = ?";
		    sql2 = "INSERT INTO user_vote_question (id,qid,value) values(?,?,-1)";
		}

		dbStatement = conn.prepareStatement(sql);
		dbStatement.setInt(1, qid);
		int rs = dbStatement.executeUpdate();

		dbStatement = conn.prepareStatement(sql2);
		dbStatement.setInt(1, id);
		dbStatement.setInt(2, qid);
		rs = dbStatement.executeUpdate();

		sql = "SELECT votes FROM questions WHERE qid = ?";
		dbStatement = conn.prepareStatement(sql);
		dbStatement.setInt(1, qid);
		ResultSet res = dbStatement.executeQuery();

		while (res.next()) {
		    return res.getInt("votes");
		}
	    }
	    else {
		return 1234;
	    }
            
        }
	else if ("expired".equals(tokenStatus)) {
            return -9999;
        }
	else {
	    return 9999;
	}
        
        return 0;
    }
    
    @WebMethod(operationName = "voteAnswer")
    public int voteAnswer(
        @WebParam(name = "aid") int aid,
        @WebParam(name = "operation") String operation,
        @WebParam(name = "token") String token,
	@WebParam(name = "id") int id
    ) throws Exception {
        String tokenStatus = isValidToken(token).trim();
        if ("valid".equals(tokenStatus)) {
            Connection conn = ConnectDb.connect();
            Statement stmt = conn.createStatement();
            PreparedStatement dbStatement;
            String sql = null, sql2=null;
	    
	    boolean canVote = false;
	    sql = "SELECT * from user_vote_answer where id = ? and aid = ? and value = ?";
	    dbStatement = conn.prepareStatement(sql);
	    dbStatement.setInt(1, id);
	    dbStatement.setInt(2, aid);
	    if("up".equals(operation)){
		dbStatement.setInt(3, 1);
	    } else {
		dbStatement.setInt(3, -1);
	    }
	    
	    ResultSet r = dbStatement.executeQuery();
	    if(r.next()) {
		canVote = false;
	    } else {
		canVote = true;
	    }
	    
	    if(canVote){
		if ("up".equals(operation)) {
		    sql = "UPDATE answers SET votes=votes + 1 WHERE aid = ?";
		    sql2 = "INSERT INTO user_vote_answer (id,aid,value) values(?,?,1)";
		} else if ("down".equals(operation)) {
		    sql = "UPDATE answers SET votes=votes-1 WHERE aid = ?";
		    sql2 = "INSERT INTO user_vote_answer (id,aid,value) values(?,?,-1)";
		}

		dbStatement = conn.prepareStatement(sql);
		dbStatement.setInt(1, aid);
		int rs = dbStatement.executeUpdate();

		dbStatement = conn.prepareStatement(sql2);
		dbStatement.setInt(1, id);
		dbStatement.setInt(2, aid);
		rs = dbStatement.executeUpdate();

		sql = "SELECT votes FROM answers WHERE aid = ?";
		dbStatement = conn.prepareStatement(sql);
		dbStatement.setInt(1, aid);
		ResultSet res = dbStatement.executeQuery();

		while (res.next()) {
		    return res.getInt("votes");
		}
	    }
	    else {
		return 1234;
	    }
            
        }
	else if ("expired".equals(tokenStatus)) {
            return -9999;
        }
	else {
	    return 9999;
	}
        
        return 0;
    }
    
    @WebMethod(operationName = "registerUser")
    public String registerUser(
            @WebParam(name = "userName") String name,
            @WebParam(name = "userEmail") String email,
            @WebParam(name = "userPassword") String password
    ) throws Exception {
        
        /* Check that no user with same name */
        
        if (User.exist(email)) {
	    return null;
	}
        else {
	    User user = User.create(name, email, password);
	    
	    Form form = new Form();
	    form.param("email", user.getEmail());
	    form.param("password", user.getPassword());
	    
	    Client client = ClientBuilder.newClient();
	    String url = "http://localhost:8080/IdentityService/login";
	    
	    String result = client.target(url).request(MediaType.APPLICATION_XML)
		    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
	    return result;
	}
    }
    
    private String isValidToken(String token) {
	Form form = new Form();
	form.param("token", token);

	Client client = ClientBuilder.newClient();
	String url = "http://localhost:8080/IdentityService/Auth";

	String valid = client.target(url).request(MediaType.TEXT_PLAIN)
		.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
	return valid;
    }
}


