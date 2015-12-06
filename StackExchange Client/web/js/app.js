/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('stackexchange', []);

app.controller("voteController", function($scope, $http) {
    $scope.votes = {};

    $scope.init = function(id, type) {
        $scope.votes[type + id] = 0;
        $http({
            method: "GET",
            url: "vote",
            params: {id: id, type: type},
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(data) {
            if (data.hasOwnProperty("votes"))
                $scope.votes[type + id] = data["votes"];
        });
    };

    $scope.vote = function(id, type, action) {
        var thisVote = $('#'+type+'-'+id+'-'+action);
        if (!thisVote.hasClass("vote-button"))
            return;

        $http({
            method: "POST",
            url: "vote",
            data: "id=" + id + "&type=" + type + "&action=" + action,
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(data) {
            if (data.hasOwnProperty("votes"))
                $scope.votes[type + id] = data["votes"];

            switch (data["status"]) {
                // vote success
                case 1:
                    alert("success gan");
                    var otherVote = action === "up"? $('#'+type+'-'+id+'-down') : $('#'+type+'-'+id+'-up');
                    thisVote.removeClass("vote-button").addClass("vote-button-yes");
                    otherVote.removeClass("vote-button").addClass("vote-button-no");
                    break;
                // vote failed
                case 0:
                    alert("gagal gan");
                    break;
                case -1:
                    alert("login dulu gan");
                    window.location.replace("signin");
                    break;
            }
        });
    };

    $scope.voteQuestionUp = function(id) {
        $scope.vote(id, "question", "up");
    };

    $scope.voteQuestionDown = function(id) {
        $scope.vote(id, "question", "down");
    };

    $scope.voteAnswerUp = function(id) {
        $scope.vote(id, "answer", "up");
    };

    $scope.voteAnswerDown = function(id) {
        $scope.vote(id, "answer", "down");
    };
});

app.controller("commentController", function($scope, $http) {
    $scope.comments = [];
    
    $scope.init = function(idQuestion){
        $http({
            method  : "GET",
            url     : "comment",
            data    : "id=" + idQuestion,
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        })
        .success(function(data){
            $scope.comments = data;
        });
    };
    
    $scope.addComment = function(idQuestion, content){
        $http({
            method  : "POST",
            url     : "comment",
            data    : "id=" + idQuestion + "&content=" + content,
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        })
        .success(function(data){
            switch(data["status"]){
                case 1 :
                    alert("alhamdulillah");
                    if(data.hasOwnProperty("comments")){ // ketika ada comment di dalam data
                        $scope.comments = data["comments"];
                    }
                    break;
                default :
                    if(data.hasOwnProperty("detail")){ // ketika ada detail di dalam data (ada error)
                        alert("astaghfirullah, " + data["details"]);
                    }
                    break;
            }
        });        
    };
});