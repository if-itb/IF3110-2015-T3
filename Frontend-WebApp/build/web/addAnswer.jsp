<%-- 
    Document   : addAnswer.jsp
    Author     : moel
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <title>Simple StackExcahange | Answer</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        
        <%
            String str = request.getParameter("id");
            int qid = Integer.parseInt(str);
            String content = request.getParameter("Content");
            
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

                    Answer.AnswerWS_Service aservice = new Answer.AnswerWS_Service();
                    Answer.AnswerWS port = aservice.getAnswerWSPort();
                    String alert;
                    String url = "";
                    
                    str = port.insertAnswer(token, qid, content);
                    // = Integer.toString(qid);
                    if (!str.equals("Success")){
                      alert = str;
                      url = "index.jsp";
                    }else{
                      alert = "Success";
                      url = "/Frontend_Webapp/displayQuestion.jsp?id=" + qid;
                      
                    }

                    out.write("<script type='text/javascript'>\n");
                    out.write("alert('"+alert+"');\n");

                    out.write("setTimeout(function(){window.location.href='"+url+"'},1000);");
                    out.write("</script>\n");
                }
            }

        %>
    </body>
    

</html>