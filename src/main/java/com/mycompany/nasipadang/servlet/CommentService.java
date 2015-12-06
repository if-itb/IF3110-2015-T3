/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.nasipadang.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Math.toIntExact;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author user
 */
public class CommentService extends HttpServlet {

    private Connection connection;
    private void connectDB() throws SQLException{
        try {
            System.out.println("Loading driver...");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot find the driver in the classpath!", e);
        }
        String url = "jdbc:mysql://localhost:3306/db_stackexchange";
        String username = "root";
        String password = "";
        connection = null;
        System.out.println("Connecting database...");
        connection = (Connection) DriverManager.getConnection(url, username, password);
        System.out.println("Database connected!");
    }
    private void closeDB() throws SQLException{
        connection.close();
    }
    private int whoIs(String token){
        String url = ("http://localhost:8080/NasiPadang/rest/identity?token=" + token);
        int id_user = 0;
        try{
            HttpClient c = new DefaultHttpClient();        
            HttpGet p = new HttpGet(url);        
 
            HttpResponse r = c.execute(p);
 
            BufferedReader rd = new BufferedReader(new InputStreamReader(r.getEntity().getContent()));
            String line = "";
            JSONObject o = null;
            while ((line = rd.readLine()) != null) {
               //Parse our JSON response        
               JSONParser j = new JSONParser();
               o = (JSONObject)j.parse(line);
            }
            if(o.get("status").equals("ok")){
                long id_us = (long) o.get("id_user");
                id_user = toIntExact(id_us);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return id_user;
    }
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
            out.println("<title>Servlet CommentService</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommentService at " + request.getContextPath() + "</h1>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        org.json.JSONObject json = new org.json.JSONObject();
        String token = request.getParameter("token");
        int id_user = whoIs(token);
        int id = Integer.parseInt(request.getParameter("id"));
        if(id_user != 0){
            try {
                connectDB();
                Statement st = connection.createStatement();
                String sql = "INSERT INTO answer (id, id_user, content) VALUES ('"+ id +"', '"+ id_user +"', '"+ request.getParameter("content") +"')";
                st.execute(sql);
                closeDB();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            json.put("status", "ok");
            org.json.JSONArray allAnswerTemp = new org.json.JSONArray();
            try {
                connectDB();
                Statement st = connection.createStatement();
                String sql = ("SELECT *, (SELECT name FROM user WHERE user.id_user = answer.id_user) as name, (SELECT SUM(vote_answer.value) as votes FROM vote_answer WHERE vote_answer.id_answer = answer.id_answer) as votes FROM answer WHERE id ='" + id + "' AND content LIKE '" + request.getParameter("content")) + "'";
                ResultSet rs = st.executeQuery(sql);

                
                while(rs.next()){
                    org.json.JSONObject j = new org.json.JSONObject();
                    j.put("id", rs.getInt("id"));
                    j.put("id_answer", rs.getInt("id_answer"));
                    j.put("name", rs.getString("name"));
                    j.put("content", rs.getString("content"));
                    j.put("timestamp", rs.getString("timestamp"));
                    j.put("vote", rs.getInt("votes"));
                    allAnswerTemp.put(j);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            org.json.JSONArray allAnswer = new org.json.JSONArray();
            if(id_user != 0){
                for(int i = 0; i < allAnswerTemp.length(); i++){
                    try{
                        org.json.JSONObject j = (org.json.JSONObject) allAnswerTemp.get(i);
                        connectDB();
                        Statement st = connection.createStatement();
                        String sql = "SELECT * FROM vote_answer WHERE id_answer = '" + j.getInt("id_answer") + "' AND id_user = '" + id_user + "'";
                        ResultSet rs = st.executeQuery(sql);
                        j.put("hasVote", rs.next());
                        closeDB();
                        allAnswer.put(j);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            json.put("answers", allAnswer);
        }
        else{
            json.put("status", "invalid");
        }
        System.out.println(json.toString());
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            out.println(json.toString());
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
