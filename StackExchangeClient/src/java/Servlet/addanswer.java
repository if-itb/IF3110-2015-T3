package Servlet;

import answermodel.AnswerWS_Service;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

public class addanswer extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_15534/StackExchangeService/AnswerWS.wsdl")
    private AnswerWS_Service service;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idQuestion = Integer.parseInt(request.getParameter("idQuestion"));
        boolean found = false;
        int i=0;
        Cookie[] cookies = null;
        cookies = request.getCookies();
        Boolean token_expired = false;
        if (cookies != null) {
            while (!found && i < cookies.length){
                if (cookies[i].getName().equals("tokenCookie")) {
                    String token = cookies[i].getValue();
                    String content = request.getParameter("content");
                    if (createAnswer(idQuestion,content,token)) {
                        token_expired = true;
                    }
                    found = true;
                }
                i++;
            }
        }
        if (token_expired) {
            int count = 0;
            i = 0;
            while (count<2 && i<cookies.length){
                if (cookies[i].getName().equals("usernameCookie") || cookies[i].getName().equals("tokenCookie")) {
                    cookies[i].setMaxAge(0);
                    cookies[i].setPath("/");
                    response.addCookie(cookies[i]);
                    count++;
                }
                i++;
            }
            response.sendRedirect("index");
        }
        else
            response.sendRedirect("viewpost?id="+idQuestion);
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

    private Boolean createAnswer(int idQuestion, java.lang.String content, java.lang.String token) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        answermodel.AnswerWS port = service.getAnswerWSPort();
        return port.createAnswer(idQuestion, content, token);
    }

}
