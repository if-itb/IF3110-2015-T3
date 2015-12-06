/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stackexchange.commentvote;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import stackexchange.webservice.auth.Auth;
import stackexchange.webservice.util.CVote;
import stackexchange.webservice.util.Database;

/**
 *
 * @author fauzanrifqy
 */
public class Vote extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            response.setContentType("application/json");
            //Get Data
            String email = request.getParameter("email");
            String token = request.getParameter("token");
            String type = request.getParameter("type");
            String qid = request.getParameter("qid");
            String id = request.getParameter("id");
            String inc = request.getParameter("inc");
            Timestamp currentTime = new Timestamp(Calendar.getInstance().getTime().getTime());
            
            //Periksa Token
            Auth auth = new Auth();
            int result = auth.check(email, token);
            JSONObject json = new JSONObject();
            
            if(result == 1 || result == 0){
                if(type.equals("question")){
                    CVote cvote = new CVote();
                    if(cvote.voting(Integer.parseInt(id),email,inc.equals("up"))){
                        //Akses database menggunakan query DB
                        Database db = new Database();
                        Connection conn = db.getConnection();
                        String val;
                        if (inc.equals("up")) {
                            val = "+";
                        }else{
                            val = "-";
                        }
                        String values="";
                        values+= "vote = vote "+ val +" 1";
                        String sql="update questions set " + values + " where id=" + id;
                        try{
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.executeUpdate();
                            
                            //Get Votes
                            sql = "select * from questions where id=" + id;
                            
                            ps = conn.prepareStatement(sql);
                            ResultSet rs = ps.executeQuery();
                            if(rs.next()){
                                json.put("newVote", rs.getInt("vote"));
                                json.put("status", "success");
                            }
                            
                        }catch(Exception e){
                            json.put("status", "update database failure");
                        }finally{
                            db.closeConnection();
                            db = null;
                        }
                    }else{
                        json.put("status", "user already vote");
                    }
                    
                }else if(type.equals("answer")){
                    CVote cvote = new CVote();
                    if(cvote.voting(Integer.parseInt(id), Integer.parseInt(qid), email, inc.equals("up"))){
                        //Akses database menggunakan query DB
                        Database db = new Database();
                        Connection conn = db.getConnection();
                        String val;
                        if (inc.equals("up")) {
                            val = "+";
                        }else{
                            val = "-";
                        }
                        String values="";
                        values+= "vote = vote "+ val +" 1";
                        String sql="update answers set " + values + " where id=" + id + " and questionId=" + qid;
                        try{
                            PreparedStatement ps = conn.prepareStatement(sql);
                            ps.executeUpdate();
                            
                            //Get Votes
                            sql = "select * from answers where id=" + id + " and questionId=" + qid;
                            
                            ps = conn.prepareStatement(sql);
                            ResultSet rs = ps.executeQuery();
                            if(rs.next()){
                                json.put("newVote", rs.getInt("vote"));
                                json.put("status", "success");
                            }
                            
                        }catch(Exception e){
                            json.put("status", "update database failure");
                        }finally{
                            db.closeConnection();
                            db = null;
                        }
                    }else{
                        json.put("status", "user already vote");
                    }
                        
                }else{
                    json.put("status", "type-unknown");
                }
                out.print(json);
                out.flush();
            }else{
                json.put("status", "denied");
                out.print(json);
                out.flush();
            }
        } finally {
            out.close();
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
