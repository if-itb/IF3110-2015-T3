<%@page import="userWebService.UserWebService_Service"%>
<%@page import="userWebService.UserWebService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  try {
    UserWebService_Service service = new userWebService.UserWebService_Service();
    UserWebService port = service.getUserWebServicePort();
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String pass = request.getParameter("password");
    boolean isExist = port.isUserExist(email);
    if (!isExist){ 
      String result = port.addUser(email,name,pass);
      response.sendRedirect("login.jsp");
    } else {
      response.sendRedirect("register.jsp?res=1");
    }
  } catch (Exception ex) {
    out.println(ex.getMessage());
  }
%>
