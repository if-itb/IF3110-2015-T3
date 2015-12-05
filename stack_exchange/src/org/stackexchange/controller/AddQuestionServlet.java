package org.stackexchange.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by X450 on 17/11/2015.
 */
public class AddQuestionServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String token = request.getParameter("token");
        if (token != null && !token.isEmpty()) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("flash", "You Need To Login First");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        String token = request.getParameter("token");
        if (token != null && !token.isEmpty()) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("flash", "You Need To Login First");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
