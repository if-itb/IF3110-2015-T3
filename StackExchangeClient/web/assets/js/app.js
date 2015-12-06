(function(){
    
    var app = angular.module('stackExchange', []);
    
    app.controller('CommentController', ['$http', '$scope', function($http, $scope){
        $scope.comments = [];
        
        $scope.getComments = function(questionId){
            $http.get('http://localhost:8080/StackExchangeCommentVote/GetComments?questionId=' + questionId).success(function(data){
                $scope.comments = data.comments;
            });
        };
       
    }]);
    
    
})();


