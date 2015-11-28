<%-- 
    Document   : upAns
    Created on : Nov 18, 2015, 5:06:59 PM
    Author     : Satria
--%>

<%@page import="java.sql.Timestamp"%>
<%-- 
    Document   : updateanswer
    Created on : Nov 18, 2015, 2:45:30 PM
    Author     : Satria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%-- start web service invocation --%><hr/>
    <%
            String token = request.getParameter("token");
            questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
            questionmodel.QuestionWS port = service.getQuestionWSPort();

            try {

                Timestamp result = new Timestamp(port.getExpiredDate(token));
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                /*out.println(ts);
                out.println(result);*/

                if (ts.after(result)) {
                    String site = "http://localhost:8001/Identity/LoginRSServlet?token="+request.getParameter("token");
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", site);
                }

            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
    <%
    try {
	answermodel.AnswerWS_Service service1 = new answermodel.AnswerWS_Service();
	answermodel.AnswerWS port1 = service1.getAnswerWSPort();
	 // TODO initialize WS operation arguments here
	int ansId = Integer.parseInt(request.getParameter("id"));
        
        
	// TODO process result here
	String result = port1.upAnswer(ansId,token);
	out.println("Result = "+result);
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    
    String site = "answer.jsp?id="+Integer.parseInt(request.getParameter("qid")) +"&token=" + request.getParameter("token");
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", site);
    %>
    <%-- end web service invocation --%><hr/>
