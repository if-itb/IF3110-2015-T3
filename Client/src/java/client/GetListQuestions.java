/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import service.Exception_Exception;
import service.Question;
import service.StackExchangeService_Service;

/**
 *
 * @author sorlawan
 */
@WebServlet(name = "GetId", urlPatterns = {"/Lists"})
public class GetListQuestions extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/StackExchangeService/StackExchangeService.wsdl")
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
        
        
        List<Question> questions = getAllQuestion();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of Questions Posted</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>List Questions" + "</h1>");
            out.println("<h1>Hasil : "+"</h1>");
            
            for(Question question : questions) {
               out.println("<p>ID : " + question.getQid()+ "</p>");
                out.println("<p>Name : " + question.getName()+ "</p>");
                out.println("<p>Email : " + question.getEmail() + "</p>");
                out.println("<p>Topic : " + question.getQtopic()+ "</p>");
                out.println("<p>Content : " + question.getQcontent()+ "</p>");
                out.println("<p>Votes : " + question.getVotes()+ "</p>");
                out.println("<p>Answer Count : " + question.getAnswerCount()+ "</p>");
                out.println("<p>Created At : " + question.getCreatedAt() + "</p>");
                out.println("</br>");
                out.println("</br>");
            }
            
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
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(GetListQuestions.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetListQuestions.class.getName()).log(Level.SEVERE, null, ex);
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

    private List<service.Question> getAllQuestion() throws Exception_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        service.StackExchangeService port = service.getStackExchangeServicePort();
        return port.getAllQuestion();
    }
}
