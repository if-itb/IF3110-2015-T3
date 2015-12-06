/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var appName = angular.module("App", []);

appName.controller("voteController", function($scope, $http) {
      $scope.voteClick = function(target,action,id,userId) {
          var responsePromise = $http.get("http://localhost:8080/VoteAndComment Service/Vote?target=" + target + "&action=" + action + "&id="+ id +"&userId=" + userId);
          if(target == "answer") {
            responsePromise.success(function(data, status, headers, config) {
                $scope.answerVoteNumber = data.answer_vote;
            });
          } else if(target == "question") {
            responsePromise.success(function(data, status, headers, config) {
                $scope.questionVoteNumber = data.question_vote;
            });
          }
          responsePromise.error(function(data, status, headers, config) {
              alert("AJAX failed!");
          });
      }
  } );

  appName.controller("commentController", function($scope, $http) {
      $scope.commentClick = function(id,target,content,userId) {
          var responsePromise = $http.get("http://localhost:8080/VoteAndComment Service/Comment?id=" + id + "&target=" + target + "&content="+ content +"&userId=" + userId);
          var responseGetComment = $http.get("http://localhost:8080/VoteAndComment Service/GetComment?id=" + id + "&target=" + target);

          responseGetComment.success(function(data,status,headers,config) {
            $scope.comment = data.comment;
          });
          responseGetComment.error(function(data,status,headers,config) {
            alert("Ajax failed");
          });
      }
  } );
