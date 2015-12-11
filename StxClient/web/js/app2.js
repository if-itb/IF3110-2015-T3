var app2 = angular.module('xapp2', []);
app2.controller('qvote2', function ($scope){
    $scope.vote2 = function(code){
        $scope.voteNum2 = code + 1;
    };
});


