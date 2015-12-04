<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import= "java.net.URL,javax.xml.namespace.QName,java.lang.String" %>
<%@page import= "com.yangnormal.sstackex.WebServiceInterface" %>
<%@page import= "com.yangnormal.sstackex.WebServiceImplService" %>
<%@page import= "com.yangnormal.sstackex.Question" %>

    <%
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        String spin = request.getParameter("spin");
        String token = "";
        Cookie[] cookies = request.getCookies();
        for (int i=0;i<cookies.length;i++){
            if (cookies[i].getName().equals("token")){
                token = cookies[i].getValue();
            }
        }
        if ((type!=null)&&(id!=null)&&(spin!=null)&&!(token.equals(""))){
            URL url = new URL("http://localhost:8082/ws/stackexchange?wsdl");
            QName qname = new QName("http://ws.sstackex.yangnormal.com/", "WebServiceImplService");
            WebServiceImplService webService = new WebServiceImplService(url, qname);
            WebServiceInterface ws = webService.getWebServiceImplPort();
            int status = ws.vote(Integer.parseInt(type),Integer.parseInt(id),Integer.parseInt(spin),token);
            response.sendRedirect(request.getHeader("Referer"));
        } else if (token.equals("")){
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    %>