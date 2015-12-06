<html>
<head>
    <title></title>
</head>
<body>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.9/angular.min.js"></script>
    <script type="text/javascript">
        var app = angular.module('commentApp', [])
        app.controller('commentAppController', function ($scope) {
            //This will hide the DIV by default.
            $scope.IsVisible = false;
            $scope.ShowHide = function () {
                //If DIV is visible it will be hidden and vice versa.
                $scope.IsVisible = $scope.IsVisible ? false : true;
            }

            $scope.update = function(user) {
		        $scope.master = angular.copy(user);
		      };

	      $scope.reset = function() {
		        $scope.user = angular.copy($scope.master);
		      };

		     $scope.master = {};

	      $scope.update = function(user) {
	        $scope.master = angular.copy(user);
	      };

	      $scope.reset = function() {
	        $scope.user = angular.copy($scope.master);
	      };

	      $scope.reset();
        });

        angular.module('formExample', [])
	    .controller('ExampleController', ['$scope', function($scope) {
	      
	    }]);

    </script>
    <style type="text/css">
	  .css-form input.ng-invalid.ng-touched {
	    background-color: #FA787E;
	  }

	  .css-form input.ng-valid.ng-touched {
	    background-color: #78FA89;
	  }
	</style>


    <div ng-app="commentApp" ng-controller="commentAppController">
        
        <input type="button" value="Add a comment" ng-click="ShowHide()" />
        <br>
        <form ng-show = "IsVisible" name ='q_form' METHOD="POST">
        	Name: <input type="text" ng-model="user.name" required /><br />
            <div class="controls">
                <textarea rows="10" cols="100" class="form-control" name="content" required maxlength="999" style="resize:none" ng-model="user.comment"></textarea>
            </div>
            <div class="row">
                <div class="12u">
                    <input type="submit" class="special" value="Submit Comment"  ng-click="update(user)"/>
                </div>
            </div>
            <input type="button" ng-click="reset()" value="Reset" />
        </form>

        <pre>user = {{user | json}}</pre>
  		<pre>master = {{master | json}}</pre>
    </div>
    
</body>
</html>