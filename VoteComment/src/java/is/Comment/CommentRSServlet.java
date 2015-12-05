/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.Comment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Asus
 */
public class CommentRSServlet extends HttpServlet {


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
        //processRequest(request, response);
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
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection conn = null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {}
        System.out.println(jb.toString());
        try {
            JSONParser parser = new JSONParser();
            
            Object tempObj = parser.parse(jb.toString());
            JSONObject obj = (JSONObject) tempObj;
            String comment = (String) obj.get("comment");
            int qid = Integer.parseInt(obj.get("qid").toString());
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            PreparedStatement dbStatement;
            //take the email from session asumsi bahwa token selalu bersama email
            sql = "INSERT INTO comments (QuestionID, Name, Content, Datetime) VALUES (?,?,?,NOW())";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            dbStatement.setString(2, "Cliff");
            dbStatement.setString(3, comment);
            
            int res = dbStatement.executeUpdate();
            if (res == 1) {
                JSONObject objOut = new JSONObject();
                objOut.put("name", "Cliff");
                objOut.put("comment", comment);
                objOut.put("qid", qid);
                Date d = new Date();
                objOut.put("time", d.toString());
                out.println(objOut);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CommentRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CommentRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

}
