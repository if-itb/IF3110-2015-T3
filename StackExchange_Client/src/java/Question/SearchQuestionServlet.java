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
import model.answer.AnswerWS_Service;
import model.question.QuestionWS_Service;
import model.user.User;
import model.user.UserWS_Service;

/**
 *
 * @author ASUS X202E
 */
@WebServlet(name = "SearchQuestionServlet", urlPatterns = {"/search"})
public class SearchQuestionServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/QuestionWS.wsdl")
    private QuestionWS_Service service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/AnswerWS.wsdl")
    private AnswerWS_Service service_1;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/UserWS.wsdl")
    private UserWS_Service service;


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
            }
        }
        
        String query = request.getParameter("q");
        java.util.List<model.question.Question> searchResults = searchQuestions(query);
        java.util.Map<Integer,model.user.User> userMap = new java.util.HashMap<>();
        java.util.Map<Integer,Integer> answerMap = new java.util.HashMap<>();
        for (model.question.Question q : searchResults) {
            userMap.put(q.getQuestionId(),getUserByID(q.getUserId()));
            answerMap.put(q.getQuestionId(),getAnswerCount(q.getQuestionId()));
        }
        
        request.setAttribute("uid",uid);
        request.setAttribute("questions",searchResults);
        request.setAttribute("users",userMap);
        request.setAttribute("answers",answerMap);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
        dispatcher.forward(request,response);
    }

    private User getUserByID(int userId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.user.UserWS port = service.getUserWSPort();
        return port.getUserByID(userId);
    }

    private int getAnswerCount(int questionId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.answer.AnswerWS port = service_1.getAnswerWSPort();
        return port.getAnswerCount(questionId);
    }

    private java.util.List<model.question.Question> searchQuestions(java.lang.String arg0) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.question.QuestionWS port = service_2.getQuestionWSPort();
        return port.searchQuestions(arg0);
    }

}
