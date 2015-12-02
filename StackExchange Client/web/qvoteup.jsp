<%-- 
    Document   : qvoteup
    Created on : Nov 26, 2015, 2:40:48 PM
    Author     : acel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <%
    try {
	questionWebService.QuestionWebService_Service service = new questionWebService.QuestionWebService_Service();
	questionWebService.QuestionWebService port = service.getQuestionWebServicePort();
	java.lang.String arg0 = request.getParameter("token");
	int arg1 = Integer.parseInt(request.getParameter("qid"));
	int arg2 = Integer.parseInt(request.getParameter("id"));
	java.lang.String result = port.incrVote(arg0, arg1, arg2);
        if(result.equals("executed")) {
            response.sendRedirect("http://localhost:8080/StackExchange_Client/question.jsp?token=" + request.getParameter("token")
                                                + "&id=" + request.getParameter("id") + "&qid=" + request.getParameter("qid"));
        } else {
            response.sendRedirect("error.jsp");
        }
    } catch (Exception ex) {
        
    }
    
    %>
