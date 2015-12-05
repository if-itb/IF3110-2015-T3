/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.WebServiceRef;
import question.QuestionCreate;
import tool.*;

/**
 *
 * @author mfikria
 */
@WebServlet(name = "UserRegister", urlPatterns = {"/register"})
public class UserRegister extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/SimpleStackExchange_WebService/User_WS.wsdl")
    private UserWS_Service service_1;

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
        
        if(!Util.isLogin(request)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String cpassword = request.getParameter("cpassword");

            if(password.equals(cpassword)) {
                if(checkEmailUser(email)) {
                user.Registereduser user = new user.Registereduser();
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);

                // Initialize Created time
                XMLGregorianCalendar date = null;
                    try {
                        date = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
                    } catch (DatatypeConfigurationException ex) {
                        Logger.getLogger(QuestionCreate.class.getName()).log(Level.SEVERE, null, ex);
                    }
                user.setCreatedtime(date);

                createUser(user);
                PrintWriter out= response.getWriter();
                out.println("<div class=\"alert alert-danger\" role=\"alert\">Email are not unique!</div>"); // pass the error message
             
                response.sendRedirect("");
                }
                else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp"); // redirect to login page

                    PrintWriter out= response.getWriter();
                    out.println("<div class=\"alert alert-danger\" role=\"alert\">Email are not unique!</div>"); // pass the error message
                    rd.include(request, response);
                }
            }
            else {
                
                 RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp"); // redirect to login page

                PrintWriter out= response.getWriter();
                out.println("<div class=\"alert alert-danger\" role=\"alert\">Pasword is missmatch!</div>"); // pass the error message
                rd.include(request, response);
                
            }
            
        }
        else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(""); // redirect to home page
            
            PrintWriter out= response.getWriter();
            out.println("<div class=\"alert alert-danger\" role=\"alert\">Please, logout first!</div>"); // pass the error message
            rd.include(request, response);
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

    private Boolean checkEmailUser(java.lang.String email) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        user.UserWS port = service_1.getUserWSPort();
        return port.checkEmailUser(email);
    }

    private void createUser(user.Registereduser user) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        user.UserWS port = service_1.getUserWSPort();
        port.createUser(user);
    }

 

   

}
