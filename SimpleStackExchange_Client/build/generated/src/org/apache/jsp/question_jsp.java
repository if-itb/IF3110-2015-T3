package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import tool.Util;

public final class question_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_set_var_value_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_if_test;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_set_var_value_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_if_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_c_set_var_value_nobody.release();
    _jspx_tagPool_c_if_test.release();
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

      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html lang=\"en\"><head>\r\n");
      out.write("    <meta charset=\"utf-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->\r\n");
      out.write("    <meta name=\"description\" content=\"\">\r\n");
      out.write("    <meta name=\"author\" content=\"\">\r\n");
      out.write("    <link rel=\"icon\" href=\"assets/img/favicon.ico\">\r\n");
      out.write("\r\n");
      out.write("    <title>Simple StackExchange</title>\r\n");
      out.write("\r\n");
      out.write("    <!-- Bootstrap core CSS -->\r\n");
      out.write("    <link href=\"assets/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    \r\n");
      out.write("    <script src=\"assets/js/angular.min.js\"></script>\r\n");
      out.write("    <script src=\"assets/js/app.js\"></script>\r\n");
      out.write("\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body>\r\n");
      out.write("      \r\n");
      out.write("      ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "template/navbar.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isLogin", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(Util.isLogin(request)), request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("    \r\n");
      out.write("    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "template/userinfo.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("isLogin", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf(Util.isLogin(request)), request.getCharacterEncoding()) + "&" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("userEmail", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode(String.valueOf("test"), request.getCharacterEncoding()), out, false);
      out.write("\r\n");
      out.write("      \r\n");
      out.write("      <div class=\"container\">\r\n");
      out.write("        ");
      if (_jspx_meth_c_if_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\r\n");
      out.write("        ");
      if (_jspx_meth_c_if_1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        <div class=\"row\">\r\n");
      out.write("             \r\n");
      out.write("            <div class=\"col-sm-12\">\r\n");
      out.write("                <h2>\r\n");
      out.write("                    <a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/question?qid=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getQid()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\">\r\n");
      out.write("                    ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getTopic()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("                    </a>\r\n");
      out.write("                </h2>\r\n");
      out.write("                <hr>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("           \r\n");
      out.write("            <div class=\"question-item row\">\r\n");
      out.write("            <div class=\"col-sm-1 \">\r\n");
      out.write("                ");
      if (_jspx_meth_c_set_0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                ");
      if (_jspx_meth_c_set_1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                ");
 if (Util.isLogin(request)) { 
      out.write("\r\n");
      out.write("                    <div class=\"btn-group-vertical\" role=\"group\" aria-label=\"...\">\r\n");
      out.write("                        <form action=\"QuestionVote\" method=\"POST\">\r\n");
      out.write("                        <input name=\"qid\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getQid()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\r\n");
      out.write("                        <input name=\"value\" type=\"hidden\" value=\"1\" />\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-success\r\n");
      out.write("                                ");
 
                                int qid = (Integer)pageContext.getAttribute("qid");
                                int uid = Util.getUid(request);
                                if(Util.hasVotedQuestion(qid, uid, 1)) {
                                out.print("disabled");
                                }
                                
      out.write("\r\n");
      out.write("                                \">\r\n");
      out.write("                            <span class=\"glyphicon glyphicon-chevron-up\" aria-hidden=\"true\"></span>\r\n");
      out.write("                        </button>\r\n");
      out.write("                        </form>\r\n");
      out.write("                        \r\n");
      out.write("                        <div class=\"text-center well-lg\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getCountvotes()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>\r\n");
      out.write("                        <form action=\"QuestionVote\" method=\"POST\">\r\n");
      out.write("                        <input name=\"qid\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getQid()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" />\r\n");
      out.write("                        <input name=\"value\" type=\"hidden\" value=\"-1\" />\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-danger\r\n");
      out.write("                                 ");
 
                                if(Util.hasVotedQuestion(qid, uid, -1)) {
                                out.print("disabled");
                                }
                                
      out.write("\r\n");
      out.write("                                \">\r\n");
      out.write("                            <span class=\"glyphicon glyphicon-chevron-down\" aria-hidden=\"true\"></span>\r\n");
      out.write("                        </button>\r\n");
      out.write("                        </form>\r\n");
      out.write("                    </div>\r\n");
      out.write("                ");
} else {
      out.write("\r\n");
      out.write("                    <div class=\"btn-group-vertical\" role=\"group\" aria-label=\"...\">\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-success disabled\">\r\n");
      out.write("                            <span class=\"glyphicon glyphicon-chevron-up\" aria-hidden=\"true\"></span>\r\n");
      out.write("                        </button>\r\n");
      out.write("                        <div class=\"text-center well-lg\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getCountvotes()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</div>\r\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-danger disabled\">\r\n");
      out.write("                            <span class=\"glyphicon glyphicon-chevron-down\" aria-hidden=\"true\"></span>\r\n");
      out.write("                        </button>\r\n");
      out.write("                    </div>\r\n");
      out.write("                ");
}
      out.write("\r\n");
      out.write("                    \r\n");
      out.write("            </div><!-- end of col-sm-1 -->\r\n");
      out.write("          \r\n");
      out.write("            <div class=\"col-sm-11\">\r\n");
      out.write("            <div class=\"panel panel-default\">\r\n");
      out.write("                <div class=\"panel-body\">\r\n");
      out.write("                    <p>\r\n");
      out.write("                        ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getContent()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("                    </p>\r\n");
      out.write("                  <span class=\"pull-right\">\r\n");
      out.write("                      <button type=\"button\" class=\"btn btn-default transparent\">\r\n");
      out.write("                        <span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getValue()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" at ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getCreatedtime()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\r\n");
      out.write("                      </button>\r\n");
      out.write("                      \r\n");
      out.write("                      ");
      if (_jspx_meth_c_set_2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("                    ");
 if(Util.isAuthUser(request,(Integer)pageContext.getAttribute("uid"))){ 
      out.write("  \r\n");
      out.write("                      <button type=\"button\" class=\"btn btn-warning \" aria-label=\"Edit\">\r\n");
      out.write("                        <a class=\"glyphicon glyphicon-pencil white\" aria-hidden=\"true\" href=\"edit?qid=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getQid()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("&save=0\"></a>\r\n");
      out.write("                      </button>\r\n");
      out.write("                      <button type=\"button\" class=\"btn btn-danger\" aria-label=\"Delete\">\r\n");
      out.write("                        <a class=\"glyphicon glyphicon-trash white\" aria-hidden=\"true\" href=\"delete?qid=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getQid()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\"></a>\r\n");
      out.write("                      </button>\r\n");
      out.write("                  ");
 }
      out.write("\r\n");
      out.write("                  </span>\r\n");
      out.write("                </div>\r\n");
      out.write("               \r\n");
      out.write("            </div>\r\n");
      out.write("        </div><!-- end of col-sm-11 -->\r\n");
      out.write("        \r\n");
      out.write("        ");
 if (Util.isLogin(request)) { 
      out.write("\r\n");
      out.write("          <form class=\"form-horizontal\" role=\"form\" action=\"AnswerCreate\" method=\"POST\">\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"col-md-12\">\r\n");
      out.write("                    <textarea class=\"form-control\" rows=\"5\" id=\"answer\" name=\"answer\"></textarea>\r\n");
      out.write("                    </div>\r\n");
      out.write("                  </div>\r\n");
      out.write("              <input name=\"qid\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getQid()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" type=\"hidden\" />\r\n");
      out.write("                <div class=\"form-group\"> \r\n");
      out.write("                  <div class=\"col-md-12\">\r\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-info btn-block\">Post</button>\r\n");
      out.write("                  </div>\r\n");
      out.write("                </div>\r\n");
      out.write("              </form>\r\n");
      out.write("            ");
} else {
      out.write("\r\n");
      out.write("                <div class=\"col-md-12 alert alert-danger\" role=\"alert\">\r\n");
      out.write("                    Please\r\n");
      out.write("                    <span>\r\n");
      out.write("                        <a class=\"btn btn-default btn-sm\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/login.jsp\" role=\"button\">Login now!</a>\r\n");
      out.write("                    </span> to answer the question!\r\n");
      out.write("                </div>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("        \r\n");
      out.write("        <div class=\"col-sm-12\">\r\n");
      out.write("            <h2>\r\n");
      out.write("                ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getCountanswers()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" Answers\r\n");
      out.write("            </h2>\r\n");
      out.write("            <hr>\r\n");
      out.write("        </div>\r\n");
      out.write("        ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
      _jspx_th_c_forEach_0.setParent(null);
      _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${answers}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
      _jspx_th_c_forEach_0.setVar("answer");
      int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
      try {
        int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
        if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("        <div class=\"row\">\r\n");
            out.write("            <div class=\"col-sm-1 col-sm-offset-1\">\r\n");
            out.write("                \r\n");
            out.write("                ");
            if (_jspx_meth_c_set_3((javax.servlet.jsp.tagext.JspTag) _jspx_th_c_forEach_0, _jspx_page_context, _jspx_push_body_count_c_forEach_0))
              return;
            out.write("\r\n");
            out.write("                ");
 if (Util.isLogin(request)) { 
            out.write("\r\n");
            out.write("                \r\n");
            out.write("                    <div class=\"btn-group-vertical\" role=\"group\" aria-label=\"...\">\r\n");
            out.write("                        <form method=\"POST\" action=\"AnswerVote\">\r\n");
            out.write("                        <input type=\"hidden\" name=\"aid\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${answer.getKey().getAid()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\" />\r\n");
            out.write("                        <input type=\"hidden\" name=\"qid\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${answer.getKey().getQid()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\" />\r\n");
            out.write("                        <input type=\"hidden\" name=\"value\" value=\"1\" />\r\n");
            out.write("                        <button type=\"submit\" class=\"btn btn-success\r\n");
            out.write("                                ");

                                int aid = (Integer)pageContext.getAttribute("aid");
                                int uid = Util.getUid(request);
                                if(Util.hasVotedAnswer(aid, uid, 1)) {
                                out.print("disabled");
                                }
                                
            out.write("\r\n");
            out.write("                                \">\r\n");
            out.write("                            <span class=\"glyphicon glyphicon-chevron-up\" aria-hidden=\"true\"></span>\r\n");
            out.write("                        </button>\r\n");
            out.write("                        </form>\r\n");
            out.write("                        <div class=\"text-center well-lg\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${answer.getKey().getCountvotes()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("</div>\r\n");
            out.write("                        <form method=\"POST\" action=\"AnswerVote\">\r\n");
            out.write("                        <input type=\"hidden\" name=\"aid\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${answer.getKey().getAid()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\" />\r\n");
            out.write("                        <input type=\"hidden\" name=\"qid\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${answer.getKey().getQid()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\" />\r\n");
            out.write("                        <input type=\"hidden\" name=\"value\" value=\"-1\" />\r\n");
            out.write("                        <button type=\"submit\" class=\"btn btn-danger\r\n");
            out.write("                                 ");

                                if(Util.hasVotedAnswer(aid, uid, -1)) {
                                out.print("disabled");
                                }
                                
            out.write("\r\n");
            out.write("                                \">\r\n");
            out.write("                            <span class=\"glyphicon glyphicon-chevron-down\" aria-hidden=\"true\"></span>\r\n");
            out.write("                        </button>\r\n");
            out.write("                        </form>\r\n");
            out.write("                    </div>\r\n");
            out.write("                ");
} else {
            out.write("\r\n");
            out.write("                    <div class=\"btn-group-vertical\" role=\"group\" aria-label=\"...\">\r\n");
            out.write("                        <button type=\"submit\" class=\"btn btn-success disabled\">\r\n");
            out.write("                            <span class=\"glyphicon glyphicon-chevron-up\" aria-hidden=\"true\"></span>\r\n");
            out.write("                        </button>\r\n");
            out.write("                        <div class=\"text-center well-lg\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${answer.getKey().getCountvotes()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("</div>\r\n");
            out.write("                        <button type=\"submit\" class=\"btn btn-danger disabled\">\r\n");
            out.write("                            <span class=\"glyphicon glyphicon-chevron-down\" aria-hidden=\"true\"></span>\r\n");
            out.write("                        </button>\r\n");
            out.write("                    </div>\r\n");
            out.write("                ");
}
            out.write("\r\n");
            out.write("            </div><!-- end of col-sm-1 -->\r\n");
            out.write("            <div class=\"col-sm-10\">\r\n");
            out.write("            <div class=\"panel panel-default\">\r\n");
            out.write("                <div class=\"panel-body\">\r\n");
            out.write("                    <p>\r\n");
            out.write("                        ");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${answer.getKey().getContent()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\r\n");
            out.write("                    </p>\r\n");
            out.write("                  <span class=\"pull-right\">\r\n");
            out.write("                      <button type=\"button\" class=\"btn btn-default transparent\">\r\n");
            out.write("                        <span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span> ");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${answer.getValue()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write(" at ");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${answer.getKey().getCreatedtime()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
            out.write("\r\n");
            out.write("                      </button>\r\n");
            out.write("                  </span>\r\n");
            out.write("                </div>\r\n");
            out.write("               \r\n");
            out.write("            </div>\r\n");
            out.write("        </div><!-- end of col-sm-10 -->\r\n");
            out.write("        </div>\r\n");
            out.write("        </div><!-- end of question-item -->\r\n");
            out.write("\r\n");
            out.write("        ");
            int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_forEach_0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_forEach_0.doFinally();
        _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
      }
      out.write("\r\n");
      out.write("        \r\n");
      out.write("      <div class=\"col-sm-12\">\r\n");
      out.write("            <h2>\r\n");
      out.write("                Answer Question!\r\n");
      out.write("            </h2>\r\n");
      out.write("            <hr>\r\n");
      out.write("        </div>\r\n");
      out.write("      \r\n");
      out.write("          ");
 if (Util.isLogin(request)) { 
      out.write("\r\n");
      out.write("          <form class=\"form-horizontal\" role=\"form\" action=\"AnswerCreate\" method=\"POST\">\r\n");
      out.write("                <div class=\"form-group\">\r\n");
      out.write("                    \r\n");
      out.write("                    <div class=\"col-md-12\">\r\n");
      out.write("                    <textarea class=\"form-control\" rows=\"5\" id=\"answer\" name=\"answer\"></textarea>\r\n");
      out.write("                    </div>\r\n");
      out.write("                  </div>\r\n");
      out.write("              <input name=\"qid\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getQid()}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" type=\"hidden\" />\r\n");
      out.write("                <div class=\"form-group\"> \r\n");
      out.write("                  <div class=\"col-md-12\">\r\n");
      out.write("                    <button type=\"submit\" class=\"btn btn-info btn-block\">Post</button>\r\n");
      out.write("                  </div>\r\n");
      out.write("                </div>\r\n");
      out.write("              </form>\r\n");
      out.write("            ");
} else {
      out.write("\r\n");
      out.write("                <div class=\"col-md-12 alert alert-danger\" role=\"alert\">\r\n");
      out.write("                    Please\r\n");
      out.write("                    <span>\r\n");
      out.write("                        <a class=\"btn btn-default btn-sm\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${pageContext.request.contextPath}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/login.jsp\" role=\"button\">Login now!</a>\r\n");
      out.write("                    </span> to answer the question!\r\n");
      out.write("                </div>\r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("      \r\n");
      out.write("      <footer class=\"col-md-12\">\r\n");
      out.write("          <p class=\"text-center\">© Simple StackExchage 2015</p>\r\n");
      out.write("      </footer>\r\n");
      out.write("    </div> <!-- /container -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <!-- Bootstrap core JavaScript\r\n");
      out.write("    ================================================== -->\r\n");
      out.write("    <!-- Placed at the end of the document so the pages load faster -->\r\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js\"></script>\r\n");
      out.write("    <script src=\"assets/js/bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</body></html>\r\n");
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

  private boolean _jspx_meth_c_if_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_0.setPageContext(_jspx_page_context);
    _jspx_th_c_if_0.setParent(null);
    _jspx_th_c_if_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${statustoken == -1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_0 = _jspx_th_c_if_0.doStartTag();
    if (_jspx_eval_c_if_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("            <div class=\"alert alert-danger\" role=\"alert\">Your token has been expired!</div>\r\n");
        out.write("        ");
        int evalDoAfterBody = _jspx_th_c_if_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_0);
    return false;
  }

  private boolean _jspx_meth_c_if_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_if_1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _jspx_tagPool_c_if_test.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_if_1.setPageContext(_jspx_page_context);
    _jspx_th_c_if_1.setParent(null);
    _jspx_th_c_if_1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${statustoken == 0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null)).booleanValue());
    int _jspx_eval_c_if_1 = _jspx_th_c_if_1.doStartTag();
    if (_jspx_eval_c_if_1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("            <div class=\"alert alert-danger\" role=\"alert\">Your token is invalid!</div>\r\n");
        out.write("        ");
        int evalDoAfterBody = _jspx_th_c_if_1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_if_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
      return true;
    }
    _jspx_tagPool_c_if_test.reuse(_jspx_th_c_if_1);
    return false;
  }

  private boolean _jspx_meth_c_set_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_0.setPageContext(_jspx_page_context);
    _jspx_th_c_set_0.setParent(null);
    _jspx_th_c_set_0.setVar("uid");
    _jspx_th_c_set_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getUid()}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_set_0 = _jspx_th_c_set_0.doStartTag();
    if (_jspx_th_c_set_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
      return true;
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_0);
    return false;
  }

  private boolean _jspx_meth_c_set_1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_1 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_1.setPageContext(_jspx_page_context);
    _jspx_th_c_set_1.setParent(null);
    _jspx_th_c_set_1.setVar("qid");
    _jspx_th_c_set_1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getQid()}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_set_1 = _jspx_th_c_set_1.doStartTag();
    if (_jspx_th_c_set_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_1);
      return true;
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_1);
    return false;
  }

  private boolean _jspx_meth_c_set_2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_2 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_2.setPageContext(_jspx_page_context);
    _jspx_th_c_set_2.setParent(null);
    _jspx_th_c_set_2.setVar("uid");
    _jspx_th_c_set_2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${question.getKey().getUid()}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_set_2 = _jspx_th_c_set_2.doStartTag();
    if (_jspx_th_c_set_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_2);
      return true;
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_2);
    return false;
  }

  private boolean _jspx_meth_c_set_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_forEach_0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_forEach_0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:set
    org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_set_3 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _jspx_tagPool_c_set_var_value_nobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
    _jspx_th_c_set_3.setPageContext(_jspx_page_context);
    _jspx_th_c_set_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_forEach_0);
    _jspx_th_c_set_3.setVar("aid");
    _jspx_th_c_set_3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${answer.getKey().getAid()}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    int _jspx_eval_c_set_3 = _jspx_th_c_set_3.doStartTag();
    if (_jspx_th_c_set_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_3);
      return true;
    }
    _jspx_tagPool_c_set_var_value_nobody.reuse(_jspx_th_c_set_3);
    return false;
  }
}
