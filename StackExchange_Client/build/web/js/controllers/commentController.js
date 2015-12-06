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
app.controller('commentController', ['$scope','$http','$location','$cookies',
  function($scope,$http,$location,$cookies) {
    var qid = $location.search()['id'];
    var req = {
        method: "GET",
        url: "http://localhost:8080/StackExchange_Client/comments",
        params: {id: qid}
    };
    $http(req)
    .then(function(response) {$scope.comments = response.data.comments;});

     $scope.submitComment = function() {
    var token = $cookies.get("stackexchange_token");
    if (token!=null) {
        var content = $scope.newcontent;
        var submitreq = {
          method: "POST",
          url: "http://localhost:8080/StackExchange_Client/addComment",
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
        .then(function(response) {
            if (response.data.ERROR==null) {
                $scope.comments.push(response.data);
            } else {
                alert(response.data.ERROR);
            } 
        });
    } else {
        alert("You must log in to comment.");
    }
  }
}]); 