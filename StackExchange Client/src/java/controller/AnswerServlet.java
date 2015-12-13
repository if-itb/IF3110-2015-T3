/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import answermodel.AnswerWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import questionmodel.Question;
import questionmodel.QuestionWS_Service;
import usermodel.UserWS_Service;

/**
 *
 * @author adek
 */
public class AnswerServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/AnswerWS.wsdl")
    private AnswerWS_Service service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/UserWS.wsdl")
    private UserWS_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/QuestionWS.wsdl")
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
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
        questionmodel.QuestionWS port = service.getQuestionWSPort();
        answermodel.AnswerWS port2 = service_2.getAnswerWSPort();
        usermodel.UserWS port3 = service_1.getUserWSPort();
        String Id = request.getParameter("id");
        Question question = null;
        if (Id != null) {
            try {                
                question = port.getQuestionByID(Integer.parseInt(Id));
            }
            catch (NumberFormatException ex) {                
            }
        }                
        if (question != null) {
            //User user = (User) request.getAttribute("user");
            request.setAttribute("question", question);
            //request.setAttribute("asker", port.getUser(question.getIdUser()));
            java.util.List<answermodel.Answer> answers = port2.getAnswerByQID(question.getId());
            Map<Integer, String> answerers = new HashMap<>();
            Map<Integer, Integer> count_answer = new HashMap();
            count_answer.put(question.getId(), port2.getAnswerByQID(question.getId()).size());
            //Map<Integer, Integer> answerStates = new HashMap<>();
            for (answermodel.Answer answer: answers) {
                answerers.put(answer.getId(), port3.getUserbyID(answer.getIdUser()));  
                //System.out.println(port3.getUserbyID(answer.getIdUser()));
                //answerStates.put(answer.getId(), user == null? 0: port.getAnswerVoteState(user.getId(), answer.getId()));
            }
            //get userid from cookies using web service
            String token = "";
            Cookie[] cookies = request.getCookies();
            if(cookies==null) {      
                System.out.println("COOKIES NULL");
            }
            else {                
                for(Cookie cookie : cookies) {
                    if("token".equals(cookie.getName())) { 
                        token = cookie.getValue();
                        System.out.println(token);
                        break;
                    }   
                }
            }
            int userid = port3.getIDUserbyToken(token);
            request.setAttribute("userid", userid);
            request.setAttribute("asker", port3.getUserbyID(question.getIdUser()));
            request.setAttribute("count_answer", count_answer);
            request.setAttribute("answers", answers);
            request.setAttribute("answerers", answerers);
            //request.setAttribute("question_state", user == null? 0: port.getQuestionVoteState(user.getId(), question.getId()));
            //request.setAttribute("answer_states", answerStates);
            request.getRequestDispatcher("/AnswersPage.jsp").forward(request, response);
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
