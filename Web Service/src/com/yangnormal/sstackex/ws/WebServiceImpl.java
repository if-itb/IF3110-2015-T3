package com.yangnormal.sstackex.ws;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import javax.jws.WebService;
import com.yangnormal.sstackex.ws.classes.*;
import org.json.*;


@WebService(endpointInterface = "com.yangnormal.sstackex.ws.WebServiceInterface")
public class WebServiceImpl implements WebServiceInterface{


    final String DB_URL="jdbc:mysql://localhost/mystackexchange";
    final String USER="root";
    final String PASS="";

    public int checkToken(String token) throws Exception{
        int status = 1;
        HttpConnection http = new HttpConnection();
        JSONObject obj = new JSONObject(http.sendGet("http://localhost:8083/v1/check?token="+token));
        return Integer.parseInt((String)obj.get("uid"));
    }

    @Override
    public int getUid(String token){
        Connection conn = null;
        Statement stmt = null;
        int uid = -999;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // Query
            String check = "SELECT uid FROM token WHERE token = '" + token + "'";
            stmt = conn.createStatement();
            ResultSet c = stmt.executeQuery(check);
            c.next();
            uid = c.getInt("uid");
            System.out.println(uid);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return uid;
    }
    @Override
    public int register(String name, String email, String password) {
        int status = 1;
        Connection conn = null;
        Statement stmt = null;
        Statement stmt2 = null;
        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // Query
            String check= "SELECT COUNT(id) as cnt FROM user WHERE email = '"+email+"'";
            String query = "INSERT INTO user (fullname, email, password) " + "VALUES ('"+name+"','"+email+"',"+"MD5('"+password+"'))";
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();
            ResultSet c = stmt2.executeQuery(check);
            c.next();
            if (c.getInt(1) == 0){
                stmt.executeUpdate(query);
            }
            else {
                status = -1; // email has been used
            }
        }
        catch (SQLException se){
            se.printStackTrace();
            System.out.println("SQL register Error");
            status = -1;
        }
        catch (Exception e){
            e.printStackTrace();
            status = -1;
        }
        finally{
            return status;
        }


    }

    @Override
    public int postQuestion(String token, String title, String content) throws Exception{
        int status=-4;
        Connection conn = null;
        PreparedStatement stmt = null;
        int uid=-1;
        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // Query
            /*String query = "SELECT uid FROM token WHERE token=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,token);
            // Result Set
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                uid=rs.getInt("uid");
            }*/
            uid = checkToken(token);
            if (uid>0) {
                String query = "INSERT INTO question (vote, topic, content, date, uid) VALUES (0,?,?,CURRENT_TIMESTAMP,?)";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, title);
                stmt.setString(2, content);
                stmt.setInt(3, uid);
                // Result Set
                stmt.executeUpdate();
                status = 1;
            } else {
                status = uid;
            }
        }
        catch (SQLException se){
            se.printStackTrace();
            System.out.println("SQL post question Error");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public int postAnswer(int qid, String token, String content) throws Exception{
        int status=-4;
        Connection conn = null;
        PreparedStatement stmt = null;
        int uid=-1;
        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // Query
            /*String query = "SELECT uid FROM token WHERE token=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,token);
            // Result Set
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                uid=rs.getInt("uid");
            }*/
            uid = checkToken(token);
            if (uid>0) {
                String query = "INSERT INTO answer (vote, content, date, uid, qid) VALUES (0,?,CURRENT_TIMESTAMP,?,?)";
                stmt = conn.prepareStatement(query);
                stmt.setString(1, content);
                stmt.setInt(2, uid);
                stmt.setInt(3, qid);
                // Result Set
                stmt.executeUpdate();
                status =1;
            } else {
                status =uid;
            }
        }
        catch (SQLException se){
            se.printStackTrace();
            System.out.println("SQL post answer Error");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }


    @Override
    public int deleteQuestion(int qid, String token) throws Exception{
        int status=-4;
        Connection conn = null;
        Statement stmt = null;
        int uid=-1;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // Query
            // Cari user yang memiliki hak akses untuk question ini
            /*String query = "SELECT uid FROM question WHERE id= " + qid;
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                uid = rs.getInt("uid");
            }
            // Cari waktu expired token
            query = "SELECT expired FROM token WHERE token= '"+token+"'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            Timestamp expired = null;
            while (rs.next()) {
                expired = rs.getTimestamp("expired");
            }

            Timestamp time = new Timestamp(Calendar.getInstance().getTime().getTime()); //Waktu sekarang
            */
            uid = checkToken(token);
            if (uid>0) {
                String query = "DELETE FROM question WHERE id= "+qid;
                stmt = conn.createStatement();
                // Result Set
                stmt.executeUpdate(query);
                status = 1;
            }  else {
                status = uid;
            }
        }
        catch (SQLException se){
            se.printStackTrace();
            System.out.println("SQL post question Error");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return status;
    }

    @SuppressWarnings("ValidExternallyBoundObject")
    @Override
    public Question getQuestion(int qid) {
        Connection conn = null;
        Statement stmt = null;
        int i = 0;
        String question[] = {"Failed"};
        Question q = new Question();
        String[][] questionList = new String[1][1];
        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // Query
            String query = "SELECT question.topic,question.vote,question.content,question.date,question.uid,fullname,count(answer.id) as answerSum " +
                    "FROM question LEFT JOIN user ON (question.uid = user.id) LEFT JOIN answer ON (question.id = answer.qid) " +
                    "WHERE question.id = "+qid;
            stmt = conn.createStatement();
            // Result Set
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                q.setId(qid);
                q.setContent(rs.getString("content"));
                q.setTopic(rs.getString("topic"));
                q.getUser().setName(rs.getString("fullname"));
                System.out.println(q.getUser().getName());
                q.getUser().setId(rs.getInt("uid"));
                System.out.println("UID: " + rs.getInt("uid"));
                q.setVote(rs.getInt("vote"));
                q.setDate(rs.getString("date"));
                q.setAnswerSum(rs.getInt("answerSum"));
            }

        }
        catch (SQLException se){
            se.printStackTrace();
            System.out.println("SQL get question Error");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            return q;
        }
    }

    @Override
    public Question[] getQuestionList(){
        ArrayList<Question> questionList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;

        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // Query
            String query = "SELECT question.id,question.topic,question.vote,question.content,question.date,question.uid,fullname,count(answer.qid) as answerSum FROM question LEFT JOIN user ON (question.uid = user.id) LEFT JOIN answer ON (question.id = answer.qid) GROUP BY question.id";
            stmt = conn.createStatement();
            // Result Set
            ResultSet rs = stmt.executeQuery(query);
            // Put result into array
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("question.id"));
                q.setContent(rs.getString("content"));
                q.setTopic(rs.getString("topic"));
                q.getUser().setName(rs.getString("fullname"));
                q.getUser().setId(rs.getInt("uid"));
                q.setVote(rs.getInt("vote"));
                q.setDate(rs.getString("date"));
                q.setAnswerSum(rs.getInt("answerSum"));
                questionList.add(q);
            }

        }
        catch (SQLException se){
            se.printStackTrace();
            System.out.println("SQL get question list Error");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            Question[] qList = questionList.toArray(new Question[questionList.size()]);
            return qList;
        }

    }

    @Override
    public Answer[] getAnswerList(int qid){
        Connection conn = null;
        Statement stmt = null;
        ArrayList<Answer> answerList = new ArrayList<>();
        try{
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            // Query
            String query = "SELECT answer.id,answer.content,answer.vote,answer.date,fullname FROM answer JOIN user ON (answer.uid=user.id) JOIN question ON (answer.qid=question.id) WHERE question.id = "+qid;
            stmt = conn.createStatement();
            // Result Set
            ResultSet rs = stmt.executeQuery(query);
            // Put result into array
            while (rs.next()) {
                Answer answer = new Answer();
                answer.setId(rs.getInt("id"));
                answer.setVote(rs.getInt("vote"));
                answer.setContent(rs.getString("content"));
                answer.setDate(rs.getString("date"));
                answer.getUser().setName(rs.getString("fullname"));
                answer.setQid(qid);
                answerList.add(answer);
            }

        }
        catch (SQLException se){
            se.printStackTrace();
            System.out.println("SQL get answer list Error");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            Answer[] alist = answerList.toArray(new Answer[answerList.size()]);
            return alist;
        }
    }

    @Override
    public int vote(int type, int id, int direction, String token) throws Exception{

            Connection conn = null;
            Statement stmt = null;
            Statement stmt2 = null;
            String query = "";
            String query2 = "";
            String querycheck="";
            int uid=-1;
            int idCheck=-1;
            int voteCheck=0;
            try{
                // Register JDBC driver
                Class.forName("com.mysql.jdbc.Driver");
                // Open a connection
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                // Query
                String queryq = "SELECT uid FROM token WHERE token= '"+token+"'";
                stmt = conn.createStatement();
                // Result Set
                ResultSet rs = stmt.executeQuery(queryq);
                rs.next();
                uid=rs.getInt("uid");
                if (type == 0) { // vote question
                    String querycount = "SELECT COUNT(id) AS count FROM vote_question WHERE qid="+id + " AND uid="+uid;

                    //querycheck= "SELECT qid,vote FROM vote_question WHERE uid="+uid+" AND qid= "+id;
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(querycount);
                    rs.next();
                    int stat = rs.getInt("count");
                    System.out.println("Stat: " + stat);
                    if (stat==1){ //udah pernah ngevote
                        query = "UPDATE question SET vote = vote WHERE id=" + id;
                        query2 = "UPDATE question SET vote = vote WHERE id=" + id;

                    } else {
                        if (direction==1){
                            query = "UPDATE question SET vote = vote + 1 WHERE id=" + id;
                        } else if (direction==-1){
                            query = "UPDATE question SET vote = vote - 1 WHERE id=" + id;
                        }
                        query2 = "INSERT INTO vote_question (qid, uid, vote) VALUES ("+id+","+uid+","+direction+")";
                    }
                }
                else if (type == 1) { // vote answer
                    querycheck= "SELECT aid,vote FROM vote_answer WHERE uid="+uid+" AND aid= "+id;
                    String querycountans = "SELECT COUNT(id) AS count FROM vote_answer WHERE aid="+id + " AND uid="+uid;
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(querycountans);
                    rs.next();
                    int stat = rs.getInt("count");
                    System.out.println("Stat: " + stat);

                    if (stat == 1){ //udah pernah ngevote
                        query = "UPDATE question SET vote = vote WHERE id=" + id;
                        query2 = "UPDATE question SET vote = vote WHERE id=" + id;

                    } else {
                        if (direction==1){
                            query = "UPDATE answer SET vote = vote + 1 WHERE id=" + id;
                        } else if (direction==-1){
                            query = "UPDATE answer SET vote = vote - 1 WHERE id=" + id;
                        }
                        query2 = "INSERT INTO vote_answer (aid, uid, vote) VALUES ("+id+","+uid+","+direction+")";
                    }
                }

                stmt = conn.createStatement();
                stmt2 = conn.createStatement();
                // Result Set
                stmt.executeUpdate(query);
                stmt2.executeUpdate(query2);
            }
            catch (SQLException se){
                se.printStackTrace();
                System.out.println("SQL vote Error");
            }
            catch (Exception e){
                e.printStackTrace();
            }
        return 1;
    }

    @Override
    public int updateQuestion(int qid, String token, String title, String content) throws Exception{

        Connection conn = null;
        PreparedStatement stmt = null;
        int uid=-1;
        int status=-4;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // Query
            // Cari user yang memiliki hak akses untuk question ini
            /*String query = "SELECT uid FROM question WHERE id=?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1,qid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                uid = rs.getInt("uid");
            }


            // Cari waktu expired token
            query = "SELECT expired FROM token WHERE token=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,token);
            rs = stmt.executeQuery();

            Timestamp expired = null;
            while (rs.next()) {
                expired = rs.getTimestamp("expired");
            }
            Timestamp time = new Timestamp(Calendar.getInstance().getTime().getTime()); //Waktu sekarang
            */
            String query;
            uid = checkToken(token);

            if (uid>0) { //berhasil, update deh!
                query = "UPDATE question SET topic=?,content=?,date=CURRENT_TIMESTAMP,uid=? WHERE id =?";
                stmt = conn.prepareStatement(query);
                stmt.setString(1,title);
                stmt.setString(2,content);
                stmt.setInt(3,uid);
                stmt.setInt(4,qid);
                // Result Set
                stmt.executeUpdate();
                status = 1;
            } else {
                status =uid;
            }
        }
        catch (SQLException se){
            se.printStackTrace();
            System.out.println("SQL post question Error");
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return status;
    }

}
