package com.dazzlesquad.comment;

import com.dazzlesquad.DBConnect.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author ryanyonata
 */
public class InsertComment extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DBConnect db = new DBConnect();
    Connection conn = db.connect();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        String token = "";
        Cookie[] cookie = request.getCookies();
        
        for(Cookie obj : cookie){
                //out.println(obj.getName());
                if(obj.getName().equals("token")){
                token = obj.getValue();
                //out.println(obj.getValue());
                break;
            }
	}
        request.setAttribute("token",token);
        
        int question_id = Integer.parseInt(request.getParameter("qid"));
        String comment_content= request.getParameter("comment_content");
        //int question_userid= Integer.parseInt(request.getParameter("question_userid"));
        
        
        int insertComment = insertComment(token, question_id, comment_content);
        
        String location= "/Stack_Exchange_Client/QuestionPage?id=" + question_id;
        response.sendRedirect(location);
            //response.sendRedirect("http://localhost:8081/Stack_Exchange_Client/QuestionServlet");
        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public int insertComment(String token, int qid, String content)
    {
        int insertSuccessful = 0;
        int tokenUserId = 0;
        try {
            String sql;
            Statement statement = conn.createStatement();

            sql = "SELECT user_id FROM tokenlist WHERE token = ? LIMIT 1";

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token);

            ResultSet result = dbStatement.executeQuery();

            tokenUserId = 0;
            if (result.next()) {
                tokenUserId = result.getInt("user_id");
            } else {
                tokenUserId = 0;
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(InsertComment.class.getName()).log(Level.SEVERE, null, ex);
            insertSuccessful = -1;
        }
        if(tokenUserId!=0){
            try {
                Statement statement = conn.createStatement();
                String sql;
                sql = "INSERT INTO comment (user_id, question_id, content, date) VALUES (?,?,?,now())";

                PreparedStatement dbStatement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                dbStatement.setInt(1,tokenUserId);
                dbStatement.setInt(2,qid);
                dbStatement.setString(3,content);

                dbStatement.executeUpdate(); 
                statement.close();

            } catch (SQLException ex) {
                Logger.getLogger(InsertComment.class.getName()).log(Level.SEVERE, null, ex);
                insertSuccessful = -1;
            }
        }
        return insertSuccessful;
    }
}