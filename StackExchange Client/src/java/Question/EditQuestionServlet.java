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
public class EditQuestionServlet extends HttpServlet {
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/UserWS.wsdl")
    private UserWS_Service service_2;

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/StackExchange_WS/TokenWS.wsdl")
    private TokenWS_Service service_1;

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
                int qid = Integer.parseInt(request.getParameter("qid"));
                out.println("<div class=\"navbar-down\">\n"
                        + "            <b class=\"navbar-down\">\n"
                        + "                <p class=\"white\">Hello, ");
                out.println(getUser(getUserIDByToken(accessToken)).get(0).getNama());
                out.println("</p> |\n"
                        + "                <a class=\"white\" href=\"LogoutServlet\">Logout</a>\n"
                        + "            </b>\n"
                        + "        </div>\n\n");
                out.println("        <div class=\"main\">\n"
                        + "            <br>\n"
                        + "            <h2>Edit your question</h2>\n"
                        + "            <hr>");
                List<Question> question = getQuestionByQID(qid);
                for (Question question1 : question) {
                    out.println("<form name=\"editForm\" action=\"EditServlet\" method=\"post\" onsubmit=\"\">\n"
                            + "                <input name=\"qid\" type=\"hidden\" value=");
                    out.println(qid);
                    out.println(">"
                            + "                <input name=\"topic\" class=\"text\" type=\"text\" placeholder=\"Question Topic\" value=\"");
                    out.println(question1.getTopic());
                    out.println("\"><br>\n"
                            + "                <textarea name=\"content\" placeholder=\"Content\">");
                    out.println(question1.getContent());
                    out.println("</textarea>\n"
                            + "                <input class=\"button\" type=\"submit\" value=\"Post\"><br>\n"
                            + "            </form>");
                }
                out.println("</div>");
            }
        } else {
            response.sendRedirect("/StackExchange_Client/login.jsp");
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

    private int getUserIDByToken(java.lang.String accessToken) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        Token.TokenWS port = service_1.getTokenWSPort();
        return port.getUserIDByToken(accessToken);
    }

    private java.util.List<UserWS.User> getUser(int userID) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        UserWS.UserWS port = service_2.getUserWSPort();
        return port.getUser(userID);
    }

}
