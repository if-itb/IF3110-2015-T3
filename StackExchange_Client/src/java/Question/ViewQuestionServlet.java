/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import model.question.Question;
import model.question.QuestionWS_Service;
import model.answer.Answer;
import model.answer.AnswerWS_Service;
import model.user.User;
import model.user.UserWS_Service;

/**
 *
 * @author Venny
 */
@WebServlet(name = "ViewQuestionServlet", urlPatterns = {"/view"})
public class ViewQuestionServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/QuestionWS.wsdl")
    private QuestionWS_Service q_service;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/AnswerWS.wsdl")
    private AnswerWS_Service a_service;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/UserWS.wsdl")
    private UserWS_Service u_service;

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
        Cookie cookies[] = request.getCookies();

        String token_id = "";
        boolean found = false;
        int i=0;
        int uid=-1;
        while (i<cookies.length && !found) {
            if ("stackexchange_token".equals(cookies[i].getName())) {
                token_id = cookies[i].getValue();
                found = true;
            } else {
                i++;
            }
        }
        
        if (found) {
            org.json.simple.JSONObject jo = ConnectionIS.ConnectionIS.requestAuth(token_id);
            uid = (int)(long) jo.get("id");
            int status = (int) (long) jo.get("status");

            if (status!=1) {
                uid = -1;
            } else {
                String uname = getUserByID(uid).getName();
                request.setAttribute("uname",uname);
            }
        }
        Question q = getQuestionByID(Integer.parseInt(request.getParameter("id")));
        model.user.User q_user = getUserByID(q.getUserId());
        java.util.List<Answer> answerList = getAnswersByQID(Integer.parseInt(request.getParameter("id")));
        java.util.Map<Integer, User> userMap = new java.util.HashMap<>();
        
        for (Answer a: answerList){
            userMap.put(a.getAnswerId(), getUserByID(a.getUserId()));
        }
        
        request.setAttribute("uid",uid);
        request.setAttribute("question",q);
        request.setAttribute("answers",answerList);
        request.setAttribute("q_user", q_user);
        request.setAttribute("a_user", userMap);
                
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view.jsp");
        dispatcher.forward(request,response);
    }


    private Question getQuestionByID(int questionId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.question.QuestionWS port = q_service.getQuestionWSPort();
        return port.getQuestionByID(questionId);
    }
    
    private java.util.List<Answer> getAnswersByQID(int i) {
        model.answer.AnswerWS port = a_service.getAnswerWSPort();
        return port.getAnswersByQID(i);
    }

    private User getUserByID(int userId) {
        model.user.UserWS port = u_service.getUserWSPort();
        return port.getUserByID(userId);
    }
}
