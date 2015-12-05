/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author vanyadeasysafrina
 */
public class VoteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Connection conn = DatabaseController.connect();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VoteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VoteServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("application/json");
        String isQuestion = request.getParameter("q"); // question = 1 answer = 0
        String id = request.getParameter("id"); // q_id or a_id
        String token = request.getParameter("token");
        String isUp = request.getParameter("up"); // up = 1 down = 0
        String vote = "null";
        // CHECK IF TOKEN HAS BEEN EXPIRED
        JSONObject object = validateUserID(token);
        
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

    public JSONObject validateUserID(String token) {
        String sql = "SELECT * FROM token WHERE access_token = ?";

        JSONObject object = new JSONObject();

        try (PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, token);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                Date now = new Date();
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    Date expiry_date = format.parse(result.getString("expiry_date"));
                    if (now.after(expiry_date)){
                        object.put("error", "Expired Token");
                        String deleteQuery = "DELETE FROM token WHERE access_token = ?";
                        try (PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery)){
                            deleteStatement.setString(1, token);
                            deleteStatement.execute();
                        }
                    }
                    else {
                        object.put("id", result.getInt("u_id"));   
                    }
               }
               catch(SQLException | ParseException e){

               }
           }
        }
        catch (SQLException e){

        }
        return object;
    }
}