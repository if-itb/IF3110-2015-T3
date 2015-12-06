/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import user.Registereduser;
import user.UserWS_Service;
/**
 *
 * @author mfikria
 */
@WebServlet(name = "QuestionList", urlPatterns = {""})
public class QuestionList extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SimpleStackExchange_WebService/Question_WS.wsdl")
    private QuestionWS_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SimpleStackExchange_WebService/User_WS.wsdl")
    private UserWS_Service service;


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
        List<question.Question> que = listQuestion();
        ArrayList<Pair<question.Question, String> > ques;
        ques = new ArrayList<Pair<question.Question, String>>();
        for(question.Question q : que) {
            user.Registereduser ru = getUserById(q.getUid());
            if(ru == null)
                ques.add(new Pair(q, "Deleted User"));
            else
                ques.add(new Pair(q, ru.getName()));
        }
        try {
            int statustoken = Integer.parseInt(request.getHeader("statustoken"));
            request.setAttribute("statustoken", statustoken);
        } catch (Exception e){
            
        }
        request.setAttribute("questions", ques);
        request.getRequestDispatcher("home.jsp").forward(request, response);
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

    private Registereduser getUserById(int uid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        user.UserWS port = service.getUserWSPort();
        return port.getUserById(uid);
    }

    private java.util.List<question.Question> listQuestion() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionWS port = service_1.getQuestionWSPort();
        return port.listQuestion();
    }



}
