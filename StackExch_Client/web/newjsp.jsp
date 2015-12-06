<!DOCTYPE html>
<html>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
<body>

<div ng-app="myApp" ng-controller="customersCtrl"> 

<ul>
  <li ng-repeat="x in names">
    {{ x.content + ', ' + x.author }}
  </li>
</ul>

</div>
 
<script>
var app = angular.module('myApp', []);
app.controller('customersCtrl', function($scope, $http) {
    var id = 1;
    var url_ = "http://localhost:21215/CommentandVote/AllComment?id="+id;
      console.log("id : "+id);
    $http.get(url_)
  .then(function (response) {$scope.names = response.data.records;});
});
</script>

</body>
</html>