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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author LUCKY
 */
@WebService(serviceName = "QuestionWS")
public class QuestionWS {
    
    Database DB = new Database();
    Connection con = DB.connect();
    
    @WebMethod(operationName = "GetAllQuestion")
    @WebResult(name="Questions")
    public ArrayList<Question> GetAllQuestion() {
       ArrayList<Question> Questions = new ArrayList<>();
       try{
           String query = "SELECT * FROM QUESTIONS";
           PreparedStatement st = con.prepareStatement(query);
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               Questions.add(new Question(rs.getInt("qid"), rs.getInt("uid"), rs.getString("Content"),rs.getString("TIMESTAMP") ));
           }

        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Questions;
    }

    @WebMethod(operationName = "GetQuestionByID")
    @WebResult(name="Question")
    public Question GetQuestionByID(int qid){
        Question q = null;
        try {
            String query = "SELECT * FROM QUESTIONS WHERE ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1,qid);
            ResultSet rs = st.executeQuery();
            q = new Question(qid,rs.getInt("uid"),rs.getString("Content"),rs.getString("TIMESTAMP"));
        } catch (SQLException ex) {
            Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return q;
    }
}
