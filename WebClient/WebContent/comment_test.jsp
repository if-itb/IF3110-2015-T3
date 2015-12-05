<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="assets/js/angular.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.4.8/angular-route.js"></script>
<head>
<title>Comment Test</title>
</head>
<body data-ng-controller="mainCtrl" data-ng-app="myapp">

<div data-ng-repeat="p in names" class="container">
<img data-ng-src="{{ p.author.avatar_URL }}"><br/>
Author: {{ p.author.nice_name }}<br/>
URL: <a href="{{ p.author.URL }}">{{ p.author.URL }}</a><br/>
Title: {{ p.title }}<br/>
Content:<br/>
<div data-ng-bind-html="p.content | to_trusted"></div><br/>
Comments: {{ p.comments_open }}
</div>
</body>
<script type="text/javascript">
	var myapp = angular.module('myapp', ['ui.bootstrap']);
	
	myapp.controller('mainCtrl', function($scope, $http) {
	
	    $http.get("https://public-api.wordpress.com/rest/v1/freshly-pressed")
	
	    .success(function(response) {
	        $scope.names = response.posts;
	    });
	});
	
	angular.module('myapp')
	    .filter('to_trusted', ['$sce', function($sce){
	        return function(text) {
	            return $sce.trustAsHtml(text);
	        };
	    }]);

</script>

</html>