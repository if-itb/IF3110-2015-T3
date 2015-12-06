/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author adek
 */
public class VoteQuestionServlet extends HttpServlet {

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
            int userid = Integer.parseInt(request.getParameter("userid"));
            int qid = Integer.parseInt(request.getParameter("qid"));
            int stat = Integer.parseInt(request.getParameter("stat"));
            String token = request.getParameter("token");
            Form form = new Form();
            form.param("token",token);
            Client client = ClientBuilder.newClient();
            String url = "http://localhost:8082/Identity_Service/CheckToken"; 
            String result = client.target(url).request(MediaType.APPLICATION_JSON).
                  post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED), String.class);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(result);
            JSONObject jobj = (JSONObject) obj;
            String message = (String) jobj.get("message");
            System.out.println(message);
            if(message.equals("valid")) {
                try {         
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
                    String sql = "SELECT id FROM voteQuestion WHERE id_user =" + userid + " && id_question = " + qid;
                    PreparedStatement dbs = (PreparedStatement) conn.prepareStatement(sql);
                    ResultSet rs = dbs.executeQuery();
                    int id = 0,unicFlag=0;
                    if(!rs.next()) {
                        unicFlag=1;
                        sql = "INSERT INTO voteQuestion(id_user,id_question,status) VALUES ("+userid+","+qid+","+stat+")";
                        java.sql.Statement stmt = conn.createStatement();
                        stmt.executeUpdate(sql);
                    }
                    else {
                        id = rs.getInt("id");
                        sql = "SELECT id FROM voteQuestion WHERE id_user =" + userid + " && id_question = " + qid + "&& status = " + stat;
                        dbs = (PreparedStatement) conn.prepareStatement(sql);
                        rs = dbs.executeQuery();
                        if(!rs.isBeforeFirst()) {
                            unicFlag=1;
                            sql = "UPDATE voteQuestion SET status='"+stat+"' WHERE id="+id;
                            java.sql.Statement stmt = conn.createStatement();
                            stmt.executeUpdate(sql);
                        }
                    }
                    if(unicFlag==1) {
                    //get current vote value
                        sql = "SELECT vote FROM questions WHERE id =" + qid;
                        dbs = (PreparedStatement) conn.prepareStatement(sql);
                        rs = dbs.executeQuery();
                        int vote =0;
                        if(rs.next())
                            vote = rs.getInt("vote")+stat;
                    //update vote value
                        sql = "UPDATE questions SET vote='"+vote+"' WHERE id="+qid;
                        java.sql.Statement stmt = conn.createStatement();
                        stmt.executeUpdate(sql);
                    }
                } catch(ClassNotFoundException | SQLException e) {}   
            }
            else if(message.equals("false-agent")) {
                request.getRequestDispatcher("http://localhost:8080/StackExchange_Client/ErrorPage/FalseAgentPage.jsp").forward(request, response);
            }
            else if(message.equals("false-ipaddr")) {
                request.getRequestDispatcher("http://localhost:8080/StackExchange_Client/ErrorPage/FalseIpAddrPage.jsp").forward(request, response);
            }
            else if(message.equals("expired")) {
                request.getRequestDispatcher("http://localhost:8080/StackExchange_Client/ErrorPage/ExpiredPage.jsp").forward(request, response);
            }
            else if(message.equals("invalid")) {
                request.getRequestDispatcher("http://localhost:8080/StackExchange_Client/ErrorPage/InvalidPage.jsp").forward(request, response);
            }
        } catch (ParseException ex) {
            Logger.getLogger(VoteQuestionServlet.class.getName()).log(Level.SEVERE, null, ex);
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

}
