<%-- 
    Document   : nyobaVote
    Created on : Dec 5, 2015, 3:28:56 PM
    Author     : FiqieUlya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js">
        </script>
	</head>
	<body>

		<div ng-app="myApp" 
        
	        ng-init="list=[]"
            
                ng-controller="myController">
                
            <div class="full-comment">
                <div class="input-comment">
                    <!--<input type="hidden" ng-model="id" ng-value=3 />--> 
                    <!--input type="text" ng-model="username" placeholder="Enter your name" /--> 
                    <br>
                    <input type="text" ng-model="content" placeholder="Enter your comment" />

                    <p><button ng-click="add(3, 'dininta')">Add Comment</button></p>
                </div>
                <div class="list-comment">

                    <h3>Comment</h3>

                    <!-- LOOP.-->
                    <ul ng-repeat="comments in list">
                        <div class = comments>
                        <li>{{comments.content}}</li>
                        <li>{{comments.username}}</li> 
                        <li> {{comments.id}}  </li>
                        </div>
                    </ul>

                </div>
            </div>

	</body>
        <script src="controller.js" type="text/javascript"></script>
</html>