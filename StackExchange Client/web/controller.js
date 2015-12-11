var app = angular.module('controller', []);

app.controller('CommentController', function($scope,$http){
        $scope.comment = {};
        $scope.comments = [];
        
        $scope.getcomment = function(){
            $http({
                url: "http://localhost:8083/Vote_Comment/CommentServlet",
                method: "GET",
                params: {qid: $scope.comment.qid}
            }).success(function(data) {
                if (!data[0].error) {
                    $scope.comments = data;
                }
            });
        };
        
        $scope.addcommentasync = function(comments){
           console.log($scope.comment);
           comments.push($scope.comment);
           var res = $http({
                method : 'POST',
                url : 'http://localhost:8083/Vote_Comment/CommentServlet',
                data : $.param({
                    'qid' : $scope.comment.qid,
                    'comment' : $scope.comment.comment  
                })
           });
           $scope.comment = {};
        };
    });
    
app.controller('voteCtrl', function($scope, $http, $location, $cookies) {
    //get question id through parameter
    var temp = $location.absUrl().split("?");
    temp = temp[1].split("=");
    $scope.id=temp[1];
    
    $http.get("http://localhost:8083/Vote_Comment/InitVote?type=q&id="+$scope.id)
    .then(function(response) {$scope.qvote = response.data.qvote;});
    
    $http.get("http://localhost:8083/Vote_Comment/InitVote?type=a&id="+$scope.id)
    .then(function(response) {$scope.avotes = response.data.avotes;});
    
    $scope.qvoteup = function() {
        $scope.url = "stat=1";
        $scope.url += "qid="+qid;
        $scope.url += "&token="+$cookies.get('token');
        $http.get($scope.url).then(function(response) {$scope.qvote = response.data.qvote;});
    };
    
    $scope.qvotedown = function() {
        $scope.url = "stat=-1";
        $scope.url += "qid="+qid;
        $scope.url += "&token="+$cookies.get('token');
        $http.get($scope.url).then(function(response) {$scope.qvote = response.data.qvote;});
    };
    
    $scope.avoteup = function(av) {
        $scope.url = "stat=1";
        $scope.url += "aid="+aid;
        $scope.url += "&token="+$cookies.get('token');
        $http.get($scope.url).then(function(response) {av = response.data.avote;});
    };
    
    $scope.avotedown = function(av) {
        $scope.url = "stat=-1";
        $scope.url += "aid="+aid;
        $scope.url += "&token="+$cookies.get('token');
        $http.get($scope.url).then(function(response) {av = response.data.avote;});
    };
});
