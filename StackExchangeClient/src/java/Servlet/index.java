package Servlet;

import questionmodel.QuestionWS_Service;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

public class index extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_15534/StackExchangeService/QuestionWS.wsdl")
    private QuestionWS_Service service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Check if already log in
        boolean found = false;
        int i=0;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        if (cookies != null) {
            while (!found && i < cookies.length){
                if (cookies[i].getName().equals("usernameCookie")) {
                    request.setAttribute("username", cookies[i].getValue());
                    found = true;
                }
                i++;
            }
        }
        
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            java.util.List<questionmodel.Question> result = getQuestions();
            request.setAttribute("result", result);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
        else {
            java.util.List<questionmodel.Question> result = searchQuestion(keyword);
            request.setAttribute("result", result);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
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

    private java.util.List<questionmodel.Question> getQuestions() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        questionmodel.QuestionWS port = service.getQuestionWSPort();
        return port.getQuestions();
    }

    private int getAnswerById(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        questionmodel.QuestionWS port = service.getQuestionWSPort();
        return port.getAnswerById(qid);
    }

    private java.util.List<questionmodel.Question> searchQuestion(java.lang.String keyword) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        questionmodel.QuestionWS port = service.getQuestionWSPort();
        return port.searchQuestion(keyword);
    }

}
