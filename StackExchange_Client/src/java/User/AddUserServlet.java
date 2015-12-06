/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import model.user.User;
import model.user.UserWS_Service;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Venny
 */
@WebServlet(name = "AddUserServlet", urlPatterns = {"/addUser"})
public class AddUserServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/UserWS.wsdl")
    private UserWS_Service service;
    
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
        model.user.User newUser = new model.user.User();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = md5(request.getParameter("password"));
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        int id = getIDbyEmail(email);
        if (id == -1){
            //user doen't exist
            addUser(newUser);
            request.setAttribute("message","Register successful. Please login to continue.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login");
            dispatcher.forward(request,response);
        } else {
            request.setAttribute("message","Email already registered. Please try again.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request,response);
        }
    }

    private void addUser(User u) {
        model.user.UserWS port = service.getUserWSPort();
        port.addUser(u);
    }
    private int getIDbyEmail(String email) {
        model.user.UserWS port = service.getUserWSPort();
        return port.getIDbyEmail(email);
    }
    
    public static String md5(String input) {
        String md5 = null;
        if(null == input) return null;
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes(), 0, input.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}
