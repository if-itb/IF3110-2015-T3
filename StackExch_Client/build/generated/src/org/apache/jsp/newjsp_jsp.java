package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class newjsp_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html ng-app ='voteApp'>\n");
      out.write("<script src=\"http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.js\"></script>\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-2.1.1.min.js\"></script>\n");
      out.write("    \n");
      out.write("<div ng-controller = 'commentController'>\n");
      out.write("     \"<h4>Comments</h4>\"\n");
      out.write("       <div ng-repeat='comment in comments' class='block-comment'>\n");
      out.write("                                <div class='bc-content'>\n");
      out.write("                                    {{comment.content}}\n");
      out.write("                                     - \n");
      out.write("                                    <a id='color-blue'>\n");
      out.write("                                    {{comment.author}}\n");
      out.write("                                    </a>- {{comment.timestamp}}\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                       \n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("  <script>\n");
      out.write("       \n");
      out.write("         var app = angular.module('voteApp',[]);\n");
      out.write("        \n");
      out.write("        console.log(\"Start\");\n");
      out.write("        app.controller('commentController', function($scope, $http) {\n");
      out.write("            var commentUrl = \"http://localhost:35476/CommentVote/AllComment\"; \n");
      out.write("            var q_id = \"1\";\n");
      out.write("            var access_token = \"aaaaa\";\n");
      out.write("            console.log(q_id);\n");
      out.write("            var commentParameter = {question_id: q_id, access_token: access_token};\n");
      out.write("            console.log(JSON.stringify(commentParameter));\n");
      out.write("            $http({\n");
      out.write("                     url: commentUrl,\n");
      out.write("                     data: JSON.stringify(commentParameter),\n");
      out.write("                     method: 'POST',\n");
      out.write("                     dataType: \"json\",\n");
      out.write("                     crossDomain: true\n");
      out.write("                 })\n");
      out.write("                 .then(function (response){\n");
      out.write("                     console.log(\"Success\");\n");
      out.write("                     $scope.comments = [];\n");
      out.write("                     $scope.comments = response.data.comments;\n");
      out.write("                     console.log(JSON.stringify($scope.comments));\n");
      out.write("                     /*if ($scope.message == 1 || $scope.message == -5){\n");
      out.write("                     } else {\n");
      out.write("                         window.location.href = \"http://localhost:8080/StackExchange_Client/error.jsp?id=\" + $scope.message + \"&token=\" + access_token;\n");
      out.write("                     }*/\n");
      out.write("                 },function (err){\n");
      out.write("                     console.log(\"Error : \" + err);\n");
      out.write("                 });    \n");
      out.write("                \n");
      out.write("                $scope.comment_content = \"Initialization\";\n");
      out.write("                \n");
      out.write("              \n");
      out.write("        \n");
      out.write("        });\n");
      out.write("    \n");
      out.write("    </script>\n");
      out.write("\n");
      out.write("</body>\n");
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
