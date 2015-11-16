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


@WebServlet(name = "editQuestion", urlPatterns = {"/editQuestion"})
public class EditQuestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        Cookie cookies[] = request.getCookies();
        String token = null;
        Long expirationDate = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName() == "expirationDate") {
                expirationDate = Long.parseLong(cookie.getValue());
            }
            if (cookie.getName() == "token") {
                token = cookie.getValue();
            }
        }
        if (token != null && expirationDate != null) {
        
            String status = editQuestion(
                                Integer.parseInt(request.getParameter("idEdited")),
                                request.getParameter("name"),
                                request.getParameter("email"),
                                request.getParameter("qtopic"),
                                request.getParameter("qcontent"),
                                token,
                                expirationDate
                            );

            if("1".equals(request.getParameter("fromDetail"))){
                response.sendRedirect("detail?idDetail="+ request.getParameter("idEdited"));
            }
            else {
                response.sendRedirect("Home");
            }
        }
        else {
            response.sendRedirect("Home");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(EditQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(EditQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private String editQuestion(
            int qid, String name, 
            String email, String qtopic, 
            String qcontent, String token, 
            long expirationDate) throws Exception_Exception {
        
        service.StackExchangeService port = service.getStackExchangeServicePort();
        return port.editQuestion(qid, name, email, qtopic, qcontent, token, expirationDate);
    }

}
