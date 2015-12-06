(function() {
    function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.href);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }
    var app = angular.module('stackexchange', [ ]);
    
    app.controller('questionController', [ '$http', function($http) {
        var questionCtrl = this;
        this.comments = [];
        
        $http({
            url: "http://localhost:8080/StackExchange_Client/QuestionComment",
            method: "GET",
            params: {qid: getParameterByName("id")}
        }).success(function(data) {
            if (!data[0].error){
                questionCtrl.comments = data;
            } else {
                alert(data[0].error);
            }
        }).error(function() {
            alert("Error");
        });
    }]);

    app.controller('commentController', [ '$http', function($http) {
       this.comment = {}; 
       this.data = {};
              
       this.addComment = function(comments) {
           var comment = this.comment;
           comment.q_id = this.data.q_id;
           comment.u_id = this.data.u_id;
           comment.user = this.data.user;
           console.log(comment);
           comments.push(comment);
           this.comment = {};
           
           $http({
               url: "http://localhost:8080/StackExchange_Client/QuestionAddComment",
               method: "POST",
               params: {
                   qid: comment.q_id,
                   uid: comment.u_id,
                   content: comment.content
                }
            }).success(function(data) {
                console.log(data);
            }).error(function() {
                alert("error.");
            });
       }
    }]);

    app.controller('questionVoteController', [ '$http', function($http) {
        var questionVoteCtrl = this;
        this.vote = 0;
        
        var voteData = this.voteData;
        this.voteData = {};  
        
        this.getVote = function() {
            $http({
                url: "http://localhost:8080/StackExchange_Client/QuestionGetVote",
                method: "GET",
                params: {qid: getParameterByName("id")}
            }).success(function(data) {
                if ( data.vote_count != null ) {
                    questionVoteCtrl.vote = data.vote_count;
                }
            }).error(function() {
                alert("Error");
            });
        }
        
        this.init = function(q_id, u_id) {
            this.voteData.q_id = q_id;
            this.voteData.u_id = u_id;          
        }
        
        this.addVote = function(val) {    
            this.voteData.val = val;
            
            $http({
               url: "http://localhost:8080/StackExchange_Client/questionvote",
               method: "POST",
               params: {
                   qid: this.voteData.q_id,
                   uid: this.voteData.u_id,
                   value: this.voteData.val
                }
            }).success(function(data) {
                console.log(data);
                questionVoteCtrl.getVote();
            }).error(function() {
                alert("error.");
            });
            
        }
    }]);

    app.controller('answerVoteController', [ '$http', function($http) {
        var answerVoteCtrl = this;
        this.vote = 0;
        this.aid = -1;
        
        var voteData = this.voteData;
        this.voteData = {};  
        
        this.getVote = function(aid) {
            answerVoteCtrl.aid = aid;
            $http({
                url: "http://localhost:8080/StackExchange_Client/AnswerGetVote",
                method: "GET",
                params: {aid: this.aid}
            }).success(function(data) {
                if ( data.vote_count != null ) {
                    answerVoteCtrl.vote = data.vote_count;
                }
            }).error(function() {
                alert("error");
            });      
        }
        
        this.init = function(a_id, u_id) {
            this.voteData.a_id = a_id;
            this.voteData.u_id = u_id;          
        }
        
        this.addVote = function(val) {    
            this.voteData.val = val;
            
            $http({
               url: "http://localhost:8080/StackExchange_Client/answervote",
               method: "POST",
               params: {
                   aid: this.voteData.a_id,
                   uid: this.voteData.u_id,
                   value: this.voteData.val
                }
            }).success(function(data) {
                answerVoteCtrl.getVote(answerVoteCtrl.aid);
            }).error(function() {
                alert("error");
            });
            
        }
    }]);

})();
