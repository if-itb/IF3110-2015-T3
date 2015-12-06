<%-- 
    Document   : updatequestion
    Created on : Nov 18, 2015, 2:45:30 PM
    Author     : yoga
--%>

<%@page import="java.sql.Timestamp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
            String token = new String();
            Cookie cookies[] = request.getCookies();
                if (cookies != null) {
                    
                    for (int i=0;i<cookies.length;i++) {
                        if (cookies[i].getName().toString().equals("access_token_frontend")) {
                            token = cookies[i].getValue();
                            break;
                        }
                    }
                }
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
        java.lang.String topic = request.getParameter("topic");
        java.lang.String content = request.getParameter("content");
        int id = Integer.parseInt(request.getParameter("id"));
        // TODO process result here
        int result = port.updateQuestion(topic, content, id);
        out.println("Result = " + result);
    } catch (Exception ex) {
        // TODO handle custom exceptions here
    }
    String site = "index.jsp";
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", site);
%>


