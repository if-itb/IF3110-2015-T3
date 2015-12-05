/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nim_13512501
 */
@WebServlet(urlPatterns = {"/Vote"})
public class Vote extends HttpServlet {

    
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
        if (request.getParameterMap().containsKey("qid")){
            int qid = Integer.parseInt(request.getParameter("qid"));
            
            Connection con = Database.connect();

            int vote;
            ResultSet rs = null;
            PreparedStatement ps = null;
            try {
                if (request.getParameterMap().containsKey("aid")){
                    int aid = Integer.parseInt(request.getParameter("aid"));
                    String query = "SELECT vote FROM Answer WHERE qid=? AND aid=?";
                    ps = con.prepareStatement(query);
                    ps.setInt(1, qid);
                    ps.setInt(2, aid);
                }else{
                    String query = "SELECT vote FROM Question WHERE qid=?";
                    ps = con.prepareStatement(query);
                    ps.setInt(1,qid);
                }
                rs = ps.executeQuery();
                if (rs.next()){
                    vote = rs.getInt("vote");
                    response.setContentType("text");
                    response.getWriter().println(vote);
                    System.out.println(vote);
                    //response.setStatus(HttpServletResponse.SC_OK);
                }else{
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch(Exception ex){
                ex.printStackTrace();
            }finally{
                try{
                    if(ps!=null)    ps.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
                try{
                    if(con!=null)    con.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }

            
        }else{
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
        if (!request.getParameterMap().containsKey("qid") ||
                !request.getParameterMap().containsKey("up")){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }else{
            SecurityParameters sp = SecurityParameters.getSecurityParameters(request);
            String Email = sp.getEmail();
            if (Email!=null){
                Connection con = Database.connect();
                int qid = Integer.parseInt(request.getParameter("qid"));
                boolean up = Boolean.parseBoolean(request.getParameter("up"));
                if (request.getParameterMap().containsKey("aid")){
                    int aid = Integer.parseInt(request.getParameter("aid"));
                    response.setStatus(
                        VoteQuery.voteAnswer(qid, aid, up, Email)
                    );
                }else{
                    response.setStatus(
                        VoteQuery.voteQuestion(qid, up, Email)
                    );
                }
            }else{
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
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
