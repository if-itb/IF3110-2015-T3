<%-- 
    Document   : updateQuestion
    Created on : Nov 25, 2015, 5:09:34 PM
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
	com.wbd.qst.QuestionWS_Service service = new com.wbd.qst.QuestionWS_Service();
	com.wbd.qst.QuestionWS port = service.getQuestionWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = theCookie;
	int qid = Integer.parseInt(request.getParameter("id"));
	java.lang.String title = request.getParameter("topic");
	java.lang.String content = request.getParameter("textarea1");
	// TODO process result here
	int result = port.updateQ(accessToken, qid, title, content);
	if (result == 1){
            String site = "index.jsp?token=" + accessToken;
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
        } else if (result == -3){
            String site = "error.jsp?id=3&token=" + accessToken;
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
