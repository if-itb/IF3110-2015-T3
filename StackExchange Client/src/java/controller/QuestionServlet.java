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
import service.*;

/**
 *
 * @author visat
 */
public class QuestionServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange.wsdl")
    private StackExchange_Service service;

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
        String paramId = request.getParameter("id");
        Question question = null;
        if (paramId != null) {
            try {
                question = port.getQuestion(Integer.parseInt(paramId));
            }
            catch (NumberFormatException ex) {
            }
        }
        if (question != null) {
            User user = (User) request.getAttribute("user");
            request.setAttribute("question", question);
            request.setAttribute("asker", port.getUser(question.getIdUser()));
            List<Answer> answers = port.getAnswers(question.getId());
            Map<Integer, User> answerers = new HashMap<>();
            Map<Integer, Integer> answerStates = new HashMap<>();
            for (Answer answer: answers) {
                answerers.put(answer.getId(), port.getUser(answer.getIdUser()));
                answerStates.put(answer.getId(), user == null? 0: port.getAnswerVoteState(user.getId(), answer.getId()));
            }
            request.setAttribute("answers", answers);
            request.setAttribute("answerers", answerers);
            request.setAttribute("question_state", user == null? 0: port.getQuestionVoteState(user.getId(), question.getId()));
            request.setAttribute("answer_states", answerStates);
            request.getRequestDispatcher("WEB-INF/view/question.jsp").forward(request, response);
        }
        else
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
        // get the content from parameter
        String content = request.getParameter("content");
        String paramId = request.getParameter("id");
        boolean paramError = true;
        int id = -1;
        try {
            if (content != null && paramId != null && !content.isEmpty() && !paramId.isEmpty()) {
                paramError = false;
                id = Integer.valueOf(paramId);
            }
        } catch (NumberFormatException ex) {

        }
        if (paramError) {
            response.sendRedirect(request.getRequestURI());
            return;
        }

        // get current signed in user
        User user = (User) request.getAttribute("user");
        if (user == null) {
            // redirect to sign in page
            response.sendRedirect(request.getContextPath() + "/signin");
            return;
        }

        // add answer via web service
        StackExchange port = service.getStackExchangePort();
        Answer answer = port.addAnswer(id, user.getId(), content);
        if (answer == null)
            request.setAttribute("error", "Failed to post answer");

        // get the page
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handle question and answer request";
    }

}
