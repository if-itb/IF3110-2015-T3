package Question;

import AnswerWS.Answer;
import AnswerWS.AnswerWS_Service;
import QuestionWS.Question;
import QuestionWS.QuestionWS_Service;
import Token.TokenWS_Service;
import UserWS.UserWS_Service;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author M. Fauzan Naufan
 */
public class QuestionByQIDServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/TokenWS.wsdl")
    private TokenWS_Service service_3;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/AnswerWS.wsdl")
    private AnswerWS_Service service_2;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/UserWS.wsdl")
    private UserWS_Service service_1;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/QuestionWS.wsdl")
    private QuestionWS_Service service;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int qid = Integer.parseInt((String) request.getAttribute("qid"));
        response.setContentType("text/html;charset=UTF-8");
        String accessToken;
        Cookie[] cookies;
        cookies = request.getCookies();
        int x = 0;
        while (x < cookies.length && !cookies[x].getName().equals("access_token")) {
            x++;
        }
        if (x < cookies.length) {
            accessToken = cookies[x].getValue();
            try (PrintWriter out = response.getWriter()) {
                out.print("<div class=\"navbar-down\">\n"
                        + "            <b class=\"navbar-down\">\n"
                        + "                <p class=\"white\">Hello, ");
                out.print(getUser(getUserIDByToken(accessToken)).get(0).getNama());
                out.print("</p> |\n"
                        + "                <a class=\"white\" href=\"LogoutServlet\">Logout</a>\n"
                        + "            </b>\n"
                        + "        </div>\n\n"
                        + "        <div class=\"main\">\n");
                List<Question> question = getQuestionByQID(qid);
                for (Question question1 : question) {
                    out.print("            <br>\n"
                            + "            <h2>");
                    out.print(question1.getTopic());
                    out.print("</h2>\n"
                            + "            <hr>\n"
                            + "            <table>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"VotesQA\">\n"
                            + "                        <a href=\"VoteQuestionServlet?qid=");
                    out.print(qid);
                    out.print("&type=up\">\n"
                            + "                            <img src=\"img/vote-up.png\">\n"
                            + "                        </a>\n"
                            + "                        <div id=\"VotesQ\">");
                    out.print(question1.getVotes());
                    out.print("</div>\n"
                            + "                        <a href=\"VoteQuestionServlet?qid=");
                    out.print(qid);
                    out.print("&type=down\">\n"
                            + "                            <img src=\"img/vote-down.png\">\n"
                            + "                        </a>\n"
                            + "                    </td>\n"
                            + "                    <td>");
                    out.print(question1.getContent());
                    out.print("</td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td class=\"Asker\">\n"
                            + "                        asked by ");
                    out.print(getUser(question1.getUserID()).get(0).getEmail());
                    out.print(" at ");
                    out.println(question1.getDateTime().substring(0, 19));
                    if (getUserIDByToken(accessToken) == question1.getUserID()) {
                        out.print("                        | <a class=\"gold\" href=\"edit.jsp?qid=");
                        out.print(question1.getQID());
                        out.print("\">\n"
                                + "                            edit\n"
                                + "                        </a> | \n"
                                + "                        <a class=\"red\" href=\"");
                        out.print("DeleteQuestionServlet?qid=" + question1.getQID());
                        out.print("\">\n"
                                + "                            delete\n"
                                + "                        </a>\n");
                    }
                    out.print("                    </td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td>\n"
                            + "                        <hr>\n");
                    out.print("                        <a class=\"blue\" href=\"\">Add comment</a>\n");
                    out.print("                    </td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "\n"
                            + "            <br>\n"
                            + "            <h2>");
                    out.print(question1.getAnswers() + " Answer");
                    if (question1.getAnswers() != 1) {
                        out.print("s");
                    }
                    out.print("</h2>\n"
                            + "            <hr>");
                }
                List<Answer> answers = getAnswerByQID(Integer.valueOf((String) request.getAttribute("qid")));
                for (Answer answer : answers) {
                    out.print("<table>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"VotesQA\">\n"
                            + "                        <a href=\"VoteAnswerServlet?qid=");
                    out.print(answer.getQID() + "&aid=" + answer.getAID());
                    out.print("&type=up\">\n"
                            + "                            <img src=\"img/vote-up.png\">\n"
                            + "                        </a>\n"
                            + "                        <br>\n"
                            + "                        <div class=\"VotesA\" id=\"\">");
                    out.print(answer.getVotes());
                    out.print("</div>\n"
                            + "                        <a href=\"VoteAnswerServlet?qid=");
                    out.print(answer.getQID() + "&aid=" + answer.getAID());
                    out.print("&type=down\">\n"
                            + "                            <img src=\"img/vote-down.png\">\n"
                            + "                        </a>\n"
                            + "                    </td>\n"
                            + "                    <td>");
                    out.print(answer.getContent());
                    out.print("</td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td class=\"Answerer\">\n"
                            + "                        answered by ");
                    out.print(getUser(answer.getUserID()).get(0).getEmail());
                    out.print(" at ");
                    out.print(answer.getDateTime().substring(0, 19));
                    out.print("</td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td>\n"
                            + "                        <hr>\n");
                    out.print("                        <a class=\"blue\" href=\"\">Add comment</a>\n");
                    out.print("                    </td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "            <hr>");
                }
                out.print("<h2>\n"
                        + "                Your Answer\n"
                        + "            </h2>\n"
                        + "\n"
                        + "            <form name=\"answerForm\" action=\"CreateAnswerServlet\" method=\"post\" onsubmit=\"\">\n"
                        + "                <input name=\"qid\" type=\"hidden\" value=");
                out.print(qid);
                out.print(">\n"
                        + "                <textarea name=\"content\" id=\"question\" placeholder=\"Content\"></textarea>\n"
                        + "                <input class=\"button\" type=\"submit\" value=\"Post\"><br>\n"
                        + "            </form>");
                out.print("</div>");
            }
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.print("<div class=\"navbar-down\">\n"
                        + "            <b class=\"navbar-down\">\n"
                        + "                <a class=\"white\" href=\"register.jsp\">Register</a> |\n"
                        + "                <a class=\"white\" href=\"login.jsp\">Login</a>\n"
                        + "            </b>\n"
                        + "        </div>"
                        + "<div class=\"main\">");
                List<Question> question = getQuestionByQID(qid);
                for (Question question1 : question) {
                    out.print("            <br>\n"
                            + "            <h2>");
                    out.print(question1.getTopic());
                    out.print("</h2>\n"
                            + "            <hr>\n"
                            + "            <table>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"VotesQA\">\n"
                            + "                        <img src=\"img/vote-up.png\">\n"
                            + "                        <div id=\"VotesQ\">");
                    out.print(question1.getVotes());
                    out.print("</div>\n"
                            + "                        <img src=\"img/vote-down.png\">\n"
                            + "                    </td>\n"
                            + "                    <td>");
                    out.print(question1.getContent());
                    out.print("</td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td class=\"Asker\">\n"
                            + "                        asked by ");
                    out.print(getUser(question1.getUserID()).get(0).getEmail());
                    out.print(" at ");
                    out.print(question1.getDateTime().substring(0, 19));
                    out.print("                    </td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td>\n"
                            + "                        <hr>\n");
                    out.print("                        <a class=\"blue\" href=\"\">Add comment</a>\n"
                            + "                    </td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "\n"
                            + "            <br>\n"
                            + "            <h2>");
                    out.print(question1.getAnswers() + " Answer");
                    if (question1.getAnswers() != 1) {
                        out.print("s");
                    }
                    out.print("</h2>\n"
                            + "            <hr>");
                }
                List<Answer> answers = getAnswerByQID(Integer.valueOf((String) request.getAttribute("qid")));
                for (Answer answer : answers) {
                    out.print("<table>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"VotesQA\">\n"
                            + "                        <a href=\"VoteAnswerServlet?qid=");
                    out.print(answer.getQID() + "&aid=" + answer.getAID());
                    out.print("&type=up\">\n"
                            + "                            <img src=\"img/vote-up.png\">\n"
                            + "                        </a>\n"
                            + "                        <br>\n"
                            + "                        <div class=\"VotesA\" id=\"\">");
                    out.print(answer.getVotes());
                    out.print("</div>\n"
                            + "                        <a href=\"VoteAnswerServlet?qid=");
                    out.print(answer.getQID() + "&aid=" + answer.getAID());
                    out.print("&type=down\">\n"
                            + "                            <img src=\"img/vote-down.png\">\n"
                            + "                        </a>\n"
                            + "                    </td>\n"
                            + "                    <td>");
                    out.print(answer.getContent());
                    out.print("</td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td class=\"Answerer\">\n"
                            + "                        answered by ");
                    out.print(getUser(answer.getUserID()).get(0).getEmail());
                    out.print(" at ");
                    out.print(answer.getDateTime().substring(0, 19));
                    out.print("</td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td>\n"
                            + "                        <hr>\n");
                    out.print("                        <a class=\"blue\" href=\"\">Add comment</a>\n"
                            + "                    </td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "            <hr>");
                }
                out.print("</div>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private java.util.List<QuestionWS.Question> getQuestionByQID(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service.getQuestionWSPort();
        return port.getQuestionByQID(qid);
    }

    private java.util.List<UserWS.User> getUser(int userID) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        UserWS.UserWS port = service_1.getUserWSPort();
        return port.getUser(userID);
    }

    private java.util.List<AnswerWS.Answer> getAnswerByQID(int qid) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        AnswerWS.AnswerWS port = service_2.getAnswerWSPort();
        return port.getAnswerByQID(qid);
    }

    private int getUserIDByToken(java.lang.String accessToken) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        Token.TokenWS port = service_3.getTokenWSPort();
        return port.getUserIDByToken(accessToken);
    }

}
