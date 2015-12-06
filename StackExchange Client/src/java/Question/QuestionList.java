/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import QuestionWS.QuestionWS_Service;
import UserWS.User;
import UserWS.UserWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Asus
 */
public class QuestionList extends HttpServlet {
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
    
    List<QuestionWS.Question> questions = getAllQuestion();
    
    HashMap answer_counts = new HashMap();
    HashMap question_asker = new HashMap();
    HashMap questions_vote_counts = new HashMap();
    
    for ( QuestionWS.Question question: questions )
    {
        questions_vote_counts.put(new Integer(question.getId()), new Integer(getVoteCountByQId(question.getId())));
        answer_counts.put(new Integer(question.getId()), new Integer(getAnswerCount(question.getId())));
        question_asker.put(new Integer(question.getId()), (getUserById(question.getIdUser())).getName());
    }
    
    request.setAttribute("questions", questions);
    request.setAttribute("questions_vote_counts", questions_vote_counts);
    request.setAttribute("answer_counts", answer_counts);
    request.setAttribute("question_asker", question_asker);
    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp"); 
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

  private java.util.List<QuestionWS.Question> getAllQuestion() {
    // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
    // If the calling of port operations may lead to race condition some synchronization is required.
    QuestionWS.QuestionWS port = service.getQuestionWSPort();
    return port.getAllQuestion();
  }

  private int getAnswerCount(int qid) {
    // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
    // If the calling of port operations may lead to race condition some synchronization is required.
    QuestionWS.QuestionWS port = service.getQuestionWSPort();
    return port.getAnswerCount(qid);
  }

  private User getUserById(int id) {
    // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
    // If the calling of port operations may lead to race condition some synchronization is required.
    UserWS.UserWS port = service_1.getUserWSPort();
    return port.getUserById(id);
  }
  
    private int getVoteCountByQId(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service.getQuestionWSPort();
        return port.getVoteCountByQId(qid);
    }


}
