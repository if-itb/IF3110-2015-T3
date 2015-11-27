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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Login Page</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\">\n");
      out.write("        <script type=\"text/javascript\" src=\"js/modernizr.custom.86080.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("    <ul class=\"cb-slideshow\">\n");
      out.write("        <li><span>Image 01</span></li>\n");
      out.write("        <li><span>Image 02</span></li>\n");
      out.write("        <li><span>Image 03</span></li>\n");
      out.write("        <li><span>Image 04</span></li>\n");
      out.write("        <li><span>Image 05</span></li>\n");
      out.write("        <li><span>Image 06</span></li>\n");
      out.write("        \n");
      out.write("    </ul>\n");
      out.write("    \n");
      out.write("        <div class=\"grad\"></div>\n");
      out.write("\t\t<div class=\"header\">\n");
      out.write("\t\t\t<div>Stack<span>Exchange</span></div>\n");
      out.write("                        <br>\n");
      out.write("                        <div>Login<span>!</span></div>\n");
      out.write("\t\t</div>\n");
      out.write("                <div class=\"login\">\n");
      out.write("                    <form method=\"POST\" action=\"http://localhost:8082/WBD_IS/testrestservlet\">\n");
      out.write("                        <input type=\"text\" placeholder=\"username\" name=\"username\"><br>\n");
      out.write("                        <input type=\"password\" placeholder=\"password\" name=\"password\"><br>\n");
      out.write("                        <input type=\"submit\" value=\"login\"><br>\n");
      out.write("                        <p><a href=\"\">forgot password?</a></p>\n");
      out.write("                    </form>\n");
      out.write("\t\t</div>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
