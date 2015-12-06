function invResult(status){
    if (status==0) {
        alert("invalid token");
    } else if (status==-1) {
        alert("expired token");
    } else if (status==-2) {
        alert("invalid user agent");
    } else if (status==-3) {
        alert("invalid ip");
    }
}

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

app.controller('MainController', function($scope, CommentFactory, globalVariable, Vote) {

    $scope.show = function () {
        $scope.addComment = true;
    };

    $scope.submit = function () {
        CommentFactory.add({id: globalVariable.qid}, {
            content: $scope.content,
            token: globalVariable.token,
            qid: globalVariable.qid
        }).$promise.then(function (result) {
            if (result.status!=1){
                invResult(result.status);
            }
            $scope.refreshComments();
        });
        $scope.addComment = false;
    };

    $scope.refreshComments = function(){
        CommentFactory.list({id: globalVariable.qid}).$promise.then(function(result){
            $scope.comments = result.comment_list;
        });
    };

    $scope.refreshQVote = function(){
        Vote.get({id: globalVariable.qid, type:"question"}).$promise.then(function(result){
            $scope.qvote = result;
        });
    };

    $scope.refreshAVote = function(aid){
        Vote.get({id: aid, type:"answer"}).$promise.then(function(result){
            $scope.avote[aid] = result;
        });
    };

    $scope.qvote = function(dir){
        return function (){
            Vote.update({id: globalVariable.qid, type: "question"}, {direction: dir, token:globalVariable.token}).$promise.then(function(result){
                $scope.refreshQVote();
            });
        }
    };

    $scope.avote = function(dir){
        return function (aid){
            Vote.update({id: aid, type: "answer"}, {direction: dir, token:globalVariable.token}).$promise.then(function(result){
                $scope.refreshAVote(aid);
            });
        }
    };

    $scope.qvoteup = $scope.qvote(1);
    $scope.qvotedown = $scope.qvote(-1);

    $scope.avoteup = $scope.avote(1);
    $scope.avotedown = $scope.avote(-1);

    $scope.refreshComments();
    $scope.refreshQVote();

    for (i in globalVariable.aids) {
        $scope.refreshAVote(globalVariable.aids[i]);
    }
});
