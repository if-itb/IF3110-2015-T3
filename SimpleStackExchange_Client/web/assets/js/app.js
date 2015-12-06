var app = angular.module('simplestackexchange', []);

app.controller('commentform', ['$scope', '$http', function($scope, $http) {
      $scope.comment = '';
      $scope.qid = 0;
      $scope.uid = 0;

      $scope.submit = function() {
       
        if ($scope.comment) {
            $scope.test = $scope.comment;
            $http({
                url: 'http://localhost:8083/SimpleStackExchange_CommentVoteService/api/comment/create',
                method: 'POST',
                data: $.param({'content': $scope.comment, 'qid': $scope.qid, 'uid': $scope.uid}),
                headers : {'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'}
            });
          $scope.comment= '';
        }
      };
    }]);

app.controller('VoteAnswerController', ['$scope', '$http', function($scope, $http) {
  $scope.voteanswers = {};

  $scope.countVote = function($http) {
    $http({
      method: 'GET',
      url: 'localhost:8083/SimpleStackExchange_CommentVoteService/api/answervoter/getvotes/'+$aid+'/'
    }).success(function(data) {
      if(data !== null) {
        $scope.countVote = data;
      }
    });
  };

  $scope.updateVote = function($aid, $value, $token) {
    $http({
      method: 'GET',
      url: 'localhost:8083/SimpleStackExchange_CommentVoteService/api/answervoter/vote/'+ $aid+'/'+$value+'/'+$token+'/'
    }).success(function(data) {
      if (data !== null) {
        $scope.countVote = data;
      }
    });
  };
}]);

app.controller('VoteQuestionController', ['$scope', '$http', function($scope, $http) {
  $scope.votequestions = {};

  $scope.countVote = function() {
    $http({
      method: 'GET',
      url: 'localhost:8083/SimpleStackExchange_CommentVoteService/api/questionvoter/getvotes/'+$qid
    }).success(function(data) {
      if(data !== null) {
        $scope.countVote = data;
      }
    });
  };

  $scope.updateVote = function($qid, $value, $token) {
    $http({
      method: 'GET',
      url: 'http://localhost:8080/SimpleStackExchange_Client/QuestionVote?qid='+ $qid+'&value='+$value+'&token='+$token
    }).success(function(data) {
      if (data !== null) {
        $scope.countVote = data;
      }
    });
  };
}]);
