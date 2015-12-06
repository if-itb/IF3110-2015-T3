<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>

<script type="text/javascript" src="js/script.js"></script>
<%@page import= "java.net.URL,javax.xml.namespace.QName,java.lang.String" %>
<%@page import= "com.yangnormal.sstackex.WebServiceInterface" %>
<%@page import= "com.yangnormal.sstackex.WebServiceImplService" %>
<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
<%

	String content = request.getParameter("content");
	int id = Integer.parseInt(request.getParameter("id"));
	String token = "";
	Cookie[] cookies = request.getCookies();
	for (int i=0;i<cookies.length;i++){
		if (cookies[i].getName().equals("token")){
			token = cookies[i].getValue();
		}
	}
	if ((content!=null)&&!(token.equals(""))){
		URL url = new URL ("http://localhost:8082/ws/stackexchange?wsdl");
		QName qname = new QName("http://ws.sstackex.yangnormal.com/","WebServiceImplService");
		WebServiceImplService webService = new WebServiceImplService(url,qname);
		WebServiceInterface ws = webService.getWebServiceImplPort();
		String userAgent = DigestUtils.md5Hex(request.getHeader("User-Agent"));
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		int status = ws.postAnswer(id,token,content,userAgent,ipAddress);
		request.setAttribute("status",status);
		request.setAttribute("name","Post Answer");
		RequestDispatcher dispatcher = request.getRequestDispatcher("status.jsp");
		dispatcher.forward(request,response);
	} else if (token.equals("")){
		response.sendRedirect("login.jsp");
	}
%>