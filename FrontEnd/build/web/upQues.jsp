<%-- 
    Document   : upQues
    Created on : Nov 22, 2015, 12:35:14 PM
    Author     : yoga
--%>

<%@page import="java.net.URLDecoder"%>
<%@page import="java.sql.Timestamp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype HTML>
<HTML>
    <HEAD></HEAD>
    <BODY>
    <%-- start web service invocation --%><hr/>
    <%
        int qid = Integer.parseInt(request.getParameter("id"));
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
                
                //decode token
                String realtoken = URLDecoder.decode(token, "UTF-8");
                //parse token
                String[] tableRT = realtoken.split("#");
                //if user have token then validate UA and IP
                if (tableRT.length > 1){
                    //check User agent. If not equals with token then redirect to login
                    if( !tableRT[1].equals(request.getHeader("User-Agent")) ){
                        String site = "http://localhost:8000/FrontEnd/login.jsp";
                        response.setStatus(response.SC_MOVED_TEMPORARILY);
                        response.setHeader("Location", site);
                    }
                    //check IPAddress. If not equals with token then redirect to login
                    if( !tableRT[2].equals(request.getRemoteAddr()) ){
                        String site = "http://localhost:8000/FrontEnd/login.jsp";
                        response.setStatus(response.SC_MOVED_TEMPORARILY);
                        response.setHeader("Location", site);
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
