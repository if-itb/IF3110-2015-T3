<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import= "java.net.URL,javax.xml.namespace.QName,java.lang.String" %>
<%@page import= "com.yangnormal.sstackex.WebServiceInterface" %>
<%@page import= "com.yangnormal.sstackex.WebServiceImplService" %>
<%@page import= "com.yangnormal.sstackex.Question" %>

    <%

        String qid = request.getParameter("id");
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        String token = "";
        Cookie[] cookies = request.getCookies();
        for (int i=0;i<cookies.length;i++){
            if (cookies[i].getName().equals("token")){
                token = cookies[i].getValue();
            }
        }
        if ((topic!=null)&&(content!=null)){
            URL url = new URL("http://localhost:8082/ws/stackexchange?wsdl");
            QName qname = new QName("http://ws.sstackex.yangnormal.com/", "WebServiceImplService");
            WebServiceImplService webService = new WebServiceImplService(url, qname);
            WebServiceInterface ws = webService.getWebServiceImplPort();
            String userAgent = request.getHeader("User-Agent");
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            int status = ws.updateQuestion(Integer.parseInt(qid),token,topic,content,userAgent,ipAddress);
            request.setAttribute("status",status);
            request.setAttribute("name","Edit Question");
            RequestDispatcher dispatcher = request.getRequestDispatcher("status.jsp");
            dispatcher.forward(request,response);
        } else if (token.equals("")){
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    %>