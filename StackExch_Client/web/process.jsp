<%-- 
    Document   : process
    Created on : Nov 12, 2015, 4:30:50 PM
    Author     : tama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <link rel="stylesheet" type="text/css" href="style/style.css">
        <%@  page import="java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service" %>
         <% webservice.NewWebService service = new webservice.NewWebService();
	webservice.StackWebSevice sws = service.getStackWebSevicePort();
           String m1="",m2="";
         %> <% String ipa = request.getHeader("X-FORWARDED-FOR");  
            if (ipa == null) {  
                ipa = request.getRemoteAddr();  
            }
            String browser = request.getHeader("user-agent");
            %>
        <% 
         String to="",us="",ex="";
          int idu=0; 
          HttpSession ss = request.getSession();
          to =String.valueOf(ss.getAttribute("token"));
          us = String.valueOf(ss.getAttribute("username"));
          ex = String.valueOf(ss.getAttribute("expire"));
          if (!to.equals("null")) idu = Integer.valueOf(String.valueOf(ss.getAttribute("id")));
        %>
        <% 
          if (idu==0) response.sendRedirect("notlogin.jsp");
        %>
        
        
        <% String action = request.getParameter("action");
            
           if (action.equals("askhere")) {
               String type = request.getParameter("process");
               String title = request.getParameter("atitle");
               String content = request.getParameter("acontent");
               String qid = request.getParameter("qid");
               if (type.contains("new")) {
                       System.out.println("Create new question");
                   int stats = sws.addQuestion(to,title,content,ipa,browser);              
                   if (stats>0) {
                       response.sendRedirect("open.jsp?id="+stats);
                   }
                   else {
                       m1= "You can't create question right now <br>";
                       if (stats==-1) m1=m1+"Your token is not valid. ";
                       else if (stats==-2) m1=m1+"Your token has expired. ";
                       else if (stats==-3) m1=m1+"You are accessing from different ip ";
                       else if (stats==-4) m1=m1+"You are using different browser";
                       m1=m1+"Please re-login.";
                       m2= "logout";                       
                   }
               }
               else if (type.contains("edit")){
                   qid=qid.substring(0,qid.length()-2); 
                   String owner = request.getParameter("owner");
                   owner = owner.substring(0,owner.length()-2);
                   String currentl = sws.getTokenOwner(to);  
                   System.out.println("Owner = |"+owner+"| Currentl = |"+currentl+"|");
                   int idd = Integer.valueOf(qid);
                   if (!owner.equals(currentl)) {
                       m1 = "You're can't edit other person's question.";
                       m2 = "home.jsp";
                   }
                   else {
                        int stats = sws.updateQuestion(to, idd,title,content,ipa,browser);  
                        if (stats>0) {
                         response.sendRedirect("open.jsp?id="+idd); 
                        }
                        else { 
                            m1= "You can't edit question.<br>";
                            if (stats==-1) m1=m1+"Your token is not valid. ";
                            else if (stats==-2) m1=m1+"Your token has expired. ";
                            else if (stats==-3) m1=m1+"You are accessing from different ip ";
                            else if (stats==-4) m1=m1+"You are using different browser";
                            m1=m1+"Please re-login.";
                            m2= "logout";    
                        }
                   }
               }
               else out.println("|"+type+"|");
           }
           else if (action.contains("del")) {
               String qid = request.getParameter("id");
               String owner = request.getParameter("owner");
               String currentl = sws.getTokenOwner(to);  
               int idd = Integer.valueOf(qid);
               if (!owner.equals(currentl)) {
                       m1 = "You're can't delete other person's question.";
                       m2 = "home.jsp";
                   }
                   else {
                        int stats = sws.deleteQuestion(to, idd,ipa,browser);
                   if (stats>0) {
                         m1 = "The question has been deleted";
                         m2 = "home.jsp";
                    }
                        else { 
                            m1= "You can't delete this. question.<br>";
                            if (stats==-1) m1=m1+"Your token is not valid. ";
                            else if (stats==-2) m1=m1+"Your token has expired. ";
                            else if (stats==-3) m1=m1+"You are accessing from different ip ";
                            else if (stats==-4) m1=m1+"You are using different browser";
                            m1=m1+"Please re-login.";
                            m2= "logout";    
                        }
                   }              
           }
           else if (action.contains("answer")) {
               String qid = request.getParameter("q_id");
               qid = qid.substring(0,qid.length()-2);
               int qidd = Integer.valueOf(qid);
               String ida = request.getParameter("id_a");
               ida = ida.substring(0,ida.length()-3);
               int idaa = Integer.valueOf(ida);
               String c = request.getParameter("content");               
               
               int st = sws.addAnswer(to,qidd,idaa,c,ipa,browser);              
               if (st>0) {
                   response.sendRedirect("open.jsp?id="+qidd);
               } else {
                    m1= "You can't make answer right now <br>";
                    if (st==-1) m1=m1+"Your token is not valid. ";
                    else if (st==-2) m1=m1+"Your token has expired. ";
                    else if (st==-3) m1=m1+"You are accessing from different ip ";
                    else if (st==-4) m1=m1+"You are using different browser";
                    m1=m1+"Please re-login.";
                    m2= "logout"; 
               }
           }
           
           else if (action.contains("vote")) {
               String idd = request.getParameter("id");
               int id = Integer.valueOf(idd);
               int num=0;
               if (action.contains("up")) num=1;
               else num=-1;
               int qid = id;
               if (qid>=1000) qid = qid/1000;
               String t = request.getParameter("t");
               int st = sws.changeVote(to, t, id, num);
               m2 = "open.jsp?id="+String.valueOf(qid);
               /*out.println("|"+id+"|");
               out.println("|"+num+"|");
               out.println("|"+qid+"|");
               out.println("|"+t+"|");*/
               if (st>0) {
                   m1 = "You're vote has been saved";
               } else if (st<=0 && st!=-999) {
                    m1= "You can't make a vote now <br>";
                    if (st==-1) m1=m1+"Your token is not valid. ";
                    else m1=m1+"Your token has expired. ";
                    m1=m1+"Please re-login.";
                    m2= "logout"; 
               } else if (st==-999) {
                   m1 = "You already vote this one. You can't vote more than once";
               }             
           }
           else out.println("|"+action+"|");
           
        %>
    </head>
    <body>
         
           <a href="home.jsp"><div id="h">Stack <at>Exchange</at></div></a>
        
        
            <div class="raq"> </div>
            <div class="separator"></div>
            <% 
                response.setHeader("Refresh", "2;url="+m2); 
               
            %>
            <div class="rsuccess">
                <%out.println(m1) ; %><br>
                You'll be redirected to 
                <% if (action.contains("vote")) out.println("previous");
                    else out.println("home"); %>
                page. If not click <a href="<% out.println(m2); %>"><rr>here</rr></a></div>

    </body>
</html>
