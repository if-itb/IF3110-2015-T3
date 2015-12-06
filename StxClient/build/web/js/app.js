/*
var app = angular.module('xapp', []);
app.controller('qvote', function ($scope,$http){
    $scope.vote = function(category,id,email,val){
        //$scope.voteNum = code + 1;
        var url = "http://localhost:8080/StxClient/actions/vote.jsp?c=" + category + "&id=" + id + "&val=" + val;
        $http.get(url).success(function(response){
           $scope.voteNum = response;
        });        
    };
});
*/

function qvote($scope){
    $scope.voteNum = 1;
}