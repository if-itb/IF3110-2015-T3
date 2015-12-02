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
    </head>
    <body>
        <center><a href='index.jsp'> <h1>Simple Stack Exchange</h1> </a>
            <form name='question' action='ask' method='POST'>
                <input type='text' name='topic' placeholder='Question Topic' size='80'><br><br>
                <textarea name='qcontent' placeholder="Question" style="resize:none;width:500px;"></textarea><br><br>
                <input type="submit" value="Submit">
            </form>
            
            
        </center>
    </body>
</html>
