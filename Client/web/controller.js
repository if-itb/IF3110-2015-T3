var app =  angular.module('App',['ngCookies']);
app.controller('commentCtrl', function($scope, $http, $log, $cookies) {

    $scope.username;
    $scope.comments = [];
        
    app.config(['$httpProvider', function ($httpProvider) {
	    $httpProvider.defaults.headers.post = {'Content-Type': 'text/plain'}
	}
    ]);

    
    $scope.comment = function() {
        console.log(JSON.stringify({
		token: $cookies.get("token"),
		name : $cookies.get("username"),
		content : $scope.content
	    }));
	$http({
	    url: "http://localhost:8083/CommentVoteService/comment",
	    method: "POST",
	    data: {
		token: $cookies.get("token"),
		name : $cookies.get("username"),
		content : $scope.content
	    },
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj)
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	}).success(function(response){
	    $scope.comments.push(response);
	});
    };
    

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

