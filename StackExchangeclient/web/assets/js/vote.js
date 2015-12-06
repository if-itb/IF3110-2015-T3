/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// var app = angular.module('comment-vote', []);
// app.controller('vote', function ($scope, $http) {
//   $scope.voteQuestionUp = 
// });
// 
window.onload = function (qid, uid) {
    // var vote = parseInt(document.getElementById(id).innerHTML) + 1;
    var param = {
      
    }
    $http({
      url : "http://localhost:8083/webresources/wbd.commentvote.votequestion/",
      method : get,
      params : param,
      success : console.log('success'),
      fail : console.log('failed')
    })
  };