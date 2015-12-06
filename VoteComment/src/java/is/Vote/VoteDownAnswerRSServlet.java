/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.Vote;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;


/**
 *
 * @author yoga
 */
@WebServlet(name = "VoteDownAnswerRSServlet", urlPatterns = {"/VoteDownAnswerRSServlet"})
public class VoteDownAnswerRSServlet extends HttpServlet {

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
            out.println("<title>Servlet voteRSServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet voteRSServlet at " + request.getContextPath() + "</h1>");
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
        int returnExecution = 0;
        int AnsId = Integer.parseInt(request.getParameter("id"));
        PrintWriter out = response.getWriter();
        String currentAccessToken = request.getParameter("token");
            currentAccessToken = URLEncoder.encode(currentAccessToken, "UTF-8");
        response.setCharacterEncoding("application/json;charset=UTF-8");
        String currentEmail = new String("");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            PreparedStatement dbStatement;
            //take the email from session asumsi bahwa token selalu bersama email
            sql = "SELECT Email FROM sessions WHERE AccessToken = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, currentAccessToken);
            ResultSet rsEmail = dbStatement.executeQuery();
            //agar index berada di elemen pertama dan get email
            if(rsEmail.next()) {
                //returnExecution = returnExecution + 1;
                currentEmail = rsEmail.getString("Email");
            }
            
            //Melakukan pengecekan apakah sudah ada atau belum dalam database
            sql = "SELECT * FROM upanswer WHERE IDAns = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, AnsId);
            dbStatement.setString(2, currentEmail);
            ResultSet rs = dbStatement.executeQuery();
            //agar index berada di elemen pertama dan jika belum ada insert terlebih dulu
            if(!rs.next()){
            
                //Up the the question table
                sql = "INSERT INTO upanswer (email,IDAns,totalVote) VALUES(?,?,0)";
                dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, currentEmail);
                dbStatement.setInt(2, AnsId);
                dbStatement.executeUpdate();
            }
            
            //Melakukan pengecekan apakah sudah pernah di upvote atau tidak
            sql = "SELECT * FROM upanswer WHERE IDAns = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, AnsId);
            dbStatement.setString(2, currentEmail);
            rs = dbStatement.executeQuery();
            
            if (rs.next()){
                //jika sudah totalVote == 1 maka dilarang vote up lagi
                returnExecution = rs.getInt("totalVote");
                if(returnExecution == -1){
                    //do nothing because already upvote
                } else { //total vote 0 atau -1
                    //search apakah sudah pernah dilakukan vote up atau down sebelumnya 
                    //Up the the question table
                    sql = "UPDATE answers SET Votes = Votes - 1 WHERE AnswerID = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, AnsId);
                    dbStatement.executeUpdate();
                    //Up the the relation of email and question in table upquestion
                    sql = "UPDATE upanswer SET totalVote = totalVote - 1 WHERE IDAns = ? AND email = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, AnsId);
                    dbStatement.setString(2, currentEmail);
                    dbStatement.executeUpdate();
                }
            }
            int Vote = 0;
            sql = "SELECT * FROM answers WHERE AnswerID = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, AnsId);
            rs = dbStatement.executeQuery();
            if (rs.next()) {
                Vote = rs.getInt("Votes");
            }
            JSONObject objOut = new JSONObject();
            objOut.put("Vote", Vote);
            out.println(objOut);
            //stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoteDownAnswerRSServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        int returnExecution = 0;
        int Vote=0;
        PrintWriter out = response.getWriter();
        int AnsId = Integer.parseInt(request.getParameter("id"));
        String currentAccessToken = request.getParameter("token");
        
        String currentEmail = new String("");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String sql;
            PreparedStatement dbStatement;
            //take the email from session asumsi bahwa token selalu bersama email
            sql = "SELECT Email FROM sessions WHERE AccessToken = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, currentAccessToken);
            ResultSet rsEmail = dbStatement.executeQuery();
            //agar index berada di elemen pertama dan get email
            if(rsEmail.next()) {
                //returnExecution = returnExecution + 1;
                currentEmail = rsEmail.getString("Email");
            }
            
            //Melakukan pengecekan apakah sudah ada atau belum dalam database
            sql = "SELECT * FROM upanswer WHERE IDAns = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, AnsId);
            dbStatement.setString(2, currentEmail);
            ResultSet rs = dbStatement.executeQuery();
            //agar index berada di elemen pertama dan jika belum ada insert terlebih dulu
            if(!rs.next()){
            
                //Up the the question table
                sql = "INSERT INTO upanswer (Email,IDAns,totalVote) VALUES(?,?,0)";
                dbStatement = conn.prepareStatement(sql);
                dbStatement.setString(1, currentEmail);
                dbStatement.setInt(2, AnsId);
                dbStatement.executeUpdate();
            }
            
            //Melakukan pengecekan apakah sudah pernah di upvote atau tidak
            sql = "SELECT * FROM upanswer WHERE IDAns = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, AnsId);
            dbStatement.setString(2, currentEmail);
            rs = dbStatement.executeQuery();
            
            if (rs.next()){
                //jika sudah totalVote == 1 maka dilarang vote up lagi
                returnExecution = rs.getInt("totalVote");
                if(returnExecution == -1){
                    //do nothing because already upvote
                } else { //total vote 0 atau -1
                    //search apakah sudah pernah dilakukan vote up atau down sebelumnya 
                    //Up the the question table
                    sql = "UPDATE answers SET Votes = Votes - 1 WHERE AnswerID = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, AnsId);
                    dbStatement.executeUpdate();
                    //Up the the relation of email and question in table upquestion
                    sql = "UPDATE upanswer SET totalVote = totalVote - 1 WHERE IDAns = ? AND email = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, AnsId);
                    dbStatement.setString(2, currentEmail);
                    dbStatement.executeUpdate();
                }
            }
             sql = "SELECT * FROM answers WHERE AnswerID = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, AnsId);
            rs = dbStatement.executeQuery();
            if (rs.next()) {
                Vote = rs.getInt("Votes");
            }
            JSONObject objOut = new JSONObject();
            objOut.put("Vote", Vote);
            out.println(objOut);
          
            //stmt.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VoteDownAnswerRSServlet.class.getName()).log(Level.SEVERE, null, ex);
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
