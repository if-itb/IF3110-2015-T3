/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import stackexchangews.Answer;
import stackexchangews.Question;
import stackexchangews.StackExchangeWS_Service;

/**
 *
 * @author Calvin
 */
@WebServlet(name = "QuestionPageServlet", urlPatterns = {"/questionpage.jsp"})
public class QuestionPageServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchangeWS/StackExchangeWS.wsdl")
    private StackExchangeWS_Service service;

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
        int qid = Integer.parseInt(request.getParameter("qid"));
        
        Question question = getQuestion(qid);
        String qname = getNameById(question.getUserId());
        
        List<Answer> answers = getAnswer(qid);
        ArrayList<String> anames = new ArrayList<String>();
        
        for (Answer a : answers) {
            anames.add(getNameById(a.getUserId()));
        }
        
        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        request.setAttribute("qname", qname);
        request.setAttribute("anames", anames);
        request.getRequestDispatcher("/answer.jsp").forward(request, response);
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

    private java.util.List<stackexchangews.Answer> getAnswer(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        stackexchangews.StackExchangeWS port = service.getStackExchangeWSPort();
        return port.getAnswer(qid);
    }

    private Question getQuestion(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        stackexchangews.StackExchangeWS port = service.getStackExchangeWSPort();
        return port.getQuestion(qid);
    }

    private String getNameById(int userId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        stackexchangews.StackExchangeWS port = service.getStackExchangeWSPort();
        return port.getNameById(userId);
    }
}
