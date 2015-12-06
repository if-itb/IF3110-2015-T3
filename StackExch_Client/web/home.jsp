<%-- 
    Document   : home
    Created on : Nov 10, 2015, 2:57:19 PM
    Author     : tama
--%>
<!DOCTYPE html>
<html>
    <head>
        <%@  page import="java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service" %>
        <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
  
    <%
        webservice.NewWebService service = new webservice.NewWebService();
	webservice.StackWebSevice sws = service.getStackWebSevicePort();
	java.util.List<webservice.Question> R = sws.getAllQuestions();
    %>
 
        <% 
        String sq = request.getParameter("s");       
        int rsize = R.size();
        if (sq!=null && sq!=""){
            R = sws.searchQuestion(sq);
            rsize = R.size();
        }
        String nto = request.getParameter("ntoken");
        if (nto!=null&&nto!="") {
            String nexp = request.getParameter("nexp");
            HttpSession ss2 = request.getSession();
            ss2.setAttribute("token", nto);
            ss2.setAttribute("expire",nexp);
        }
          String to="",us="",ex="";
          int idu=0; 
          HttpSession ss = request.getSession();
          to =String.valueOf(ss.getAttribute("token"));
          us = String.valueOf(ss.getAttribute("username"));
          ex = String.valueOf(ss.getAttribute("expire"));
          if (!to.equals("null")) idu = Integer.valueOf(String.valueOf(ss.getAttribute("id")));
        %> <% String ipa = request.getHeader("X-FORWARDED-FOR");  
            if (ipa == null) {  
                ipa = request.getRemoteAddr();  
            }
            String browser = request.getHeader("user-agent");
            %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stack Exchange</title>
        <link rel="stylesheet" type="text/css" href="style/style.css">
    <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
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
        
         <form id="searchbox" method="GET">
             <input id="search" type="text" name="s" placeholder=" Type any keyword here . . . ">
             <input id="submit" type="submit" value="Search">
        </form>
        <p>Cannot find what you are looking for ? <a href="askhere.jsp"><as>Ask here</as></a></p>        
        <div class="raq">Recently Asked Questions </div>
        <div class="separator"></div>       
        <% for (int i=0; i<rsize;i++) { %>        
            <div class ="info">
                <div class ="vote">
                    <div class="vnum"> <% out.println(R.get(i).getVote()); %></div>
                    <div class="vname">Votes</div>           
                </div>

                <div class ="answers">
                    <div class="vnum"><% out.println(R.get(i).getTanswer()); %></div>
                    <div class="vname">Answers</div>           
                </div>

                <div class="thread">
                    <a href="open.jsp?id=<%out.println(R.get(i).getIdQ());%>"><div class="title"><% out.println(R.get(i).getTitle()); %></div></a>
                    <div class = "content">                     
                        <%
                        String c = R.get(i).getContent();
                        if (c.length() > 150) c = c.substring(0,149) + " . . . ";
                        out.println(c); %><br>
                    </div>
                </div>


               </div>
                <div class="utility">
                    <aa>asked by : </aa>
                    <a1> <% out.println(R.get(i).getUsername()); %></a1>|
                    <a href="askhere.jsp?id=<% out.println(R.get(i).getIdQ()) ; %>"><span class="bb">edit</span></a> |
                    <a onclick ="return confirm('Are you sure to delete this question ?')" href="process.jsp?action=del&id=<% out.println(R.get(i).getIdQ()) ;%>&owner=<% out.println(R.get(i).getUsername());%>"><span class="cc">delete</span></a>
                </div>
             <div class="uline"></div>       
        <% } %>
        
       
    </body>
</html>
