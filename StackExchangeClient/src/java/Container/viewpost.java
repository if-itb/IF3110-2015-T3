/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Container;

import ClientValidate.ClientValidate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import question.Question;
import question.QuestionsWS_Service;
import answer.AnswersWS_Service;
import javax.servlet.http.Cookie;
import user.User;
import user.UserWS_Service;
/**
 *
 * @author Bimo
 */
public class viewpost extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchangeWS/UserWS.wsdl")
    private UserWS_Service service_2;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchangeWS/AnswersWS.wsdl")
    private AnswersWS_Service service_1;

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
       
        Cookie[] cookies = request.getCookies();
        String useragent = request.getHeader("User-Agent");         // Ambil user agent dari client
        String ipAddress = request.getHeader("X-FORWARDED-FOR");    // ** Ambil IP Address Client
        if (ipAddress == null)
           ipAddress = request.getRemoteAddr();  
        
        String token = ClientValidate.tokenExtract(ipAddress, useragent, cookies);    
        if (token != null) {
            User user = getUserByToken(token);
            request.setAttribute("name", user.getName());
        }
        
       String paramqid = request.getParameter("qid");
       request.setAttribute("qid", Integer.parseInt(paramqid));       
       
       java.util.List<answer.Answer> answers = getAnswersByQid(Integer.parseInt(paramqid));
       request.setAttribute("answers", answers);
       
       question.Question result = getQuestionById(Integer.parseInt(paramqid)); 
       request.setAttribute("result", result); 
       request.setAttribute("asker", getUser(result.getQuestionUid()).getName()); 

       HashMap<Integer, String> hmap = new HashMap<>();
       HashMap<Integer, Integer> ansvotemap = new HashMap<>();

       for (int i = 0;i<answers.size();i++){
           hmap.put(answers.get(i).getAnswerId(), getUser(answers.get(i).getAnswerUid()).getName());
           ansvotemap.put(answers.get(i).getAnswerId(), getanswervote(answers.get(i).getAnswerId()));
       }
       
       request.setAttribute("hmap", hmap);
       request.setAttribute("ansvotemap", ansvotemap);

       User username = getUser(result.getQuestionUid());
       request.setAttribute("username", username); 

       int questionvote = getquestionvote(Integer.parseInt(paramqid));
       request.setAttribute("questionvote", questionvote); 
       
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/viewpost.jsp"); 
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

    private java.util.List<answer.Answer> getAnswersByQid(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        answer.AnswersWS port = service_1.getAnswersWSPort();
        return port.getAnswersByQid(qid);
    }

    private Question getQuestionById(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionsWS port = service.getQuestionsWSPort();
        return port.getQuestionById(qid);
    }

    private int getquestionvote(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionsWS port = service.getQuestionsWSPort();
        return port.getquestionvote(qid);
    }

    private User getUser(int userId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        user.UserWS port = service_2.getUserWSPort();
        return port.getUser(userId);
    }

    private int getanswervote(int aid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        answer.AnswersWS port = service_1.getAnswersWSPort();
        return port.getanswervote(aid);
    }

    private User getUserByToken(java.lang.String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        user.UserWS port = service_2.getUserWSPort();
        return port.getUserByToken(token);
    }
    
    
    
}