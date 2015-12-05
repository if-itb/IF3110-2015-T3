package org.stackexchange.identityservice.controller;

import org.stackexchange.identityservice.dao.TokenDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by X450 on 05/12/2015.
 */
@WebServlet(name = "TokenCompleteCheckServlet")
public class TokenCompleteCheckServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        String ip = request.getParameter("ip");
        String user_agent = request.getParameter("user_agent");
        response.setContentType("application/json");

        TokenDao tokenDao = new TokenDao();
        boolean exist = tokenDao.existByToken(token, ip, user_agent);

        try {
            PrintWriter out = response.getWriter();
            out.print(String.valueOf(exist));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
