/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Answer;

import Database.DB;
import Question.QuestionWS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;


/**
 *
 * @author Vanji
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {
    
    /* Connect to Database */
    Connection conn = DB.connect();
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAnswerByQID")
    @WebResult(name = "Answer")
    public ArrayList<Answer> getAnswerByQID(@WebParam(name = "qid") int qid) {
        //TODO write your implementation code here:
        ArrayList<Answer> answers = new ArrayList<Answer>();
        
        try{
            Statement state = conn.createStatement();
            String sql;
            sql = "SELECT * FROM answers WHERE q_id = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet result = dbStatement.executeQuery();
            
            int i = 0;
            while (result.next()){
                answers.add(new Answer(result.getInt("id"), 
                        result.getInt("q_id"),
                        result.getInt("u_id"),
                        result.getString("content"),
                        result.getString("timestamp")
                    ));
                i++;
            }
            result.close();
            state.close();
            
        } catch(SQLException e){
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, e);
        }
        return answers;
    }
}