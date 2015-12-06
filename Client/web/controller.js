var id = document.getElementsByClassName("rowQuestion")[0].id;

var app =  angular.module('App',['ngCookies']);
app.controller('commentCtrl', function($scope, $http, $log, $cookies) {
    
    $scope.username;
    $scope.comments = [];
    $scope.id;
    
    getComment();
    
    app.config(['$httpProvider', function ($httpProvider) {
	    $httpProvider.defaults.headers.post = {'Content-Type': 'text/plain'};
	    $httpProvider.defaults.headers.get = {'Content-Type': 'text/plain'};
	}
    ]);
    
    function getComment() {
	console.log(JSON.stringify({
	    token: $cookies.get("token"),
	    name: $cookies.get("username"),
	    content: "",
	    qid: id
	}));
	
	$http({
	    url: "http://localhost:8083/CommentVoteService/comment",
	    method: "GET",
	    params: { qid: id },
	    transformResponse: function (data) {
                console.log(data);
		var x2js = new X2JS();
		var json = x2js.xml_str2json(data);
		return json;
	    }
	}).success(function (response) {
	    $scope.comments.push(response);
	});
    }
    
    $scope.comment = function() {
        console.log(JSON.stringify({
		token: $cookies.get("token"),
		name : $cookies.get("username"),
		content : $scope.content,
		qid : id
	    }));
	$http({
	    url: "http://localhost:8083/CommentVoteService/comment",
	    method: "POST",
	    data: {
		token: $cookies.get("token"),
		name : $cookies.get("username"),
		content : $scope.content,
		qid : id
	    },
            transformRequest: function(obj) {
                var str = [];
                for(var p in obj)
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
	    transformResponse: function (data) {
		var x2js = new X2JS();
		var json = x2js.xml_str2json(data);
		return json;
	    },
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	}).success(function(response){
	    $scope.comments.push(response);
	});
    };
});


app.controller('voteCtrl', function($scope, $http, $log, $cookies) {
    $scope.username;
    $scope.comments = [];
    $scope.id;


    app.config(['$httpProvider', function ($httpProvider) {
	    $httpProvider.defaults.headers.post = {'Content-Type': 'text/plain'};
	    $httpProvider.defaults.headers.get = {'Content-Type': 'text/plain'};
	}
    ]);

    function getVote() {
	console.log(JSON.stringify({
	    token: $cookies.get("token"),
	    name: $cookies.get("username"),
	    content: "",
	    qid: id
	}));

	$http({
	    url: "http://localhost:8083/CommentVoteService/comment",
	    method: "GET",
	    data: {
		token: $cookies.get("token"),
		name: $cookies.get("username"),
		content: $scope.content,
		qid: id
	    },
	    transformRequest: function (obj) {
		var str = [];
		for (var p in obj)
		    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		return str.join("&");
	    },
	    transformResponse: function (data) {
		var x2js = new X2JS();
		var json = x2js.xml_str2json(data);
		return json;
	    },
	    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	}).success(function (response) {
	    $scope.comments.push(response);
	});
    }

    $scope.voteUp = function () {
	console.log(JSON.stringify({
	    token: $cookies.get("token"),
	    userid: $cookies.get("id"),
	    qid : id,
	    value : 1
	}));
	$http({
	    url: "http://localhost:8083/CommentVoteService/votequestion",
	    method: "POST",
	    data: {
		token: $cookies.get("token"),
		userid: $cookies.get("id"),
		content: $scope.content,
		qid: id
	    },
	    transformRequest: function (obj) {
		var str = [];
		for (var p in obj)
		    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
		return str.join("&");
	    },
	    transformResponse: function (data) {
		var x2js = new X2JS();
		var json = x2js.xml_str2json(data);
		return json;
	    },
	    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
	}).success(function (response) {
	    $scope.comments.push(response);
	});
    };
    
});

