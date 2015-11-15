/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import service.Answer;
import service.Exception_Exception;
import service.Question;
import service.StackExchangeService_Service;

/**
 *
 * @author sorlawan
 */
@WebServlet(name = "DetailQuestion", urlPatterns = {"/detail"})
public class DetailQuestion extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        int qid = Integer.parseInt(request.getParameter("idDetail"));
        
        Question question = getQuestion(qid);
        List<Answer> answers = getAnswers(qid);
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("    <meta charset='UTF - 8' >");
            out.println("    <title>Stack Exchange</title>");
            out.println("    <link href='https://fonts.googleapis.com/css?family=Josefin+Slab:400,700italic,300' rel='stylesheet' type='text/css'>");
            out.println("    <link rel='stylesheet' href='style.css'>");
            out.println("</head>");

            out.println("<body>");
            out.println("<div class='header'><a href='Home'><h1>Simple StackExhange</h1></a></div>");
            out.println("<div class='container clearfix'>");
            out.println("<div class='containerDetail'>");
            out.println("    <h2>" + question.getQtopic() + "</h2>");
            out.println("    <div class='row rowQuestion clearfix'>");
            out.println("        <div class='colVote'>");
            out.println("            <div class='qVote arrow-up' id='" + question.getQid() + "'></div>");
            out.println("            <span class='qVoteVal'>" + question.getVotes() + "</span>");
            out.println("            <div class='qVote arrow-down' id='" + question.getQid() + "'></div>");
            out.println("        </div>");
            out.println("        <div class='elemQDetail'>");
            out.println("            <p>" + question.getQcontent() + "</p>");
            out.println("            <div class='elemAuthor'>");
            out.println("                <span class='askedBy'>Asked By : </span>");
            out.println("                <div class='author'>");
            out.println("                    <span class='name'>" + question.getName() + " at " + question.getCreatedAt()
            );
            out.println("                        <a href='edit?idEdited="+ question.getQid() +"&fromDetail=1'><span class='edit' >Edit</span></a>");
            out.println("            <a href='delete?idDeleted="+ question.getQid() +"'><span class='delete'>Delete</span></a>");
            out.println("                    </span>");
            out.println("                </div>");
            out.println("            </div>");
            out.println("        </div>");
            out.println("    </div>");
            
            out.println("    <div class='answer'>");
            out.println("        <h2>" + answers.size() + " Answer</h2>");
            out.println("        <div class='row clearfix'>");
            
            for(Answer answer : answers) {
                out.println("            <div class='colVote'>");
                out.println("                <div class='aVote arrow-up' id='" + answer.getAid() + "'></div>");
                out.println("                <span class='voteVal'>" + answer.getVotes() + "</span>");
                out.println("                <div class='aVote arrow-down' id='" + answer.getAid() + "'></div>");
                out.println("            </div>");
                out.println("            <div class='elemQDetail'>");
                out.println("                <div class='elemQuestion elemA'>" + answer.getContent() + "</div>");
                out.println("                <div class='elemAuthor'>");
                out.println("                    <span class='answeredBy'>Answered By :</span>");
                out.println("                    <div class='author'>");
                out.println("                        <span class='name'>" + answer.getName() + " at " + answer.getCreatedAt() + "</span>");
                out.println("                    </div>");
                out.println("                </div>");
                out.println("            </div>");
                
            }
            out.println("</div>");
            out.println("</div>");
            
            out.println("<div class='yourAnswer'>");
            out.println("        <h2 class='yourAnswerTitle'>Your Answer Here</h2>");
            out.println("        <form name='questionForm' action='answer' method='POST'>");
            out.println("            <input type='text'  name='name'  placeholder='Name'/>");
            out.println("            <input type='text'  name='email'  placeholder='Email'/>");
            out.println("            <textarea  name='content' id='content'  placeholder='Content'></textarea>");
            out.println("            <button  id='submitBtn' class='submitBtn' >Answer</button>");
            out.println("            <input type='hidden'  name='qid' value='" + question.getQid() + "'/>");
            out.println("        </form>");
            out.println("    </div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<script src='scripts/detail.js'></script>");
            out.println("</body>");
            out.println("</html>");
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code."
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(DetailQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(DetailQuestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private java.util.List<service.Answer> getAnswers(int qid) throws Exception_Exception {
        service.StackExchangeService port = service.getStackExchangeServicePort();
        return port.getAnswers(qid);
    }

    private Question getQuestion(int qid) throws Exception_Exception {
        service.StackExchangeService port = service.getStackExchangeServicePort();
        return port.getQuestion(qid);
    }

}
