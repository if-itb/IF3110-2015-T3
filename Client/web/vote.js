var app =  angular.module('voteApp',[]);
app.controller('voteCtrl', function($scope, $http, $log) {

	$scope.username;
    $scope.vote;

    $http.get("https://api.github.com/users/" + $scope.username)
    	.success(function(data) {
    		for(var i = 0; i < data.length; i++) {
    			$scope.vote = data.id;
			}
    	});

    $scope.comment = function () {
    	$log.log("Asss");
    	$http.get("https://api.github.com/users/" + $scope.username)
    		.success(function(data) {
    			$log.log(data);
    			$scope.vote = data.id;
    		});
    }


});