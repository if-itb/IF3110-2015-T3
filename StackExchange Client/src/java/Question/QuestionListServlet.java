package Question;

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
public class QuestionListServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/TokenWS.wsdl")
    private TokenWS_Service service_2;

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
        String accessToken;
        Cookie[] cookies;
        int x = 0;
        cookies = request.getCookies();
        while (x < cookies.length && !cookies[x].getName().equals("access_token")) {
            x++;
        }
        if (x == cookies.length) {
            try (PrintWriter out = response.getWriter()) {
                out.print("<div class=\"navbar-down\">\n"
                        + "            <b class=\"navbar-down\">\n"
                        + "                <a class=\"white\" href=\"register.jsp\">Register</a> |\n"
                        + "                <a class=\"white\" href=\"login.jsp\">Login</a>\n"
                        + "            </b>\n"
                        + "        </div>\n\n"
                        + "        <br>\n"
                        + "        <div class=\"main\">\n"
                        + "            <div class=\"searchbar\">\n"
                        + "                <form id=\"searchform\" action=\"\" method=\"post\">\n"
                        + "                    <input id=\"searchbar\" type=\"text\" name=\"name\">\n"
                        + "                    <input id=\"searchbutton\" type=\"submit\" name=\"submit\" value=\"Search\"><br>\n"
                        + "                </form>\n"
                        + "                <p id=\"searchbar\">\n"
                        + "                     Cannot find what you are looking for?\n"
                        + "                     <strong class=\"gold\">Ask here</strong>\n"
                        + "                </p>\n"
                        + "            </div>\n"
                        + "\n"
                        + "            <p id=\"RecentlyAsked\">\n"
                        + "                Recently Asked Questions\n"
                        + "            </p>\n"
                        + "            <hr>\n");
                List<Question> questions = getAllQuestion();
                for (Question question : questions) {
                    out.print("            <table>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"Votes\" rowspan=\"2\">\n"
                            + "                        <b>");
                    out.print(question.getVotes());
                    out.print("<br>\n"
                            + "                            Votes\n"
                            + "                        </b>\n"
                            + "                    </td>\n"
                            + "                    <td class=\"Answers\" rowspan=\"2\">\n"
                            + "                        <b>");
                    out.print(question.getAnswers());
                    out.print("<br>\n"
                            + "                            Answers\n"
                            + "                        </b>\n"
                            + "                    </td>\n"
                            + "                    <td>\n"
                            + "                        <p class=\"topic\">\n"
                            + "                            <a href=\"question.jsp?qid=");
                    out.print(question.getQID());
                    out.print("\">");
                    out.print(question.getTopic());
                    out.print("</a>\n"
                            + "                        </p>\n"
                            + "                        <p class=\"content\">");
                    out.print(question.getContent());
                    out.print("</p>\n"
                            + "                        <br>\n"
                            + "                    </td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"Asker\">\n"
                            + "                        asked by\n"
                            + "                        <p class=\"blue\">");
                    out.print(getUser(question.getUserID()).get(0).getEmail());
                    out.print("</p>\n"
                            + "                    </td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "            <hr>");
                }
                out.print("</div>");
            }
        } else {
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
                        + "        <br>\n"
                        + "        <div class=\"main\">\n"
                        + "            <div class=\"searchbar\">\n"
                        + "                <form id=\"searchform\" action=\"\" method=\"post\">\n"
                        + "                    <input id=\"searchbar\" type=\"text\" name=\"name\">\n"
                        + "                    <input id=\"searchbutton\" type=\"submit\" name=\"submit\" value=\"Search\"><br>\n"
                        + "                </form>\n"
                        + "                <p id=\"searchbar\">\n"
                        + "                    Cannot find what you are looking for?\n"
                        + "                    <a class=\"gold\" href=\"create.jsp\">\n"
                        + "                        Ask here\n"
                        + "                    </a>\n"
                        + "                </p>\n"
                        + "            </div>\n"
                        + "\n"
                        + "            <p id=\"RecentlyAsked\">\n"
                        + "                Recently Asked Questions\n"
                        + "            </p>\n"
                        + "            <hr>\n");
                List<Question> questions = getAllQuestion();
                for (Question question : questions) {
                    out.print("            <table>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"Votes\" rowspan=\"2\">\n"
                            + "                        <b>");
                    out.print(question.getVotes());
                    out.print("<br>\n"
                            + "                            Votes\n"
                            + "                        </b>\n"
                            + "                    </td>\n"
                            + "                    <td class=\"Answers\" rowspan=\"2\">\n"
                            + "                        <b>");
                    out.print(question.getAnswers());
                    out.print("<br>\n"
                            + "                            Answers\n"
                            + "                        </b>\n"
                            + "                    </td>\n"
                            + "                    <td>\n"
                            + "                        <p class=\"topic\">\n"
                            + "                            <a href=\"question.jsp?qid=");
                    out.print(question.getQID());
                    out.print("\">");
                    out.print(question.getTopic());
                    out.print("</a>\n"
                            + "                        </p>\n"
                            + "                        <p class=\"content\">");
                    out.print(question.getContent());
                    out.print("</p>\n"
                            + "                        <br>\n"
                            + "                    </td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td class=\"Asker\">\n"
                            + "                        asked by\n"
                            + "                        <p class=\"blue\">");
                    out.print(getUser(question.getUserID()).get(0).getEmail());
                    out.print("</p>\n");
                    if (getUserIDByToken(accessToken) == question.getUserID()) {
                        out.print("                        | <a class=\"gold\" href=\"edit.jsp?qid=");
                        out.print(question.getQID());
                        out.print("\">\n"
                                + "                            edit\n"
                                + "                        </a> | \n"
                                + "                        <a class=\"red\" href=\"");
                        out.print("DeleteQuestionServlet?qid=" + question.getQID());
                        out.print("\">\n"
                                + "                            delete\n"
                                + "                        </a>\n");
                    }
                    out.print("                    </td>\n"
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

    private java.util.List<QuestionWS.Question> getAllQuestion() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        QuestionWS.QuestionWS port = service.getQuestionWSPort();
        return port.getAllQuestion();
    }

    private java.util.List<UserWS.User> getUser(int userID) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        UserWS.UserWS port = service_1.getUserWSPort();
        return port.getUser(userID);
    }

    private int getUserIDByToken(java.lang.String accessToken) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        Token.TokenWS port = service_2.getTokenWSPort();
        return port.getUserIDByToken(accessToken);
    }
}
