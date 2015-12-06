/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('questionVoteController', [ '$scope','$http','$location','$cookies', function($scope,$http,$location,$cookies)
    {
        var questionvotecontrol = this;
        $scope.vote = 0;
        var votedata = this.votedata;
        this.votedata = {};
        var qid = $location.search()['id'];
            $http(
                {
                    url: "http://localhost:8080/StackExchange_Client/vote",
                    method: "GET",
                    params: {id: qid, db: "question"}
                }).success(function(data)
                {
                    if (data.vote !== null)
                    {
                        $scope.vote = data.vote;
                    }
                }    
                    );
        $scope.addVote = function(x){
            var token = $cookies.get("stackexchange_token");
            if (token!==null) {
                $http({
                    url: "http://localhost:8080/StackExchange_Client/addVote",
                    method: "POST",
                    data: {
                        id: qid,
                        vote: x,
                        db: "question",
                        token: token
                    },
                     headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                  transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                  },
                }).success(function(data){
                    if (data.ERROR==null) {
                        $scope.vote = data.vote;
                    }
                    else {
                        alert(data.ERROR);
                    }
                });
            }
                else {
                    alert("You must log in to vote.");
                }
        };
        this.init = function(qid)
        {
            alert("tes");
            this.votedata.qid = qid;
        };
        
    }
]);