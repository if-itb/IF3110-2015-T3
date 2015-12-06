<%-- 
    Document   : createAnswer
    Created on : Nov 25, 2015, 10:05:29 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <%-- start web service invocation --%><hr/>
    <%
    try {
	com.wbd.ans.AnswerWS_Service service = new com.wbd.ans.AnswerWS_Service();
	com.wbd.ans.AnswerWS port = service.getAnswerWSPort();
	 // TODO initialize WS operation arguments here
        Cookie[] cookieArray = request.getCookies();
                String theCookie = "null";
                if (cookieArray != null){
                    for (int i = 0; i < cookieArray.length; i++){
                        if(cookieArray[i].getName().equals("access_token")){
                            theCookie = cookieArray[i].getValue();
                            break;
                        }
                    }
                }
	java.lang.String accessToken = theCookie;
	java.lang.String qid = request.getParameter("id");
	java.lang.String content = request.getParameter("content");
	// TODO process result here
	int result = port.createAns(accessToken, qid, content);
	out.println("Result = "+result);
        if (result == 1){
            String site = "question.jsp?id=" + qid + "&token=" + accessToken;
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        } else if (result == -1){
            String site = "error.jsp?id=-1&token=" + accessToken;
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        } else if (result == -2){
            String site = "error.jsp?id=-2&token=" + accessToken;
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site);
        }
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <%-- end web service invocation --%><hr/>

    </body>
</html>
