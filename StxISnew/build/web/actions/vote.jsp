<%-- 
    Document   : vote
    Created on : Nov 27, 2015, Nov 27, 2015 8:17:15 AM
    Author     : Fikri-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    String site;
    if(session.getAttribute("sessionName") != null){
        String category = request.getParameter("c");
        int id = Integer.parseInt(request.getParameter("id"));
        int value = Integer.parseInt(request.getParameter("val"));
        java.lang.String usermail = request.getParameter("email");

        if (category.equals("q")){
            QuestionWS.QuestionWS_Service service = new QuestionWS.QuestionWS_Service();
            QuestionWS.QuestionWS port = service.getQuestionWSPort();
             // TODO initialize WS operation arguments here
            // TODO process result here
            int result = port.vote(id, usermail, value);
            out.println("Result = "+result);       
        } else {

            int q_id = Integer.parseInt(request.getParameter("q_id"));
            AnswerWS.AnswerWS_Service service = new AnswerWS.AnswerWS_Service();
            AnswerWS.AnswerWS port = service.getAnswerWSPort();
             // TODO initialize WS operation arguments here
            // TODO process result here
            int result = port.voteAns(q_id, usermail, value);
            out.println("Result = "+result);
        }
            site = "../view-question.jsp?id="+id;
            
    } else {
        site = "../login.jsp";
    }
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", site);
%> 
    <%-- start web service invocation --%><hr/>



    
 
