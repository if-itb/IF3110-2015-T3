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
import vc.auth.Auth;

/**
 *
 * @author yoga
 */
@WebServlet(name = "VoteUpQuestionRSServlet", urlPatterns = {"/VoteUpQuestionRSServlet"})
public class VoteUpQuestionRSServlet extends HttpServlet {

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
        Integer Vote = 0;
        PrintWriter out = response.getWriter();
        try {
            /*final PrintWriter out = response.getWriter();
             StringBuffer jb = new StringBuffer();
             String line = null;
             try {
             BufferedReader reader = request.getReader();
             while ((line = reader.readLine()) != null)
             jb.append(line);
             } catch (Exception e) {}
             out.println(jb.toString());*/
            int returnExecution = 0;
            String token = request.getParameter("token");
            token = URLEncoder.encode(token, "UTF-8");
            int qid = Integer.parseInt(request.getParameter("qid"));
            response.setContentType("application/json;charset=UTF-8");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");

            String currentEmail = new String("");

            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String currentAccessToken = token;
            String sql;
            PreparedStatement dbStatement;
            //take the email from session asumsi bahwa token selalu bersama email
            Auth auth = new Auth();
            currentEmail = auth.getEmail(token);

            //Melakukan pengecekan apakah sudah ada atau belum dalam database
            sql = "SELECT * FROM upquestion WHERE IDQuestion = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            dbStatement.setString(2, currentEmail);
            ResultSet rs = dbStatement.executeQuery();
            //agar index berada di elemen pertama dan jika belum ada insert terlebih dulu
            if (!rs.next()) {
                if (!(currentEmail.equals(""))) {
                    //Up the the question table
                    sql = "INSERT INTO upquestion (email,IDQuestion,totalVote) VALUES(?,?,0)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setString(1, currentEmail);
                    dbStatement.setInt(2, qid);
                    dbStatement.executeUpdate();
                }

            }

            //Melakukan pengecekan apakah sudah pernah di upvote atau tidak
            sql = "SELECT * FROM upquestion WHERE IDQuestion = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            dbStatement.setString(2, currentEmail);
            rs = dbStatement.executeQuery();

            if (rs.next()) {
                //jika sudah totalVote == 1 maka dilarang vote up lagi
                returnExecution = rs.getInt("totalVote");
                if (returnExecution == 1) {
                    //do nothing because already upvote

                } else { //total vote 0 atau -1
                    //search apakah sudah pernah dilakukan vote up atau down sebelumnya 
                    //Up the the question table
                    sql = "UPDATE questions SET Votes = Votes + 1 WHERE QuestionID = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, qid);
                    dbStatement.executeUpdate();
                    //Up the the relation of email and question in table upquestion
                    sql = "UPDATE upquestion SET totalVote = totalVote+1 WHERE IDQuestion = ? AND email = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, qid);
                    dbStatement.setString(2, currentEmail);
                    dbStatement.executeUpdate();

                }
            }
            sql = "SELECT * FROM questions WHERE QuestionID = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            rs = dbStatement.executeQuery();
            if (rs.next()) {
                Vote = rs.getInt("Votes");
            }
            JSONObject objOut = new JSONObject();
            objOut.put("Vote", Vote.toString());
            out.println(objOut);
            //stmt.close();
        } catch (SQLException ex) {

            Logger.getLogger(VoteUpQuestionRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {

            Logger.getLogger(VoteUpQuestionRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
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
        Integer Vote = 0;
        PrintWriter out = response.getWriter();
        try {
            /*final PrintWriter out = response.getWriter();
             StringBuffer jb = new StringBuffer();
             String line = null;
             try {
             BufferedReader reader = request.getReader();
             while ((line = reader.readLine()) != null)
             jb.append(line);
             } catch (Exception e) {}
             out.println(jb.toString());*/
            int returnExecution = 0;
            String token = request.getParameter("token");
            int qid = Integer.parseInt(request.getParameter("qid"));
            response.setContentType("application/json;charset=UTF-8");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");

            String currentEmail = new String("");
            System.out.println("asasasas");
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/stackexchange?zeroDateTimeBehavior=convertToNull", "root", "");
            Statement stmt = conn.createStatement();
            String currentAccessToken = token;
            String sql;
            PreparedStatement dbStatement;
            //take the email from session asumsi bahwa token selalu bersama email
            sql = "SELECT Email FROM sessions WHERE AccessToken = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setString(1, token);
            ResultSet rsEmail = dbStatement.executeQuery();
            //agar index berada di elemen pertama dan get email
            if(rsEmail.next()) {
                //returnExecution = returnExecution + 1;
                currentEmail = rsEmail.getString("Email");
            }

            //Melakukan pengecekan apakah sudah ada atau belum dalam database
            sql = "SELECT * FROM upquestion WHERE IDQuestion = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            dbStatement.setString(2, currentEmail);
            ResultSet rs = dbStatement.executeQuery();
            //agar index berada di elemen pertama dan jika belum ada insert terlebih dulu
            if (!rs.next()) {
                if (!(currentEmail.equals(""))) {
                    //Up the the question table
                    sql = "INSERT INTO upquestion (Email,IDQuestion,totalVote) VALUES(?,?,0)";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setString(1, currentEmail);
                    dbStatement.setInt(2, qid);
                    dbStatement.executeUpdate();
                }

            }

            //Melakukan pengecekan apakah sudah pernah di upvote atau tidak
            sql = "SELECT * FROM upquestion WHERE IDQuestion = ? AND email = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            dbStatement.setString(2, currentEmail);
            rs = dbStatement.executeQuery();

            if (rs.next()) {
                //jika sudah totalVote == 1 maka dilarang vote up lagi
                returnExecution = rs.getInt("totalVote");
                if (returnExecution == 1) {
                    //do nothing because already upvote

                } else { //total vote 0 atau -1
                    //search apakah sudah pernah dilakukan vote up atau down sebelumnya 
                    //Up the the question table
                    sql = "UPDATE questions SET Votes = Votes + 1 WHERE QuestionID = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, qid);
                    dbStatement.executeUpdate();
                    //Up the the relation of email and question in table upquestion
                    sql = "UPDATE upquestion SET totalVote = totalVote+1 WHERE IDQuestion = ? AND email = ?";
                    dbStatement = conn.prepareStatement(sql);
                    dbStatement.setInt(1, qid);
                    dbStatement.setString(2, currentEmail);
                    dbStatement.executeUpdate();

                }
            }
            sql = "SELECT * FROM questions WHERE QuestionID = ?";
            dbStatement = conn.prepareStatement(sql);
            dbStatement.setInt(1, qid);
            rs = dbStatement.executeQuery();
            if (rs.next()) {
                Vote = rs.getInt("Votes");
            }
            JSONObject objOut = new JSONObject();
            objOut.put("Vote", Vote);
            out.println(objOut);
    
            //stmt.close();
        } catch (SQLException ex) {

            Logger.getLogger(VoteUpQuestionRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {

            Logger.getLogger(VoteUpQuestionRSServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
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
