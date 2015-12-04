/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import model.question.Question;
import model.question.QuestionWS_Service;

/**
 *
 * @author ASUS X202E
 */
@WebServlet(name = "EditQuestionServlet", urlPatterns = {"/edit"})
public class EditQuestionServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/QuestionWS.wsdl")
    private QuestionWS_Service service;

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
            }
        }
        Question q = getQuestionByID(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("uid",uid);
        request.setAttribute("question",q);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        dispatcher.forward(request,response);
    }

    private Question getQuestionByID(int questionId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.question.QuestionWS port = service.getQuestionWSPort();
        return port.getQuestionByID(questionId);
    }


}
