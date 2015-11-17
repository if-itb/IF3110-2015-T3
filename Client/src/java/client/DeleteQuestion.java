/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import service.Exception_Exception;
import service.StackExchangeService_Service;

/**
 *
 * @author sorlawan
 */
@WebServlet(name = "DeleteQuestion", urlPatterns = {"/delete"})
public class DeleteQuestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8082/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws service.Exception_Exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        String idDeleted = request.getParameter("idDeleted");
        
        Cookie cookies[] = request.getCookies();
        String token = null;
        Long expirationDate = null;
        for (Cookie cookie : cookies) {
            if ("expirationDate".equals(cookie.getName())) {
                expirationDate = Long.parseLong(cookie.getValue());
            }
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }
        if (token != null && expirationDate != null) {
            deleteQuestion(Integer.parseInt(idDeleted), token, expirationDate);
        }
        
        // Redirect ke Lists
        response.sendRedirect("Home");
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
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(DeleteQuestion.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(DeleteQuestion.class.getName()).log(Level.SEVERE, null, ex);
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

    private String deleteQuestion(int qid, String token, long expirationDate) throws Exception_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        service.StackExchangeService port = service.getStackExchangeServicePort();
        return port.deleteQuestion(qid, token, expirationDate);
    }

}
