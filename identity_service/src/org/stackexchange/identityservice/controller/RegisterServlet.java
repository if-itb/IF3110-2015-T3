package org.stackexchange.identityservice.controller;

import org.stackexchange.identityservice.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserDao userDao = new UserDao();

        String email = request.getParameter("email");
        if (!userDao.emailExist(email)) {
            userDao.insertUser(request.getParameter("name"), request.getParameter("email"), request.getParameter("password"));
            response.sendRedirect("/login");
        } else {
            request.setAttribute("flash", "tess");
            response.setContentType("text/html");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
