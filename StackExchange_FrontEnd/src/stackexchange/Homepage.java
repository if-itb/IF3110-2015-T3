package stackexchange;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import util.XmlParser;
import util.HttpRequest;
import models.Question;

/**
 * Created by elvan_owen on 11/17/15.
 */
@WebServlet(name = "Homepage")
public class Homepage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, String> requestParams = new HashMap<>();
        ArrayList<String> params = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();

        // Set target method
        String requestResponse = HttpRequest.executeMethod("getAllQuestions", requestParams);

        // Add method parameters
        Collections.addAll(params, "content", "createDate", "title", "userId", "questionId", "vote");

        // Get soap response
        ArrayList< HashMap<String, String> > responseParams = XmlParser.parse(requestResponse, params);

        // Create Models
        for (int i = 0; i < responseParams.size(); i++) {
            questions.add(new Question(responseParams.get(i)));
        }

        request.setAttribute("questions", questions);
        request.setAttribute("response", requestResponse);

        if (request.getParameter("token") != null && !request.getParameter("token").isEmpty()){
            request.setAttribute("login", "true");
            request.setAttribute("token", request.getParameter("token"));
        }

        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("views/index.jsp").forward(request, response);
    }
}
