/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import model.QuestionModel;
import service.Exception_Exception;
import service.Question;
import service.StackExchangeService_Service;

/**
 *
 * @author sorlawan
 */
@WebServlet(name = "GetListQuestions", urlPatterns = {"/Home"})
public class GetListQuestions extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchangeService/StackExchangeService.wsdl")
    private StackExchangeService_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws service.Exception_Exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception_Exception {
        response.setContentType("text/html;charset=UTF-8");
        
        
        List<Question> questions = getAllQuestion();
	
	ArrayList<QuestionModel> qs = new ArrayList<>();
	
	for (Question question : questions) {
	    qs.add(new QuestionModel (
		    Integer.toString(question.getQid()),
		    question.getName(),
		    question.getEmail(),
		    question.getQtopic(),
		    question.getQcontent(),
		    Integer.toString(question.getVotes()),
		    Integer.toString(question.getAnswerCount()),
		    question.getCreatedAt()
	    ));
	}
	response.setContentType("text/html");
	request.setAttribute("questions", qs);
	request.getRequestDispatcher("/list.jsp").forward(request, response);
	
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html lang='en'>");
//            out.println("<head>");
//            out.println("    <meta charset='UTF - 8' >");
//            out.println("    <title>Stack Exchange</title>");
//            out.println("    <link href='https://fonts.googleapis.com/css?family=Josefin+Slab:400,700italic,300' rel='stylesheet' type='text/css'>");
//            out.println("    <link rel='stylesheet' href='style.css'>");
//            out.println("</head>");
//            
//            out.println("<body>");
//            out.println("<div class='header'><a href='Home'><h1>Simple StackExhange</h1></a></div>");
//            out.println("<div class='container clearfix'>");
//            out.println("<form class='searchForm clearfix' action='php/Search.php' method='POST'>");
//            out.println("        <div class='searchInput'>");
//            out.println("        <input  name='keyword' type='text' placeholder='Keyword Pencarian'/>");
//            out.println("        <p class='askHere'>Cannot find what you are looking for ? <a href='Create.html'>Ask here</a></p>");
//            out.println("        </div>");
//            out.println("        <button class='searchBtn' type='submit'>Search</button>");
//            out.println("    </form>");
//            out.println("<h4>Recently Answered Questions</h4>");
//            
//            out.println("<div class='table'>");
//            out.println("    <div class='row clearfix'>");
//            for(Question question : questions) {
//                out.println("<div class='elemValue'>");
//                out.println("    <span>" + question.getVotes() + "</span>");
//                out.println("    <span class='vote'>Votes</span>");
//                out.println("</div>");
//
//                out.println("<div class='elemAnswer'>");
//                out.println("    <span>" + question.getAnswerCount() + "</span>");
//                out.println("    <span class='ans'>Answers</span>");
//                out.println("</div>");
//
//                out.println("<div class='elemQ'>");
//                out.println("    <div class='elemQuestion'>");
//                out.println("        <a href='detail?idDetail="+question.getQid()+"'><span class='topic'>" + question.getQtopic() + "</span></a>"
//                );
//                out.println(question.getQcontent());
//                out.println("    </div>");
//
//                out.println("    <div class='elemAuthor'>");
//                out.println("        <span class='askedBy'>Asked By:</span>");
//                out.println("        <div class='author'>");
//                out.println("            <span class='name'>" + question.getName() + "</span>");
//                out.println("            <a href='edit?idEdited="+ question.getQid() +"&fromDetail=0'> <span class='edit'>Edit</span></a>");
//                out.println("            <a href='delete?idDeleted="+ question.getQid() +"'> <span class='delete'>Delete</span></a>");
//                out.println("        </div>");
//                out.println("    </div>");
//                out.println("</div>");
//            }
//            out.println("</div>");
//            out.println("</div>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(GetListQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception_Exception ex) {
            Logger.getLogger(GetListQuestions.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    private List<service.Question> getAllQuestion() throws Exception_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        service.StackExchangeService port = service.getStackExchangeServicePort();
        return port.getAllQuestion();
    }
}
