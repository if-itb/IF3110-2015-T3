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
        });
    </script>
    <div ng-app="commentApp" ng-controller="commentAppController">
        
        <input type="button" value="Add a comment" ng-click="ShowHide()" />
        <br>
        <form ng-show = "IsVisible" name ='q_form' METHOD="POST">
            <div class="controls">
                <textarea rows="10" cols="100" class="form-control" name="content" required maxlength="999" style="resize:none"></textarea>
            </div>
            <div class="row">
                <div class="12u">
                    <input type="submit" class="special" value="Submit Comment" />
                </div>
            </div>
        </form>
    </div>
    
</body>
</html>