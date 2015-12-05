/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wbd.service;

import com.wbd.tokenChecker.TokenChecker;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author User
 */
public class voteServiceQuestion extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet voteServiceQuestion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet voteServiceQuestion at " + request.getContextPath() + "</h1>");
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

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //Connection conn = null;
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {}
        
        System.out.println(jb.toString());
        try {
            System.out.println("Hello World");
            JSONParser parser = new JSONParser();
            
            Object tempObj = parser.parse(jb.toString());
            JSONObject obj = (JSONObject) tempObj;
            String access_token = (String) obj.get("access_token");
            int qid = Integer.parseInt(obj.get("question_id").toString());
            int direction = Integer.parseInt(obj.get("direction").toString());
            Connection conn = null;
            int message = 0;
            TokenChecker token_check = new TokenChecker();

            System.out.println("ACCESS TOKEN : " + access_token);
            token_check.check(access_token);

            System.out.println("Validity : " + token_check.getValid());
            if (token_check.getExpired() == 1){
                message = -2; //Expired
            } else {
                try {
                    if (token_check.getValid() == 1){
                        //Can Vote. Right Identity
                        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd","root","");
                        String sql = "SELECT * FROM vote_question NATURAL JOIN token WHERE access_token = ? AND IDQ = ?";
                        PreparedStatement dbStatement = conn.prepareStatement(sql);
                        dbStatement.setString(1,access_token);
                        dbStatement.setInt(2,qid);
                        ResultSet rs = dbStatement.executeQuery();
                        if (!rs.next()){
                            //Jika gak da vote di database, Bisa vote
                            String sql2 = "SELECT * FROM user NATURAL JOIN token WHERE access_token = ?";
                                PreparedStatement dbStatement2 = conn.prepareStatement(sql2);
                                dbStatement2.setString(1,access_token);
                                ResultSet rs2 = dbStatement2.executeQuery();
                                int user_id = 0;
                                if (rs2.next()){
                                    user_id = rs2.getInt("IDUser");
                                }

                                String sql4 = "INSERT INTO vote_question(IDUser,IDQ,vote_direction) VALUES(?,?,?)";
                                PreparedStatement dbStatement4 = conn.prepareStatement(sql4);
                                dbStatement4.setInt(1,user_id);
                                dbStatement4.setInt(2,qid);
                                dbStatement4.setInt(3,1);
                                 System.out.println("Checkpoint 3");
                                dbStatement4.executeUpdate();

                                 conn.setAutoCommit(false);

                                Statement stmt = conn.createStatement();
                                String sql3 = "";
                                if (direction == 1){
                                     sql3 = "UPDATE question SET Vote = Vote + 1 WHERE IDQ = ?";
                                } else if (direction == 0){
                                     sql3 = "UPDATE question SET Vote = Vote - 1 WHERE IDQ = ?";
                                } else {
                                    
                                }
                                PreparedStatement pstmt = conn.prepareStatement(sql3);
                                pstmt.setInt(1, qid);
                                pstmt.executeUpdate();
                                conn.commit();
                                message = 1;
                                conn.close();
                        }
                        else{
                            //Jika ada vote di database, gak bisa vote lagi
                            message = -5;
                        }
                    }else{
                        //Wrong identity. Something wrong
                        message = -1;                
                    } 
                } catch (SQLException e){
                    e.printStackTrace();
                    message = 11;
                }
            }
            
            JSONObject objOut = new JSONObject();
            objOut.put("message", message);
            out.println(objOut);
            
        } catch (Exception ex) {
            //Logger.getLogger(AuthRSServlet.class.getName()).log(Level.SEVERE, null, ex);
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
