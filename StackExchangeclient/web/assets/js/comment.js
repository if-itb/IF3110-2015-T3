/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getName($http, uid) {
  $http.get("http://localhost:8080/StackExchangeclient/webresources/wbd.commentvote.user/" + uid).then(function(response) {
    return data.name;
  });
}

function join(uid, qid, content) {
  return {
  "uid" : uid,
  "qid" : qid,
  "content" : content
  };
}

var app = angular.module('app', []);

app.controller('comment', function($scope, $http) {
  $scope.showed = false;
  $scope.caption = "Add comment..";
  $scope.show = function() {
    if($scope.showed) {
      $scope.caption = "Add comment..";
    } else {
      $scope.caption = "Cancel comment..";
    }
    $scope.showed = !$scope.showed;
  };
  $http.get("http://localhost:8080/StackExchangeclient/webresources/wbd.commentvote.comment").then(function(response) {
    var ret = [];
    angular.forEach(response.data, function(c) {
      c.name = c.uid;
      if(c.qid == document.getElementById("qid").value) {
        ret.push(c);
      }
    });
    $scope.comments = ret;
  });
  $scope.submit = function(uid) {
    var qid = $scope.qid;
    var content = $scope.content;
    $http.post("http://localhost:8080/StackExchangeclient/webresources/wbd.commentvote.comment", join(uid, qid, content))
    .success(function(status, data) {
              console.log(status);
    })
            .error(function(status) {
              console.log(status);
    }
            );
    $http.get("http://localhost:8080/StackExchangeclient/webresources/wbd.commentvote.comment").then(function(response) {
      var ret = [];
      angular.forEach(response.data, function(c) {
        c.name = c.uid;
        if(c.qid == document.getElementById("qid").value) {
          ret.push(c);
        }
      });
      $scope.comments = ret;
    });
  };
});