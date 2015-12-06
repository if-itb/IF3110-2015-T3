var urlParam = function(name, w) {
    w = w || window;
    var rx = new RegExp('[\&|\?]'+name+'=([^\&\#]+)'),
        val = w.location.search.match(rx);
    return !val ? '':val[1];
}

var app = angular.module("stackExchange", []);

/**** COMMENTS CONTROLLER ****/
app.controller('commentsController', ['$scope', '$http', '$cookies'
	function($scope, $http) {
		$scope.comments = [];
		$scope.errormsg = "";
		var qid = urlParam("qid");
		var token = $cookies.get('token_cookie');

		$scope.init = function($http) {
			$http.get('http://localhost:8083/StackExchangeAJS/Comment', {
	  			params: {
	  				qid: qid
	  			}
	  		}).success( function(data) {
	  			$scope.comments = data["comments"];
	  		}).error( function(err) {
	  			console.log(err);
	  		});
	  	}

  		$scope.addComment = function($http, token, comment) {

  			$http.post('http://localhost:8083/StackExchangeAJS/Comment', {
  				data: {
  					token: token,
  					qid: qid,
  					content: comment.contents
  				}
  			}).success(function(data) {
  				if (data["success"] === 0)
  					$scope.errormsg = "Your session was expired!";
  				else if (data["success"] === -1)
  					$scope.errormsg = "Unexpected error";
  			}).error( function(err) {
	  			console.log(err);
	  		});
		}

	}]
	
);

/**** VOTE CONTROLLER ****/
app.controller('voteQuestionController', ['$scope', '$http' function(){
	
}])