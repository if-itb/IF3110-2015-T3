<%-- 
    Document   : askhere
    Created on : Nov 10, 2015, 5:26:06 PM
    Author     : tama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@  page import="java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service" %>
       
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <%
             webservice.NewWebService service = new webservice.NewWebService();
            webservice.StackWebSevice sws = service.getStackWebSevicePort();
            
            String t_id = request.getParameter("id");
            webservice.Question Q = new webservice.Question();
            String prcs = "new";
            String owner="";
            int id = 0 ;
            if (t_id !=null) {
                id = Integer.valueOf(t_id);
                Q = sws.getQuestions(id);
                prcs = "edit";
                owner=Q.getUsername();
            }
          String to="",us="",ex="";
          int idu=0; 
          HttpSession ss = request.getSession();
          to =String.valueOf(ss.getAttribute("token"));
          us = String.valueOf(ss.getAttribute("username"));
          ex = String.valueOf(ss.getAttribute("expire"));
          if (!to.equals("null")) idu = Integer.valueOf(String.valueOf(ss.getAttribute("id")));
       
          if (idu==0) response.sendRedirect("notlogin.jsp");
    %> <% String ipa = request.getHeader("X-FORWARDED-FOR");  
            if (ipa == null) {  
                ipa = request.getRemoteAddr();  
            }
            String browser = request.getHeader("user-agent");
            %>
        
        <title><% if (id==0) out.println("Create New Questions");
                    else out.println("Edit Question"); %>   
        </title>
        <link rel="stylesheet" type="text/css" href="style/style.css">
        
       
    </head>
    <body>
      <% if (idu==0) { %>
        <div id="head1">            
            <a href="login.jsp"><div id="login" >Login</div></a>
            <a href="register.jsp"><div id ="reg">Register</div></a>
        </div>
        <% } else { %>
        <div id="head1">            
            <a href="logout"><div id="logout" >Logout</div></a>
            <a href=""><div id ="hello"> Hello , <% out.println(us) ; %></div></a>           
        </div>
        <% } %>
        <a href="home.jsp"><div id="h">Stack <at>Exchange</at></div></a>
         <form id="searchbox">
             <input id="search" type="text" name="s" placeholder=" Type any keyword here . . . ">
             <input id="submit" type="submit" value="Search">
        </form>
        
        <div class="raq"><% if (id==0) out.println("Create New Questions");
                    else out.println("Edit Your Question"); %>   </div>
        <div class="separator"></div>
        
        <form action="process.jsp" method="POST">
            <input id="atitle" type="text" name="atitle" placeholder="Title" value="<% if (id!=0) out.println(Q.getTitle()) ; %>"/><br>             
             <textarea id="acontent" name="acontent" placeholder="Content" ><% if (id!=0) out.println(Q.getContent()) ; %></textarea> <br>
             <input id="asubmit" type="submit" value="Post this !!"><br>
             <input type="hidden" name="process" value="<% out.println(prcs) ; %>">
             <input type="hidden" name="qid" value="<% out.println(id) ; %>">
             <input type="hidden" name="action" value="askhere">
             <input type="hidden" name="owner" value="<% out.println(owner) ; %>">
             <input type="hidden" name="cipa" value="<%out.println(ipa);%>">
             <input type="hidden" name="cbrowser" value="<%out.println(browser);%>">
         </form>
    </body>
</html>
