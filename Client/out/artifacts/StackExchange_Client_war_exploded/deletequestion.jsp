<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%
	int id=Integer.parseInt(request.getParameter("id"));
	if (id!=null){
		URL url = new URL ("http://localhost:8080/ws/stackexchange?wsdl");
		QName qname = new QName("http://ws.yangnormal.com/","RegistrationImplService");
		Service service = Service.create(url,qname);
		WebServiceImpl ws = service.getPort(WebService.class);
		int status = ws.deleteQuestion(id);
		if (status==0){
			response.redirect("registerSuccess.jsp");
		} else {
			response.redirect("registerFail.jsp");
		}
	}
%>