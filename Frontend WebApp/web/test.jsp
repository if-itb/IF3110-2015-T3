<%-- 
    Document   : test
    Created on : Nov 17, 2015, 5:10:19 PM
    Author     : Devina
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            List<AnswerWS.Answer> answers = new ArrayList<AnswerWS.Answer>();
            if (request.getAttribute("result") != null) {
              answers = (ArrayList<AnswerWS.Answer>)request.getAttribute("result");
              for (int i = 0; i < answers.size(); i++) {
                out.println(answers.get(i).getContent());
              }
            } else {
              out.println("no values");
            }
         %>
    </body>
</html>
