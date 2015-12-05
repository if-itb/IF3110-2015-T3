/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stackexchange.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import model.Question;
import org.stackexchange.webservice.service.QuestionWS;
import org.stackexchange.webservice.service.QuestionWS_Service;
import org.stackexchange.webservice.service.TokenService;

/**
 *
 * @author X450
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/IndexServlet"})
public class IndexServlet extends HttpServlet {
    //@WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/stack_exchange_web_services/QuestionWS.wsdl")
    private QuestionWS_Service service = new QuestionWS_Service();
    
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet IndexServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IndexServlet at " + request.getContextPath() + "</h1>");
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
        String token = request.getParameter("token");
        TokenService ts = new TokenService();
        int user_id = ts.getUserId(token);
        String json = getAll();  
        Gson gson = new Gson();
//        Question question = gson.fromJson(json, Question.class);
        Type listType = new TypeToken<List<Question>>() {}.getType();
        List<Question> questionListFromJson = gson.fromJson(json, listType);
        for (int i =0;i<questionListFromJson.size();i++){
            questionListFromJson.get(i).setName(ts.getUserInfo(questionListFromJson.get(i).getUserId()).get("name"));
        }
        
        request.setAttribute("QList",questionListFromJson);
        request.setAttribute("token",token);
        request.setAttribute("user_id",user_id);
        request.getRequestDispatcher("index.jsp").forward(request, response);
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
        request.getRequestDispatcher("index.jsp").forward(request, response);
        //processRequest(request, response);
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

    private String getAll() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.stackexchange.webservice.service.QuestionWS port = service.getQuestionWSPort();
        return port.getAll();
    }

    private String getById(long id) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        org.stackexchange.webservice.service.QuestionWS port = service.getQuestionWSPort();
        return port.getById(id);
    }

}
