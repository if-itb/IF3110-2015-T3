        
        
        
        var app = angular.module('xapp', []);
        app.controller('qvote', function ($scope){
            $scope.vote = function(code){
                $scope.voteNum = 10;
            };
        });
        app.controller('avote', function ($scope){
            $scope.vote = function(code){
                $scope.avoteNum = 10;
            };
        });