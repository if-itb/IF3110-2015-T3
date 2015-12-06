/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            url: "http://localhost:8080/StackExchange_Client/comment",
            method: "GET",
            params: {question_id: getParameterByName("id")}
        }).success(function(data) {
            if (!data[0].error) {
                questionCtrl.comments = data;
            }
        });
    }]);

    app.controller('commentController', [ '$http', function($http) {
       this.comment = {}; 
       this.addComment = function(comments) {
           var comment = this.comment;
           console.log(comment);
           comments.push(comment);
           this.comment = {};
           
           $http({
               url: "http://localhost:8080/StackExchange_Client/comment",
               method: "POST",
               params: {
                   qid: comment.question_id,
                   content: comment.content
                }
            }).success(function(data) {
                console.log(data);
            });
       }
    }]);
})();
