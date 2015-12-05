/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import TokenOperator.Token;
import TokenOperator.TokenAdapter;
import UserOperator.User;
import UserOperator.UserAdapter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
/**
 *
 * @author Aidin
 */
@WebServlet(urlPatterns = {"/Handler"})
public class Handler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


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

        String tokenStr = request.getParameter("token");
        String userAgent = request.getHeader("user-agent");
        String userIP = request.getRemoteAddr();

        TokenExtractor TokExt= new TokenExtractor();

        String[] tokenData = TokExt.getParams(tokenStr, 2);
        String tokenAgent = tokenData[0];
        String tokenIP = tokenData[1];


        if(!userIP.equals(tokenIP)){
            response.sendError(402, "IP is different ");
        } else {
            if(!userAgent.equals(tokenAgent)){
                response.sendError(402, "User-agent is different");
            } else{
                Token token= new Token();
                TokenAdapter tadb = new TokenAdapter();

                try {
                    token = tadb.getTokenToken(tokenStr);
                    if((token.getEmail()).isEmpty()){//token tidak ditemukan
                        response.sendError(404, "token is invalid");
                    } else {//token ditemukan

                        Date current = new Date();
                        Date expired = new Date((token.getExpired()).getTime());
                        if(current.after(expired)){//token kadaluarsa
                            response.sendError(404, "token is expired");
                        } else {//token masih available
                            response.setContentType("application/xml");
                            PrintWriter printWriter;
                            try {
                                printWriter = response.getWriter();
                                printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                                printWriter.println("<root>");
                                printWriter.print("<email>");
                                printWriter.print(token.getEmail());
                                printWriter.println("</email>");
                                printWriter.println("</root>");
                                printWriter.flush();
                            } catch (IOException e) {

                            }
                        }
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
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
        String email, pass;

        email = request.getParameter("email");
        pass = request.getParameter("pass");

        String userAgent = request.getHeader("user-agent");
        String userIP = request.getRemoteAddr();
        String userData[]= {userAgent, userIP};



        TokenGenerator tokGen = new TokenGenerator();
        String tokenStr = tokGen.generateToken(userData);

        User clientUser = new User(email,pass);
        User dbUser = new User();
        UserAdapter uadb = new UserAdapter();

        Token token = new Token();
        TokenAdapter tadb = new TokenAdapter();
        int lifetime = 2;

        try {
            dbUser=uadb.getUser(email);
            if((dbUser.getEmail()).isEmpty()){//user tidak ditemukan
                response.sendError(404, "email is not found");
            } else {//user ditemukan
                if(!clientUser.isEqual(dbUser)){//password tidak sesuai
                    response.sendError(404, "email and password do not match");
                } else {//password sesuai
                    token=tadb.getTokenEmail(email);//mengambill token dari database
                    //membuat token baru
                    Date expiredDate = new Date();
                    int minutes=expiredDate.getMinutes()+lifetime;
                    expiredDate.setMinutes(minutes);
                    Timestamp expired = new Timestamp(expiredDate.getTime());

                    Token newToken = new Token (email, tokenStr, expired);

                    if((token.getEmail().isEmpty())){//token belum ada di database
                        tadb.InsertToken(newToken);
                    } else {//token sudah ada di database
                        tadb.UpdateToken(newToken);
                    }

                    response.setContentType("application/xml");
                    PrintWriter printWriter;
                    try {
                        printWriter = response.getWriter();
                        printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                        printWriter.println("<root>");
                        printWriter.print("<token>");
                        printWriter.print(newToken.getTokenStr());
                        printWriter.println("</token>");
                        printWriter.print("<lifetime>");
                        printWriter.print(lifetime);
                        printWriter.println("</lifetime>");
                        printWriter.println("</root>");
                        printWriter.flush();
                    } catch (IOException e) {

                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


}
