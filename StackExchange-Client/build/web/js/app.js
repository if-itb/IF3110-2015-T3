var app = angular.module("commentApp", ['ngCookies']);

app.controller('voteCtrl',function($scope, $cookies, $http, $location){
    $scope.value = "-";
    $scope.id = 0;
    
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
                    if(response.data.status == "success"){
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
                    if(response.data.status == "success"){
                        $scope.value = response.data.newVote;
                    }
                });
    };
});