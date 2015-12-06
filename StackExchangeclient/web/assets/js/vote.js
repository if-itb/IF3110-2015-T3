/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('cmv', []);
app.controller('vote', function ($scope, $http) {
  $scope.voteQuestionUp = function (qid, uid) {
    // var vote = parseInt(document.getElementById(id).innerHTML) + 1;
    console.log('tes');
    var param = {
      
    }
    $http({
      url : "http://localhost:8088/StackExchangeclient/webresources/wbd.commentvote.votequestion/",
      method : 'get',
      // params : param,
    })
    // . then (
    //   console.log('success'),
    //   console.log('failed')
    // )
  };
});
