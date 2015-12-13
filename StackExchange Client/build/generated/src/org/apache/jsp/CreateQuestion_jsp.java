package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.json.simple.JSONObject;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public final class CreateQuestion_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\"/>\n");
      out.write("\t<title>Questions</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("\t <div id=\"big\">Simple StackExchange</div>\n");
      out.write("\t <div class=\"mediumbaru\">\n");
      out.write("\t <div id=\"m1\">What's your question?</div>\n");
      out.write("\t <form name=\"makequestion\" method=\"post\" action=\"CreateQuestion.jsp\" >\n");
      out.write("\t\t<input type=\"text\" name=\"question\" placeholder=\"Question Topic\" class=\"medium\">\n");
      out.write("\t\t<textarea name=\"content\" placeholder=\"Content\" class=\"medium\" id=\"content\"></textarea> \n");
      out.write("\t\t<input type=\"submit\" value=\"Post\" id=\"button\">\n");
      out.write("\t </form></div>\n");
      out.write("         ");

            String title = request.getParameter("question");
            String content = request.getParameter("content");            
            if(title!=null && title!="") {
                String token = "";
                Cookie[] cookies = request.getCookies();
                if(cookies==null) {      
                    System.out.println("COOKIES NULL");
                }
                else {                
                    for(Cookie cookie : cookies) {
                        if("token".equals(cookie.getName())) { 
                            token = cookie.getValue();
                            System.out.println(token);
                            break;
                        }   
                    }
                }
                try {
                    questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
                    questionmodel.QuestionWS port = service.getQuestionWSPort();
                    int result = port.createQuestion(token, title, content);
                    if(result==1) 
                        response.sendRedirect(request.getContextPath() + "/CreateQuestion.jsp");
                    else if(result==0)
                        response.sendRedirect(request.getContextPath() + "/LogInPage.jsp");
                    else
                        response.sendRedirect(request.getContextPath() + "/SignUpPage.jsp");                         
                } catch (Exception ex) {}                
            }
         
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>");
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
