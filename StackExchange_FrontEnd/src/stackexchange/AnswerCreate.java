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
 * Created by elvan_owen on 11/18/15.
 */
@WebServlet(name = "AnswerCreate")
public class AnswerCreate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> requestParams = new HashMap<>();
        ArrayList<String> params = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();

        requestParams.put("access_token", request.getParameter("token"));
        requestParams.put("question_id", request.getParameter("question_id"));
        requestParams.put("content", request.getParameter("content"));
        requestParams.put("user_agent", request.getHeader("user-agent"));
        requestParams.put("ip_address", request.getRemoteAddr());

        System.out.println("+++");
        System.out.println(requestParams.get("user_agent"));
        System.out.println(requestParams.get("ip"));
        System.out.println("+++");

        // Set target method
        String requestResponse = HttpRequest.executeMethod("createAnswer", requestParams);

        // Get soap response
        String responseCode = XmlParser.checkResponse(requestResponse);

        System.out.println(responseCode);

        if (responseCode.equals("success")){
            response.setContentType("text/html;charset=UTF-8");
//            response.sendRedirect("/?token=" + request.getParameter("token"));
//            request.getRequestDispatcher("/views/index.jsp").forward(request, response);
            return;
        } else if (responseCode.equals("expired")){
            System.out.println("-- LOGIN");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"expired\" : true}");
//            response.sendRedirect("/login");
            return;
        }

        if (request.getParameter("token") != null && !request.getParameter("token").isEmpty()){
            System.out.println("-- ERROR");
            response.getWriter().write("{\"error\" : true}");
            response.setContentType("text/html;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

//            request.setAttribute("error", "Internal Server Error");
//            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        } else {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("{\"expired\" : true}");
            System.out.println("-- LOGIN");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
