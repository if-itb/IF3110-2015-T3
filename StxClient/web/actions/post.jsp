<%-- 
    Document   : post
    Created on : Nov 26, 2015, Nov 26, 2015 6:04:59 PM
    Author     : Fikri-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <%-- start web service invocation --%><hr/>
    <% 
        String q_id = request.getParameter("id");
        String site;
        if (q_id == null){
            QuestionWS.QuestionWS_Service service = new QuestionWS.QuestionWS_Service();
            QuestionWS.QuestionWS port = service.getQuestionWSPort();
             // TODO initialize WS operation arguments here
            java.lang.String name = session.getAttribute("sessionName").toString();
            java.lang.String email = session.getAttribute("sessionEmail").toString();
            java.lang.String topic = request.getParameter("Topik");
            java.lang.String content = request.getParameter("Konten");
            // TODO process result here
            int result = port.post(session.getAttribute("token").toString(), topic, content);
            if(result < 0) {
                site = "../login.jsp";
            } else {
                site = "../index.jsp";
            }
        } else {
            QuestionWS.QuestionWS_Service service = new QuestionWS.QuestionWS_Service();
            QuestionWS.QuestionWS port = service.getQuestionWSPort();
             // TODO initialize WS operation arguments here
            int id = Integer.parseInt(q_id);
            java.lang.String name = session.getAttribute("sessionName").toString();
            java.lang.String email = session.getAttribute("sessionEmail").toString();
            java.lang.String topic = request.getParameter("Topik");
            java.lang.String content = request.getParameter("Konten");
            // TODO process result here
            int result = port.update(id, session.getAttribute("token").toString(), topic, content);
            if(result < 0) {
                site = "../login.jsp";
            } else {
                site = "../view-question.jsp?id="+q_id;
            }
        }  
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site); 
    %>
    <%-- end web service invocation --%><hr/>
