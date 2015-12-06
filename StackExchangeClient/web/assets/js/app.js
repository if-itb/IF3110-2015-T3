(function(){
    
    var app = angular.module('stackExchange', []);
    
    app.controller('CommentController', ['$http', '$scope', function($http, $scope){
        $scope.questionId = 1;
        $scope.comments = [];
        
        $http.get('http://localhost:8080/StackExchangeCommentVote/GetComments?questionId=' + $scope.questionId).success(function(data){
            $scope.comments = data.comments;
        });
       
    }]);
    
    
})();


