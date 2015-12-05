/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Class.*;
import javax.servlet.RequestDispatcher;
import org.json.simple.JSONObject;


@WebServlet(name = "ISLogin", urlPatterns = {"/ISLogin"})
public class ISLogin extends HttpServlet {

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
       
        JSONObject ret = new JSONObject();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String user = request.getParameter("lusername");
            String pass = request.getParameter("lpass");
            String ipa = request.getParameter("lipa");
            String browser = request.getParameter("lbrowser");
            Validation V = new Validation() ;
            int status = V.ExistInDB(user,pass);
            Token T = new Token();
            T.ac_Token="";
            T.expire="";
            T.username="";
            T.id = -1;
            if (status!=-1) {
                T.generateToken(user,ipa,browser);
                T.id=status;
                T.username = V.getUsername(status);
                T.saveToken();
            }          
           
            
            ret.put("token", T.ac_Token);
            ret.put("expire",T.expire);
            ret.put("id",T.id);
            ret.put("username",T.username);
            System.out.println("ISLogin username : "+user+" | Pass : "+pass);
            System.out.println("ISLogin token : "+T.ac_Token);
            System.out.println("ISLogin expire : "+T.expire);
            System.out.println("ISLogin id : "+T.id);
            System.out.println("ISLogin username : "+T.username);
            out.print(ret);
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
