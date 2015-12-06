/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import ClientValidate.ClientValidate;
import answer.AnswersWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import question.QuestionsWS_Service;
import user.User;
import user.UserWS_Service;

/**
 *
 * @author mochamadtry
 */
public class home extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchangeWS/AnswersWS.wsdl")
    private AnswersWS_Service service_2;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchangeWS/UserWS.wsdl")
    private UserWS_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchangeWS/QuestionsWS.wsdl")
    private QuestionsWS_Service service;

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
        boolean found = false; 
        int i = 0;     
        Cookie[] cookies;
        cookies = request.getCookies();
        String useragent = request.getHeader("User-Agent").replace(';', '%');// Ambil user agent dari client
        useragent = useragent.replace(',', '$');
        String ipAddress = request.getHeader("X-FORWARDED-FOR");    // ** Ambil IP Address Client
        if (ipAddress == null) ipAddress = request.getRemoteAddr();
        
        // validate the token
        String token = ClientValidate.tokenExtract(cookies);    
        if (token != null) {
            User user = getUserByToken(token);
            request.setAttribute("name", user.getName());
        }
//       if (cookies != null) {
//                while (!found && i < cookies.length){
//                    String[] parts = cookies[i].getValue().split("#");
//                    if (cookies[i].getName().equals("token_cookie") && parts[1].equals(ipAddress) && parts[2].equals(useragent)) {
//                        User user = getUserByToken(parts[0]);
//                        request.setAttribute("name", user.getName());
//                        found = true; 
//                         
//                    }
//                    i++;
//                    
//                }
//            }
        HashMap<Integer, Integer> hmap = new HashMap<>();
        HashMap<Integer, Integer> answermap = new HashMap<>();
        HashMap<Integer, String> askmap = new HashMap<>();

        String keyword = request.getParameter("keyword");
        java.util.List<question.Question> result;
        if (keyword == null){
            result = getAllQuestions(); 
        } else {
            result = searchQuestions(keyword); 
        }
        
        request.setAttribute("result", result); 
            
        for (int j = 0;j<result.size();j++){
            hmap.put(result.get(j).getQuestionId(), getquestionvote(result.get(j).getQuestionId()));
            answermap.put(result.get(j).getQuestionId(), getAnswersByQid(result.get(j).getQuestionId()).size());
            askmap.put(result.get(j).getQuestionId(), getUser(result.get(j).getQuestionUid()).getName());
        }
        request.setAttribute("hmap", hmap); 
        request.setAttribute("answermap", answermap); 
        request.setAttribute("askmap", askmap); 
            
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp"); 
        dispatcher.forward(request, response); 
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
    
    private java.util.List<question.Question> getAllQuestions() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionsWS port = service.getQuestionsWSPort();
        return port.getAllQuestions();
    }

    private User getUserByToken(java.lang.String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        user.UserWS port = service_1.getUserWSPort();
        return port.getUserByToken(token);
    }

    private java.util.List<question.Question> searchQuestions(java.lang.String keyword) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionsWS port = service.getQuestionsWSPort();
        return port.searchQuestions(keyword);
    }

    private java.util.List<answer.Answer> getAnswersByQid(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        answer.AnswersWS port = service_2.getAnswersWSPort();
        return port.getAnswersByQid(qid);
    }

    private User getUser(int userId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        user.UserWS port = service_1.getUserWSPort();
        return port.getUser(userId);
    }

    private int getquestionvote(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionsWS port = service.getQuestionsWSPort();
        return port.getquestionvote(qid);
    }
    
    
    
}
