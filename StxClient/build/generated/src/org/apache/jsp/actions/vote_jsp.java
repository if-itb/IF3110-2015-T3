package org.apache.jsp.actions;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class vote_jsp extends org.apache.jasper.runtime.HttpJspBase
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
 
    if(session.getAttribute("sessionName") != null){
        String category = request.getParameter("c");
        int id = Integer.parseInt(request.getParameter("id"));
        int value = Integer.parseInt(request.getParameter("val"));

        if (category.equals("q")){
            QuestionWS.QuestionWS_Service service = new QuestionWS.QuestionWS_Service();
            QuestionWS.QuestionWS port = service.getQuestionWSPort();
             // TODO initialize WS operation arguments here
            // TODO process result here
            int result = port.vote(id, session.getAttribute("token"), value);
            if(result < 0) {
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "../login.jsp");
            } else{
                out.print(result);
            }       
        } else {

            int q_id = id;
            AnswerWS.AnswerWS_Service service = new AnswerWS.AnswerWS_Service();
            AnswerWS.AnswerWS port = service.getAnswerWSPort();
             // TODO initialize WS operation arguments here
            // TODO process result here
            int result = port.voteAns(id, session.getAttribute("token"), value);
            if(result < -5) {
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "../login.jsp");
            } else{
                out.print(result);
            }
        }
            
            
    } else {
        site = "../login.jsp";
    }
    

      out.write(" \r\n");
      out.write("    ");
      out.write("<hr/>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write(" \r\n");
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
