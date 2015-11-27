<%-- 
    Document   : voteAnswer.jsp
    Author     : moel
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExcahange | Answer</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <%
            int qid = Integer.parseInt(request.getParameter("id"));
            String v = request.getParameter("up");
            int aid = Integer.parseInt(request.getParameter("aid"));
            
            boolean up;
            if (v.equals("true")) up=true;
            else up=false;
    
            //getting token
            String token = (String)session.getAttribute("access_token");
            if (token == null) {response.sendRedirect("signin.jsp");}
            else{
                Calendar limcal = ((Calendar)session.getAttribute("start"));
                Integer lifetime = (Integer)session.getAttribute("lifetime");
                limcal.add(Calendar.SECOND, lifetime);
                if (limcal.before(Calendar.getInstance())){
                    response.sendRedirect("signin.jsp");
                }else{
                    String str;
                    Answer.AnswerWS_Service aservice = new Answer.AnswerWS_Service();
                    Answer.AnswerWS port = aservice.getAnswerWSPort();
                    String alert;
                    String url = "";
                    
                    str = port.voteAnswer(qid,aid,up,token);

                    alert = str;
                    url = "displayQuestion.jsp?id=" + qid;
                      
                    
                    out.write("<script type='text/javascript'>\n");
                    out.write("alert('"+alert+"');\n");

                    out.write("setTimeout(function(){window.location.href='"+url+"'},1000);");
                    out.write("</script>\n");
                }
            }
        %>
    </body>
</html>
