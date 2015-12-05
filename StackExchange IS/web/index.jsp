<%-- 
    Document   : index
    Created on : Nov 12, 2015, 10:22:00 PM
    Author     : alberttriadrian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stack Exchange</title>
    </head>
    <body>
        <h1>Stack Exchange Hello </h1>
        <table border="0">
            <thead>
                <tr>
                    <th>Table 1</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Row 1</td>
                </tr>
                <tr>
                    <td>
                        <form name="response-form" action="response.jsp">
                            <strong>Select a subject:</strong>
                            <select name="subject_id">
                                <option>Business Operation</option>
                                <option>DevOps</option>
                            </select>
                            <input type="submit" value="submit" name="submit" />         
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>

    </body>
</html>
