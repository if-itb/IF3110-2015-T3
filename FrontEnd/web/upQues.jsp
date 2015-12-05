<%-- 
    Document   : upQues
    Created on : Nov 22, 2015, 12:35:14 PM
    Author     : yoga
--%>

<%@page import="java.sql.Timestamp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype HTML>
<HTML>
    <HEAD></HEAD>
    <BODY>
    <%-- start web service invocation --%><hr/>
    <%
        int qid = Integer.parseInt(request.getParameter("id"));
            String token = request.getParameter("token");
            questionmodel.QuestionWS_Service service = new questionmodel.QuestionWS_Service();
            questionmodel.QuestionWS port = service.getQuestionWSPort();

            try {

                Timestamp result = new Timestamp(port.getExpiredDate(token));
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                /*out.println(ts);
                out.println(result);*/

                if (ts.after(result)) {
                    String site = "http://localhost:8001//LoginRSServlet?token="+request.getParameter("token");
                    response.setStatus(response.SC_MOVED_TEMPORARILY);
                    response.setHeader("Location", site);
                }

            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
        %>
    <%

	
	 // TODO initialize WS operation arguments here
	
        
	// TODO process result here
	
  
    
    String site = "http://localhost:8001/VoteComment/VoteUpQuestionRSServlet?token="+request.getParameter("token")+"&qid="+qid;
    
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", site);
    %>
    <%-- end web service invocation --%><hr/>
    </BODY>
</HTML>
