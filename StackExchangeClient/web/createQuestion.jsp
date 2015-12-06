<%-- 
    Document   : createQuestion
    Created on : Nov 25, 2015, 9:26:47 AM
    Author     : chairuniaulianusapati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple StackExchange</title>
    </head>
    <body>     
    <%
    try {
	com.wbd.qst.QuestionWS_Service service = new com.wbd.qst.QuestionWS_Service();
	com.wbd.qst.QuestionWS port = service.getQuestionWSPort();
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
	java.lang.String title = request.getParameter("topic");        
	java.lang.String content = request.getParameter("content");
	// TODO process result here
	int result = port.createQ(accessToken, title, content);
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
        }
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    
    </body>
</html>
