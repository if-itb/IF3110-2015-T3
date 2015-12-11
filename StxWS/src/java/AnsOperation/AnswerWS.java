/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnsOperation;

import Xml.InformationToken;
import java.sql.*;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author Asus
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {

    @WebMethod(operationName = "getAnswers")
    @WebResult(name = "Answers")
    public ArrayList<Answer> getAAnswers(@WebParam(name = "q_id") int q_id) throws Exception {
        ArrayList<Answer> Ans = new ArrayList<Answer>();
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
                ps = conn.prepareStatement("select * from answer where q_id = ?");
                ps.setInt(1, q_id);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int qid = rs.getInt("q_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String content = rs.getString("content");
                    int vote = rs.getInt("vote");
                    Ans.add(new Answer(id, qid, name, email, content,vote));
                }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
          if(rs!= null) rs.close();
          if(ps!= null) ps.close();
        }
        return Ans;
        
    }
        
    @WebMethod(operationName = "postAns")
    public int postAns(@WebParam(name = "qid") int qid, @WebParam(name = "token") String token, @WebParam(name = "user-agent") String ua, @WebParam(name = "ip") String ip,@WebParam(name = "content") String content) {
        int res = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        InformationToken it = new InformationToken();
        String email = it.getEmail(token,ua,ip);
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
                 ps = conn.prepareStatement("select name from user where email = ?");
                ps.setString(1, email);
                ResultSet rs = ps.executeQuery();
                rs.next();
                String name = rs.getString("name");
                ps = conn.prepareStatement("insert into Answer value(0,?,?,?,?,0)");
                ps.setInt(1, qid);
                ps.setString(2, name);
                ps.setString(3, email);
                ps.setString(4, content);
                res = ps.executeUpdate();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
                try { if (ps != null) ps.close(); } catch (SQLException e) {}
                try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return res;
    }
    //return value -1 = gagal, 0 = sudah divote, 1 = berhasil
    @WebMethod(operationName = "voteAns")
    public int voteAns(@WebParam(name = "id") int id, @WebParam(name= "token") String token, @WebParam(name = "user-agent") String ua, @WebParam(name = "ip") String ip,@WebParam(name = "value") int val) {
        Connection conn = null;
        PreparedStatement ps = null;
        int executeUpdate = -1;
        String connectionPassword = "";
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
                conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                
                ps = conn.prepareStatement("select * from uservote where id_mail = ? and category = 'a' and id = ?");
                ps.setString(1, mail);
                ps.setInt(2, id);
                ResultSet rs1 = ps.executeQuery();
                if (rs1.next()) {
                    return 0;
                } else {
                    ps = conn.prepareStatement("insert into uservote values(?,'a',?)");         
                    ps.setString(1, mail);
                    ps.setInt(2, id);
                    ps.executeUpdate();
                    ps = conn.prepareStatement("select vote from answer where id = ?");
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    rs.next();
                    int currentVote = rs.getInt("vote");
                    ps = conn.prepareStatement("update Answer set vote = ? where id = ?");
                    ps.setInt(1, currentVote+val);
                    ps.setInt(2, id);
                    executeUpdate = ps.executeUpdate();
                }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            
        } finally {
                try { if (ps != null) ps.close(); } catch (SQLException e) {}
                try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return executeUpdate;
    }
    
    @WebMethod(operationName = "deleteAns")
    public int deleteAns(@WebParam(name = "id")int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        int res= -1;
        try {
                //new com.mysql.jdbc.Driver();
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/databasename?user=username&password=password");
                String connectionUrl = "jdbc:mysql://localhost:3306/stackexchange";
                String connectionUser = "root";
                String connectionPassword = "";
                conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
                ps = conn.prepareStatement("delete from Answer where id = ?;");
                ps.setInt(1, id);
                res = ps.executeUpdate();
                ps = conn.prepareStatement("delete from uservote where category = 'a' and id = ?;");
                ps.setInt(1, id);
                res = ps.executeUpdate();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
        } finally {
                try { if (ps != null) ps.close(); } catch (SQLException e) {}
                try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
        return res;
    }
    
    @WebMethod(operationName = "getAnsVote")
    public int getAnsVote(@WebParam(name = "id") int id) {
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
                ps = conn.prepareStatement("select vote from Answer where id = ?;");
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
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
