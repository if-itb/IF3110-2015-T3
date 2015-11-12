/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import mysql.ConnectDb;
import model.Question;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author sorlawan
 */
@WebService(serviceName = "StackExchangeService")
public class StackExchangeService {

    /**
     * This is a sample web service operation
     * @param txt
     * @return 
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "getAllQuestion")
    public ArrayList<Question> getAllQuestion() throws Exception {  
        
        ArrayList<Question> questions = new ArrayList<>();        
        
        Connection conn = ConnectDb.connect();
        Statement stmt;
        stmt = conn.createStatement();
        String sql = "select * from Question";
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
    
}
