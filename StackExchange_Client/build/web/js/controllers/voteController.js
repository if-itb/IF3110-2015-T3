/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
app.controller('questionVoteController', [ '$scope','$http','$location','$cookies', function($scope,$http,$location,$cookies)
    {
        var questionvotecontrol = this;
        this.vote = 0;
        var votedata = this.votedata;
        this.votedata = {};
        this.getVote = function()
        {
            $http(
                {
                    url: "http://localhost:8080/Comment-Vote_Service/vote",
                    method: "GET",
                    params: {id: getParameterByName("id"), db: "question"}
                }).success(function(data)
                {
                    if (data.vote != null)
                    {
                        questionvotecontrol.vote = data.vote;
                    }
                }    
                    )
        }
        this.addVote = function(x){
            this.votedata.token = $cookies.get("stackexchange_token");
            if (token!=null) {
                this.votedata.sum = x;
                $http({
                    url: "http://localhost:8080/Comment-Vote_Service/addVote",
                    method: "POST",
                    params: {
                        id: this.votedata.qid,
                        vote: this.votedata.sum,
                        db: "question",
                        token: this.votedata.token
                    }
                });
            }
                else {
                    alert("You must log in to vote.");
                }
        }
        this.init = function(qid)
        {
            this.votedata.qid = qid;
        }
        
    }
])

