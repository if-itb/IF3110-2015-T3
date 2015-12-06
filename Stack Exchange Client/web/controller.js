/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module("stackexchange", []);



app.controller('vote', function($scope,$http) {
    $scope.answer_vote = {};
    //Question
    $scope.initQuestion = function (vote) {
        $scope.question_vote = vote;
    } 
    $scope.voteQuestion = function(question_id, flag) {
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
    
    //Answer
    $scope.initAnswer= function (answer_id, vote) {
        $scope.answer_vote[answer_id] = vote;
    } 
    $scope.voteAnswer = function(answer_id, flag) {
        var location = "/Stack_Exchange_Client/VoteAnswer?aid=" + answer_id + "&flag=" + flag
         
        $http({
            method: 'GET',
            url: location,
            headers: {'Access-Control-Allow-Origin' : '*'}
        }).success(function(response){
            if(response.status=="success")
                $scope.answer_vote[answer_id] = response.vote;
            });
    }
});



