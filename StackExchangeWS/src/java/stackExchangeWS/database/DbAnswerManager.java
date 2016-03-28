package stackExchangeWS.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author davidkwan
 */
public class DbAnswerManager {
    private static Connection conn = null;
        public static void answer(int questionId, Answer answer) throws SQLException{
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "INSERT INTO answer (questionId, answererId, content) VALUES(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, questionId);
            pstmt.setInt(2, answer.getAnswererId());
            pstmt.setString(3, answer.getContent());
            
            pstmt.executeUpdate();
            ConnectionManager.getInstance().close();
	}
        
        public static ArrayList<Answer> getAllAnswers(int questionId) throws SQLException{
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM answer, user WHERE questionId = ? AND userId = answererId";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, questionId);
            ResultSet rs = pstmt.executeQuery();
            ArrayList<Answer> answers = new ArrayList<Answer>();
            while(rs.next()){
                Answer answer = new Answer();          
                answer.setAnswerId(rs.getInt("answerId"));
                answer.setQuestionId(questionId);
                answer.setContent(rs.getString("content"));
                answer.setTime(rs.getString("time"));
                answer.setAnswererId(rs.getInt("answererId"));
                answer.setAnswererEmail(rs.getString("email"));
                
                answers.add(answer);
            }
            
            for(Answer a : answers){
                sql = "SELECT SUM(value) AS countVotes FROM votes_answer WHERE answerId = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, a.getAnswerId());
                
                rs = pstmt.executeQuery();
                
                while(rs.next()){
                   a.setVotes(rs.getInt("countVotes"));
                }
            }
            
            ConnectionManager.getInstance().close();
            
            return answers;
        }
        
        public static void voteAnswer(int answerId, int userId, int value) throws SQLException{
            conn = ConnectionManager.getInstance().getConnection();
            
            // Check whether user has ever vote or not
            String SelectSql = "SELECT * FROM votes_answer WHERE answerId = ? AND voter=?";
            PreparedStatement pstmt = conn.prepareStatement(SelectSql);
            
            pstmt.setInt(1, answerId);
            pstmt.setInt(2, userId);
            ResultSet rs = pstmt.executeQuery();
            
            // No row available
            if(rs.next() == false){
                String updateSql = "INSERT INTO votes_answer VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(updateSql);
                
                pstmt.setInt(1, answerId);
                pstmt.setInt(2, userId);
                pstmt.setInt(3, value);
                pstmt.executeUpdate();
            }
            else{
                int vote = rs.getInt("value");
                vote = (vote == -value)?value:vote^value; // If negative of value, change it to the opposite, else vote/unvote.
                
                String updateSql = "UPDATE votes_answer SET value=? WHERE answerId = ? AND voter = ?";
                pstmt = conn.prepareStatement(updateSql);
                
                pstmt.setInt(1, vote);
                pstmt.setInt(2, answerId);
                pstmt.setInt(3, userId);
                pstmt.executeUpdate();
            }
            
            ConnectionManager.getInstance().close();
            
        }
        
        public static int getQuestionId(int answerId) throws SQLException{
            conn = ConnectionManager.getInstance().getConnection();
            String sql = "SELECT questionId FROM answer WHERE answerId = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, answerId);
            
            ResultSet rs = pstmt.executeQuery();
            
            int questionId = 0; 
            while(rs.next()){
                questionId = rs.getInt("questionId");
            }
            return questionId;
        }
       
}