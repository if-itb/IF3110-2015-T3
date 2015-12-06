var app = angular.module("commentApp", ['ngCookies']);

app.controller('voteCtrl',function($scope, $cookies, $http, $location, $window){
    $scope.value = "-";
    $scope.id = 0;
    
    if($window.navigator.userAgent.indexOf("Chrome/") > -1){
        $scope.browser = "chrome";
    }else if($window.navigator.userAgent.indexOf("Firefox/") > -1){
        $scope.browser = "firefox";
    }else if($window.navigator.userAgent.indexOf("Safari/") > -1){
        $scope.browser = "safari";
    }else{
        $scope.browser = "unknown";
    }
    
    $cookies.put("browser",$scope.browser);
    
    //hard code for get param
    if ($location.absUrl().indexOf("?") > -1){
        var temp = $location.absUrl().split("?");
        temp = temp[1].split("&");
        temp = temp[0].split("=");
        $scope.id = temp[1];
    }
    $scope.query = "type=question&id="+$scope.id;
    $scope.url = "http://localhost:8080/StackExchange-CommentVote/initVote?"+$scope.query;
    $http.get($scope.url)
            .then(function(response){
                console.log(response.data);
                    if(response.data.status === "success"){
                        $scope.value = response.data.newVote;
                    }else{
                        $scope.value = "-";
                    }
            });
            
    $scope.query = "type=answer&qid="+$scope.id;
    $scope.url = "http://localhost:8080/StackExchange-CommentVote/initVote?"+$scope.query;
    $http.get($scope.url)
            .then(function(response){
                console.log(response.data);
                    if(response.data.status === "success"){
                        $scope.voteAnswers = response.data.votes;
                    }
            });       
            
    $scope.query = "qid="+$scope.id;
    $scope.url = "http://localhost:8080/StackExchange-CommentVote/initComment?"+$scope.query;
    $http.get($scope.url)
            .then(function(response){
                console.log(response.data);
                    if(response.data.status === "success"){
                        $scope.comments = response.data.comments;
                    }
            });
            
    
    
    $scope.email = $cookies.get('email');
    $scope.token = $cookies.get('token');
    $scope.voteUpQ = function(){
        $scope.query = "email="+$scope.email;
        $scope.query += "&token="+$scope.token;
        $scope.query += "&type=question&inc=up&id="+$scope.id;
        $scope.url = "http://localhost:8080/StackExchange-CommentVote/Vote?"+$scope.query;
        $http.get($scope.url)
                .then(function(response){
                    console.log(response.data);
                    if(response.data.status === "success"){
                        $scope.value = response.data.newVote;
                    }
                });
    };
    $scope.voteDownQ = function(){
        
        $scope.query = "email="+$scope.email;
        $scope.query += "&token="+$scope.token;
        $scope.query += "&type=question&inc=down&id="+$scope.id;
        $scope.url = "http://localhost:8080/StackExchange-CommentVote/Vote?"+$scope.query;
        $http.get($scope.url)
                .then(function(response){
                    console.log(response.data);
                    if(response.data.status === "success"){
                        $scope.value = response.data.newVote;
                    }
                });
    };
    $scope.voteUpA = function(id){
        $scope.query = "email="+$scope.email;
        $scope.query += "&token="+$scope.token;
        $scope.query += "&type=answer&inc=up&id="+id+"&qid="+$scope.id;
        $scope.url = "http://localhost:8080/StackExchange-CommentVote/Vote?"+$scope.query;
        $http.get($scope.url)
                .then(function(response){
                    console.log(response.data);
                    if(response.data.status === "success"){
                        $scope.voteAnswer = response.data.newVote;
                    }
                });
    };
    $scope.submitComment = function(){
        
        $scope.editing = false;
        $scope.comMessage = "";
        
        $scope.query = "email="+$scope.email;
        $scope.query += "&token="+$scope.token;
        $scope.query += "&content="+$scope.comment.text;
        $scope.query += "&qid="+$scope.id;
        $scope.url = "http://localhost:8080/StackExchange-CommentVote/Comment?"+$scope.query;
        $http.get($scope.url)
                .then(function(response){
                    console.log(response.data);
                    if(response.data.status === "success"){
                        $scope.comments = response.data.comments;
                        $scope.comment.text = "";
                    }else{
                        $scope.comMessage = "You must login first";
                        $scope.comment.text = "";
                    }
                });
        
    };
});