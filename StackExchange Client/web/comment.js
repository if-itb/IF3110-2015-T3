function GetComments($scope, $http){
    $http.get('http://localhost:8080/StackExchange_Client/question').success(function(data){
        $scope.comments = data;
    });
}

