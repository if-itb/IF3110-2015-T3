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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nim_13512501
 */
@WebServlet(urlPatterns = {"/Comment"})
public class Comment extends HttpServlet {

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
            out.println("<title>Servlet Comment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Comment at " + request.getContextPath() + "</h1>");
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
        if (!request.getParameterMap().containsKey("qid")){
            response.getWriter().println("UNSUPPLIED PARAMETER");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        
        int qid = Integer.parseInt(request.getParameter("qid"));
            
            Connection con = Database.connect();

        int vote;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String query = "SELECT cid,Email,AuthorName,qcomment,created_at FROM QuestionComments NATURAL JOIN UAccount WHERE qid=?;";
            ps = con.prepareStatement(query);
            ps.setInt(1, qid);
            rs = ps.executeQuery();
            response.setContentType("application/xml");
            PrintWriter wr = response.getWriter();
            wr.println("<list>");
            while(rs.next()){
                int cid = rs.getInt("cid");
                String Email = rs.getString("Email");
                String AuthorName = rs.getString("AuthorName");
                String qcomment = rs.getString("qcomment");
                String created_at = rs.getString("created_at");
                wr.println("\t<comment" +
                        " cid=\""+cid+"\"" +
                        " Email=\""+Email+"\"" +
                        " AuthorName=\""+AuthorName+"\"" +
                        " created_at=\""+created_at+"\"" +
                        " >" +
                        xmlEscapeText(qcomment) +
                        "</comment>");
            }
            wr.println("</list>");
            wr.flush();
            rs.close();
        } catch (SQLException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
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
        if (!request.getParameterMap().containsKey("access_token")){
            response.getWriter().println("UNSUPPLIED ACCESS TOKEN");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
         if (!request.getParameterMap().containsKey("qid") ||
                !request.getParameterMap().containsKey("qcomment")){
            response.getWriter().println("UNSUPPLIED PARAMETER");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
         } 

        SecurityParameters sp = SecurityParameters.getSecurityParameters(request);    
        String Email = sp.getEmail();
            if (Email!=null){
                Connection con = null;
                PreparedStatement ps = null;
             try {
                 con = Database.connect();
                 int qid = Integer.parseInt(request.getParameter("qid"));
                 String qcomment = request.getParameter("qcomment");
                 String query = "INSERT INTO QuestionComments (qid, Email, qcomment) VALUES (?,?,?);";
                 ps = con.prepareStatement(query);
                 ps.setInt(1, qid);
                 ps.setString(2, Email);
                 ps.setString(3, qcomment);
                 ps.executeUpdate();
                 response.setStatus(HttpServletResponse.SC_CREATED);
             } catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException ex){
                 response.getWriter().println("question not found");
                 response.setStatus(HttpServletResponse.SC_NOT_FOUND);
             }catch (SQLException ex) {
                 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                 Logger.getLogger(Comment.class.getName()).log(Level.SEVERE, null, ex);
             } finally{
                 if (ps!=null)
                     try{
                         ps.close();
                     }catch(SQLException ex){
                         ex.printStackTrace();
                     }
                 if (con!=null)
                     try{
                         con.close();
                     }catch(SQLException ex){
                         ex.printStackTrace();
                     }
             }
                
                
                
            }else{
                response.getWriter().print(sp.getEmailError());
                System.out.println(sp.getEmailError());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
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
    
    private String xmlEscapeText(String t) {
   StringBuilder sb = new StringBuilder();
   for(int i = 0; i < t.length(); i++){
      char c = t.charAt(i);
      switch(c){
      case '<': sb.append("&lt;"); break;
      case '>': sb.append("&gt;"); break;
      case '\"': sb.append("&quot;"); break;
      case '&': sb.append("&amp;"); break;
      case '\'': sb.append("&apos;"); break;
      default:
         if(c>0x7e) {
            sb.append("&#"+((int)c)+";");
         }else
            sb.append(c);
      }
   }
   return sb.toString();
}

}
