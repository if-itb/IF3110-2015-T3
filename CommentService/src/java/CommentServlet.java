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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
public class CommentServlet extends HttpServlet {
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
        ArrayList<CommentClass> comment = new ArrayList<>();
        
        try (PrintWriter out = response.getWriter()){
            //parsing json    
            String question_id = request.getParameter("q_id");
            
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(localhost, USER, PASS);
                String sql = "SELECT commentID, questionID, userID, content FROM comment WHERE questionID = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.valueOf(question_id));
                ResultSet rs = preparedStatement.executeQuery();
            
                if (rs.next()) {
                    while (rs.next()) {
                        int commentID  = rs.getInt("commentID");
                        int questionID  = rs.getInt("questionID");
                        int userID  = rs.getInt("userID");
                        String content = rs.getString("content");

                        CommentClass commentRecord = new CommentClass(commentID, questionID, userID, content);
                        comment.add(commentRecord);
                    }
                }
                json = gson.toJson(comment);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            out.print(json);
        } 
    }
    
    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException, JSONException {
        response.setContentType("application/json");
        Connection conn = null;
        Statement stmt = null;
        JSONObject object = new JSONObject();
        
        try (PrintWriter out = response.getWriter()){
            String questionID = request.getParameter("q_id");
            String userID = request.getParameter("user_id");
            String content = request.getParameter("content");
            System.out.println("user ID : " + userID);
            System.out.println("question ID : " + questionID );
            System.out.println("content : " + content );
            
            try {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(localhost, USER, PASS);
                String sql = "INSERT INTO comment(questionID, userID, content) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setInt(1, Integer.valueOf(questionID));
                preparedStatement.setInt(2, Integer.valueOf(userID) );
                preparedStatement.setString(3, content);
                preparedStatement.executeUpdate();
                
                object.put("message", "Success Add Comment");
                out.print(object);
                
            } catch (Exception ex) {
                ex.printStackTrace();
                object.put("message", "Failed Add Comment");
                out.print(object);
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
