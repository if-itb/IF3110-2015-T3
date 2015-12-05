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
            if (!data[0].error) {
                questionCtrl.comments = data;
            }
        });
    }]);

    app.controller('commentController', function() {
       this.comment = {}; 
       this.addComment = function(comments) {
           comments.push(this.comment);
       }
    });
})();
