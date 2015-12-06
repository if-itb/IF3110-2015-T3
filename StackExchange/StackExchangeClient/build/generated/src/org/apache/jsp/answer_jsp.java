package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import stackexchangews.Answer;
import stackexchangews.Question;

public final class answer_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("  <head>\r\n");
      out.write("      <script src=\"http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js\"></script>\r\n");
      out.write("      <title>Stack Exchange</title>\r\n");
      out.write("      <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\r\n");
      out.write("      <link href=\"css/materialize.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/>\r\n");
      out.write("      <link href=\"css/register-login.css\" type=\"text/css\" rel=\"stylesheet\" media=\"screen,projection\"/>\r\n");
      out.write("  </head>\r\n");
      out.write("  \r\n");
      out.write("  ");

      String token = (String)request.getParameter("token");
  
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("    <nav class=\"deep-purple darken-2\" role=\"navigation\">\r\n");
      out.write("      <div class=\"nav-wrapper container\"><a id=\"logo-container\" href=\"#\" class=\"brand-logo\">Stack Exchange - Answer</a>\r\n");
      out.write("        <ul class=\"right hide-on-med-and-down\">\r\n");
      out.write("          <li><a href=\"index.jsp?token=");
out.print(token);
      out.write("\">Home</a></li>\r\n");
      out.write("          <li><a href=\"register.jsp\">Register</a></li>\r\n");
      out.write("          <li><a href=\"login.jsp\">Login</a></li>\r\n");
      out.write("          <li><a href=\"index.jsp?token=null\">Logout</a></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("\r\n");
      out.write("        <ul id=\"nav-mobile\" class=\"side-nav\">\r\n");
      out.write("          <li><a href=\"index.jsp?token=");
out.print(token);
      out.write("\">Home</a></li>\r\n");
      out.write("          <li><a href=\"register.jsp\">Register</a></li>\r\n");
      out.write("          <li><a href=\"login.jsp\">Login</a></li>\r\n");
      out.write("        </ul>\r\n");
      out.write("        <a href=\"#\" data-activates=\"nav-mobile\" class=\"button-collapse\"><i class=\"material-icons\">menu</i></a>\r\n");
      out.write("      </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("    <br><br><br>\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("      <nav class=\"deep-purple darken-2\" role=\"navigation\">\r\n");
      out.write("        <div class=\"nav-wrapper\">\r\n");
      out.write("          <form>\r\n");
      out.write("            <div class=\"input-field\">\r\n");
      out.write("              <input id=\"search\" type=\"search\" required>\r\n");
      out.write("              <label for=\"search\"><i class=\"material-icons\">search</i></label>\r\n");
      out.write("              <i class=\"material-icons\">close</i>\r\n");
      out.write("            </div>\r\n");
      out.write("          </form>\r\n");
      out.write("        </div>\r\n");
      out.write("      </nav>\r\n");
      out.write("    </div>\r\n");
      out.write("    <br><br>\r\n");
      out.write("    <div class=\"section white\">\r\n");
      out.write("        ");

            Question q = (Question)request.getAttribute("question");
            String qname = (String)request.getAttribute("qname");
            out.println("<div class='container'>");
            out.println("<div class='card deep-purple darken-2'>");
            out.println("<div class='card-content white-text'>");
            out.println("<span class='card-title'>" + q.getTopic() + "</a></span>");
            out.println("<p>" + q.getContent() + "</p>");
            out.println("</div>");
            out.println("<div class='card-action'>");
            out.println("<a class='left' href='#'>Asked by " + qname + "</a>");
            out.println("<a href='votequestion?qid=" + q.getId() + "&type=1" + "&token=" + token + "'>");
            out.println("<i class='left small deep-purple darken-2 material-icons' style='padding-left:20px'>thumb_up</i>");
            out.println("</a>");
            out.println("<a class='left' style='padding-left:5px'>" + q.getVote() + "</a>");
            out.println("<a href='votequestion?qid=" + q.getId() + "&type=-1" + "&token=" + token + "'>");
            out.println("<i class='left small deep-purple darken-2 material-icons'>thumb_down</i>");
            out.println("</a>");
            out.println("<a class='right' href='edit.jsp?qid=" + q.getId() + "&token=" + token + "'>Edit</a>");
            out.println("<a class='right' href='delete?qid=" + q.getId() + "&token=" + token + "'>Delete</a>");
            out.println("</div></div></div>");
        
      out.write("\r\n");
      out.write("        <div class=\"divider\"></div>\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <form class=\"container center\">\r\n");
      out.write("              <div class=\"row\">\r\n");
      out.write("                <div class=\"input-field\">\r\n");
      out.write("                  <input name=\"content\" id=\"content\" type=\"text\" class=\"validate\">\r\n");
      out.write("                  <label for=\"content\">Comment</label>\r\n");
      out.write("                  ");

                      out.println("<input name='qid' type='hidden' value='" + request.getParameter("qid") + "'>");
                      out.println("<input name='token' type='hidden' value='" + request.getParameter("token") + "'>");
                  
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"container center\">\r\n");
      out.write("                  <button class=\"btn waves-effect waves-light deep-purple darken-2\" type=\"submit\">\r\n");
      out.write("                      Comment\r\n");
      out.write("                      <i class=\"material-icons right\">send</i>\r\n");
      out.write("                  </button>\r\n");
      out.write("              </div>\r\n");
      out.write("            </form>\r\n");
      out.write("          </div>\r\n");
      out.write("        \r\n");
      out.write("        <div class=\"divider\"></div>\r\n");
      out.write("        <h2 align=\"center\">Answers</h2>\r\n");
      out.write("        ");

            List<Answer> answers = (List<Answer>)request.getAttribute("answers");
            List<String> anames = (List<String>)request.getAttribute("anames");
            int i = 0;
            for (Answer a : answers) {
                out.println("<div class='container'>");
                out.println("<div class='card deep-purple darken-2'>");
                out.println("<div class='card-content white-text'>");
                out.println("<p>" + a.getContent() + "</p>");
                out.println("</div>");
                out.println("<div class='card-action'>");
                out.println("<a class='left' href='#'>Answered by " + anames.get(i) + "</a>");
                out.println("<a href='voteanswer?qid=" + q.getId() + "&aid=" + a.getId() + "&type=1&token=" + token + "'>");
                out.println("<i class='left small deep-purple darken-2 material-icons' style='padding-left:20px'>thumb_up</i>");
                out.println("</a>");
                out.println("<a class='left' style='padding-left:5px'>" + a.getVote() + "</a>");
                out.println("<a href='voteanswer?qid=" + q.getId() + "&aid=" + a.getId() + "&type=-1&token=" + token + "'>");
                out.println("<i class='left small deep-purple darken-2 material-icons'>thumb_down</i>");
                out.println("</a>");
                out.println("</div></div></div>");
                i++;
            }
        
      out.write("    \r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("      <form class=\"container center\" action=\"AnswerServlet\">\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("          <div class=\"input-field\">\r\n");
      out.write("            <input name=\"content\" id=\"content\" type=\"text\" class=\"validate\">\r\n");
      out.write("            <label for=\"content\">Content</label>\r\n");
      out.write("            ");

                out.println("<input name='qid' type='hidden' value='" + request.getParameter("qid") + "'>");
                out.println("<input name='token' type='hidden' value='" + request.getParameter("token") + "'>");
            
      out.write("\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"container center\">\r\n");
      out.write("            <button class=\"btn waves-effect waves-light deep-purple darken-2\" type=\"submit\">\r\n");
      out.write("                Answer\r\n");
      out.write("                <i class=\"material-icons right\">send</i>\r\n");
      out.write("            </button>\r\n");
      out.write("        </div>\r\n");
      out.write("      </form>\r\n");
      out.write("    </div>\r\n");
      out.write("    \r\n");
      out.write("    \r\n");
      out.write("        \r\n");
      out.write("    <footer class=\"page-footer deep-purple darken-2\">\r\n");
      out.write("      <div class=\"footer-copyright\">\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("          © 2015 Created by 3xcelsi0r\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </footer>\r\n");
      out.write("\r\n");
      out.write("    <script src=\"js/jquery-2.1.1.min.js\"></script>\r\n");
      out.write("    <script src=\"js/materialize.min.js\"></script>\r\n");
      out.write("    <script src=\"js/init.js\"></script>\r\n");
      out.write("  </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
