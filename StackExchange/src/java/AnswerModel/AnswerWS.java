/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AnswerModel;

import DatabaseWS.DB;
import QuestionModel.QuestionWS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import java.sql.Statement;
import java.sql.ResultSet;

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
    @WebResult(name="Answer")
    public ArrayList<Answer> getAnswerByQID(@WebParam(name = "qid") int qid) {
        ArrayList<Answer> answers = new ArrayList<Answer>();
        
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM answers WHERE q_id = ?";
            
            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            
            ResultSet rs = dbStatement.executeQuery();
            
            int i = 0;
            while (rs.next()){
                answers.add(new Answer (rs.getInt("id"),
                                        rs.getInt("q_id"),
                                        rs.getInt("u_id"),
                                        rs.getString("content"),
                                        rs.getString("timestamp")
                                        )
                            );
                ++i;
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e){
            Logger.getLogger(QuestionWS.class.getName()).log
                    (Level.SEVERE, null, e);
        }
        
        return answers;
    }
}
