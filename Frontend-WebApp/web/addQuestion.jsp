<%-- 
    Document   : addQuestion.jsp
    Author     : moel
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <title>Simple StackExcahange | Question</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        
        <%
            String str = request.getParameter("id");
            int qid = Integer.parseInt(str);
            String topic = request.getParameter("Topic");
            String content = request.getParameter("Content");
            String user_agent = request.getHeader("User-Agent");
            String user_ip =  request.getRemoteAddr();
            
            System.out.println("check");
            System.out.println(user_agent);
            System.out.println(user_ip);
            
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

                    QuestionModule.QuestionWS_Service qservice = new QuestionModule.QuestionWS_Service();
                    QuestionModule.QuestionWS port = qservice.getQuestionWSPort();
                    String alert;
                    String url = "";
                    if(qid != 0) {     // edit question
                      alert = port.updateQuestion(token, user_agent, user_ip,
                              qid, topic, content);
                      str = Integer.toString(qid);
                      url = "/Frontend_Webapp/displayQuestion.jsp?id=" + str;
                    } else {
                      qid = port.insertQuestion(token, user_agent, user_ip,
                              topic, content);
                      // = Integer.toString(qid);
                      if (qid<=0){
                        alert = "failed";
                        url = "index.jsp";
                      }else{
                        alert = "Success";
                        url = "/Frontend_Webapp/displayQuestion.jsp?id=" + qid;
                      }
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