/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import AnswerWS.AnswerWS_Service;
import QuestionWS.Question;
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
public class QuestionView extends HttpServlet {
  @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/UserWS.wsdl")
  private UserWS_Service service_2;
  @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/AnswerWS.wsdl")
  private AnswerWS_Service service_1;
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
        
    QuestionWS.Question question = getQuesstionById(Integer.parseInt(request.getParameter("id")));
    int questionVoteCount = new Integer(getVoteCountByQId(question.getId()));
    java.util.List<AnswerWS.Answer> answers = getAnswerByQID(Integer.parseInt(request.getParameter("id")));
    
    HashMap answers_vote_counts = new HashMap();
    HashMap answers_answerer = new HashMap();
    
    for ( AnswerWS.Answer answer: answers )
    {
        answers_answerer.put(new Integer(answer.getId()), getUserById(new Integer(answer.getIdUser())));
        answers_vote_counts.put(new Integer(answer.getId()), new Integer(getVoteCountByAId(answer.getId())));
    }
    
    request.setAttribute("question", question);
    request.setAttribute("question_vote_count", questionVoteCount);
    request.setAttribute("answers", answers);
    request.setAttribute("answer_count", new Integer(getAnswerCount(question.getId())));
    request.setAttribute("answers_vote_counts", answers_vote_counts);
    request.setAttribute("answers_answerer", answers_answerer);
    request.setAttribute("question_asker", (getUserById(question.getIdUser())).getName());
    RequestDispatcher dispatcher = request.getRequestDispatcher("/question.jsp"); 
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

  private Question getQuesstionById(int id) {
    // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
    // If the calling of port operations may lead to race condition some synchronization is required.
    QuestionWS.QuestionWS port = service.getQuestionWSPort();
    return port.getQuestionById(id);
  }

  private java.util.List<AnswerWS.Answer> getAnswerByQID(int qid) {
    // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
    // If the calling of port operations may lead to race condition some synchronization is required.
    AnswerWS.AnswerWS port = service_1.getAnswerWSPort();
    return port.getAnswerByQID(qid);
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
    UserWS.UserWS port = service_2.getUserWSPort();
    return port.getUserById(id);
  }

    private int getVoteCountByQId(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service.getQuestionWSPort();
        return port.getVoteCountByQId(qid);
    }

    private int getVoteCountByAId(int aid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        AnswerWS.AnswerWS port = service_1.getAnswerWSPort();
        //return port.getVoteCountByAId(aid);
        return 0;
    }

}
