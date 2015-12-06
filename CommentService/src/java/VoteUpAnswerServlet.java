/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Jessica
 */
public class VoteUpAnswerServlet extends HttpServlet {
    final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final static String localhost = "jdbc:mysql://localhost:3306/wbd2";
    final static String USER = "root";
    final static String PASS = "";
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {
        response.setContentType("application/json");
        Connection conn = null;
        Statement stmt = null;
        int vote = 0;
        JSONObject object = new JSONObject();
        String json = "";
        
        try (PrintWriter out = response.getWriter()){
            //parsing json    
            String answerId = request.getParameter("ans_id");
            //String questionId = request.getParameter("q_id");
            
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(localhost, USER, PASS);
                String sql = "SELECT vote FROM answers WHERE answer_id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.valueOf(answerId));
                //preparedStatement.setInt(2, Integer.valueOf(questionId));
                ResultSet rs = preparedStatement.executeQuery();
                
                if (rs.next()) {
                    vote  = rs.getInt("vote");
                }
                object.put("answerId", answerId);
                //object.put("questionId", questionId);
                object.put("vote", vote);
                out.print(object);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } 
    }
    
    
    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        Connection conn = null;
        Statement stmt = null;
        int userID_Exist = 0, questionID_Exist = 0, answerID_Exist = 0;
        int countVote = 0, value = 0, dataUserID = 0;
        JSONObject object = new JSONObject();
        
        try (PrintWriter out = response.getWriter()){
            //parsing json    
            String questionId = request.getParameter("q_id");
            String userId = request.getParameter("user_id");
            String answerId = request.getParameter("ans_id");            
            
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(localhost, USER, PASS);
                String checkAnswer = "SELECT `userID` FROM answers WHERE answer_id= ?"; 
                PreparedStatement preparedStatement = conn.prepareStatement(checkAnswer);
                preparedStatement.setInt(1, Integer.valueOf(answerId));
                ResultSet rsQuestion = preparedStatement.executeQuery();
                if(rsQuestion.next()) {
                    dataUserID = rsQuestion.getInt(1);
                }
                if (dataUserID != Integer.valueOf(userId)) {
                    String voteTable = "SELECT count(*) FROM voteanswer";
                    ResultSet rs = preparedStatement.executeQuery(voteTable);
                    if(rs.next()) {
                        countVote = rs.getInt(1);
                    }
                    int i = 0;
                    Boolean found = false;
                    while (i < countVote && !found) {
                        String checkEmail = "SELECT userID, questionID, answerID FROM voteanswer WHERE userID= ? AND questionID = ?  And answerID= ?";
                        preparedStatement = conn.prepareStatement(checkEmail);
                        preparedStatement.setInt(1, Integer.valueOf(userId));
                        preparedStatement.setInt(2, Integer.valueOf(questionId));
                        preparedStatement.setInt(3, Integer.valueOf(answerId));
                        ResultSet rsVote = preparedStatement.executeQuery();
                        if (rsVote.next()) {
                            userID_Exist = rsVote.getInt(1);                            
                            questionID_Exist = rsVote.getInt(2);
                            answerID_Exist = rsVote.getInt(3);
                        }
                        if ((Integer.valueOf(userId) == userID_Exist) && (Integer.valueOf(answerId) == answerID_Exist) && (Integer.valueOf(questionId) == questionID_Exist)) {
                            found = true;
                        } else {
                            i++;
                        }
                    }
                    if (found == false) {
                        String insertVote = "INSERT INTO voteanswer(`userID`, `answerID`, `questionID`) VALUES (?, ?,?)";
                        preparedStatement = conn.prepareStatement(insertVote);
                        preparedStatement.setInt(1, Integer.valueOf(userId));
                        preparedStatement.setInt(2, Integer.valueOf(answerId));      
                        preparedStatement.setInt(3, Integer.valueOf(questionId));
                        preparedStatement.executeUpdate();
                    }
                        String checkVote = "SELECT `value` FROM voteanswer WHERE questionID= ? AND userID= ? AND answerID= ?";
                        preparedStatement = conn.prepareStatement(checkVote);
                        preparedStatement.setInt(1, Integer.valueOf(questionId));        
                        preparedStatement.setInt(2, Integer.valueOf(userId));
                        preparedStatement.setInt(3, Integer.valueOf(answerId));
                        ResultSet rsVote = preparedStatement.executeQuery();
                    if(rsVote.next()) {
                        value = rsVote.getInt("value");
                    }
                    if (value == 0) {
                        String query2 = "UPDATE voteanswer SET `value` = 1 WHERE questionId = ? And userID= ? And answerID = ?";
                        preparedStatement = conn.prepareStatement(query2);
                        preparedStatement.setInt(1, Integer.valueOf(questionId));
                        preparedStatement.setInt(2, Integer.valueOf(userId));
                        preparedStatement.setInt(3, Integer.valueOf(answerId));
                        preparedStatement.executeUpdate();
                        String query = "UPDATE answers SET `vote` = `vote`+1 WHERE question_id= ? And answer_id= ?";
                        preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setInt(1, Integer.valueOf(questionId));        
                        preparedStatement.setInt(2, Integer.valueOf(answerId));        
                        preparedStatement.executeUpdate();
                    } else if (value == 1) {
                        String query2 = "UPDATE voteanswer SET `value` = 0 WHERE questionId= ? And userID = ? And answerID = ?";
                        preparedStatement = conn.prepareStatement(query2);
                        preparedStatement.setInt(1, Integer.valueOf(questionId));        
                        preparedStatement.setInt(2, Integer.valueOf(userId));
                        preparedStatement.setInt(3, Integer.valueOf(answerId));
                        preparedStatement.executeUpdate();
                        String query = "UPDATE answers SET `vote` = `vote`-1 WHERE question_id= ? And answer_id= ?";
                        preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setInt(1, Integer.valueOf(questionId));
                        preparedStatement.setInt(2, Integer.valueOf(answerId));
                        preparedStatement.executeUpdate();
                    } else if (value == -1) {
                        String query2 = "UPDATE voteanswer SET `value` = 1 WHERE questionId= ? And userID = ? And answerID = ?";
                        preparedStatement = conn.prepareStatement(query2);
                        preparedStatement.setInt(1, Integer.valueOf(questionId));
                        preparedStatement.setInt(2, Integer.valueOf(userId));
                        preparedStatement.setInt(3, Integer.valueOf(answerId));
                        preparedStatement.executeUpdate();
                        String query = "UPDATE answers SET `vote` = `vote`+2 WHERE question_id = ? And answer_iD= ?";
                        preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setInt(1, Integer.valueOf(questionId));        
                        preparedStatement.setInt(2, Integer.valueOf(answerId));        
                        preparedStatement.executeUpdate();
                    }
                    object.put("message", "Success.");
                    out.print(object);
                } else {
                    object.put("message", "You are the owner of this question.");
                    out.print(object);
                }
            }catch (Exception e) {
                object.put("message", "You are the owner of this question.");
                e.printStackTrace();
            }   
        } 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        processGetRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        try {
            processPostRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Info";
    }
}
