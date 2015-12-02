<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="main.css">
        <title>Simple StackExchange</title>
    </head>
    <body>
        <div class="navbar-up">
            <h1 class="white">Simple StackExchange</h1>
        </div>

        <div>
            <jsp:include page="/QuestionListServlet"/>
        </div>
    </body>
</html>
