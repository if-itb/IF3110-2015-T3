<%-- 
    Document   : vote
    Created on : Nov 27, 2015, Nov 27, 2015 8:17:15 AM
    Author     : Fikri-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    if(session.getAttribute("sessionName") != null){
        String category = request.getParameter("c");
        int id = Integer.parseInt(request.getParameter("id"));
        int value = Integer.parseInt(request.getParameter("val"));

        if (category.equals("q")){
            QuestionWS.QuestionWS_Service service = new QuestionWS.QuestionWS_Service();
            QuestionWS.QuestionWS port = service.getQuestionWSPort();
             // TODO initialize WS operation arguments here
            // TODO process result here
            int result = port.vote(id, session.getAttribute("token"), value);
            if(result < 0) {
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "../login.jsp");
            } else{
                out.print(result);
            }       
        } else {

            int q_id = id;
            AnswerWS.AnswerWS_Service service = new AnswerWS.AnswerWS_Service();
            AnswerWS.AnswerWS port = service.getAnswerWSPort();
             // TODO initialize WS operation arguments here
            // TODO process result here
            int result = port.voteAns(id, session.getAttribute("token"), value);
            if(result < -5) {
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", "../login.jsp");
            } else{
                out.print(result);
            }
        }
            
            
    } else {
        site = "../login.jsp";
    }
    
%> 
    <%-- start web service invocation --%><hr/>



    
 
