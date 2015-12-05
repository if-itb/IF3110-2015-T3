package stackexchange;

import models.Question;
import models.Answer;
import org.json.JSONArray;
import org.json.JSONObject;
import util.HttpRequest;
import util.XmlParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by elvan_owen on 11/17/15.
 */
@WebServlet(name = "Answerpage")
public class Answerpage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, String> requestParams = new HashMap<>();
        ArrayList<String> params = new ArrayList<>();
        ArrayList<Question> questions = new ArrayList<>();

        requestParams.put("id", request.getParameter("id"));

        // Set target method
        String requestResponse = HttpRequest.executeMethod("getQuestionById", requestParams);

        // Add method parameters
        Collections.addAll(params, "content", "createDate", "title", "questionId", "userId", "vote", "userName");

        // Get soap response
        ArrayList< HashMap<String, String> > responseParams = XmlParser.parse(requestResponse, params);

        Question question = new Question(responseParams.get(0));

        HashMap<String, String> requestParams2 = new HashMap<>();
        ArrayList<String> params2 = new ArrayList<>();
        ArrayList<Answer> answers = new ArrayList<>();

        // Set target method
        String requestResponse2 = HttpRequest.executeMethod("getAllAnswersFromQuestionId", requestParams);

        // Add method parameters
        Collections.addAll(params2, "content", "createDate", "questionId", "answerId", "userId", "vote", "userName");

        // Get soap response
        ArrayList< HashMap<String, String> > responseParams2 = XmlParser.parse(requestResponse2, params2);

        JSONArray answersJson = new JSONArray();
        // Create Models
        for (int i = 0; i < responseParams2.size(); i++) {
            answers.add(new Answer(responseParams2.get(i)));
            try {
                answersJson.put(new JSONObject(new Answer(responseParams2.get(i)).toString()));
            } catch (Exception e){
            }
        }

        System.out.println("-- Questions");
        System.out.println(question.toString());

        System.out.println("-- Answers");
        System.out.println(answersJson.toString());

        request.setAttribute("question", question.toString());
        request.setAttribute("answers", answersJson.toString());
        request.setAttribute("response", requestResponse2);

        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("views/answer.jsp").forward(request, response);
    }
}
