/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comment;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.DB;
import java.sql.Connection;
import org.json.simple.JSONArray;


@WebServlet(urlPatterns = {"/Comment"})
public class Comment extends HttpServlet {
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
        response.setContentType("application/json");
        JSONArray ret = new JSONArray();
        try (PrintWriter out = response.getWriter()) {
           String qid = request.getParameter("qid");
           try{
               Statement stt = conn.createStatement();
               String sql;
               sql = "select comment.id, id_question, content, name from "+"(select * from comment_question where id_question = ?) as comment"+"left join"+"(select id,name from user) as user"+"on comment.id_user = user.id";
               PreparedStatement dbStatement = conn.prepareStatement(sql);
               dbStatement.setString(1,qid);
               ResultSet res = dbStatement.executeQuery();
               if(res.next()){
                   do{
                       JSONObject object = new JSONObject();
                       object.put("id",res.getInt("id"));
                       object.put("q_id",res.getInt("id_question"));
                       object.put("user",res.getString("name"));
                       object.put("content",res.getInt("content"));
                       ret.add(object);
                   } while(res.next());
                   out.print(ret);
               }
               else{
                   JSONObject object = new JSONObject();
                   object.put("error");
                   ret.add(object);
                   out.print(ret);
               }
               stt.close();
           }
           catch (SQLException exc){
               JSONObject object = new JSONObject();
               object.put("error",exc);
               ret.add(object);
               out.print(ret);
           }
        }
    }
    
    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("application/json");
    JSONObject object = new JSONObject();
    try (PrintWriter out = response.getWriter()) {
      String qid = request.getParameter("qid");
      String uid = request.getParameter("uid");
      String content = request.getParameter("content");
      String token = request.getParameter("token");
      String userIP = request.getParameter("userIP");
      String userAgent = request.getParameter("userAgent");
      
      Auth auth = new Auth();
      int ret = auth.check(token,userIP,userAgent);
      
      if ( ret==1){
        try {      
          Statement stt = conn.createStatement();
          String sql;
          sql = "insert into comment_question (id_question, id_user, content) values (?, ?, ?)";
          PreparedStatement dbStatement = conn.prepareStatement(sql);
          dbStatement.setInt(1, Integer.valueOf(qid));
          dbStatement.setInt(2, Integer.valueOf(uid));
          dbStatement.setString(3, content);
          dbStatement.executeUpdate();
          stt.close();
          object.put("error","success"); 
          out.print(object);       
        }
        catch (SQLException exc){
            object.put("error", exc);  
            out.print(object);        
        }      
      }
      else{
        object.put("error", "validation");  
        out.print(object);        
      }
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
