(function(){
    
    var app = angular.module('stackExchange', []);
    
    app.controller('CommentController', ['$http', '$scope', function($http, $scope){
        $scope.comments = [];
        
        $scope.getComments = function(questionId){
            $http.get('http://localhost:8080/StackExchangeCommentVote/GetComments?questionId=' + questionId).success(function(data){
                $scope.comments = data.comments;
            });
        };
        
        $scope.submitComment = function(token, questionId){
            console.log($scope.commentArea);
            if($scope.commentArea){
                $http.get('http://localhost:8080/StackExchangeCommentVote/CreateComment?questionId=' + questionId + "&token=" + encodeURIComponent(token) + "&comment=" + $scope.commentArea).success(function(data){
                    console.log("Success");
                });
            }
            else
                alert("Error");
        };
       
    }]);
    
    
})();


