/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */
public class CommentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Statement statement;
        
        DBConnection connection = new DBConnection();
        Connection conn = connection.getConn();
        if (request.getParameter("token") != null) { 
            int uid = IdentityValidator.getUID(request.getParameter("token"));
            if (uid != 0 && request.getParameter("comment") != null && request.getParameter("q_id") != null) {
                try {
                    String query1 = "SELECT * FROM question WHERE Q_ID = '" + request.getParameter("q_id") + "'";

                    statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery(query1);

                    if (rs.next()) {
                        String insertComment = "INSERT INTO comment(Q_ID, Content, User_ID) values('" + request.getParameter("q_id") + "', '" + request.getParameter("comment") + "', '" + uid + "')";
                        statement.execute(insertComment);
                        response.sendRedirect("http://localhost:8082/Front-End/index");
                    }
                    else {
                        response.sendRedirect("http://localhost:8082/Front-End/index");
                    }

                    rs.close();
                    statement.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CommentServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                response.sendRedirect("http://localhost:8082/Front-End/index");
            }
        }
        else {
            response.sendRedirect("http://localhost:8082/Front-End/index");
        }
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

}
