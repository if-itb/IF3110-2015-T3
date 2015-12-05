<html lang="en">
    <head> 
        <meta charset="UTF-8">
        <title>Simple StackExchange </title>
        <link rel="stylesheet" type="text/css" href="css/mainstyle.css">
    </head>
    <body>
        <%
            String token = "";
            Cookie[] cookies = request.getCookies();
            for(Cookie temp : cookies){
                if(temp.getName().equals("token")){
                    token = temp.getValue();
                }
            }
        %>
	<div id="wrapper">
            <jsp:include page="header.jsp" />
            <h2 class ="underline"> What's your question? </h2>
            <form action="addQuestion.jsp" method="POST" class="block">
                <input type="text" placeholder="Question Topic" name="topic" value="" />
                <textarea name="content" placeholder="Content"></textarea>
                <input type="submit" value="Post" />
                <input type="hidden" name="token" value="<%= token %>" />
                <input type="hidden" name="uid" value="<%= request.getParameter("id")%>" />
            </form>
        </div>
    </body>
</html>
