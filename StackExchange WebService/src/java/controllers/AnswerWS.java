/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static connector.ISConnector.validateToken;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import models.*;

/**
 *
 * @author vanyadeasysafrina
 */
@WebService(serviceName = "AnswerWS")
public class AnswerWS {
    // Open connection to database
    Connection conn = DatabaseController.connect();
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getAnswerByQId")
    @WebResult(name="Answer")
    public ArrayList<Answer> getAnswerByQId(@WebParam(name = "q_id") int q_id) {
        Answer a = null;
        ArrayList<Answer> answers = new ArrayList<Answer>();
        try {    
            try (Statement stmt = conn.createStatement()) {
                String sql = "SELECT * FROM answer natural join user WHERE q_id="+q_id;
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                /* Get data */
                //int a_id, int u_id, String content, int vote, String date_created, int q_id
                try (ResultSet rs = dbStatement.executeQuery()) {
                    /* Get data */
                    //int a_id, int u_id, String content, int vote, String date_created, int q_id
                    while (rs.next()) {
                        a = new Answer (
                            rs.getInt("a_id"),
                            rs.getInt("u_id"),
                            rs.getString("email"),
                            rs.getString("content"),
                            rs.getInt("vote"),
                            rs.getString("date_created"),
                            rs.getInt("q_id"));
                        answers.add(a);
                    }
                }
            }
        } catch (SQLException e) {
            //Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
        return answers;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addNewAnswer")
    public int addNewAnswer(@WebParam(name = "token") String token, @WebParam(name = "content") String content, @WebParam(name = "q_id") int q_id) {
        int u_id = validateToken(token);
        if(u_id==-1) {
            return -1;
        }
        else {
            try {
                String sql = "INSERT INTO answer(u_id,content,q_id,date_created) VALUE (?,?,?,now())";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, u_id);
                dbStatement.setString(2,content);
                dbStatement.setInt(3, q_id);
                dbStatement.executeUpdate();
            } catch (SQLException e) {
                //Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, e);
                e.printStackTrace();
                return -1;
            }
        }
        return q_id;
    }
}
