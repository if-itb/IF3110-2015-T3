package org.stackexchange.identityservice.controller;

import org.stackexchange.identityservice.dao.TokenDao;
import org.stackexchange.identityservice.dao.UserDao;
import org.stackexchange.identityservice.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UserApiServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getParameter("token");
        response.setContentType("application/json");

        TokenDao tokenDao = new TokenDao();
        UserDao userDao = new UserDao();
        long userId = tokenDao.getFromToken(token).getUserId();
        User user = userDao.getById(userId);

        try {
            PrintWriter out = response.getWriter();
            out.print(user.getName());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
