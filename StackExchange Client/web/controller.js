/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('controller', []);

app.controller("commentController", function($scope, $http) {
    $scope.comments = [];
    
    $scope.init = function(idQuestion){
        $http({
            method  : "GET",
            url     : "ListComment",
            params  : {qid: idQuestion},
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        })
        .success(function(data){
            $scope.comments = data;
        });
    };
    
    $scope.addComment = function(idQuestion) {
        var theContent = $scope.content;
        $scope.content = "";        
        $http({
            method  : "POST",
            url     : "AddComment",
            data    : $.param({qid: idQuestion, content: theContent}),
            headers : {'Content-Type': 'application/x-www-form-urlencoded'}
        })
        .success(function(data){            
            switch(data["status"]){
                case 1 :                    
                    if (data.hasOwnProperty("comments")){ // ketika ada comment di dalam data
                        $scope.comments = data.comments;
                    }
                    break;
            }
        });        
    };
});
