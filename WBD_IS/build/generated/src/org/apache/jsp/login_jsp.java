package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>Login Page</title>\r\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\r\n");
      out.write("        <script type=\"text/javascript\" src=\"js/modernizr.custom.86080.js\"></script>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("    <ul class=\"cb-slideshow\">\r\n");
      out.write("        <li><span>Image 01</span></li>\r\n");
      out.write("        <li><span>Image 02</span></li>\r\n");
      out.write("        <li><span>Image 03</span></li>\r\n");
      out.write("        <li><span>Image 04</span></li>\r\n");
      out.write("        <li><span>Image 05</span></li>\r\n");
      out.write("        <li><span>Image 06</span></li>\r\n");
      out.write("        \r\n");
      out.write("    </ul>\r\n");
      out.write("    \r\n");
      out.write("        <div class=\"grad\"></div>\r\n");
      out.write("\t\t<div class=\"header\">\r\n");
      out.write("\t\t\t<div>Stack<span>Exchange</span></div>\r\n");
      out.write("                        <br>\r\n");
      out.write("                        <div>Login<span>!</span></div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("                <div class=\"login\">\r\n");
      out.write("                    <form method=\"POST\" action=\"http://localhost:8082/WBD_IS/testrestservlet\">\r\n");
      out.write("                        <input type=\"text\" placeholder=\"username\" name=\"username\"><br>\r\n");
      out.write("                        <input type=\"password\" placeholder=\"password\" name=\"password\"><br>\r\n");
      out.write("                        <input type=\"submit\" value=\"login\"><br>\r\n");
      out.write("                        <p><a href=\"\">forgot password?</a></p>\r\n");
      out.write("                    </form>\r\n");
      out.write("                    \r\n");
      out.write("                    <br><br><br><br>\r\n");
      out.write("                    <p>\r\n");
      out.write("                        Don't have an account yet ?\r\n");
      out.write("                        <a href=\"http://localhost:8080/StackExchangeFE/register.jsp\">Sign Up</a>\r\n");
      out.write("                    </p>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\r\n");
      out.write("    </body>\r\n");
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
