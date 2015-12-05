/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import answermodel.AnswerWS_Service;
import answermodel.ParseException_Exception;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import questionmodel.QuestionWS_Service;
import usermodel.UserWS_Service;

/**
 *
 * @author adek
 */
public class VoteServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/AnswerWS.wsdl")
    private AnswerWS_Service service_2;
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet VoteServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VoteServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        
        String token = "";
        Cookie[] cookies = request.getCookies();
        if(cookies==null) {      
            System.out.println("COOKIES NULL");
        }
        else {                
            for(Cookie cookie : cookies) {
                if("token".equals(cookie.getName())) { 
                    token = cookie.getValue();
                    System.out.println(token);
                    break;
                }   
            }
        }
        int result,stat=0;
        String temp = request.getParameter("stat");
        if(temp.equals("up")) stat=1;
        else stat=-1;
        questionmodel.QuestionWS port = service.getQuestionWSPort();
        usermodel.UserWS port2 = service_1.getUserWSPort();
        answermodel.AnswerWS port3 = service_2.getAnswerWSPort();
        int userid = port2.getIDUserbyToken(token);
        int useridhome = Integer.parseInt(request.getParameter("userid"));
        int qid=0;
        if(userid==useridhome) {
            String type = request.getParameter("type");
            if(type.equals("a")) {
                try {
                    int aid = Integer.parseInt(request.getParameter("aid"));
                    result = port3.voteAnswer(userid,aid,stat,token);
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/dadakanDB","root","");
                    String sql = "SELECT id_question FROM answers WHERE id =" + aid;
                    PreparedStatement dbs = conn.prepareStatement(sql);
                    ResultSet rs = dbs.executeQuery();
                    if(rs.next()) {
                        qid = rs.getInt("id_question");
                    }
                } catch (ParseException_Exception | ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(VoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                try {
                    qid = Integer.parseInt(request.getParameter("qid"));
                    result = port.voteQuestion(userid,qid,stat,token);
                } catch (questionmodel.ParseException_Exception ex) {
                    Logger.getLogger(VoteServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/AnswerServlet?id="+qid);
            
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

}
