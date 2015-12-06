/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module('xapp', []);
app.controller('xvote', function ($scope){
    $scope.vote = function(category, id, email, val){
        /*
        var a = $scope.obj1;
        var b = $scope.obj2;
        switch (code){
            case 1: 
                $scope.operation = "ADD"; 
                $scope.result = a+b;
                break;
            case 2: 
                $scope.operation = "SUBSTRATION"; 
                $scope.result = a-b;
                break;
            case 3: 
                $scope.operation = "MULTIPLY"; 
                $scope.result = a*b;
                break;
            case 4: 
                $scope.operation = "DIVIDE"; 
                $scope.result = a/b;
                break;
        };
*/
        $scope.vote_num = "actions/vote.jsp?c=" + category + "&id=" + id + "&email=" + email + "&val=" + val;
    };
});


app.controller('xcomment', function ($scope){
    $scope.comment = function(code){
        var a = $scope.obj1;
        var b = $scope.obj2;
        switch (code){
            case 1: 
                $scope.operation = "ADD"; 
                $scope.result = a+b;
                break;
            case 2: 
                $scope.operation = "SUBSTRATION"; 
                $scope.result = a-b;
                break;
            case 3: 
                $scope.operation = "MULTIPLY"; 
                $scope.result = a*b;
                break;
            case 4: 
                $scope.operation = "DIVIDE"; 
                $scope.result = a/b;
                break;
        };
    };
});



