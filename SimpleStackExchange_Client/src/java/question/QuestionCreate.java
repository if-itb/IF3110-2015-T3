/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package question;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import tool.ConsumerREST;
import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.xml.ws.WebServiceRef;
/**
 *
 * @author mfikria
 */
@WebServlet(name = "QuestionCreate", urlPatterns = {"/QuestionCreate"})
public class QuestionCreate extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SimpleStackExchange_WebService/Question_WS.wsdl")
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
            throws ServletException, IOException{
        
            HttpSession session = request.getSession();
            question.Question q = new question.Question();
            
            String token = new String();
            Cookie[] cookies = request.getCookies();
            if(cookies !=null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("token")) token = cookie.getValue();
                }
            }
            
            
            ConsumerREST r = new ConsumerREST(); // Create object for consumming REST Web service
            // Get data from user and data from session
            q.setUid(tool.Util.getUid(request));
            q.setTopic(request.getParameter("topic"));
            q.setContent(request.getParameter("content"));
        
            // Initialize Created time
            XMLGregorianCalendar date = null;
                    try {
                        date = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
                    } catch (DatatypeConfigurationException ex) {
                        Logger.getLogger(QuestionCreate.class.getName()).log(Level.SEVERE, null, ex);
                    }
            q.setCreatedtime(date);
            
            Integer res = createQuestion(token, q);
            
            // Pass token and object question to web service
            String url = "";
            request.setAttribute("statustoken", res);
            response.sendRedirect(url);
                
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

    private Integer createQuestion(java.lang.String token, question.Question question) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        question.QuestionWS port = service.getQuestionWSPort();
        return port.createQuestion(token, question);
    }

  



}
