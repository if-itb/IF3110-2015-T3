/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jessica
 */
public class AnswerServlet extends HttpServlet {
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
        Gson gson = new Gson();
        String json = "";
        ArrayList<AnswerClass> answer = new ArrayList<>();
        
        try (PrintWriter out = response.getWriter()){
            //parsing json    
            String question_id = request.getParameter("q_id");
            
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(localhost, USER, PASS);
                String sql = "SELECT * FROM answers WHERE question_id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.valueOf(question_id));
                ResultSet rs = preparedStatement.executeQuery();
            
                if (rs.next()) {
                    while (rs.next()) {
                        int answerID  = rs.getInt("answer_id");
                        int questionID  = rs.getInt("question_id");
                        String content  = rs.getString("content");
                        int vote  = rs.getInt("vote");
                        String date  = rs.getString("date");
                        int userID  = rs.getInt("userID");

                        AnswerClass answerVoteRecord = new AnswerClass(answerID, questionID, content, vote, date, userID);
                        answer.add(answerVoteRecord);
                    }
                }
                json = gson.toJson(answer);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            out.print(json);
        } 
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        processGetRequest(request, response);
    }

}
