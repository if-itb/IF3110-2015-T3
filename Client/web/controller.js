var app =  angular.module('App',[]);
app.controller('commentCtrl', function($scope, $http, $log) {

	$scope.username;
    $scope.comments = [];

    $http.get("https://api.github.com/users/" + $scope.username)
    	.success(function(data) {
    		for(var i = 0; i < data.length; i++) {
    			$scope.comments.push(new Comment(data.content, data.name, data.created_at));
			}
    	});

    $scope.comment = function () {
    	$log.log("Asss");
    	$http.get("https://api.github.com/users/" + $scope.username)
    		.success(function(data) {
    			$log.log(data);
    			$scope.comments.push(new Comment(data.login, data.id, data.avatar_url));
    		});
    }

    function Comment(content, name, date) {
    	this.content = content;
    	this.name = name;
    	this.created_at = date;
    }
});


app.controller('voteCtrl', function($scope, $http, $log) {

    $scope.username;

    $http.get("https://api.github.com/users/" + $scope.username)
        .success(function(data) {
            for(var i = 0; i < data.length; i++) {
                $scope.vote = data.id;
            }
        });

    $scope.voteUp = function () {
        $log.log("Asss");
        $http.get("https://api.github.com/users/" + $scope.username)
            .success(function(data) {
                $log.log(data);
                $scope.vote = data.id;
            });
    }

    $scope.voteDown = function () {
        $log.log("Asss");
        $http.get("https://api.github.com/users/" + $scope.username)
            .success(function(data) {
                $log.log(data);
                $scope.vote = data.id;
            });
    }


});

