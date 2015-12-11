/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionOperation;

import java.sql.*;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import Xml.*;
/**
 *
 * @author Muhtar Hartopo
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {    
       /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getAllQuestion")
    @WebResult(name = "Question")
    public java.util.ArrayList<Question> getAllQuestion() throws Exception {
        //TODO write your implementation code here:
        ArrayList <Question> Questions  = new ArrayList<Question>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
                //new com.mysql.jdbc.Driver();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
                String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
                String connectionUser = "root";
                String connectionPassword = "";
                conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                ps = conn.prepareStatement("select * from question");
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String topic = rs.getString("topic");
                    String content = rs.getString("content");
                    int vote = rs.getInt("vote");

                    Questions.add(new Question(id, name, email, topic, content,vote));
                }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
          throw e;
        } finally {
          if(rs!= null) rs.close();
          if(ps!= null) ps.close();
        }
        return Questions;
    }
    
    @WebMethod(operationName = "getQuestionById")
    @WebResult(name = "Question")
    public Question getQuestionById(@WebParam(name = "qid") int qid) throws Exception {
        //TODO write your implementation code here:
        Question Q = new Question();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
                //new com.mysql.jdbc.Driver();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
                String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
                String connectionUser = "root";
                String connectionPassword = "";
                conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                ps = conn.prepareStatement("select * from question where id = ?");
                ps.setInt(1, qid);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String topic = rs.getString("topic");
                    String content = rs.getString("content");
                    int vote = rs.getInt("vote");

                   Q = new Question(id, name, email, topic, content,vote);
                }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
          throw e;
        } finally {
          if(rs!= null) rs.close();
          if(ps!= null) ps.close();
        }
        return Q;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "search")
    @WebResult(name = "Question")
    public ArrayList <Question> search(@WebParam(name = "key") String key) {
        //TODO write your implementation code here:
        ArrayList <Question> Questions  = new ArrayList<Question>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //new com.mysql.jdbc.Driver();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
            String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
            String connectionUser = "root";
            String connectionPassword = "";
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
            ps = conn.prepareStatement("select * from question where topic like ? or content like ?;");
            ps.setString(1,"%"+key+"%");
            ps.setString(2, "%"+key+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String topic = rs.getString("topic");
                String content = rs.getString("content");
                int vote = rs.getInt("vote");
                
                Questions.add(new Question(id, name, email, topic, content,vote));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
                try { if (rs != null) rs.close(); } catch (SQLException e) {}
                try { if (ps != null) ps.close(); } catch (SQLException e) {}
                try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }

        return Questions;
    }
    
    @WebMethod(operationName = "post")
    public int post(@WebParam(name = "token") String token,@WebParam(name = "user-agent") String ua, @WebParam(name = "ip") String ip, @WebParam(name = "topic") String topic, @WebParam(name = "content") String content) {
        Connection conn = null;
        PreparedStatement ps = null;
        InformationToken it = new InformationToken();
        String email = it.getEmail(token,ua,ip);
        if(it.getStatus() != 200) {
            return -1*it.getStatus();
        } else {
            int res = -1;
            try {
                    //new com.mysql.jdbc.Driver();
                    Class.forName("com.mysql.jdbc.Driver").newInstance();
                    //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
                    String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
                    String connectionUser = "root";
                    String connectionPassword = "";
                    conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                    ps = conn.prepareStatement("select name from user where email = ?");
                    ps.setString(1, email);
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    String name = rs.getString("name");
                    ps = conn.prepareStatement("insert into question values(0,?,?,?,?,0)");
                    ps.setString(1, name);
                    ps.setString(2, email);
                    ps.setString(3, topic);
                    ps.setString(4, content);

                    res = ps.executeUpdate();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            } finally {
                    try { if (ps != null) ps.close(); } catch (SQLException e) {}
                    try { if (conn != null) conn.close(); } catch (SQLException e) {}
            }
            return res;
        }
    }
    
    @WebMethod(operationName = "update")
    public int update(@WebParam(name = "id") int id, @WebParam(name = "token") String token, @WebParam(name = "user-agent") String ua, @WebParam(name = "ip") String ip,@WebParam(name = "topic") String topic, @WebParam(name = "content") String content) {
        
        Connection conn = null;
        PreparedStatement ps = null;
        int res = -1;
        InformationToken it = new InformationToken();
        String email = it.getEmail(token,ua,ip);
        if(it.getStatus() != 200) {
            return -1*it.getStatus();
        } else {
        try {
                //new com.mysql.jdbc.Driver();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
                String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
                String connectionUser = "root";
                String connectionPassword = "";
                conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                ps = conn.prepareStatement("select name from user where email = ?");
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                rs.next();
                String name = rs.getString("name");
                ps = conn.prepareStatement("update question set name = ?, email = ?, topic = ?, content = ? where id = ?;");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, topic);
                ps.setString(4, content);
                ps.setInt(5, id);
                res = ps.executeUpdate();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            
        } finally {
                try { if (ps != null) ps.close(); } catch (SQLException e) {}
                try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return res;
        }
    }
    @WebMethod(operationName = "vote")
    public int vote(@WebParam(name = "id") int id, @WebParam(name = "token") String token,@WebParam(name = "user-agent") String ua, @WebParam(name = "ip") String ip, @WebParam(name = "value") int val) {
        Connection conn = null;
        PreparedStatement ps = null;
        int executeUpdate = -2;
        InformationToken it = new InformationToken();
        String mail = it.getEmail(token,ua,ip);
        if(it.getStatus() != 200) {
            return -1*it.getStatus();
        }
        try {
                //new com.mysql.jdbc.Driver();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
                String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
                String connectionUser = "root";
                String connectionPassword = "";
                conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                ps = conn.prepareStatement("select * from uservote where id_mail = ? and category = 'q' and id = ?");
                ps.setString(1, mail);
                ps.setInt(2, id);
                ResultSet rs1 = ps.executeQuery();
                
                if (rs1.next()) {
                    
                    return -1;
                } else {
                    ps = conn.prepareStatement("insert into uservote values(?,'q',?)");         
                    ps.setString(1, mail);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                    ps = conn.prepareStatement("select vote from question where id = ?");
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    int currentVote = rs.getInt("vote");
                    ps = conn.prepareStatement("update question set vote = ? where id = ?");
                    ps.setInt(1, currentVote+val);
                    ps.setInt(2, id);
                    executeUpdate = ps.executeUpdate();
                }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            return 0;
        } finally {
                try { if (ps != null) ps.close(); } catch (SQLException e) {}
                try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return executeUpdate;
    }
    
    @WebMethod(operationName = "delete")
    public int delete(@WebParam(name = "qid")int qid) {
        Connection conn = null;
        PreparedStatement ps = null;
        int res = -1;
        try {
                //new com.mysql.jdbc.Driver();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
                String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
                String connectionUser = "root";
                String connectionPassword = "";
                conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                ps = conn.prepareStatement("delete from question where id = ?;");
                ps.setInt(1, qid);
                ps.executeUpdate();
                ps = conn.prepareStatement("delete from uservote where category = 'q' and id = ?;");
                ps.setInt(1, qid);
                res = ps.executeUpdate();
                ps = conn.prepareStatement("select id from Answer where q_id = ?");
                ps.setInt(1, qid);
                ResultSet rs = ps.executeQuery();
                while(rs.next()) {
                    ps = conn.prepareStatement("delete from uservote where id = ? and category = 'a';");
                    ps.setInt(1, rs.getInt("id"));
                    res = ps.executeUpdate();
                }
                ps = conn.prepareStatement("delete from Answer where q_id = ?;");
                ps.setInt(1, qid);
                res = ps.executeUpdate();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
                try { if (ps != null) ps.close(); } catch (SQLException e) {}
                try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return res;
    }
    @WebMethod(operationName = "getNumAnswer")
    public int getNumAnswer(@WebParam(name = "id") int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int res = -1;
        try {
                //new com.mysql.jdbc.Driver();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
                String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
                String connectionUser = "root";
                String connectionPassword = "";
                conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                ps = conn.prepareStatement("SELECT COUNT(id) AS numAns FROM answer where q_id = ?");
                ps.setInt(1, id);
                rs = ps.executeQuery();
                rs.next();
                res = rs.getInt("numAns");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
                try { if (ps != null) ps.close(); } catch (SQLException e) {}
                try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return res;
    }

    /**
     *
     * @param qid
     * @return
     */
    @WebMethod(operationName= "getVote")
    
    public int getVote(@WebParam(name = "qid") int qid) {
        int vote = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
                //new com.mysql.jdbc.Driver();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
                String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
                String connectionUser = "root";
                String connectionPassword = "";
                conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                ps = conn.prepareStatement("select vote from question where id = ?");
                ps.setInt(1, qid);
                ResultSet rs = null;
                rs = ps.executeQuery();
                rs.next();
                vote = rs.getInt("vote");
                
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
                try { if (ps != null) ps.close(); } catch (SQLException e) {}
                try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return vote;
    }
}
