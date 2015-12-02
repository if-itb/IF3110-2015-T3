package Question;

import AnswerWS.Answer;
import AnswerWS.AnswerWS_Service;
import QuestionWS.Question;
import QuestionWS.QuestionWS_Service;
import Token.TokenWS_Service;
import UserWS.UserWS_Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
                out.println("<div class=\"navbar-down\">\n"
                        + "            <b class=\"navbar-down\">\n"
                        + "                <p class=\"white\">Hello, ");
                out.println(getUser(getUserIDByToken(accessToken)).get(0).getNama());
                out.println("</p> |\n"
                        + "                <a class=\"white\" href=\"LogoutServlet\">Logout</a>\n"
                        + "            </b>\n"
                        + "        </div>\n\n"
                        + "        <div class=\"main\">");
                List<Question> question = getQuestionByQID(qid);
                for (Question question1 : question) {
                    out.println("            <br>\n"
                            + "            <h2>");
                    out.println(question1.getTopic());
                    out.println("</h2>\n"
                            + "            <hr>\n"
                            + "            <table>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"VotesQA\">\n"
                            + "                        <a href=\"VoteQuestionServlet?qid=");
                    out.println(qid);
                    out.println("&type=up\">\n"
                            + "                            <img src=\"img/vote-up.png\">\n"
                            + "                        </a>\n"
                            + "                        <div id=\"VotesQ\">");
                    out.println(question1.getVotes());
                    out.println("</div>\n"
                            + "                        <a href=\"VoteQuestionServlet?qid=");
                    out.println(qid);
                    out.println("&type=down\">\n"
                            + "                            <img src=\"img/vote-down.png\">\n"
                            + "                        </a>\n"
                            + "                    </td>\n"
                            + "                    <td>");
                    out.println(question1.getContent());
                    out.println("</td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td class=\"Asker\">\n"
                            + "                        asked by ");
                    out.println(getUser(question1.getUserID()).get(0).getEmail());
                    out.println(" at ");
                    out.println(question1.getDateTime().substring(0, 19));
                    if (getUserIDByToken(accessToken) == question1.getUserID()) {
                        out.println("                        | <a class=\"gold\" href=\"edit.jsp?qid=");
                        out.println(question1.getQID());
                        out.println("\">\n"
                                + "                            edit\n"
                                + "                        </a> | \n"
                                + "                        <a class=\"red\" href=\"");
                        out.println("DeleteQuestionServlet?qid=" + question1.getQID());
                        out.println("\">\n"
                                + "                            delete\n"
                                + "                        </a>\n");
                    }
                    out.println("                    </td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "\n"
                            + "            <br>\n"
                            + "            <h2>");
                    out.print(question1.getAnswers() + " Answer");
                    if (question1.getAnswers() != 1) {
                        out.println("s");
                    }
                    out.println("</h2>\n"
                            + "            <hr>");
                }
                List<Answer> answers = getAnswerByQID(Integer.valueOf((String) request.getAttribute("qid")));
                for (Answer answer : answers) {
                    out.println("<table>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"VotesQA\">\n"
                            + "                        <a href=\"VoteAnswerServlet?qid=");
                    out.println(answer.getQID() + "&aid=" + answer.getAID());
                    out.println("&type=up\">\n"
                            + "                            <img src=\"img/vote-up.png\">\n"
                            + "                        </a>\n"
                            + "                        <br>\n"
                            + "                        <div class=\"VotesA\" id=\"\">");
                    out.println(answer.getVotes());
                    out.println("</div>\n"
                            + "                        <a href=\"VoteAnswerServlet?qid=");
                    out.println(answer.getQID() + "&aid=" + answer.getAID());
                    out.println("&type=down\">\n"
                            + "                            <img src=\"img/vote-down.png\">\n"
                            + "                        </a>\n"
                            + "                    </td>\n"
                            + "                    <td>");
                    out.println(answer.getContent());
                    out.println("</td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td class=\"Answerer\">\n"
                            + "                        answered by ");
                    out.println(getUser(answer.getUserID()).get(0).getEmail());
                    out.println(" at ");
                    out.println(answer.getDateTime().substring(0, 19));
                    out.println("</td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "            <hr>");
                }
                out.println("<h2>\n"
                        + "                Your Answer\n"
                        + "            </h2>\n"
                        + "\n"
                        + "            <form name=\"answerForm\" action=\"CreateAnswerServlet\" method=\"post\" onsubmit=\"\">\n"
                        + "                <input name=\"qid\" type=\"hidden\" value=");
                out.println(qid);
                out.println(">\n"
                        + "                <textarea name=\"content\" id=\"question\" placeholder=\"Content\"></textarea>\n"
                        + "                <input class=\"button\" type=\"submit\" value=\"Post\"><br>\n"
                        + "            </form>");
                out.println("</div>");
            }
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("<div class=\"navbar-down\">\n"
                        + "            <b class=\"navbar-down\">\n"
                        + "                <a class=\"white\" href=\"register.jsp\">Register</a> |\n"
                        + "                <a class=\"white\" href=\"login.jsp\">Login</a>\n"
                        + "            </b>\n"
                        + "        </div>"
                        + "<div class=\"main\">");
                List<Question> question = getQuestionByQID(qid);
                for (Question question1 : question) {
                    out.println("            <br>\n"
                            + "            <h2>");
                    out.println(question1.getTopic());
                    out.println("</h2>\n"
                            + "            <hr>\n"
                            + "            <table>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"VotesQA\">\n"
                            + "                            <img src=\"img/vote-up.png\">\n"
                            + "                        <div id=\"VotesQ\">");
                    out.println(question1.getVotes());
                    out.println("</div>\n"
                            + "                            <img src=\"img/vote-down.png\">\n"
                            + "                        </a>\n"
                            + "                    </td>\n"
                            + "                    <td>");
                    out.println(question1.getContent());
                    out.println("</td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td class=\"Asker\">\n"
                            + "                        asked by ");
                    out.println(getUser(question1.getUserID()).get(0).getEmail());
                    out.println(" at ");
                    out.println(question1.getDateTime().substring(0, 19));
                    out.println("                    </td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "\n"
                            + "            <br>\n"
                            + "            <h2>");
                    out.print(question1.getAnswers() + " Answer");
                    if (question1.getAnswers() != 1) {
                        out.println("s");
                    }
                    out.println("</h2>\n"
                            + "            <hr>");
                }
                List<Answer> answers = getAnswerByQID(Integer.valueOf((String) request.getAttribute("qid")));
                for (Answer answer : answers) {
                    out.println("<table>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"VotesQA\">\n"
                            + "                        <a href=\"VoteAnswerServlet?qid=");
                    out.println(answer.getQID() + "&aid=" + answer.getAID());
                    out.println("&type=up\">\n"
                            + "                            <img src=\"img/vote-up.png\">\n"
                            + "                        </a>\n"
                            + "                        <br>\n"
                            + "                        <div class=\"VotesA\" id=\"\">");
                    out.println(answer.getVotes());
                    out.println("</div>\n"
                            + "                        <a href=\"VoteAnswerServlet?qid=");
                    out.println(answer.getQID() + "&aid=" + answer.getAID());
                    out.println("&type=down\">\n"
                            + "                            <img src=\"img/vote-down.png\">\n"
                            + "                        </a>\n"
                            + "                    </td>\n"
                            + "                    <td>");
                    out.println(answer.getContent());
                    out.println("</td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td></td>\n"
                            + "                    <td class=\"Answerer\">\n"
                            + "                        answered by ");
                    out.println(getUser(answer.getUserID()).get(0).getEmail());
                    out.println(" at ");
                    out.println(answer.getDateTime().substring(0, 19));
                    out.println("</td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "            <hr>");
                }
                out.println("</div>");
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
