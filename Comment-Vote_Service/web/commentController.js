angular.module('commentApp', ['addCommentApp'])
.config(function($locationProvider) {
    $locationProvider.html5Mode({
      enabled: true,
      requireBase: false
    });
})
.controller('commentListController', ['$scope','$http','$location',
  function($scope,$http,$location) {
  var qid = $location.search()['id'];
    var req = {
   method: "GET",
   url: "http://localhost:8083/Comment-Vote_Service/comments",
   params: {id: qid}
  }
    $http(req)
    .then(function(response) {$scope.comments = response.data.comments;});
}]); 

angular.module('addCommentApp', ['ngCookies'])
.config(function($locationProvider) {
    $locationProvider.html5Mode({
      enabled: true,
      requireBase: false
    });
})
.controller('addCommentController', ['$scope','$http','$location','$cookies',
  function($scope,$http,$location,$cookies) {
    $scope.submitComment = function() {
    var qid = $location.search()['id'];
    var token = $cookies.get("stackexchange_token");
    var content = $scope.newcontent;
    var submitreq = {
      method: "GET",
      url: "http://localhost:8083/Comment-Vote_Service/addComment",
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      transformRequest: function(obj) {
        var str = [];
        for(var p in obj)
        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
      },
      params: {id: qid, content: content, token: token}
    }
    $http(submitreq)
    .then(function(response) {alert("tes");});
  }
}]); 