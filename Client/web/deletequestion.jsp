<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import= "java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service" %>
<%@ page import="com.yangnormal.sstackex.*" %>
<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
<%

	String id = request.getParameter("id");
	String token = "";
	Cookie[] cookies = request.getCookies();
	for (int i=0;i<cookies.length;i++){
		if (cookies[i].getName().equals("token")){
			token = cookies[i].getValue();
		}
	}
	if ((id!=null)&&!(token.equals(""))) {
		URL url = new URL("http://localhost:8082/ws/stackexchange?wsdl");
		QName qname = new QName("http://ws.sstackex.yangnormal.com/", "WebServiceImplService");
		WebServiceImplService webService = new WebServiceImplService(url, qname);
		WebServiceInterface ws = webService.getWebServiceImplPort();
		String userAgent = DigestUtils.md5Hex(request.getHeader("User-Agent"));
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		int status = ws.deleteQuestion(Integer.parseInt(id),token,userAgent,ipAddress);
		request.setAttribute("status",status);
		request.setAttribute("name","Delete Question");
		RequestDispatcher dispatcher = request.getRequestDispatcher("status.jsp");
		dispatcher.forward(request,response);
	} else if (token.equals("")){
		response.sendRedirect("login.jsp");
	}
	else {
		response.sendRedirect("index.jsp");
	}
%>