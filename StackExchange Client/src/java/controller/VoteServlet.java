/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connector.VCConnector;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
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
        String sId = request.getParameter("id");
        String type = request.getParameter("type");

        if (sId == null || type == null)
            return;

        int id;
        try {
            id = Integer.parseInt(sId);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            return;
        }
        
        JSONObject result = new JSONObject();
        switch (type) {
            case "question":
                result.put("votes", service.getStackExchangePort().getQuestion(id).getVotes());
                break;
            case "answer":
                result.put("votes", service.getStackExchangePort().getAnswer(id).getVotes());
                break;
        }

        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            out.println(result.toString());
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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            JSONObject jsonResponse = new JSONObject();
            String questionId = request.getParameter("id");
            String type = request.getParameter("type");
            String action = request.getParameter("action");
            if (questionId != null && type != null && action != null) {
                try {
                    Cookie cookies[] = request.getCookies();
                    String auth = null;
                    for (Cookie cookie: cookies) {
                        if (cookie.getName().equals("auth")) {
                            auth = cookie.getValue();
                            break;
                        }
                    }

                    if (auth != null) {
                        type = type.toLowerCase();
                        action = action.toLowerCase();
                        String userAgent = request.getHeader("User-Agent");
                        String remoteAddr = request.getRemoteAddr();
                        if (userAgent == null) userAgent = "";
                        if (remoteAddr == null) remoteAddr = "";
                        auth = auth + "#" + userAgent + "#" + remoteAddr;
                        jsonResponse = VCConnector.requestVote(auth, questionId, type, action);
                    }
                }
                catch (NumberFormatException ex) {
                }
            }
            out.write(jsonResponse.toString());
        }
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