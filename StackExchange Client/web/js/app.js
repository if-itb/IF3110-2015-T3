(function() {
    function getParameterByName(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.href);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }
    var app = angular.module('stackexchange', [ ]);
    
    app.controller('commentController', [ '$http', function($http) {
        var commentCtrl = this;
        $http({
            url: "http://localhost:8080/StackExchange_Client/QuestionComment",
            method: "GET",
            params: {qid: getParameterByName("id")}
        }).success(function(data) {
            if (!data.error) {
                commentCtrl.commentItems = data;
            }
        });
        
    }]);
})();
