package stackexchange;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.xml.*;
import java.util.HashMap;

import util.HttpRequest;
import util.HttpRequest.*;
import util.XmlParser;

/**
 * Created by elvan_owen on 11/17/15.
 */
@WebServlet(name = "Askpage")
public class Askpage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> data = new HashMap<>();

        String token = request.getParameter("token");

        data.put("access_token", token);
        data.put("title", request.getParameter("title"));
        data.put("content", request.getParameter("content"));

        String requestResponse = HttpRequest.executeMethod("createQuestion", data);

        // Get soap response
        String responseCode = XmlParser.checkResponse(requestResponse);

        if (responseCode.equals("success")){
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect("/?token=" + request.getParameter("token"));
//            request.getRequestDispatcher("/views/index.jsp").forward(request, response);
            return;
        } else if (responseCode.equals("expired")){
            response.sendRedirect("/login");
            return;
        }

        if (request.getParameter("token") != null && !request.getParameter("token").isEmpty()){
            response.setContentType("text/html;charset=UTF-8");

            request.setAttribute("error", "Internal Server Error");
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            response.sendRedirect("/login");
//            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
