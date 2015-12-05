/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Asus
 */
@WebServlet(urlPatterns = {"/VoteServlet"})
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
        Statement statement;
        
        DBConnection connection = new DBConnection();
        Connection conn = connection.getConn();
        
        if (request.getParameter("token") != null) {
            int uid = IdentityValidator.getUID(request.getParameter("token"));
            if (uid != 0 && request.getParameter("q_id") != null && request.getParameter("voteid") != null) {
                try {
                    String query1 = "SELECT * FROM question WHERE Q_ID = '" + request.getParameter("q_id") + "'";
                    
                    statement = conn.createStatement();
                    ResultSet rs = statement.executeQuery(query1);
                    
                    if (rs.next()) {
                        String votecheck = "SELECT * FROM questionvote WHERE Q_ID = '" + request.getParameter("q_id") + "' AND User_ID = '" + uid + "'";
                    
                        statement = conn.createStatement();
                        ResultSet rd = statement.executeQuery(votecheck);
                        
                        if (rd.next()) {
                            int voteid = rd.getInt("VoteType");
                            int q_id = rd.getInt("Q_ID");
                            String sql;
                            sql = "UPDATE question SET Vote = Vote + ? WHERE Q_ID = ?";
                            PreparedStatement dbStatement = conn.prepareStatement(sql);
                            if (voteid == parseInt(request.getParameter("voteid"))) {
                                if (voteid == -1) {
                                    dbStatement.setInt(1, 1);
                                    dbStatement.setInt(2, q_id);
                    
                                    dbStatement.executeUpdate();
                                }
                                else {
                                    dbStatement.setInt(1, -1);
                                    dbStatement.setInt(2, q_id);
                    
                                    dbStatement.executeUpdate();
                                }
                            }
                            else {
                                String update;
                                update = "UPDATE questionvote SET VoteType = ? WHERE Q_ID = ? AND User_ID = ?";
                                PreparedStatement statement1 = conn.prepareStatement(update);
                                if (voteid == -1) {
                                    dbStatement.setInt(1, -2);
                                    dbStatement.setInt(2, q_id);
                    
                                    dbStatement.executeUpdate();
                                    
                                    statement1.setInt(1, -1);
                                    statement1.setInt(2, q_id);
                                    statement1.setInt(3, uid);
                                    
                                    statement1.executeUpdate();
                                }
                                else {
                                    dbStatement.setInt(1, 2);
                                    dbStatement.setInt(2, q_id);
                    
                                    dbStatement.executeUpdate();
                                    
                                    statement1.setInt(1, 1);
                                    statement1.setInt(2, q_id);
                                    statement1.setInt(3, uid);
                                    
                                    statement1.executeUpdate();
                                }
                            }
                        }
                        else {
                            String sql;
                            sql = "UPDATE question SET Vote = Vote + ? WHERE Q_ID = ?";
                            PreparedStatement dbStatement = conn.prepareStatement(sql);
                            
                            int q_id = parseInt(request.getParameter("q_id"));
                            int voteid = parseInt(request.getParameter("voteid"));
                            String update;
                            update = "UPDATE questionvote SET VoteType = ? WHERE Q_ID = ? AND User_ID = ?";
                            PreparedStatement statement1 = conn.prepareStatement(update);
                            if (voteid == -1) {
                                dbStatement.setInt(1, -1);
                                dbStatement.setInt(2, q_id);

                                dbStatement.executeUpdate();

                                statement1.setInt(1, -1);
                                statement1.setInt(2, q_id);
                                statement1.setInt(3, uid);

                                statement1.executeUpdate();
                            }
                            else {
                                dbStatement.setInt(1, 1);
                                dbStatement.setInt(2, q_id);

                                dbStatement.executeUpdate();
                                
                                statement1.setInt(1, 1);
                                statement1.setInt(2, q_id);
                                statement1.setInt(3, uid);

                                statement1.executeUpdate();
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(VoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                if (uid != 0 && request.getParameter("a_id") != null && request.getParameter("voteid") != null) {
                    try {
                        String query1 = "SELECT * FROM answer WHERE A_ID = '" + request.getParameter("a_id") + "'";

                        statement = conn.createStatement();
                        ResultSet rs = statement.executeQuery(query1);

                        if (rs.next()) {
                            String votecheck = "SELECT * FROM answervote WHERE A_ID = '" + request.getParameter("a_id") + "' AND User_ID = '" + uid + "'";

                            statement = conn.createStatement();
                            ResultSet rd = statement.executeQuery(votecheck);

                            if (rd.next()) {
                                int voteid = rd.getInt("VoteType");
                                int a_id = rd.getInt("A_ID");
                                String sql;
                                sql = "UPDATE answer SET Vote = Vote + ? WHERE A_ID = ?";
                                PreparedStatement dbStatement = conn.prepareStatement(sql);
                                if (voteid == parseInt(request.getParameter("voteid"))) {
                                    if (voteid == -1) {
                                        dbStatement.setInt(1, 1);
                                        dbStatement.setInt(2, a_id);

                                        dbStatement.executeUpdate();
                                    }
                                    else {
                                        dbStatement.setInt(1, -1);
                                        dbStatement.setInt(2, a_id);

                                        dbStatement.executeUpdate();
                                    }
                                }
                                else {
                                    String update;
                                    update = "UPDATE answervote SET VoteType = ? WHERE A_ID = ? AND User_ID = ?";
                                    PreparedStatement statement1 = conn.prepareStatement(update);
                                    if (voteid == -1) {
                                        dbStatement.setInt(1, -2);
                                        dbStatement.setInt(2, a_id);

                                        dbStatement.executeUpdate();

                                        statement1.setInt(1, -1);
                                        statement1.setInt(2, a_id);
                                        statement1.setInt(3, uid);

                                        statement1.executeUpdate();
                                    }
                                    else {
                                        dbStatement.setInt(1, 2);
                                        dbStatement.setInt(2, a_id);

                                        dbStatement.executeUpdate();

                                        statement1.setInt(1, 1);
                                        statement1.setInt(2, a_id);
                                        statement1.setInt(3, uid);

                                        statement1.executeUpdate();
                                    }
                                }
                            }
                            else {
                                String sql;
                                sql = "UPDATE answer SET Vote = Vote + ? WHERE A_ID = ?";
                                PreparedStatement dbStatement = conn.prepareStatement(sql);

                                int a_id = parseInt(request.getParameter("a_id"));
                                int voteid = parseInt(request.getParameter("voteid"));
                                String update;
                                update = "UPDATE answervote SET VoteType = ? WHERE A_ID = ? AND User_ID = ?";
                                PreparedStatement statement1 = conn.prepareStatement(update);
                                if (voteid == -1) {
                                    dbStatement.setInt(1, -1);
                                    dbStatement.setInt(2, a_id);

                                    dbStatement.executeUpdate();

                                    statement1.setInt(1, -1);
                                    statement1.setInt(2, a_id);
                                    statement1.setInt(3, uid);

                                    statement1.executeUpdate();
                                }
                                else {
                                    dbStatement.setInt(1, 1);
                                    dbStatement.setInt(2, a_id);

                                    dbStatement.executeUpdate();

                                    statement1.setInt(1, 1);
                                    statement1.setInt(2, a_id);
                                    statement1.setInt(3, uid);

                                    statement1.executeUpdate();
                                }
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(VoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
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
