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
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author adar
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {
    
    Connection conn = DB.connect();
    @WebMethod(operationName = "getAnswerByQID")
    @WebResult(name="Answer")
    public java.util.ArrayList<Answer> getAnswerByQID
    (@WebParam(name = "qid") int qid) {
        ArrayList<Answer> answer = new ArrayList<Answer>();
        try {
            Statement stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM answer WHERE id_question = ?";
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);

            ResultSet rs = dbStatement.executeQuery();

             /* Get every data returned by SQL query */
            int i = 0;
            while(rs.next()){
                answer.add(new Answer(rs.getInt("id"),
                rs.getInt("id_question"),
                rs.getInt("id_user"),
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
        return answer;
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    
}
