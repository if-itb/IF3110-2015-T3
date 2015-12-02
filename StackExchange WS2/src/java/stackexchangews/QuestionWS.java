/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchangews;

import database.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author adar
 */
@WebService(serviceName = "Question")
public class QuestionWS {

    Connection conn = DB.connect();
    
    @WebMethod(operationName = "getListQuestion")
    @WebResult(name = "listQuestion")
    public java.util.ArrayList<Question> getListQuestion() {
        ArrayList<Question> listQuestion = new ArrayList<Question>();
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM question";
            PreparedStatement dbStatement = conn.prepareStatement(sql);

            ResultSet rs = dbStatement.executeQuery();

             /* Get every data returned by SQL query */
            int i = 0;
            while(rs.next()){
                listQuestion.add(new Question(rs.getInt("id"),
                rs.getInt("id_user"),
                rs.getString("topic"),
                rs.getString("content"),
                rs.getString("timepost")
                ));
                ++i;
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            //Logger.getLogger(QuestionWS.class.getName()).log
             //(Level.SEVERE, null, ex);
        }
        return listQuestion;
    }
    
    @WebMethod(operationName = "getQuestionByID")
    @WebResult(name = "question")
    public Question getQuestionByID(@WebParam(name = "qid") int qid) {
        Question question = null;
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM question WHERE id = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet rs = dbStatement.executeQuery();

             /* Get every data returned by SQL query */
            int i = 0;
            while(rs.next()){
                question = (new Question(rs.getInt("id"),
                rs.getInt("id_user"),
                rs.getString("topic"),
                rs.getString("content"),
                rs.getString("timepost")
                ));
                ++i;
            }

            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            //Logger.getLogger(QuestionWS.class.getName()).log
             //(Level.SEVERE, null, ex);
        }
        return question;
    }
    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

}
