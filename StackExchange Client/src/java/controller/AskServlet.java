/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import connector.ISConnector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import service.*;

/**
 *
 * @author visat
 */
@WebServlet(name = "AskServlet", urlPatterns = {"/ask"})
public class AskServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange.wsdl")
    private StackExchange_Service service;

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
        request.getRequestDispatcher("WEB-INF/view/ask.jsp").forward(request, response);
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
        // check request topic and content
        // reload this page if they are empty
        String
                topic = request.getParameter("topic"),
                content = request.getParameter("content");
        if (topic == null || content == null || topic.isEmpty() || content.isEmpty()) {
            response.sendRedirect(request.getRequestURI());
            return;
        }

        // check access token
        // forward to login page if access token is not set
        User user = (User) request.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        else {
            StackExchange port = service.getStackExchangePort();
            Question question = port.addQuestion(user.getId(), topic, content);
            // redirect to question page if success
            if (question != null) {
                response.sendRedirect(request.getContextPath() + "/question?id=" + question.getId());
            }
            // if failed let the get method handles it
            else {
                request.setAttribute("error", "Failed to create question");
                doGet(request, response);
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
        return "Handle ask request";
    }// </editor-fold>

}
