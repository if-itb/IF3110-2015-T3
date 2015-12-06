<%-- 
    Document   : open
    Created on : Nov 11, 2015, 7:48:38 PM
    Author     : tama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
       <%@  page import="java.net.URL,javax.xml.namespace.QName,javax.xml.ws.Service" %>
       <% webservice.NewWebService service = new webservice.NewWebService();
	webservice.StackWebSevice sws = service.getStackWebSevicePort();
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
      
            String t_id = request.getParameter("id");
            int id = Integer.valueOf(t_id);
           webservice.Question  Q = sws.getQuestions(id);
           java.util.List<webservice.Answer> A = sws.getAllAnswers(id);
           int asize = A.size() ;
           String content = "";
           int ca = Integer.valueOf(Q.getTanswer()) ;
           int z = (id*1000) + ca+1;
          java.util.List<webservice.Comment> Cm = sws.getAllComment(id);
          int csize = Cm.size() ;
          int ccm = (id*1000) + csize+1;
        %>;
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>

         <link rel="stylesheet" type="text/css" href="style/style.css">
        <title><% out.println(Q.getTitle()) ; %></title>
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
         <%  String s = request.getParameter("s");
           if (s!=null) response.sendRedirect("home.jsp?s="+s);
           %>
         <form id="searchbox" href="home.jsp" action="home.jsp?s=<% out.println(s) ; %>">
             <input id="search" type="text" name="s" placeholder=" Type any keyword here . . . ">
             <input id="submit" type="submit" value="Search">
        </form>
        <p>Cannot find what you are looking for ? <a href="askhere.jsp"><as>Ask here</as></a></p>     
        <div class="raq"><% out.println(Q.getTitle()) ; %></div>
        <div class="separator"></div>      
        
        <div id="wrap">
            <div id="main">         
               <% out.println(Q.getContent()) ; %>
            </div>
            <div id="sidebar">
			
                <div id="vnum"><div id="<% out.println(Q.getIdQ());%>"><% out.println(Q.getVote()) ;%> </div> </div> 
                <div id="vup">
                    <a a href="votep?action=voteup&id=<% out.println(Q.getIdQ()) ; %>&t=q">
                    <img src="img/up.png" alt="Vote Up" style="width: 32px;height:32px">
                    </a>
                </div>
                <div id="vdown">
                    <a a href="votep?action=votedown&id=<% out.println(Q.getIdQ()) ; %>&t=q">
                    <img src="img/down.png" alt="Vote Down" style="width: 32px;height:32px">
                     </a>
                </div>
            </div>
            <div id="footer"> </div>
            <div id="qinfo">asked by <aa><% out.println(Q.getUsername()) ; %></aa> at<aa><% out.println(Q.getDate()) ; %></aa> |
            <a href="askhere.jsp?id=<% out.println(Q.getIdQ()) ; %>"><span class="bb">edit</span></a> |
            <a onclick ="return confirm('Are you sure to delete this question ?')" href="process.jsp?action=del&id=<% out.println(id) ;%>&owner=<% out.println(Q.getUsername());%>"><span class="cc">delete</span></a>  
            </div>
        </div><br><br>
        <div class="separator2"></div>        
         
        <div class="commentbox">   
          
<div ng-app="myApp" ng-controller="customersCtrl">

  <div ng-repeat="x in names">
      <div class="cmcontent"> {{ x.content}} </div> <cmdate><cmdd>at</cmdd>  {{x.date  }} </cmdate> by <cmu>{{x.username}} </cmu>
    <div class="separator3"></div>
  </div>

 
</div>
         <form action="http://localhost:21215/StackExch_Client/commentp" method="POST">
                 <input id="fcomment" type="text" name="ncomment" placeholder="Add a comment : "/><br> 
                 <input type="hidden" name="cipa" value="<%out.print(ipa);%>">
                 <input type="hidden" name="cbrowser" value="<%out.print(browser);%>">
                 <input type="hidden" name="q_id" value="<% out.print(id) ; %>" >
                 <input type="hidden" name="id_c" value="<%out.print(ccm) ; %>" >
                 <input id="fsubmit2" type="submit" value="Comment "><br>   
            </form>     
        </div>
        <div class="separator2"></div>
        <div id ="qans"><% out.println(Q.getTanswer()) ; %> Answers</div>
         <div class="separator"></div>
         
        <% for (int i=0;i<asize;i++) { %>
           <div id="wrap">
            <div id="main">         
                <% out.println(A.get(i).getContent()) ; %>
            </div>
            <div id="sidebar">         
                <div id="vnum"><div id="<% out.println(A.get(i).getIdA()) ; %>"> <% out.println(A.get(i).getVote()) ; %> </div></div> 
                <div id="vup"><a href="votep?action=voteup&id=<% out.println(A.get(i).getIdA()) ; %>&t=a">
                        <img src="img/up.png" alt="Vote Up" style="width: 32px;height:32px"></a>
                </div>
                <div id="vdown"><a a href="votep?action=votedown&id=<% out.println(A.get(i).getIdA()) ; %>&t=a">
                        <img src="img/down.png" alt="Vote Down" style="width: 32px;height:32px"></a>
                    </div>
            </div>
            <div id="footer"> </div>
            <div id="qinfo">answered by <aa><% out.println(A.get(i).getUsername()) ; %></aa> at<aa> <% out.println(A.get(i).getDate()) ; %></aa> 
             </div>
            
        </div>
          <div class="separator2"></div>
         <% } %>
         
         <div id="yans">Your Answer</div>
           
        <form action="process.jsp" method="POST">            
            <textarea id="fcontent" name="content" placeholder="Content : " ><% out.println(content); %></textarea> 
            <input id="fsubmit" type="submit" value="Answer "><br>               
            <input type="hidden" name="bb" value="addans" >              
            <input type="hidden" name="id_a" value="<%out.println(z) ; %>;" >
            <input type="hidden" name="q_id" value="<% out.println(id) ; %>" >
            <input type="hidden" name="action" value="answer">
            <input type="hidden" name="cipa" value="<%out.println(ipa);%>">
             <input type="hidden" name="cbrowser" value="<%out.println(browser);%>">
          </form>
 <script>
var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
    var id = <%= request.getParameter("id") %>;
    var url_ = "http://localhost/CommentandVote/CommentandVote/getAllComment.php?id="+id;
   
   console.log("id : "+id);
    $http.get(url_)
   .then(function (response) {$scope.names = response.data.records;});
});
</script>
          
    </body>
    
</html>

<%--
ret[0] = String.valueOf(aa);
                ret[1] = rs.getString("title");
                ret[2] = rs.getString("content");
                ret[3] = String.valueOf(rs.getInt("vote"));
                ret[4] = rs.getString("date");
                ret[5] = rs.getString("username");
                ret[6] = rs.getString("email"); 
                ret[7] = String.valueOf(countAnswer(aa));
  int aa = rs.getInt("id_a");
                    int bb = rs.getInt("q_id");
                    ret[count][0] = String.valueOf(aa);
                    ret[count][1] = String.valueOf(bb);
                    ret[count][2] = rs.getString("content");
                    ret[count][3] = String.valueOf(rs.getInt("vote"));
                    ret[count][4] = rs.getString("date");
                    ret[count][5] = rs.getString("username");
                    ret[count][6] = rs.getString("email"); 
--%>