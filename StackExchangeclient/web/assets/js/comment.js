/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
  $http.get("dummy.json").then(function(response) {
    var ret = [];
    angular.forEach(response.data.result, function(c) {
      if(c.qid == document.getElementById("qid").value) {
        ret.push(c);
      }
    });
    $scope.comments = ret;
  });
  $scope.submit = function() {
    var qid = $scope.qid;
    var content = $scope.content;
    alert(qid + " " + content);
  };
});