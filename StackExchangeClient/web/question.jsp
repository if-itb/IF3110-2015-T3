<%-- 
    Document   : question
    Created on : Nov 16, 2015, 11:31:39 PM
    Author     : chairuniaulianusapati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
    <head>
          <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">    
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script> 
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.js"></script>
        <nav class="light-blue lighten-1" role="navigation">
            <div class="nav-wrapper container">
                
            <% out.write("<a id='logo-container' href='index.jsp?token="+ request.getParameter("token") +"' class='brand-logo'>Home</a>");%>
            <%
                if (request.getParameter("token").equals("null")){
                    String border = "<ul class='right hide-on-med-and-down'>"
                                        + "<li><a href='login.jsp'>Login</a></li>"
                                        + "<li><a href='register.jsp'>Register</a></li>"
                                    + " </ul>" + 
                                    "<ul id='nav-mobile' class='side-nav'>"
                                        + "<li><a href='login.jsp'>Login</a></li>"
                                        + "<li><a href='register.jsp'>Register</a></li>"
                                    + " </ul>";
                    out.write(border);
                } else {
                    com.wbd.rgs.RegisterWS_Service service = new com.wbd.rgs.RegisterWS_Service();
                    com.wbd.rgs.RegisterWS port = service.getRegisterWSPort();
                    java.lang.String accessToken = request.getParameter("token");
                    java.lang.String result = port.getUsername(accessToken);
                    String border = "<ul class='right hide-on-med-and-down'>"
                                        + "<li>" + result + "</li>"
                                        + "<li><a href='signout.jsp?token=" + accessToken +"'>Sign Out</a></li>"
                                    + " </ul>" + 
                                    "<ul id='nav-mobile' class='side-nav'>"
                                        + "<li>" + result + "</li>"
                                        + "<li><a href='signout.jsp?token=" + accessToken +"'>Sign Out</a></li>"
                                    + " </ul>";
                    out.write(border);
                }
            %>
            <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
            </div>
        </nav>   
        <script>
            $(document).ready(function(){

              // Initialize collapse button
              $(".button-collapse").sideNav();
            });
        </script>
        
                 <title>Simple StackExchange</title>
</head>

    <body>
        
        <%
        try {
            com.wbd.qst.QuestionWS_Service service = new com.wbd.qst.QuestionWS_Service();
            com.wbd.qst.QuestionWS port = service.getQuestionWSPort();
            com.wbd.qst.QuestionWS_Service service2 = new com.wbd.qst.QuestionWS_Service();
            com.wbd.qst.QuestionWS port2 = service2.getQuestionWSPort();
             // TODO initialize WS operation arguments here
            int qid = Integer.parseInt(request.getParameter("id"));
            // TODO process result here
            //out.println("QID : " + qid);
            java.util.List<com.wbd.qst.Question> result = port.getQuestionbyID(qid);
            java.lang.String result2 = port2.getNama(qid);
            int i = 0;
            out.write("<h2 class='header center-align orange-text'>" + result.get(i).getQuestionTopic() + "</h2>");
            out.write("<ul class='collection'>");
            
            
                String question = 
 
                    "<div ng-app ='voteApp' ng-controller='voteController' class='block-QA'>"
			+"<div class='bQA-vote'>"
                            +"<a href = voteUp.jsp?id=" + request.getParameter("id") + "&token=" + request.getParameter("token") + "><div class='vote-up'>"
                            +"</div></a>"
                            +"<br>"
                            +"<a class='vote-value' id='question_vote-" + qid +"'>"
				+result.get(i).getVote()
                            +"</a>"
                            +"<br><br>"
                            +"<a href = voteDown.jsp?id=" + request.getParameter("id") + "&token=" + request.getParameter("token") + "><div class='vote-down'>"
                        +"</div>"
                        +"</a>"
			+"</div>"
			+"<div class='bQA-content'>"
                            +result.get(i).getContent()
                            +"<br><br>"
                        +"</div>"
                        +"<div class='bQA-identity'>"
                            +"asked by "
                            +"<a id='color-blue'>"
                            +result2
                            +"</a>"
                            +" | "
                            +"<a id='color-orange' href=edit.jsp?id=" + result.get(i).getIDQ() + "&token=" + request.getParameter("token") + ">"
                                +"edit"
                            +"</a>"
                            +" | "
                            +"<a id='color-red' href=delete.jsp?id=" + result.get(i).getIDQ() + "&token=" + request.getParameter("token") + ">"
                                +"delete"
                            +"</a>"
	    		+"</div>"
	    	+"</div>";
                
                String question_comment = 
                "<div class='block-comment'>"
                    +"<div class='bc-content'>"
                        +"ini komen"
                        +" - "
                        +"<a id='color-blue'>"
                            +"penanya"
                        +"</a>"
                    +"</div>"
	    	+"</div>";
                
                String start = "<li class='collection-item avatar'><i class='material-icons circle'>folder</i>";
                String end = "<br><br><br><br><br><br><a href='' class='secondary-content'><i class='material-icons'>grade</i></a></li>";
                
                out.write(start + question + question_comment + end);
                out.write("<br><br>");
            
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        %>
   
        <%
        try {
            com.wbd.ans.AnswerWS_Service service = new com.wbd.ans.AnswerWS_Service();
            com.wbd.ans.AnswerWS port = service.getAnswerWSPort();
            com.wbd.ans.AnswerWS_Service service2 = new com.wbd.ans.AnswerWS_Service();
            com.wbd.ans.AnswerWS port2 = service2.getAnswerWSPort();
             // TODO initialize WS operation arguments here
            int qid = Integer.parseInt(request.getParameter("id"));
            // TODO process result here
            java.util.List<com.wbd.ans.Answer> result = port.getAnswerByQID(qid);
            String isManyAnswer;
            if(result.size() > 1){
                isManyAnswer = " Answers";
            }
            else{
                isManyAnswer = " Answer";
            }
            String answerTitle = 
                "<h2 class='header center-align orange-text'>" + result.size() + isManyAnswer + "</h2>"
            ;
            out.write(answerTitle);
            out.write("<ul class='collection'>");
            for(int i = 0; i < result.size() ; i++){
                
                java.lang.String result2 = port2.getNamaAns(result.get(i).getIDAns());

                // java.lang.String result2 = port.getNamaAns(result.get(i).getIDAns());

                String answer = 
                    "<div class='block-QA'>"
                        +"<div class='bQA-vote'>"
                            +"<a href = voteUpAns.jsp?id=" + request.getParameter("id") + "&token=" + request.getParameter("token") + "&ansid=" + result.get(i).getIDAns() + "><div class='vote-up'>"
                            +"</div></a>"
                            +"<br>"
                            +"<a class='vote-value' id='answer_vote-"+ result.get(i).getIDAns() +"'>" + result.get(i).getVote()
                            +"</a>"
                            +"<br><br>"
                            +"<a href = voteDownAns.jsp?id=" + request.getParameter("id") + "&token=" + request.getParameter("token") + "&ansid=" + result.get(i).getIDAns() + "><div class='vote-down'>"
                            +"</div></a>"
			+"</div>"
                        +"<div class='bQA-content'>"
                            +result.get(i).getAnswer()
                            +"<br><br>"
                        +"</div>"
                        +"<div class='bQA-identity'>" 
                            +"answered by "
                            +"<a id='color-blue'>"
                            +result2
                            +"</a>"
                        +"</div>"
                    +"</div>"
                ;
                String answer_comment = 
                "<div class='block-comment'>"
                    +"<div class='bc-content'>"
                        +"ini komen"
                        +" - "
                        +"<a id='color-blue'>"
                            +"penanya"
                        +"</a>"
                    +"</div>"
	    	+"</div>"           
                ;
                String start = "<li class='collection-item avatar'><i class='material-icons circle'>folder</i>";
                String end = "<br><br><br><br><br><br><a href='' class='secondary-content'><i class='material-icons'>grade</i></a></li>";

            out.write(start + answer + answer_comment + end);
                
            }
            out.write("</ul>");
            
            //out.write("<div ng-app='myApp' ng-controller='customersCtrl'> <ul><li ng-repeat='x in names'>{{ x.Name + ', ' + x.Country }}</li></ul></div>'");
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
        %>
        <%
        %>
        <div class="container">
            <br><br>
            <h1 class="header center orange-text">Answer here!</h1>
        </div>

        <div class="row">
            <%out.write("<form class='col s12' name='loginForm' action='createAnswer.jsp?id="+ request.getParameter("id")+"&token="+ request.getParameter("token") +"' onsubmit='' method='POST'>");%>
            <%out.write("<input type='hidden' name='question_id' value='"+ request.getParameter("id") +"'>");%>  
            <div class="row">
                <div class="input-field col s12">
                  <textarea id="textarea1" class="materialize-textarea" placeholder="The content goes here..." name="content" type="text" required></textarea>
                   <label for="textarea1" data-error="wrong" data-success="right">Content</label>
                </div>
              </div>
              <button class="btn waves-effect waves-light" type="submit" name="action">answer
                <i class="material-icons right">send</i>
            </button>
            </form>
          </div>
    </body>

<div ng-app="myApp" ng-controller="customersCtrl"> 

<ul>
  <li ng-repeat="x in names">
    {{ x.Name + ', ' + x.Country }}
  </li>
</ul>

</div>
<script>
var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
  $http.get("http://www.w3schools.com/angular/customers.php")
  .then(function (response) {$scope.names = response.data.records;});
});
</script>

    <script>
        var app = angular.module('voteApp',[]);
        app.controller('voteController', function($scope,$http){
            console.log("Luminto Homo");
            var access_token = "<%= request.getParameter("token") %>";
            var qid = "<%= request.getParameter("id") %>";
            var data = {access_token: access_token ,question_id: qid , direction: 1  };
            console.log("Access Token : " + access_token);
            console.log("Question ID : " + qid);
            console.log(data);
            console.log(JSON.stringify(data));
            $scope.voteUrl = "http://localhost:8082/StackExchange_IS/rest/voteQ";
            
            $http({
                url: $scope.voteUrl,
                data: data,
                method: 'POST'
            })
            .then(function successCallback(response){
                console.log("Success");
            },function errorCallback(err){
                console.log("Error : " + err);
            });
         
        });
    </script>
</html>
