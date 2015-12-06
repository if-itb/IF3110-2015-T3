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
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String isQuestion = request.getParameter("type"); // question = 1 answer = 0
        String id = request.getParameter("id"); // q_id or a_id
        String token = request.getParameter("token");
        String isUp = request.getParameter("up"); // up = 1 down = 0
        
        // CHECK IF TOKEN HAS BEEN EXPIRED
        JSONObject object = validateUserID(token);
        // CHECK USER AGENT AND IP ADDRESS
        String userAgent = request.getHeader("user-agent");
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        String vote = null;
        if(ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        String[] arrToken = token.split("#");
        if(arrToken[1].equals(userAgent) && arrToken[2].equals(ipAddress)) {
            // VOTE
            try (Statement stmt = conn.createStatement()) {
                String sql;
                if(isQuestion.equals("1")) {
                    // QUESTION VOTE
                    sql = "SELECT vote FROM vote_question WHERE q_id=? and u_id=?";
                } else {
                    sql = "SELECT vote FROM vote_answer WHERE a_id=? and u_id=?";
                }                    
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1,Integer.parseInt(id));
                dbStatement.setInt(2,(Integer)object.get("id"));
                ResultSet rs = dbStatement.executeQuery();
                boolean empty = true;
                while (rs.next()) {
                    empty = false;
                    vote = Integer.toString(rs.getInt("vote"));
                }
                if((!empty && (vote.equals("1")) && isUp.equals("1"))||(!empty && (vote.equals("-1")) && isUp.equals("0"))) {
                    // If vote up and vote saved equals 1
                    // or if vote down and vote saved equals -1
                    object.put("error","question/answer has been vote already");
                } else {
                    if(isQuestion.equals("1")) { // If question
                        if(isUp.equals("1")) { // If vote up
                            sql = "UPDATE question SET vote = vote+1 WHERE q_id=?";
                        } else {
                            sql = "UPDATE question SET vote = vote-1 WHERE q_id=?";
                        }
                    } else { // If answer
                        if(isUp.equals("1")) { // If vote up
                            sql = "UPDATE answer SET vote = vote+1 WHERE q_id=?";
                        } else {
                            sql = "UPDATE answer SET vote = vote-1 WHERE q_id=?";
                        }
                    }
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, Integer.parseInt(id));
                    dbStatement.executeUpdate();
                    if(!empty) {
                        if(isQuestion.equals("1")) { // If question
                            if(isUp.equals("1")) { // If vote up
                                sql = "UPDATE vote_question SET vote = vote+1 WHERE q_id=? and u_id=?";
                            } else {
                                sql = "UPDATE vote_question SET vote = vote-1 WHERE q_id=? and u_id=?";
                            }
                        } else { // If answer
                            if(isUp.equals("1")) { // If vote up
                                sql = "UPDATE vote_answer SET vote = vote+1 WHERE q_id=? and u_id=?";
                            } else {
                                sql = "UPDATE vote_answer SET vote = vote-1 WHERE q_id=? and u_id=?";
                            }
                        }
                    }
                    else {
                        if(isQuestion.equals("1")) { // If question
                            sql = "INSERT INTO vote_question VALUE (?,?,1)";
                        } else { // If answer
                            sql = "INSERT INTO vote_answer VALUE (?,?,1)";
                        }
                    }
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, Integer.parseInt(id));
                    dbStatement.setInt(2, (Integer)object.get("id"));
                    dbStatement.executeUpdate();
                    sql = "SELECT vote FROM question WHERE q_id=?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, Integer.parseInt(id));
                    rs = dbStatement.executeQuery();
                    while (rs.next()) {
                        vote = Integer.toString(rs.getInt("vote"));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(VoteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            object.put("vote", vote);
        } else {
            object.put("error","User agent and/or IP address is incorrect");
        }
        PrintWriter out = response.getWriter();
        out.println(object.toString());
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