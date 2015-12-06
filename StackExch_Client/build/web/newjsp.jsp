<%-- 
    Document   : newjsp
    Created on : Dec 6, 2015, 10:35:38 AM
    Author     : tama
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app ='voteApp'>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.js"></script>
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    
<div ng-controller = 'commentController'>
     "<h4>Comments</h4>"
       <div ng-repeat='comment in comments' class='block-comment'>
                                <div class='bc-content'>
                                    {{comment.content}}
                                     - 
                                    <a id='color-blue'>
                                    {{comment.author}}
                                    </a>- {{comment.timestamp}}
                                </div>
                            </div>
                       
                </div>

  <script>
       
         var app = angular.module('voteApp',[]);
        
        console.log("Start");
        app.controller('commentController', function($scope, $http) {
            var commentUrl = "http://localhost:35476/CommentVote/AllComment"; 
            var q_id = "1";
            var access_token = "aaaaa";
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
                     /*if ($scope.message == 1 || $scope.message == -5){
                     } else {
                         window.location.href = "http://localhost:8080/StackExchange_Client/error.jsp?id=" + $scope.message + "&token=" + access_token;
                     }*/
                 },function (err){
                     console.log("Error : " + err);
                 });    
                
                $scope.comment_content = "Initialization";
                
              
        
        });
    
    </script>

</body>
</html>