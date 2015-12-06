/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dazzlesquad.vote;

import com.dazzlesquad.DBConnect.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.json.simple.JSONObject;

/**
 *
 * @author NithoAlif
 */
public class VoteQuestion extends HttpServlet {


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //Connect to Database
   DBConnect db = new DBConnect();
   Connection conn = db.connect();
  
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        
        try (PrintWriter out = response.getWriter()) {
            
            int question_id = Integer.parseInt(request.getParameter("qid"));
            int flag = Integer.parseInt(request.getParameter("flag"));
            String token = request.getHeader("X-Token");
           
            
            
            JSONObject message = voteQuestion(question_id, token, flag);
            message.put("token", token);
            
            out.flush();
            out.print(message);
            out.close();
            //String location = "http://localhost:8083/Stack_Exchange_Client/QuestionPage?id=" + question_id;
            //response.sendRedirect(location);
            
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
    
    public JSONObject voteQuestion(int id, String token, int flag) {
        JSONObject message = new JSONObject();
        int votesuccessful = 0, questionUserId = 0, tokenUserId = 0, isvoted = 0;
        //Mencari user_id dari token
        try {
            String sql;
            Statement statement = conn.createStatement();
           
            sql = "SELECT user_id FROM tokenlist WHERE token = ? LIMIT 1";

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token);

            ResultSet result = dbStatement.executeQuery();

            if (result.next()) {
                tokenUserId = result.getInt("user_id");
                message.put("id", tokenUserId);
            } else {
                message.put("id", "Gak Ada");
            }

            statement.close();
        } catch (SQLException ex) {
            message.put("status", "fail");  
            message.put("error", ex); 
        }

        try {
            //Mencari id dari tabel vote_question
            String sql;
            Statement statement = conn.createStatement();

            sql = "SELECT id FROM vote_question WHERE user_id = ? AND question_id = ?";

            PreparedStatement dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, tokenUserId);
            dbStatement.setInt(2, id);

            ResultSet result = dbStatement.executeQuery();
            statement.close();

            //Kalau sudah ada di tabel vote question (sudah pernah vote)
            if (result.next() || tokenUserId==0) {
                message.put("status", "fail");  
                message.put("error", "You have voted before");  
            
            //Belum pernah vote
            } else {

                try {
                    statement = conn.createStatement();

                    //update kolom vote
                    sql = "UPDATE question SET vote = vote+? WHERE id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, flag);
                    dbStatement.setInt(2, id);
                    dbStatement.executeUpdate();
                    
                    //ambil jumlah vote sekarang
                    sql = "SELECT vote FROM question WHERE id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, id);
                    result = dbStatement.executeQuery();
                    
                    if (result.next()) {
                        int vote = result.getInt("vote");
                        message.put("vote", vote);
                        message.put("status", "success");
                    }
                    

                    //masukkan user ke tabel vote_question
                    sql = "INSERT INTO vote_question (question_id, user_id) VALUES (?, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, id);
                    dbStatement.setInt(2, tokenUserId);
                    dbStatement.executeUpdate();

                    statement.close();
                    votesuccessful = 1;


                } catch (SQLException ex) {
                    message.put("status", "fail");
                    message.put("error", ex); 
                }

            }

        } catch (SQLException ex) {
            message.put("status", "fail");
            message.put("error", ex);   
        }
        return message;
    }
}
