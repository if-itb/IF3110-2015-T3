var urlParam = function(name, w) {
    w = w || window;
    var rx = new RegExp('[\&|\?]'+name+'=([^\&\#]+)'),
        val = w.location.search.match(rx);
    return !val ? '':val[1];
};

var app = angular.module("stackExchange", []);

/**** COMMENTS CONTROLLER ****/
app.controller('commentsController', ['$scope', '$http',
	function($scope, $http) {
		$scope.comments = [];
		$scope.errormsg = "";
		var qid = urlParam("qid");

		$scope.init = function($http) {
			$http.get('http://localhost:8080/StackExchangeClient/comment', {
	  			params: {
	  				qid: qid
	  			}
	  		}).success( function(data) {
	  			$scope.comments = data["comments"];
	  		}).error( function(err) {
	  			console.log(err);
	  		});
	  	};

  		$scope.addComment = function($http, comment) {

  			$http.post('http://localhost:8080/StackExchangeClient/comment', {
  				data: {
  					qid: qid,
  					content: comment.contents
  				}
  			}).success(function(data) {
  				if (data["success"] === 0)
  					alert("Your session was expired!");
  				else if (data["success"] === -1)
  					alert("IP address is not same");
                                else if (data["success"] === -2)
  					alert("Browser is not same");
                                else if (data["success"] === -3)
  					alert("Unexpected error");
                                else
  					init();
  			}).error( function(err) {
	  			console.log(err);
	  		});
		};

	}]
	
);


/**** VOTE QUESTION CONTROLLER ****/
app.controller('voteQuestion', ['$scope', '$http',
	function($scope, $http) {
		$scope.votes = 0;
		var qid = urlParam("qid");

		$scope.init = function() {
                        console.log("tomas");
			$http.get('http://localhost:8080/StackExchangeClient/votequestion', {
	  			params: {
	  				qid: qid
	  			}
	  		}).success( function(data) {
	  			$scope.votes = data["value"];
	  		}).error( function(err) {
	  			console.log(err);
	  		});
	  	};

  		$scope.vote = function($http, value) {

  			$http.post('http://localhost:8080/StackExchangeClient/votequestion', {
  				data: {
  					qid: qid,
  					jlhvote: value
  				}
  			}).success(function(data) {
  				if (data["success"] === 0)
  					alert("Your session was expired!");
  				else if (data["success"] === -1)
  					alert("IP address is not same");
                                else if (data["success"] === -2)
  					alert("Browser is not same");
                                else if (data["success"] === -3)
  					alert("Unexpected error");
                                else
  					$scope.votes++;
  			}).error( function(err) {
	  			console.log(err);
	  		});
		};

	}]
	
);