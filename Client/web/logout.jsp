<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%

	Cookie token = new Cookie("token","token");
	token.setMaxAge(0);
	token.setValue("");
	response.addCookie(token);
	response.sendRedirect("index.jsp");

%>