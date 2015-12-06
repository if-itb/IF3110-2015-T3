/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import TokenOperator.Token;
import TokenOperator.TokenAdapter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aidin
 */
@WebServlet(urlPatterns = {"/HandlerVC"})
public class HandlerVC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private String clearAgent(String agent){
      return agent.replace(" ","");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tokenStr = request.getParameter("token");

        String userAgent = clearAgent(request.getParameter("user-agent"));

        String userIP = request.getParameter("ip");

        TokenExtractor TokExt= new TokenExtractor();
        
        String[] tokenData = TokExt.getParams(tokenStr, 2);
        String tokenAgent = tokenData[0];
        String tokenIP = tokenData[1];

        if(!userIP.equals(tokenIP)){
            response.sendError(402, "IP is different ");
        } else {
            if(!userAgent.equals(tokenAgent)){
                response.sendError(402, "User-agent is different");
            } else{
                Token token= new Token();
                TokenAdapter tadb = new TokenAdapter();

                try {
                    token = tadb.getTokenToken(tokenStr);
                    if((token.getEmail()).isEmpty()){//token tidak ditemukan
                        response.sendError(404, "token is invalid");
                    } else {//token ditemukan

                        Date current = new Date();
                        Date expired = new Date((token.getExpired()).getTime());
                        if(current.after(expired)){//token kadaluarsa
                            response.sendError(404, "token is expired");
                        } else {//token masih available
                            response.setContentType("application/xml");
                            PrintWriter printWriter;
                            try {
                                printWriter = response.getWriter();
                                printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                                printWriter.println("<root>");
                                printWriter.print("<email>");
                                printWriter.print(token.getEmail());
                                printWriter.println("</email>");
                                printWriter.println("</root>");
                                printWriter.flush();
                            } catch (IOException e) {

                            }
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
                }
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
