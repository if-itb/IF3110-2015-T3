package org.stackexchange.identityservice.controller;

import com.google.gson.Gson;
import org.stackexchange.identityservice.dao.UserDao;
import org.stackexchange.identityservice.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserInfoApiServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("id"));

        UserDao userDao = new UserDao();
        User user = userDao.getById(id);

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("name", user.getName());
        userInfo.put("email", user.getEmail());

        Gson gson = new Gson();
        String json = gson.toJson(userInfo);

        try {
            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
