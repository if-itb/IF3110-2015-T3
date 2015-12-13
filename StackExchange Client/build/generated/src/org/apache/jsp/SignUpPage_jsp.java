package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.PrintWriter;

public final class SignUpPage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>StackExchange Sign Up</title>\r\n");
      out.write("        <meta charset=\"UTF-8\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div id=\"big\">StackExchange Register</div>\r\n");
      out.write("\t <div class=\"mediumbaru\">\r\n");
      out.write("        <form action=\"SignUpPage.jsp\" name=\"newuser\" method=\"post\">\r\n");
      out.write("\t\t<input type=\"text\" name=\"name\" placeholder=\"Name\" class=\"medium\">\r\n");
      out.write("\t\t<input type=\"email\" name=\"email\" placeholder=\"Email\" class=\"medium\">\r\n");
      out.write("\t\t<input type=\"password\" name=\"password\" placeholder=\"Password\" class=\"medium\">\r\n");
      out.write("\t\t<input type=\"submit\" value=\"Post\" id=\"button\">\r\n");
      out.write("        </form> \r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("    ");

        // TODO initialize WS operation arguments here
        java.lang.String name = request.getParameter("name");
        java.lang.String email = request.getParameter("email");
        java.lang.String password = request.getParameter("password");
        if(email!="" && email!=null) {
            try {
                registration.RegistrationWS_Service service = new registration.RegistrationWS_Service();
                registration.RegistrationWS port = service.getRegistrationWSPort();
                boolean result = port.register(name, email, password);
                if(result) 
                    response.sendRedirect(request.getContextPath() + "/LogInPage.jsp");
                else 
                    response.sendRedirect(request.getContextPath() + "/SignUpPage.jsp");
            } catch (Exception ex) {}
        }
    
      out.write("\r\n");
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
