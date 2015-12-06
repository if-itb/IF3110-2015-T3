<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="Functions.js"></script>
        <style>
            <%@ include file="style.css"%>
        </style>
        <title>Stack Exchange</title>
    </head>
    <body>
        <div class="container">
            <% 
                // Check if already log in
                String username =  "";
                boolean found = false;
                int i=0;
                Cookie[] cookies = null;
                cookies = request.getCookies();
                if (cookies != null) {
                    while (!found && i < cookies.length){
                        if (cookies[i].getName().equals("usernameCookie")) {
                            username = cookies[i].getValue();
                            found = true;
                        }
                        i++;
                    }
                }
                if (!found) {
                    out.print("<div class=\"login\"" +
                                    "<h1>Log-in</h1>" +
                                        "<form action=\"/StackExchangeClient/login\" method=\"POST\">" +
                                            "<input type=\"text\" name=\"user\" placeholder=\"Username\">" +
                                            "<input type=\"password\" name=\"pass\" placeholder=\"Password\">" +
                                            "<input type=\"submit\" name=\"login\" value=\"Login\">" +
                                        "</form>" +
                                        "<div class=\"login-help\">" +
                                            "<a href=\"register.jsp\">Register</a>" +
                                        "</div>" +
                                "</div>");
                }
                else {
                    out.print("<p style=\"text-align:right\">You're log in as " + username +" <button onclick=\"window.location.href='index';\">Log out</button></p>");
                }
            %>
                
            <a href="/StackExchangeClient/index"><h1>Simple StackExchange</h1></a><br>
            <h2>What's your question? </h2><br>
            <form class="QuestionForm" action="askquestion" method="POST">
		<input type="text" name="topic" placeholder="Question Topic"><br>
		<textarea name="content" placeholder="Content"></textarea><br><br>
		<input type="submit" value="Post">
            </form>
	</div>
    </body>
</html>
