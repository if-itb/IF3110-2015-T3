/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('commentListController', ['$scope','$http','$location',
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

app.controller('addCommentController', ['$scope','$http','$location','$cookies',
  function($scope,$http,$location,$cookies) {
    $scope.submitComment = function() {
    var qid = $location.search()['id'];
    var token = $cookies.get("stackexchange_token");
    var content = $scope.newcontent;
    var submitreq = {
      method: "POST",
      url: "http://localhost:8083/Comment-Vote_Service/addComment",
      headers: {'Content-Type': 'application/x-www-form-urlencoded'},
      transformRequest: function(obj) {
        var str = [];
        for(var p in obj)
        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
        return str.join("&");
      },
      data: {id: qid, content: content, token: token}
    }
    $http(submitreq)
    .then(function(response) {alert("tes");});
  }
}]); 



