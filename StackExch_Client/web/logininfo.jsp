

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%  String status = "";
            status = request.getParameter("status");
            int s = Integer.valueOf(status);
            String m1="",m2="";
             String to="",us="",ex="";
            int idd=0; 
        %> <% String ipa = request.getHeader("X-FORWARDED-FOR");  
            if (ipa == null) {  
                ipa = request.getRemoteAddr();  
            }
            String browser = request.getHeader("user-agent");
            %>
         <link rel="stylesheet" type="text/css" href="style/style.css">       
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> <% if (s!=-1) {
            out.println("Login Success !!") ; 
            m1="home.jsp";
            m2="Login success !!";
           
            HttpSession ss = request.getSession();
            to =String.valueOf(ss.getAttribute("token"));
            us = String.valueOf(ss.getAttribute("username"));
            ex = String.valueOf(ss.getAttribute("expire"));
            idd = Integer.valueOf(String.valueOf(ss.getAttribute("id")));
        }
        else {out.println("Login failed ");m1="login.jsp"; m2="Login failed" ;}%>
        </title>
    </head>
    <body>
         
           <a href="home.jsp"><div id="h">Stack <at>Exchange</at></div></a>
        
        
            <div class="raq"> </div>
            <div class="separator"></div>
            <% 
                response.setHeader("Refresh", "2;url="+m1); 
               
            %>
            <div class="rsuccess">
                <%out.println(m2) ; %><br>
                You'll be redirected to 
                <% if (s!=-1)  {
                    out.println("home");    
                }
                
                    else out.println("login"); %>
                page. If not click <a href="<% out.println(m1); %>"><rr>here</rr></a></div>

    </body>
</html>
