/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import service.Question;
import service.StackExchange;
import service.StackExchange_Service;
import service.User;

/**
 *
 * @author Adz
 */
public class SearchServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange.wsdl")
    private StackExchange_Service service;
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
        StackExchange port = service.getStackExchangePort();

        String key = request.getParameter("key");
        List<Question> questions = port.search(key);
        Map<Integer, Integer> answers = new HashMap<>();
        Map<Integer, User> askers = new HashMap<>();
        if(questions.size() > 0){
            for (Question question: questions) {
                answers.put(question.getId(), port.getAnswers(question.getId()).size());
                askers.put(question.getId(), port.getUser(question.getIdUser()));
            }
            request.setAttribute("questions", questions);
            request.setAttribute("answers", answers);
            request.setAttribute("askers", askers);
            request.setAttribute("heading", "Search Result");
        }
        else{
            request.setAttribute("heading", "Search Result - Not Found");
        }
        request.getRequestDispatcher("WEB-INF/view/index.jsp").forward(request, response);
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
