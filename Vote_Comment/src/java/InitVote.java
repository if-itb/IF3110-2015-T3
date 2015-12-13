/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author adek
 */
public class InitVote extends HttpServlet {

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
        try(PrintWriter out = response.getWriter()) {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
            String type = request.getParameter("type");
            int qid = Integer.parseInt(request.getParameter("id"));
            Statement stmt = (Statement) conn.createStatement();
            JSONObject jobj = new JSONObject();
            int vote = 777; 
            if(type.equals("q")) {
                String sql = "SELECT vote FROM questions WHERE id="+qid;
                PreparedStatement dbStatement = (PreparedStatement) conn.prepareStatement(sql);
                ResultSet results = dbStatement.executeQuery();
                if(results.next()){
                    vote = results.getInt("vote");
                    jobj.put("qvote", vote);
                }
            }
            else if(type.equals("a")) {
                String sql = "SELECT * FROM answers WHERE id_question="+qid;
                PreparedStatement dbStatement = (PreparedStatement) conn.prepareStatement(sql);
                ResultSet results = dbStatement.executeQuery();
                JSONArray alist = new JSONArray();
                while(results.next()){
                    vote = results.getInt("vote");
                    int aid = results.getInt("id");
                    String topic = results.getString("content");
                    //get email from users table
                    int userid = results.getInt("id_user");
                    sql = "SELECT email FROM users WHERE id="+userid;
                    JSONObject cild = new JSONObject();
                    dbStatement = (PreparedStatement) conn.prepareStatement(sql);
                    ResultSet rs = dbStatement.executeQuery();
                    String email = "";
                    if(rs.next())
                        email = rs.getString("email");
                    //include them in JSON list to send
                    cild.put("vote",vote);
                    cild.put("aid", aid);
                    cild.put("topic",topic);
                    cild.put("email",email);
                    alist.add(cild);
                }
                jobj.put("avotes",alist);
            }
            out.write(jobj.toString());
            out.flush();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(InitVote.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader reader = request.getReader();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                System.out.println(line);
            }
        } catch (Exception e) { e.printStackTrace(); }
 
//        JSONParser parser = new JSONParser();
//        JSONObject joUser = null;
//        try {
//            joUser = (JSONObject) parser.parse(sb.toString());
//        } catch (ParseException e) { e.printStackTrace(); }
 
//        String qid = (String) joUser.get("id");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.write(sb.toString());
        out.flush();
        out.close();
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
