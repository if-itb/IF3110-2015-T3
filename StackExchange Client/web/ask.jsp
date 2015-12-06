<%-- 
    Document   : ask
    Created on : Nov 24, 2015, 1:19:48 AM
    Author     : Raihan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ask a Question!</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
    </head>
    <body>
        <h1>Simple Stack Exchange</h1>
            <form name='question' action='answer.jsp'>
                <input class="inputform" type='text' name='topic' placeholder='Question Topic'><br><br>
                <textarea class="inputform" name='qcontent' placeholder="Question" rows="4"></textarea><br><br>
                <input class="button" type="submit" value="Submit">
            </form>
            
            
        
    </body>
</html>
