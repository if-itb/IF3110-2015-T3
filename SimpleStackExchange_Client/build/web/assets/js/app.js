var app = angular.module('simplestackexchange', []);
app.controller('answerform', ['$scope', function($scope) {
      $scope.test = '';
      $scope.answer = 'hello';
      $scope.submit = function() {
        if ($scope.answer) {
          $scope.test = $scope.answer;
          $scope.answer = '';
        }
      };
    }]);