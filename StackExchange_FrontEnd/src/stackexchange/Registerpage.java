package stackexchange;

import models.Question;
import util.HttpRequest;
import util.XmlParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by elvan_owen on 11/17/15.
 */
@WebServlet(name = "Registerpage")
public class Registerpage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> requestParams = new HashMap<>();
        ArrayList<String> params = new ArrayList<>();


        requestParams.put("name", request.getParameter("name"));
        requestParams.put("email", request.getParameter("email"));
        requestParams.put("password", request.getParameter("password"));

        // Set target method
        String requestResponse = HttpRequest.executeMethod("registerUser", requestParams);

        // Get soap response
        String responseCode = XmlParser.checkResponse(requestResponse);

        if (responseCode.equals("success")){
            response.sendRedirect("/");
            return;
        }

        request.setAttribute("name", request.getParameter("name"));
        request.setAttribute("email", request.getParameter("email"));
        request.setAttribute("password", request.getParameter("password"));

        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/views/register.jsp").forward(request, response);
    }
}
