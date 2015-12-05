package org.stackexchange.identityservice.controller;

import org.stackexchange.identityservice.dao.TokenDao;
import org.stackexchange.identityservice.dao.UserDao;
import org.stackexchange.identityservice.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TokenCheckApiServlet extends HttpServlet {

    @Override
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
