/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wbd.service;

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
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *
 * @author User
 */
public class allComments extends HttpServlet {

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
            out.println("<title>Servlet allComments</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet allComments at " + request.getContextPath() + "</h1>");
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

        System.out.println("GET Started");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {}
        
        System.out.println("Hello World");
        
        System.out.println(jb.toString());
        try {
               System.out.println("Hello World2");
 
            JSONParser parser = new JSONParser();
            Object tempObj = parser.parse(jb.toString());
            JSONObject obj = (JSONObject) tempObj;
            int qid = Integer.parseInt(obj.get("question_id").toString());
            
        
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wbd?zeroDateTimeBehavior=convertToNull","root","");
                Statement stmt = conn.createStatement();
                String sql;
                sql = "SELECT * FROM comment_question NATURAL JOIN user WHERE IDQ = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1,qid);
                ResultSet rs = dbStatement.executeQuery();
                int i = 0;

                JSONArray listOfComment = new JSONArray();
                System.out.println("Setelah membentuk JSON Array");
                while (rs.next()){
                    JSONObject comment = new JSONObject();
                    System.out.println("Iterasi" + rs.getString("Comment") + " " + rs.getString("Nama") + rs.getTimestamp("created_at"));
                    //Luminto
                    comment.put("content", rs.getString("Comment"));
                    comment.put("author", rs.getString("Nama"));
                    comment.put("timestamp", rs.getTimestamp("created_at"));
                    listOfComment.put(comment);        
                }

                System.out.println("Finish Array");
                JSONObject mainObj = new JSONObject();
                mainObj.put("comments", listOfComment);
                out.println(mainObj);

                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException ex){
                //Logger.getLogger(QuestionWS.class.getName()).log(Level.SEVERE, null, ex);
            }
   
    }   catch (ParseException ex) {
            //Logger.getLogger(comment.class.getName()).log(Level.SEVERE, null, ex);
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
