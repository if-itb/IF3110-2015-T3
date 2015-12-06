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

