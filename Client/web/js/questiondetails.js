app = angular.module('myApp', ['ngResource']);

app.factory('CommentFactory', function ($resource) {
    return $resource('http://127.0.0.1:8084/v1/comment/:id', {id:'@id'}, {
        list: { method: 'GET'},
        add: { method: 'POST'},
    })
});

app.factory('Vote', function ($resource) {
    return $resource('http://127.0.0.1:8084/v1/vote/:type/:id', {type:'@type', id:'@id'}, {
        get: { method: 'GET'},
        update: { method: 'POST'},
    })
});

app.controller('MainController', function($scope, CommentFactory) {

    $scope.show = function(){
        $scope.addComment = true;
    };

    $scope.submit = function(){
        console.log(token);
        CommentFactory.add({id:qid}, {content:$scope.content,token:token,qid:qid}).$promise.then(function(a,b){
            console.log(a);
            console.log(b);
        });
        $scope.addComment = false;
    };

    var init = function () {
        CommentFactory.list({id:qid}).$promise.then(function(result){
            console.log(result.comment_list);
            $scope.comments = result.comment_list;
        });
    };

    init();
});

