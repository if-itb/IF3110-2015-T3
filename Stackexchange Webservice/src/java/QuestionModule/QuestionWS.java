/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuestionModule;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

import java.sql.*;

import java.util.ArrayList;

import DatabaseModule.Database;
import QuestionModule.Question;


/**
 *
 * @author LUCKY
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {
    
    Database DB = new Database();
    Connection con = DB.connect();
    
    @WebMethod(operationName = "GetAllQuestion")
    @WebResult(name="Questions");
    public ArrayList<Question> GetAllQuestion() {
       ArrayList<Question> Questions = new ArrayList<Question>;
       try{
           String query = "SELECT * FROM QUESTIONS";
           PreparedStatement st = con.prepareStatement(query);
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               Questions.add(new Question(rs.getInt()))
           
           }
       }
       catch{
       
       }
    }

    
}
