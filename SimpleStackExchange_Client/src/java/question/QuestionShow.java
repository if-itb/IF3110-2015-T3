/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import answer.AnswerWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javafx.util.Pair;
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
@WebServlet(name = "QuestionShow", urlPatterns = {"/question"})
public class QuestionShow extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SimpleStackExchange_WebService/User_WS.wsdl")
    private UserWS_Service service_2;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SimpleStackExchange_WebService/Answer_WS.wsdl")
    private AnswerWS_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SimpleStackExchange_WebService/Question_WS.wsdl")
    private QuestionWS_Service service;

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
        int qid = Integer.parseInt(request.getParameter("qid"));
        question.Question q = getQuestion(qid);
        Pair<question.Question, String> que;
        user.Registereduser ru = getUserById(q.getUid());
        if(ru == null)
            que = new Pair(q, "Invalid User");
        else
            que = new Pair(q, ru.getName());
        request.setAttribute("question", que);
        
        
        List<answer.Answer> ans = getAnswers(qid);
        ArrayList<Pair<question.Question, String> > answers;
        answers = new ArrayList<Pair<question.Question, String>>();
        for(answer.Answer a : ans) {
            user.Registereduser aru = getUserById(a.getUid());
            if(aru == null)
                answers.add(new Pair(a, "Invalid User"));
            else
                answers.add(new Pair(a, aru.getName()));
        }
        try {
            int statustoken = Integer.parseInt(request.getHeader("statustoken"));
            request.setAttribute("statustoken", statustoken);
        } catch (Exception e){
            
        }
        request.setAttribute("answers", answers);
        request.getRequestDispatcher("question.jsp").forward(request, response);
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

    private Question getQuestion(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionWS port = service.getQuestionWSPort();
        return port.getQuestion(qid);
    }

    private java.util.List<answer.Answer> getAnswers(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        answer.AnswerWS port = service_1.getAnswerWSPort();
        return port.getAnswers(qid);
    }

    private Registereduser getUserById(int uid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        user.UserWS port = service_2.getUserWSPort();
        return port.getUserById(uid);
    }



}
