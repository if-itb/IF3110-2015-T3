/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("stackexchange", []);



app.controller('voteQuestion', function($scope,$http) {
    
    $scope.init = function (vote) {
        $scope.question_vote = vote;
    } 
    $scope.vote = function(question_id, flag) {
        var location = "/Stack_Exchange_Client/VoteQuestion?qid=" + question_id + "&flag=" + flag
         
        $http({
            method: 'GET',
            url: location,
            headers: {'Access-Control-Allow-Origin' : '*'}
        }).success(function(response){
            if(response.status=="success")
                $scope.question_vote = response.vote;
            });
    }
});



