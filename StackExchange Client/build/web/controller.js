/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('controller', [ ]);

app.controller('CommentController',[
    $scope,$http,
    function($scope,$http){
        $scope.comment = {};
        $scope.comments = [];
        
        $scope.getcomment = function(){
            $http({
                url: "http://localhost:8083/Vote_Comment/CommentServlet",
                method: "GET",
                params: {qid: $scope.comment.qid}
            }).success(function(data) {
                if (!data[0].error) {
                    $scope.comments = data;
                }
            });
        };
        
        $scope.addcommentasync = function(comments){
           console.log($scope.comment);
           comments.push($scope.comment);
           var res = $http({
                method : 'POST',
                url : 'http://localhost:8083/Vote_Comment/CommentServlet',
                data : $.param({
                    'qid' : $scope.comment.qid,
                    'comment' : $scope.comment.comment  
                })
           });
           $scope.comment = {};
        };
    }
]);
