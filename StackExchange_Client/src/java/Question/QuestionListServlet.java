/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import javax.servlet.RequestDispatcher;
import model.answer.AnswerWS_Service;
import model.question.QuestionWS_Service;
import model.user.User;
import model.user.UserWS_Service;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;

/**
 *
 * @author ASUS X202E
 */
@WebServlet (name = "QuestionListServlet", urlPatterns = {"","/index"})
public class QuestionListServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/QuestionWS.wsdl")
    private QuestionWS_Service service;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/AnswerWS.wsdl")
    private AnswerWS_Service service_2;
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/UserWS.wsdl")
    private UserWS_Service service_1;

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

        

        java.util.List<model.question.Question> questionList = getAllQuestions();
        java.util.Map<Integer,User> userMap = new java.util.HashMap<>();
        java.util.Map<Integer,Integer> answerMap = new java.util.HashMap<>();
        for (model.question.Question q : questionList) {
            userMap.put(q.getQuestionId(),getUserByID(q.getUserId()));
            answerMap.put(q.getQuestionId(),getAnswerCount(q.getQuestionId()));
        }

        request.setAttribute("uid",uid);
        request.setAttribute("questions",questionList);
        request.setAttribute("users",userMap);
        request.setAttribute("answers",answerMap);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request,response);
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



    private User getUserByID(int userId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.user.UserWS port = service_1.getUserWSPort();
        return port.getUserByID(userId);
    }

    private int getAnswerCount(int questionId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.answer.AnswerWS port = service_2.getAnswerWSPort();
        return port.getAnswerCount(questionId);
    }

    private java.util.List<model.question.Question> getAllQuestions() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.question.QuestionWS port = service.getQuestionWSPort();
        return port.getAllQuestions();
    }

    
    

 


}
