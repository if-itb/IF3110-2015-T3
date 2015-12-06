<%-- 
    Document   : addQuestion
    Created on : Nov 25, 2015, 8:48:07 PM
    Author     : William Sentosa
--%>

<%@page import="userWebService.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    try {
        String token = "";
        Cookie[] cookies = request.getCookies();
        for(Cookie temp : cookies){
            if(temp.getName().equals("token")){
                token = temp.getValue();
            }
        }

        questionWebService.QuestionWebService_Service service = new questionWebService.QuestionWebService_Service();
        questionWebService.QuestionWebService port = service.getQuestionWebServicePort();
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        String identification = request.getParameter("uid");
        String ip = request.getParameter("ip");
        String ua = request.getParameter("ua");
        int id = Integer.parseInt(identification);
        userWebService.UserWebService_Service userService = new userWebService.UserWebService_Service();
        userWebService.UserWebService userPort = userService.getUserWebServicePort();
        User user = userPort.getUser(id);
        String result = port.addQuestion(token, user.getName(), user.getEmail(), topic, content, id, ip, ua);
        if(result.equals("executed")) {
          response.sendRedirect("index.jsp?id=" + id);
        } else {
            out.println(result);
          //response.sendRedirect("error.jsp");
        }
    } catch (Exception ex) {
        out.println("Gagal");
    }
%>
