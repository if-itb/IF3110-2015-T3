var app = angular.module('question', []);

var apiBaseUrl = "http://localhost:8080/stack_exchange_api";

app.service("QuestionVoteService", function ($q, $http) {
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
    
    var factory = {};
    
    var path = apiBaseUrl + "/question";
    
    factory.upvote = function (id) {
        var deffered = $q.defer();
        
        $.ajax({
            method: 'POST',
            url: path + "/upvote",
            data: {
                token: window.token,
                id: id
            }
        }).done(function (data) {
            deffered.resolve(JSON.parse(data));
        }).fail(function (data) {
            console.log("error");
        });
        
        return deffered.promise;
    }
    
    factory.downvote = function (id) {
        var deffered = $q.defer();
        
        $.ajax({
            method: 'POST',
            url: path + "/downvote",
            data: {
                token: window.token,
                id: id
            }
        }).done(function (data) {
            deffered.resolve(JSON.parse(data));
        }).fail(function (data) {
            console.log("error");
        });
        
        return deffered.promise;
    }
    
    return factory;
});

app.service("AnswerVoteService", function ($q, $http) {
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
    
    var factory = {};
    
    var path = apiBaseUrl + "/answer";
    
    factory.upvote = function (id) {
        var deffered = $q.defer();
        
        $.ajax({
            method: 'POST',
            url: path + "/upvote",
            data: {
                token: token,
                id: id
            }
        }).done(function (data) {
            deffered.resolve(JSON.parse(data));
        }).fail(function (data) {
            console.log("error");
        });
        
        return deffered.promise;
    }
    
    factory.downvote = function (id) {
        var deffered = $q.defer();
        
        $.ajax({
            method: 'POST',
            url: path + "/downvote",
            data: {
                token: token,
                id: id
            }
        }).done(function (data) {
            deffered.resolve(JSON.parse(data));
        }).fail(function (data) {
            console.log("error");
        });
        
        return deffered.promise;
    }
    
    return factory;
});

app.service("CommentService", function ($q, $http) {
    $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
    
    var factory = {};
    
    var path = apiBaseUrl + "/comment";
    
    factory.getByQuestionId = function (questionId) {
        var deffered = $q.defer();
        
        $.ajax({
            method: 'GET',
            url: path,
            data: 'question_id=' + questionId
        }).done(function (data) {
            deffered.resolve(JSON.parse(data));
        }).fail(function (data) {
            console.log("error")
        });
        
        return deffered.promise;
    };
    
    factory.insert = function (questionId, content) {
        var deffered = $q.defer();
        
        $.ajax({
            method: 'POST',
            url: path,
            data: {
                question_id: questionId,
                content: content,
                token: window.token
            }
        }).done(function (data) {
            deffered.resolve(JSON.parse(data));
        }).fail(function (data) {
            console.log("error");
        });
        
        return deffered.promise;
    }
    
    return factory;
});

app.controller("QuestionController", function ($scope, QuestionVoteService, AnswerVoteService, CommentService) {
    $scope.init = function () {
        $scope.data = {
            comments: []
        };
        
        $scope.form = {
            comment: ""
        };
        
        loadComment();
    };
    
    $scope.upvoteQuestion = function (id) {
        console.log("called");
        QuestionVoteService.upvote(id).then(function (data) {
            $("#question-vote").html("<span>" + data.vote + "</span>");
        });
    };
    
    $scope.downvoteQuestion = function (id) {
        QuestionVoteService.downvote(id).then(function (data) {
            $("#question-vote").html("<span>" + data.vote + "</span>");
        });
    };
    
    $scope.upvoteAnswer = function (id) {
        AnswerVoteService.upvote(id).then(function (data) {
            $("#answer-" + id).html("<span>" + data.vote + "</span>");
        });
    };
    
    $scope.downvoteAnswer = function (id) {
        AnswerVoteService.downvote(id).then(function (data) {
            $("#answer-" + id).html("<span>" + data.vote + "</span>");
        });
    };
    
    $scope.submitComment = function () {
        CommentService.insert(window.question_id, $scope.form.comment).then(function (data) {
            loadComment();
        });
        
        $scope.form.comment = "";
    };
    
    var loadComment = function () {
        CommentService.getByQuestionId(window.question_id).then(function (data) {
            $scope.data.comments = data;
            console.log($scope.data.comments);
        });
    }
    
    $scope.init();
});
