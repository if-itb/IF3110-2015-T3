/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VoteRS;


import Auth.Auth;
import Database.DB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
 * @author Vanji
 */
public class voteAnswer extends HttpServlet {

    
    DB db = new DB();
    Connection conn = db.connect();
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
        JSONObject obj = new JSONObject();
        try (PrintWriter out = response.getWriter()) {
            String aid = request.getParameter("aid");            
            int v_count = 0;
            try {
                Statement stmt = conn.createStatement();
                String sql;

                sql = "SELECT SUM(v_count) v_count FROM `vote` WHERE a_id = ?";
                PreparedStatement dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, Integer.parseInt(aid));

                ResultSet rs = dbStatement.executeQuery();

                while(rs.next()) {
                    v_count = rs.getInt("v_count");
                }

                obj.put("aid", aid);
                obj.put("v_count", v_count);   
                out.print(obj);   

                stmt.close();
            } catch(SQLException ex) {
                obj.put("error", ex);  
                out.print(obj);   
            }
        }
        
    }
    
    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        StringBuffer sb = new StringBuffer();
        String line= null;
        try {
            BufferedReader reader = request.getReader();
            while ((line=reader.readLine()) != null)
            {
                sb.append(line);
            }
        } catch (Exception e) {
        out.println(e);}
        
        try {
            JSONParser parser = new JSONParser();
            Object temp = parser.parse(sb.toString());
            JSONObject input = (JSONObject) temp;
            String token = (String) input.get("token");
            int qid = Integer.parseInt(input.get("qid").toString());
            int aid = Integer.parseInt(input.get("aid").toString());
            int uid = Integer.parseInt(input.get("uid").toString());
            int vcount = Integer.parseInt(input.get("vcount").toString());
            String qvote = (String) input.get("qvote");

            Auth auth = new Auth();
            int ret = auth.validation(token);

            if ( ret == 1 ) {
                int count = 0;
                int v_count = 0;

                Statement stmt = conn.createStatement();
                String sql;
                PreparedStatement dbStatement;

                sql = "SELECT * FROM vote WHERE u_id = ? AND a_id = ?";
                dbStatement = conn.prepareStatement(sql);
                dbStatement.setInt(1, uid);
                dbStatement.setInt(2, aid);

                ResultSet rs = dbStatement.executeQuery();

                // Extract data from result set
                while(rs.next()){        
                  ++count;
                }

                if ( count == 0 ) {
                    // Insert Vote Baru
                    sql = "INSERT INTO vote (u_id, q_id, a_id, v_count) VALUES (?, 0, ?, ?)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, uid);
                    dbStatement.setInt(2, aid);
                    dbStatement.setInt(3, vcount);

                    dbStatement.executeUpdate();
                    
                    // Dapatkan Jumlah Vote Answer Setelah Insert
                    sql = "SELECT SUM(v_count) v_count FROM `vote` WHERE a_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, aid);

                    rs = dbStatement.executeQuery();

                    while(rs.next()) {
                        v_count = rs.getInt("v_count");
                    }
                    
                    // Update Jumlah Vote Answer
                    sql = "UPDATE answer SET a_vote = ? WHERE a_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, v_count);
                    dbStatement.setInt(2, aid);
                    
                    dbStatement.executeUpdate();
                    
                    
                } else {
                    // Update Vote
                    sql = "UPDATE vote SET v_count = ? WHERE u_id = ? AND a_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, vcount);
                    dbStatement.setInt(2, uid);
                    dbStatement.setInt(3, aid);

                    dbStatement.executeUpdate();
                    
                    // Dapatkan Jumlah Vote Answer Setelah Update
                    sql = "SELECT SUM(v_count) AS v_count FROM vote WHERE a_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, aid);

                    rs = dbStatement.executeQuery();

                    while(rs.next()) {
                        v_count = rs.getInt("v_count");
                    }
                    
                    // Update Jumlah Vote Answer
                    sql = "UPDATE answer SET a_vote = ? WHERE a_id = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, vcount);
                    dbStatement.setInt(2, aid);
                    
                    dbStatement.executeUpdate();
                    
                }

                obj.put("vote", v_count);  
                out.print(obj);   

                stmt.close();
            
            
          } else {
            obj.put("error", "validation");  
            out.print(obj);
        }
        }catch(SQLException ex) {
                obj.put("error", ex);  
                out.print(obj);

      } catch (ParseException ex) {
            Logger.getLogger(voteAnswer.class.getName()).log(Level.SEVERE, null, ex);
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
        processPostRequest(request, response);
        
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
