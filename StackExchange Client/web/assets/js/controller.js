
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function() {
    var app = angular.module('stackexchange', []);

    function getUrlParameter(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.href);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }
    
    app.controller('ViewCommentsController', ['$scope', '$http',
        function($scope, $http) {
        $scope.comments = [];
        $http({url: "http://localhost:8080/StackExchange_Client/comment", 
            method: "GET", 
            params: {q_id : getUrlParameter("q_id")}
        }).then(function successCallback(response) {
            $scope.comments = response.data.comments;
          });
    }]);

    app.controller('AddCommentController', ['$scope', '$http', '$window',
        function($scope, $http, $window) {
        $scope.addComment = function(q_id, content, token) {
            $http({url: "http://localhost:8080/StackExchange_Client/comment", 
                method: "POST",
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data: {q_id: q_id, content: content, token: token}
            }).success(function(response){ 
                    $scope.comments = response.data;
             });    
        }
                
    }]);

    app.controller('ViewVoteController', ['$scope', '$http',
        function($scope, $http) {
        $scope.init = function(id, type) {
        $http({
            method: "GET",
            url: "http://localhost:8080/StackExchange_Client/vote",
            params: {id: id, type: type},
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).then(function successCallback(response) {
            $scope.vote = response.data.vote;
        });
    };
    }]);
})();
