/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
 * @author Acer
 */
@WebServlet(name = "EditServlet", urlPatterns = {"/edit"})
public class EditServlet extends HttpServlet {
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
        int id = Integer.parseInt(request.getParameter("id"));
        Question question = port.getQuestion(id);
        if (question != null) {
            User user = (User) request.getAttribute("user");
            if (user != null && user.getId() == question.getIdUser()) {
                request.setAttribute("question", question);
                request.getRequestDispatcher("WEB-INF/view/ask.jsp").forward(request, response);
                return;
            }
        }
        response.sendRedirect(request.getContextPath());
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

        int id = Integer.parseInt(request.getParameter("id"));
        String
                topic = request.getParameter("topic"),
                content = request.getParameter("content");
        if (topic == null || content == null || topic.isEmpty() || content.isEmpty()) {
            response.sendRedirect(request.getRequestURI());
            return;
        }

        User user = (User) request.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        else {
            StackExchange port = service.getStackExchangePort();
            Question question = port.getQuestion(id);
            if (user.getId() == question.getIdUser()) {
                boolean flag = port.updateQuestion(id, topic, content);
                // redirect to question page if success
                if (flag) {
                    response.sendRedirect(request.getContextPath() + "/question?id=" + question.getId());
                }
                // if failed let the get method handles it
                else {
                    request.setAttribute("error", "Failed to edit question");
                    doGet(request, response);
                }
            }
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

}
