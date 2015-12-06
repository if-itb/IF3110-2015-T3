/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function() {
    function getUrlParameter(name) {
        name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
        var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.href);
        return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    }
    
    var app = angular.module('stackexchange', []);

    app.controller('ViewCommentsController', ['$scope', '$http', '$location',
        function($scope, $http, $location) {
        var id = $location.search()['q_id'];
        $http({url: "http://localhost:8080/StackExchange_Client/comment", 
            method: "GET", 
            params: {q_id : getUrlParameter("q_id")}
        }).success(function(data) {
           $scope.comments = data;  
        });
    }]);
})();
