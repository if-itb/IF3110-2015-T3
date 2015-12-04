/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vote;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author ASUS X202E
 */
@WebServlet(name = "VoteServlet", urlPatterns = {"/vote"})
public class VoteServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/VoteWS.wsdl")
    private model.vote.VoteWS_Service service;

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
        response.setContentType("text/html;charset=UTF-8");

        Cookie cookies[] = request.getCookies();

        String token_id="";
        boolean found = false;
        int i=0;
        while (i<cookies.length && !found) {
            if ("stackexchange_token".equals(cookies[i].getName())) {
                token_id = cookies[i].getValue();
                found = true;
            } else {
                i++;
            }
        }
        
        int success = -1;
        if (found) {
            try (PrintWriter out = response.getWriter()) {
                int id = Integer.parseInt(request.getParameter("id"));
                int vote = Integer.parseInt(request.getParameter("vote"));
                String db = request.getParameter("db");
                if ("question".equals(db)) {
                    success = voteQuestion(token_id,id,vote);
                    if (success>0) out.print(getQuestionVotes(id)+"");
                } else if ("answer".equals(db)) {
                    success = voteAnswer(token_id,id,vote);
                    if (success>0) out.print(getAnswerVotes(id)+"");
                }
            }
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

    private int getQuestionVotes(int questionId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.vote.VoteWS port = service.getVoteWSPort();
        return port.getQuestionVotes(questionId);
    }


    private int getAnswerVotes(int answerId) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.vote.VoteWS port = service.getVoteWSPort();
        return port.getAnswerVotes(answerId);
    }

    private int voteAnswer(java.lang.String token, int answerId, int vote) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.vote.VoteWS port = service.getVoteWSPort();
        return port.voteAnswer(token, answerId, vote);
    }

    private int voteQuestion(java.lang.String token, int questionId, int vote) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        model.vote.VoteWS port = service.getVoteWSPort();
        return port.voteQuestion(token, questionId, vote);
    }

}
