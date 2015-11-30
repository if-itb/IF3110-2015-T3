/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.json.simple.JSONObject;
import service.StackExchange;
import service.StackExchange_Service;
import service.User;

/**
 *
 * @author visat
 */
public class VoteServlet extends HttpServlet {
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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonResponse = new JSONObject();
            String questionId = request.getParameter("id");
            String type = request.getParameter("type");
            String action = request.getParameter("action");
            User user = (User) request.getAttribute("user");
            if (questionId != null && type != null && action != null && user != null) {
                try {
                    int id = Integer.parseInt(questionId);
                    type = type.toLowerCase();
                    action = action.toLowerCase();
                    StackExchange port = service.getStackExchangePort();
                    boolean success = false;
                    if (type.equals("question")) {
                        if (action.equals("up"))
                            success = port.voteQuestionUp(user.getId(), id);
                        else if (action.equals("down"))
                            success = port.voteQuestionDown(user.getId(), id);
                        if (success)
                            jsonResponse.put("votes", port.getQuestion(id).getVotes());
                    }
                    else if (type.equals("answer")) {
                        if (action.equals("up"))
                            success = port.voteAnswerUp(user.getId(), id);
                        else if (action.equals("down"))
                            success = port.voteAnswerDown(user.getId(), id);
                        if (success)
                            jsonResponse.put("votes", port.getAnswer(id).getVotes());
                    }
                }
                catch (NumberFormatException ex) {
                }
            }
            out.write(jsonResponse.toString());
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
        return "Handles voting system";
    }// </editor-fold>

}
