/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var app = angular.module('cmv', []);
app.controller('vote', function ($scope, $http) {
  // vote question
function getQVote($http)
{
  $http.get(
        "http://localhost:8080/StackExchangeclient/webresources/wbd.commentvote.votequestion/"
      )
      .success(function(data, status) {
        var ar = []
        for (var isi in data) {
          ar.push(isi);
          ar['name'] = ar.uid['name'];
          ar['uid'] = ar.uid['id'];
          ar['qid'] = ar.qid['id'];
        }
        return ar;
      })
      .error(function(status) {
        console.log(status);
      })
};
// vote answer
function getAVote($http)
{
  $http.get(
        "http://localhost:8080/StackExchangeclient/webresources/wbd.commentvote.voteanswer/"
      )
      .success(function(data, status) {
        var ar = []
        for (var isi in data) {
          ar.push(isi);
          ar['name'] = ar.uid['name'];
          ar['uid'] = ar.uid['id'];
          ar['qid'] = ar.qid['id'];
        }
        return ar;
      })
      .error(function(status) {
        console.log(status);
      })
};
});
