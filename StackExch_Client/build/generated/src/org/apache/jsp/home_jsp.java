package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=ISO-8859-1");
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("  \n");
      out.write("    ");

        webservice.NewWebService service = new webservice.NewWebService();
	webservice.StackWebSevice sws = service.getStackWebSevicePort();
	java.util.List<webservice.Question> R = sws.getAllQuestions();
    
      out.write("\n");
      out.write(" \n");
      out.write("        ");
 
        String sq = request.getParameter("s");       
        int rsize = R.size();
        if (sq!=null && sq!=""){
            R = sws.searchQuestion(sq);
            rsize = R.size();
        }
        String nto = request.getParameter("ntoken");
        if (nto!=null&&nto!="") {
            String nexp = request.getParameter("nexp");
            HttpSession ss2 = request.getSession();
            ss2.setAttribute("token", nto);
            ss2.setAttribute("expire",nexp);
        }
          String to="",us="",ex="";
          int idu=0; 
          HttpSession ss = request.getSession();
          to =String.valueOf(ss.getAttribute("token"));
          us = String.valueOf(ss.getAttribute("username"));
          ex = String.valueOf(ss.getAttribute("expire"));
          if (!to.equals("null")) idu = Integer.valueOf(String.valueOf(ss.getAttribute("id")));
        
      out.write(' ');
 String ipa = request.getHeader("X-FORWARDED-FOR");  
            if (ipa == null) {  
                ipa = request.getRemoteAddr();  
            }
            String browser = request.getHeader("user-agent");
            
      out.write("\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Stack Exchange</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"style/style.css\">\n");
      out.write("    <script src=\"http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
 if (idu==0) { 
      out.write("\n");
      out.write("        <div id=\"head1\">            \n");
      out.write("            <a href=\"login.jsp\"><div id=\"login\" >Login</div></a>\n");
      out.write("            <a href=\"register.jsp\"><div id =\"reg\">Register</div></a>\n");
      out.write("        </div>\n");
      out.write("        ");
 } else { 
      out.write("\n");
      out.write("        <div id=\"head1\">            \n");
      out.write("            <a href=\"logout\"><div id=\"logout\" >Logout</div></a>\n");
      out.write("            <a href=\"\"><div id =\"hello\"> Hello , ");
 out.println(us) ; 
      out.write("</div></a>           \n");
      out.write("        </div>\n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("         <a href=\"home.jsp\"><div id=\"h\">Stack <at>Exchange</at></div></a>\n");
      out.write("        \n");
      out.write("         <form id=\"searchbox\" method=\"GET\">\n");
      out.write("             <input id=\"search\" type=\"text\" name=\"s\" placeholder=\" Type any keyword here . . . \">\n");
      out.write("             <input id=\"submit\" type=\"submit\" value=\"Search\">\n");
      out.write("        </form>\n");
      out.write("        <p>Cannot find what you are looking for ? <a href=\"askhere.jsp\"><as>Ask here</as></a></p>        \n");
      out.write("        <div class=\"raq\">Recently Asked Questions </div>\n");
      out.write("        <div class=\"separator\"></div>       \n");
      out.write("        ");
 for (int i=0; i<rsize;i++) { 
      out.write("        \n");
      out.write("            <div class =\"info\">\n");
      out.write("                <div class =\"vote\">\n");
      out.write("                    <div class=\"vnum\"> ");
 out.println(R.get(i).getVote()); 
      out.write("</div>\n");
      out.write("                    <div class=\"vname\">Votes</div>           \n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class =\"answers\">\n");
      out.write("                    <div class=\"vnum\">");
 out.println(R.get(i).getTanswer()); 
      out.write("</div>\n");
      out.write("                    <div class=\"vname\">Answers</div>           \n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"thread\">\n");
      out.write("                    <a href=\"open.jsp?id=");
out.println(R.get(i).getIdQ());
      out.write("\"><div class=\"title\">");
 out.println(R.get(i).getTitle()); 
      out.write("</div></a>\n");
      out.write("                    <div class = \"content\">                     \n");
      out.write("                        ");

                        String c = R.get(i).getContent();
                        if (c.length() > 150) c = c.substring(0,149) + " . . . ";
                        out.println(c); 
      out.write("<br>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("               </div>\n");
      out.write("                <div class=\"utility\">\n");
      out.write("                    <aa>asked by : </aa>\n");
      out.write("                    <a1> ");
 out.println(R.get(i).getUsername()); 
      out.write("</a1>|\n");
      out.write("                    <a href=\"askhere.jsp?id=");
 out.println(R.get(i).getIdQ()) ; 
      out.write("\"><span class=\"bb\">edit</span></a> |\n");
      out.write("                    <a onclick =\"return confirm('Are you sure to delete this question ?')\" href=\"process.jsp?action=del&id=");
 out.println(R.get(i).getIdQ()) ;
      out.write("&owner=");
 out.println(R.get(i).getUsername());
      out.write("\"><span class=\"cc\">delete</span></a>\n");
      out.write("                </div>\n");
      out.write("             <div class=\"uline\"></div>       \n");
      out.write("        ");
 } 
      out.write("\n");
      out.write("        \n");
      out.write("       \n");
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
