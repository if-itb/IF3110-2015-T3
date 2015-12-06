<%-- 
    Document   : question
    Created on : Nov 16, 2015, 11:31:39 PM
    Author     : chairuniaulianusapati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app ='voteApp'>
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
 
                    "<div  ng-controller='voteQuestionController' class='block-QA'>"
			+"<div class='bQA-vote'>"
                            +"<a ng-click='direction=1; voteQuestion( " + request.getParameter("token") + "," + request.getParameter("id") + "," + "direction)" + "'><div class='vote-up'>"
                            +"</div></a>"
                            +"<br>"
                            +"<a ng-model='voteQuestionCounter' ng-init='loadInitial(" + request.getParameter("id") + ") ;' class='vote-value' id='question_vote-" + qid +"'>"
				+ "{{voteQuestionCounter}}"
                            +"</a>"
                            +"<br><br>"
                            +"<a ng-click='direction=0; voteQuestion( " + request.getParameter("token") + "," + request.getParameter("id") + "," + "direction)" + "'><div class='vote-down'>"
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
                "<div ng-controller = 'commentController'>"
                        + "Add Comment"
                        + "<form >"
                            + "<textarea ng-model='comment_content' placeholder='Your Comment' style='width:550px'></textarea>"
                            + "<button ng-click='addComment(" + request.getParameter("token") + "," + request.getParameter("id") + "," + "comment_content);' style='margin-top:10px;'>Post Comment</button>"
                            + "<h4>Comments</h4>"

                            + "<div ng-repeat='comment in comments' class='block-comment'>"
                                +"<div class='bc-content'>"
                                    +"{{comment.content}}"
                                    +" - "
                                    +"<a id='color-blue'>"
                                    +"{{comment.author}}"
                                    +"</a>" +"- {{comment.timestamp}}"
                                +"</div>"
                            +"</div>"
                        + "</form>"
                + "</div>";
                
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
                    "<div ng-app ='voteApp' ng-controller='voteAnswerController' class='block-QA'>"
                        +"<div class='bQA-vote'>"
                            +"<a ng-click='direction=1; voteAnswer(" + request.getParameter("token") + "," + result.get(i).getIDAns() + "," + "direction)" + "'><div class='vote-up'>"
                            +"</div></a>"
                            +"<br>"
                            +"<a ng-init='voteAnswerCounter=" + result.get(i).getVote() + ";' ng-model='voteAnswerCounter' class='vote-value' id='answer_vote-"+ 
                            "'>{{voteAnswerCounter}}" + "</a>"
                            +"<br><br>"
                            +"<a ng-click='direction=0; voteAnswer( " + request.getParameter("token") + "," + result.get(i).getIDAns() + "," + "direction)" + "'><div class='vote-down'>"
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
                    +"</div>";
                
                String start = "<li class='collection-item avatar'><i class='material-icons circle'>folder</i>";
                String end = "<br><br><br><br><br><br><a href='' class='secondary-content'><i class='material-icons'>grade</i></a></li>";

            out.write(start + answer + end);
                
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


    <script>
        var app = angular.module('voteApp',[]);
        
        console.log("Start");
        app.controller('commentController', function($scope, $http) {
            var commentUrl = "http://localhost:8082/StackExchange_IS/allComments"; 
            var q_id = "<%= request.getParameter("id") %>";
            var access_token = "<%= request.getParameter("token") %>";
            console.log(q_id);
            var commentParameter = {question_id: q_id, access_token: access_token};
            console.log(JSON.stringify(commentParameter));
            $http({
                     url: commentUrl,
                     data: JSON.stringify(commentParameter),
                     method: 'POST',
                     dataType: "json",
                     crossDomain: true
                 })
                 .then(function (response){
                     console.log("Success");
                     $scope.comments = [];
                     $scope.comments = response.data.comments;
                     console.log(JSON.stringify($scope.comments));
                     console.log(JSON.stringify($scope.message));
                     /*if ($scope.message == 1 || $scope.message == -5){

                     } else {
                         window.location.href = "http://localhost:8080/StackExchange_Client/error.jsp?id=" + $scope.message + "&token=" + access_token;
                     }*/
                 },function (err){
                     console.log("Error : " + err);
                 });    
                
                $scope.addComment = function(access_token, qid,comment_content) {
                    
                    console.log("Luminto Homo");
                    var addCommentUrl = "http://localhost:8082/StackExchange_IS/commentService";                                 
                    var access_token = "<%= request.getParameter("token") %>";
                    var qid = "<%= request.getParameter("id") %>";
                    var content = $scope.comment_content;
                    var yourComment = {access_token: access_token ,question_id: qid, comment: content};
                    console.log("Access Token : " + access_token);
                    console.log("Question ID : " + qid);
                    console.log("Comment Content : " + content);
                    console.log(JSON.stringify(yourComment));
                    $http({
                        url: addCommentUrl,
                        data: JSON.stringify(yourComment),
                        method: 'POST',
                        dataType: "json",
                        crossDomain: true
                    })
                    .then(function (response){
                        console.log("Success");
                        $scope.comments.push(response.data);
                        $scope.message = response.data.message;
                        $scope.coba = response.data;
                        console.log(JSON.stringify(response));
                        console.log("Message " + $scope.message);
                        console.log("Cba" + JSON.stringify($scope.comments))
                        if ($scope.message == 1 || $scope.message == -5 || $scope.message == undefined){

                        } else {
                            window.location.href = "http://localhost:8080/StackExchange_Client/error.jsp?id=" + $scope.message + "&token=" + access_token;
                        }
                    },function (err){
                        console.log("Error : " + err);
                    });
            };

        
        });

        app.controller('voteQuestionController', function($scope,$http){
         var voteUrl = "http://localhost:8082/StackExchange_IS/initiateQVote";
            $scope.token = "<%= request.getParameter("token") %>";
            $scope.direction = -99;

            $scope.loadInitial = function(qid){
                var access_token = "<%= request.getParameter("token") %>";

            var qid = "<%= request.getParameter("id") %>";

            var commentParameter = {question_id: qid};
             console.log("Question ID - Quesr=tion: " + qid);
             console.log(JSON.stringify(commentParameter));
             $http({
                 url: voteUrl,
                 data: JSON.stringify(commentParameter),
                 method: 'POST',
                 dataType: "json",
                 crossDomain: true
             })
             .then(function (response){
                 console.log("Success");
                  console.log("DEBUG 1 : " + JSON.stringify(response));
                $scope.voteQuestionCounter = response.data.vote;    
             },function (err){
                 console.log("Error : " + err);
             });
         };

    
            console.log("Hello World");
            $scope.voteQuestion = function(access_token, qid, direction) {
       
                var voteUrl = "http://localhost:8082/StackExchange_IS/voteServiceQuestion";
                var access_token = "<%= request.getParameter("token") %>";
                var qid = "<%= request.getParameter("id") %>";
                var commentParameter = {access_token: access_token ,question_id: qid,direction : $scope.direction};
                console.log("Access Token : " + access_token);
                console.log("Question ID : " + qid);
                console.log(JSON.stringify(commentParameter));
                $http({
                    url: voteUrl,
                    data: JSON.stringify(commentParameter),
                    method: 'POST',
                    dataType: "json",
                    crossDomain: true
                })
                .then(function (response){
                    console.log("Success");
                    $scope.message = response.data.message;
                    console.log("Message " + $scope.message);
                    if ($scope.message == 1){
                        $scope.voteQuestionCounter = response.data.vote;
                    } else if ($scope.message == -5) {
                        
                    } else {
                        window.location.href = "http://localhost:8080/StackExchange_Client/error.jsp?id=" + $scope.message + "&token=" + access_token;
                    }
                },function (err){
                    console.log("Error : " + err);
                });
            };
        });
        
                
        //var aApp = angular.module('voteAnswerApp',[]);

        app.controller('voteAnswerController', function($scope,$http){
            var voteUrl = "http://localhost:8082/StackExchange_IS/voteServiceAnswer";
            $scope.token = "<%= request.getParameter("token") %>";
            $scope.direction = -99;
            console.log("CUPU");
            $scope.voteAnswer = function(access_token, ansid, direction) {
                var access_token = "<%= request.getParameter("token") %>";
                var commentParameter = {access_token: access_token ,answer_id: ansid,direction : $scope.direction};
                console.log("Access Token : " + access_token);
                console.log("Answer ID : " + ansid);
                console.log(JSON.stringify(commentParameter));
                $http({
                    url: voteUrl,
                    data: JSON.stringify(commentParameter),
                    method: 'POST',
                    dataType: "json",
                    crossDomain: true
                })
                .then(function (response){
                    console.log("Success");
                    $scope.message = response.data.message;
                    console.log("Message " + $scope.message);
                    if ($scope.message == 1){
                        $scope.voteAnswerCounter = response.data.vote;
                    } else if ($scope.message == -5) {
                        
                    } else {
                        window.location.href = "http://localhost:8080/StackExchange_Client/error.jsp?id=" + $scope.message + "&token=" + access_token;
                    }
                },function (err){
                    console.log("Error : " + err);
                });
            };
        });

    
    </script>
</html>
