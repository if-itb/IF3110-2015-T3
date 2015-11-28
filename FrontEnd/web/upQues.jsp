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
	
	 // TODO initialize WS operation arguments here
	int qid = Integer.parseInt(request.getParameter("id"));
        
	// TODO process result here
	String result = port.upQuestion(qid,token);
	out.println("Result = "+result);
        if (result.equals("0")){
            out.println("<script>alert('you must login first to vote');</script>");
        }
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    
    String site = "answer.jsp?id="+Integer.parseInt(request.getParameter("id")) +"&token=" + request.getParameter("token");
    
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", site);
    %>
    <%-- end web service invocation --%><hr/>
    </BODY>
</HTML>
