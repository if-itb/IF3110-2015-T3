<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<% 
		Cookie cookie = null;
		Cookie[] cookies = null;
		String access_token = null;
		
		// Get an array of Cookies associated with this domain
		cookies = request.getCookies();
		if( cookies != null ){
			for (int i = 0; i < cookies.length; i++){
				cookie = cookies[i];
				if(cookie.getName().equals("access_token")){
					access_token = cookie.getValue();
					String user_agent = request.getHeader("User-Agent");
					String ip_adr = request.getRemoteAddr();
					access_token += "#" + user_agent + "#" + ip_adr;
					break;
				}
			}
		}
		String q_id_string = request.getParameter("q_id");
		if((q_id_string!=null) && (q_id_string.matches("\\d+"))){
		
		int q_id = Integer.parseInt(q_id_string);

		QuestionService qservice = new QuestionService();
		Question qs = qservice.getQuestionPort();
		QuestionItem q = qs.getQuestionInfo(access_token,q_id);
		
		AnswerService aservice = new AnswerService();
		Answer as = aservice.getAnswerPort();
		List<AnswerItem> a = as.getAnswerList(access_token,q_id);
	%>
	<% if (q.getIdQuestion() != -1){ %>
	<%@ page import="java.util.*, java.io.*"%>
	<%@ page import = "org.tusiri.ws.question.QuestionService" %>
	<%@ page import = "org.tusiri.ws.question.Question" %>
	<%@ page import = "org.tusiri.ws.question.QuestionItem" %>
	<%@ page import = "org.tusiri.ws.question.GetQuestionInfo" %>
	<%@ page import = "org.tusiri.ws.answer.AnswerService" %>
	<%@ page import = "org.tusiri.ws.answer.Answer" %>
	<%@ page import = "org.tusiri.ws.answer.AnswerItem" %>
	<%@ page import = "org.tusiri.ws.answer.GetAnswerList" %>
	
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    	pageEncoding="ISO-8859-1"%>
    <jsp:include page="Header.jsp" flush="true">
		<jsp:param name="pageTitle" value="<%= q.getTopic() %>" />
		<jsp:param name="needDeleteQuestion" value="true" />
		<jsp:param name="check" value="1" />
	</jsp:include>
	<script src="assets/js/vote.js"></script>
	<script src="assets/js/validator.js"></script>
	<% } %>
</head>
<% if (q.getIdQuestion() != -1){ %>
<body class="contact">
	
	
		<article id="main">
			<header class="special container">
				<span class="icon fa-github-alt"></span>
				<strong><h2>Question</h2></strong>
			</header>
			
	<div id="page-wrapper" ng-app="questionApp" ng-controller="questionCtrl" ng-init="init()">
		<!-- Header -->
		<header id="header">
			<h1 id="logo"><a href="index.jsp">Stack Exchange <span>| by Tusiri</span></a></h1>
			<jsp:include page="navigationbar.jsp" flush ="true"/>
		</header>
			<div class = 'container wrapper style1'>
				<h2><%= q.getTopic() %></h2>
				<div class = 'q_details'>
					<div class = 'only_q'>
						<div class = 'a_left'>
							<div class = 'vote_buttons'>
								<div hidden class='up_button user' ng-click="VoteUp()"><img id='q_up' ng-src={{imageup}} width='30' height='30'></div>
									<div class = 'vote' id='q_vote<%=q_id%>'>{{vote}}</div>
								<div hidden class='down_button user'ng-click="VoteDown()"><img id='q_down' ng-src={{imagedown}} width='30' height='30'></div>
							</div>
						</div>
						<div class = 'a_mid'>
							<div class = 'a_content'><%= q.getContent() %></div></div>
						</div>
						<div class = 'details'>Asked by 
							<span class = 'b_link'><%= q.getUsername() %> </span>
							<span hidden class = 'modify_<%=q.getIdUser()%>'>
				              		<a href = 'question_edit.jsp?id=<%= q.getIdQuestion() %>' class = 'y_link'> edit </a>|
				              		<a onclick='delQuestion(<%= q.getIdQuestion() %>,false)' class = 'r_link'>delete</a><br>
			              	</span>
		              	</div>
					</div>
				</div>
				<div class = 'container wrapper style1'>
					<div class = "comment_section">
						<ul>
				 			<li ng-repeat="x in comments">
				 			<div class = 'a_mid'>
				   				<div class = 'a_content'>{{ x.comment }} | {{ x.commentDate }}  | {{ x.username }}</div>
				 			</div>
				 			</li>
						</ul>
						<input type="button" value="Add a comment" ng-click="ShowHide()" />
						<form ng-show = "IsVisible" ng-submit="submitComment()">
							<input type="text" ng-model="comment" required/>
						  <input type="hidden" ng-model="idQuestion" value="{{id_question}}" />
						  <input type="hidden" ng-model="accessToken" value="{{access_token}}" />
						</form>
					</div>
				</div>
			<div class = 'container wrapper style3'>
				<h3><%=a.size()%> Answer</h3>
				<div ng-repeat="answer in answers track by $index">
					<div class = 'row q_or_a'>
						<div class = 'a_left'>
							<div class = 'vote_buttons'>
								<div hidden class='up_button user' ng-click="VoteAnswerUp(answer)"><img ng-src={{answer.imageup}} width='30' height='30'></div>
									<div class = 'vote'>{{answer.vote}}</div>
								<div hidden class='down_button user'ng-click="VoteAnswerDown(answer)"><img ng-src={{answer.imagedown}} width='30' height='30'></div>
							</div>
						</div>
						<div class = 'a_mid'>
							<div class = 'a_content'>{{answer.content}}</div>
						</div>
						<div class = 'details'>Answered by 
							<span class = 'b_link'>{{answer.user}} </span>
						</div>
					</div>
				</div>
				<h3>Your Answer</h3>
				<form name ='q_form' action="answer_create_post.jsp"  onsubmit='return validate_AForm()' METHOD="POST" >
					<div class="controls">
						<input type = 'hidden' name = 'q_id' value = '<%=q_id%>'/>
                    	<textarea rows="10" cols="100" class="form-control" name="content" required maxlength="999" style="resize:none"></textarea>
					</div>
					<div class="row">
						<div class="12u">
							<ul class="buttons">
								<li><input type="submit" class="special" value="Submit Answer" /></li>
							</ul>
						</div>
					</div>
				</form>
				
			</div>
		</article>
		<%@include file="footer.jsp" %>
		
	</div>
	
	<script>
	
	
var app = angular.module('questionApp', []);
app.controller('questionCtrl', function($scope, $http) {
	
	var answers = [];
	<% for (int i=0; i<a.size(); i++) { %>
			var obj = {
				id : <%= a.get(i).getNumAnswer() %>,
				vote : <%= a.get(i).getNumVotes() %>,
				status : <%= a.get(i).getStatus() %>,
				content : "<%= a.get(i).getContent() %>", 
				user : "<%= a.get(i).getUsername() %>", 
				imageup : "assets/img/up"+<%= a.get(i).getStatus() %>+".png",
				imagedown : "assets/img/down"+<%= a.get(i).getStatus() %>+".png"
			};
			answers.push(obj);
	<% } %>
	
	$scope.init = function() {
		$scope.vote = <%= q.getNumVote()%>;
    	$scope.access_token = "<%=access_token%>";
    	$scope.id_question = <%= q_id%>;
    	$scope.q_status = <%=q.getStatus()%>;
    	$scope.imageup = "assets/img/up"+<%= q.getStatus() %>+".png",
    	$scope.imagedown = "assets/img/down"+<%= q.getStatus() %>+".png"
    	$scope.answers = answers;
	}
	
	$scope.IsVisible = false;
    $scope.ShowHide = function () {
        //If DIV is visible it will be hidden and vice versa.
        $scope.IsVisible = $scope.IsVisible ? false : true;
    }
    
   $http.get("http://localhost:8081/Comment_Vote-WS/comment/question/show/<%=q_id_string%>")
   .then(function(response) {$scope.comments = response.data;});
   
   
   $scope.submitComment = function() {
		alert($scope.comment);
		var data = $.param({
		  access_token: $scope.access_token,
           comment: $scope.comment,
           id_question: $scope.id_question
       });
   
       var config = {
    		  
           headers : {
               'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
           },
       }

       $http.post('http://localhost:8081/Comment_Vote-WS/comment/question/add/', data, config)
       .success(function (data, status, headers, config) {
    	   
           if(data.id_comment > 0){
        	  $scope.comments = $scope.comments.concat(data);
        	  
           }
           
       })
       $scope.comment = null;
   };
	
    $scope.VoteUp = function(){
    	var data = $.param({
 		   	access_token: $scope.access_token,
         	id_question: $scope.id_question
     	});
    	var config = { headers : {
            	 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'}}
     		$http.post('http://localhost:8081/Comment_Vote-WS/rest/votequestion/voteup/', data, config)
     		.success(function (data, status, headers, config) {
        		 $scope.PostDataResponse = data;
        		 $scope.vote = data;
        		 
        		 if($scope.q_status ==1){
        			 $scope.q_status = 0;
	    		 }
	    		 else{
	    			 $scope.q_status = 1;
	    		 }
        		 $scope.imageup = "assets/img/up"+$scope.q_status+".png";
        		 $scope.imagedown = "assets/img/down"+$scope.q_status+".png";
        		 
     		})
  		};
  		
	$scope.VoteDown = function(){
    	var data = $.param({
 		   	access_token: $scope.access_token,
         	id_question: $scope.id_question
     	});
    	var config = { headers : {
            	 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'}}
     		$http.post('http://localhost:8081/Comment_Vote-WS/rest/votequestion/votedown/', data, config)
     		.success(function (data, status, headers, config) {
        		 $scope.PostDataResponse = data;
        		 $scope.vote = data;
        		 
        		 if($scope.q_status ==-1){
        			 $scope.q_status = 0;
	    		 }
	    		 else{
	    			 $scope.q_status = -1;
	    		 }
        		 $scope.imageup = "assets/img/up"+$scope.q_status+".png";
        		 $scope.imagedown = "assets/img/down"+$scope.q_status+".png";
        		 
     		})
  		};
    
	$scope.VoteAnswerUp = function(answer){
		var data = $.param({
			access_token: $scope.access_token,
	     	id_answer: answer.id
	 	});
		var config = { headers : {
	        	 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'}}
	 		$http.post('http://localhost:8081/Comment_Vote-WS/rest/voteanswer/voteup/', data, config)
	 		.success(function (data, status, headers, config) {
	    		 $scope.PostDataResponse = data;
	    		 answer.vote = data;
	    		 if(answer.status ==1){
	    			 answer.status = 0;
	    		 }
	    		 else{
	    			 answer.status = 1;
	    		 }
	    		 answer.imageup = "assets/img/up"+answer.status+".png";
	    		 answer.imagedown = "assets/img/down"+answer.status+".png";
	 		})
		};
		
		$scope.VoteAnswerDown = function(answer){
			var data = $.param({
				access_token: $scope.access_token,
		     	id_answer: answer.id
		 	});
			var config = { headers : {
		        	 'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'}}
		 		$http.post('http://localhost:8081/Comment_Vote-WS/rest/voteanswer/votedown/', data, config)
		 		.success(function (data, status, headers, config) {
		    		 $scope.PostDataResponse = data;
		    		 answer.vote = data;
		    		 if(answer.status ==-1){
		    			 answer.status = 0;
		    		 }
		    		 else{
		    			 answer.status = -1;
		    		 }
		    		 answer.imageup = "assets/img/up"+answer.status+".png";
		    		 answer.imagedown = "assets/img/down"+answer.status+".png";
		 		})
			};
});

</script>

</body>
	<% 	} else {
			%>
			<jsp:include page="notfound.jsp" flush="true">
				<jsp:param name="onlyBody" value="true" />
			</jsp:include>
		<%}
	} else {
		%>
		<jsp:include page="notfound.jsp" flush="true">
			<jsp:param name="onlyBody" value="true" />
		</jsp:include>
	<%
	} %>
</html>