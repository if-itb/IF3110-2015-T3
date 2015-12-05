<%-- 
    Document   : main
    Created on : Nov 6, 2015, 5:53:06 PM
    Author     : tama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@  page import="java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service" %>
        <% webservice.NewWebService service = new webservice.NewWebService();
	webservice.StackWebSevice sws = service.getStackWebSevicePort();
        %>
        
        
  
       
        <% 
            
           String ru = request.getParameter("rusername"); 
           String re = request.getParameter("remail") ;
           String rp = request.getParameter("rpass") ;
           int status = 0;          
        %>
        
        <%   if (ru==null||re==null) {status=0;}
            else {            
                if (ru.length()>0||re.length()>0) {                    
                    status = sws.register(ru, re, rp);         
                    if (status==0) {                                  
                        ru="" ;
                        re="";
                        rp="";
                        response.sendRedirect("regsuccess.jsp");
                    }
                    else {}
                }
                else status=0;
            }
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>         
        <link rel="stylesheet" type="text/css" href="style/style.css">
    </head>
    <body>
        
        <div id="head1">            
            <a href="login.jsp"><div id="login" >Login</div></a>
            <a href="register.jsp"><div id ="reg">Register</div></a>
        </div>
         <a href="home.jsp"><div id="h">Stack <at>Exchange</at></div></a>
        
        
        <div class="raq">Register Page </div>
        <div class="separator"></div>
        
         <form method="post" >
             <input id="rname" type="text" name="rusername" placeholder="Name : "/><br> 
            <input id="remail" type="text" name="remail" placeholder="Email : " /><br>
            <input id="rpass" type="password" name="rpass" placeholder="Password : " /><br> 
            <div id="regerror">
                <% if (status!=0) { %>
                This account has been registered. Click <a href="login.jsp"><rr>here</rr></a> to login.
                <% } %>
            </div>
            
            <input id="rsubmit" type="submit" value="Register"><br>
            
            
         </form>
    </body>
</html>
