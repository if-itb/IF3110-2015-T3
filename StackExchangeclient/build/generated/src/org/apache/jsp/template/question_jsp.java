package org.apache.jsp.template;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class question_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <title>Question | Overflow48</title>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"../assets/css/style.css\">\r\n");
      out.write("    <link rel=\"icon\" type=\"image/png\" href=\"../assets/white-icon.jpg\">\r\n");
      out.write("  </head>\r\n");
      out.write("  <body>\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("      <h1 class=\"text-center\">OVERFLOW48</h1>\r\n");
      out.write("      <br/> <h2>Overflow48 vs AKB48</h2>\r\n");
      out.write("\r\n");
      out.write("      <div class=\"question\">\r\n");
      out.write("        <hr class=\"line\">\r\n");
      out.write("        <div class=\"item\">\r\n");
      out.write("          <div class=\"vote\">\r\n");
      out.write("            <table width=\"100%\" class=\"text-center\">\r\n");
      out.write("              <tr><td><div class=\"arrow\">&#9650;</div></td></tr>\r\n");
      out.write("              <tr><td>6</td></tr>\r\n");
      out.write("              <tr><td><div class=\"arrow\">&#9660;</div></td></tr>\r\n");
      out.write("            </table>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"text-long\">\r\n");
      out.write("            <p>Overflow48 baru saja rilis. Apakah web ini related dengan AKB48?<br><br>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"text-right\">\r\n");
      out.write("            <p>asked by Fans AKB48 at 8 Oktober 2015 22:44 | edit | delete</p>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("      \r\n");
      out.write("      <br/> <h2>2 Answers</h2>\r\n");
      out.write("\r\n");
      out.write("      <div class=\"question\">\r\n");
      out.write("        <hr class=\"line\">\r\n");
      out.write("        <div class=\"item\">\r\n");
      out.write("          <div class=\"vote\">\r\n");
      out.write("            <table width=\"100%\" class=\"text-center\">\r\n");
      out.write("              <tr><td><div class=\"arrow\">&#9650;</div></td></tr>\r\n");
      out.write("              <tr><td>21</td></tr>\r\n");
      out.write("              <tr><td><div class=\"arrow\">&#9660;</div></td></tr>\r\n");
      out.write("            </table>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"text-long\">\r\n");
      out.write("            <p>Tidak.<br><br>Karena lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam nunc tellus, aliquam eget ex a, vestibulum tempor odio. Curabitur est mauris, semper sit amet bibendum sit amet, rutrum dignissim metus. Aenean posuere rhoncus pulvinar. Morbi egestas neque ipsum, eu laoreet lectus fermentum nec. Nunc enim velit, vulputate in aliquam id, iaculis vel purus. Donec in velit nunc. In pharetra justo quam, vel ornare ipsum mattis vel. In vestibulum pharetra nulla eget tincidunt. Vivamus sed neque et nulla consequat faucibus. Morbi id erat vel risus euismod vehicula porta sit amet nunc. </p>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"text-right\">\r\n");
      out.write("            answered by Sang Penjaga Hutan at 8 Oktober 2015 22:46\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("\r\n");
      out.write("      <div class=\"question\">\r\n");
      out.write("        <hr class=\"line\">\r\n");
      out.write("        <div class=\"item\">\r\n");
      out.write("          <div class=\"vote\">\r\n");
      out.write("            <table width=\"100%\" class=\"text-center\">\r\n");
      out.write("              <tr><td><div class=\"arrow\">&#9650;</div></td></tr>\r\n");
      out.write("              <tr><td>-5</td></tr>\r\n");
      out.write("              <tr><td><div class=\"arrow\">&#9660;</div></td></tr>\r\n");
      out.write("            </table>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"text-long\">\r\n");
      out.write("            <p>Ndassmu! In mollis congue massa id vehicula. Suspendisse quis ornare sem. Suspendisse potenti. Mauris semper sodales porta. Curabitur nec tincidunt libero. Integer commodo vehicula turpis, quis feugiat magna pellentesque eget. Quisque mi mauris, molestie eget justo nec, hendrerit eleifend lorem. Donec pharetra pharetra mi, ut condimentum felis venenatis eget. Nunc iaculis sem nisi, sit amet ullamcorper nisl aliquam eget. Integer feugiat dolor et est commodo pellentesque. Mauris et risus et ligula tempor pharetra in id dui. Nunc at elit nulla. Suspendisse ac convallis felis, eu fringilla elit. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. </p>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"text-right\">\r\n");
      out.write("            answered by Kadal Juanc*k at 8 Oktober 2015 22:50\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("\r\n");
      out.write("      <br/><h3>Your Answer</h3>\r\n");
      out.write("\r\n");
      out.write("      <form action=\"/controller/answer.php\" action=\"POST\">\r\n");
      out.write("        <input placeholder=\"Name\" class=\"form\" type=\"text\" name=\"authorName\">\r\n");
      out.write("        <input placeholder=\"Email\" class=\"form\" type=\"text\" name=\"authorEmail\">\r\n");
      out.write("        <textarea placeholder=\"Content\" class=\"box\" name=\"content\"></textarea>\r\n");
      out.write("        <div class=\"text-right\">\r\n");
      out.write("            <button class=\"button\" class=\"text-right\" type=\"submit\">Post</button>\r\n");
      out.write("        </div>\r\n");
      out.write("      </form>\r\n");
      out.write("    </div>\r\n");
      out.write("  </body>\r\n");
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
