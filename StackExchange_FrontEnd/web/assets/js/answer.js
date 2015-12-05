angular.module("stackexchangeApp", [])
    .constant("voteAndCommentHost", "http://localhost:5555")
    .service("parameterService", function(){
        this.getParameter = function(name){
            name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
            var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
            return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        }
    })
    .service("voteService", function($http, parameterService, voteAndCommentHost){
        this.upvote = function(type, id, successCallback, errorCallback){
            if (parameterService.getParameter("token") == ""){
                window.location.href = "/login";
            }

            return $http({
                method: 'POST',
                url: voteAndCommentHost + '/' + type + '/' + id + '/upvote?token=' + parameterService.getParameter("token")
            })
        };

        this.downvote = function(type, id, successCallback, errorCallback){
            if (parameterService.getParameter("token") == ""){
                window.location.href = "/login";
            }

            return $http({
                method: 'POST',
                url: voteAndCommentHost + '/' + type + '/' + id + '/downvote?token=' + parameterService.getParameter("token")
            });
        };
    })
    .service("commentService", function($http, parameterService, voteAndCommentHost){
        this.addComment = function(type, id, content, successCallback, errorCallback){
            if (parameterService.getParameter("token") == ""){
                window.location.href = "/login";
            }

            return $http({
                method: 'POST',
                url: voteAndCommentHost + '/' + type + '/' + id + '/comment?token=' + parameterService.getParameter("token"),
                data: {
                    content : content
                }
            });
        }

        this.getComment = function(type, id, successCallback, errorCallback){
            return $http({
                method: 'GET',
                url: voteAndCommentHost + '/' + type + '/' + id + '/comment'
            });
        }

        this.upvote = function(type, id, successCallback, errorCallback){
            if (parameterService.getParameter("token") == ""){
                window.location.href = "/login";
            }

            return $http({
                method: 'POST',
                url: voteAndCommentHost + '/' + type + '/comment/' + id + '/upvote?token=' + parameterService.getParameter("token")
            }).then(successCallback, errorCallback);
        };

        this.downvote = function(type, id, successCallback, errorCallback){
            if (parameterService.getParameter("token") == ""){
                window.location.href = "/login";
            }

            return $http({
                method: 'POST',
                url: voteAndCommentHost + '/' + type + '/comment/' + id + '/downvote?token=' + parameterService.getParameter("token")
            }).then(successCallback, errorCallback);
        };
    })
    .controller("AnswerCommentController", ["commentService", "$scope", function(commentService, $scope){
        $scope.comments = {};
        $scope.newComment = "";

        this.getComments = function(answerId){
            if ($scope.comments[answerId]){
                return $scope.comments[answerId];
            }

            commentService.getComment("answer", answerId).then(function(data){
                $scope.comments[answerId] = data.data.data;
            })
        };

        this.addComment = function(id){
            commentService.addComment("answer", id, $scope.newComment).then(function(response){
                if ($scope.comments[id].length == 0){
                    $scope.comments[id] = [];
                }
                $scope.comments[id].push({"comment_id": response.data.data.insertId, "content" : $scope.newComment, "vote": 0, "user_name": "Not Available", "create_date": new Date()});
                $scope.newComment = "";
            }, function(data){
                if (data.status == 440){
                    window.location.href = "/login";
                }
            })
        };

        this.upvote = function(answerId, commentId){
            commentService.upvote("answer", commentId).then(function(response){
                console.log("--RESPONSE");
                console.log(response);

                for (var i=0;i<$scope.comments[answerId].length;i++){
                    if ($scope.comments[answerId][i].comment_id == commentId){
                        $scope.comments[answerId][i].vote++;
                    }
                }
            }, function(data){
                if (data.status == 440){
                    window.location.href = "/login";
                }
            })
        };

        this.downvote = function(answerId, commentId){
            commentService.downvote("answer", commentId).then(function(response){
                console.log("--RESPONSE");
                console.log(response);

                for (var i=0;i<$scope.comments[answerId].length;i++){
                    if ($scope.comments[answerId][i].comment_id == commentId){
                        $scope.comments[answerId][i].vote--;
                    }
                }
            }, function(data){
                if (data.status == 440){
                    window.location.href = "/login";
                }
            })
        };
    }])
    .controller("QuestionCommentController", ["commentService", "$scope", function(commentService, $scope){
        $scope.comments = [];
        $scope.newComment = "";

        this.getComments = function(questionId){
            console.log("**IN");
            commentService.getComment("question", questionId).then(function(data){
                console.log("****");
                console.log(data);
                $scope.comments = data.data.data;
                console.log($scope.comments);
            })
        };

        this.addComment = function(id){
            commentService.addComment("question", id, $scope.newComment).then(function(response){
                $scope.comments.push({"comment_id": response.data.data.insertId, "content" : $scope.newComment, "vote": 0, "user_name": "Not Available", "create_date": new Date()});
                $scope.newComment = "";
            }, function(data){
                if (data.status == 440){
                    window.location.href = "/login";
                }
            })
        }

        this.upvote = function(commentId){
            commentService.upvote("question", commentId).then(function(response){
                for (var i=0;i<$scope.comments.length;i++){
                    if ($scope.comments[i].comment_id == commentId){
                        $scope.comments[i].vote++;
                    }
                }
            }, function(data){
                if (data.status == 440){
                    window.location.href = "/login";
                }
            })
        }

        this.downvote = function(commentId){
            commentService.downvote("question", commentId).then(function(response){
                for (var i=0;i<$scope.comments.length;i++){
                    if ($scope.comments[i].comment_id == commentId){
                        $scope.comments[i].vote--;
                    }
                }
            }, function(data){
                if (data.status == 440){
                    window.location.href = "/login";
                }
            })
        }
    }])
    .controller("AnswerController", ["voteService", function(voteService){
        this.answers = ${answers};
        console.log(this.answers);

        var answers = this.answers;

        this.upvote = function(answerId){
            voteService.upvote("answer", answerId).then(function(){
                for (var i=0;i<answers.length;i++){
                    if (answerId == answers[i].id){
                        answers[i].vote++;
                    }
                }
            }, function(data){
                if (data.status == 440){
                    window.location.href = "/login";
                }
            })
        };

        this.downvote = function(answerId){
            voteService.downvote("answer", answerId).then(function(){
                for (var i=0;i<answers.length;i++){
                    if (answerId == answers[i].id){
                        answers[i].vote--;
                    }
                }
            }, function(data){
                if (data.status == 440){
                    window.location.href = "/login";
                }
            })
        }
    }])
    .controller("QuestionController", ["$http", "voteService", "parameterService", function($http, voteService, parameterService){
        this.question = ${question};
        console.log(this.question);

        this.newAnswer = "";
        var newAnswer = this.newAnswer;

        var question = this.question;

        this.addAnswer = function(questionId){
            var url = "";
            var token = parameterService.getParameter('token');

            if (token == ""){
                window.location.href = "/login"
            }

            url = '/answer/add';

            $http({
                method: 'POST',
                url: url,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data: {content: this.newAnswer, question_id: questionId, token:token}
            }).then(function () {
                window.location.href = "/answer?id=" + questionId + "&token=" + parameterService.getParameter('token');
            }, function(response){
                console.log(response);
                if (response.status == 401){
                    window.location.href = "/login";
                } else {
                    window.location.href = "/error"
                }

            });
        };

        this.upvote = function(questionId){
            voteService.upvote("question", questionId).then(function(){
                question.vote++;
            }, function(data){
                if (data.status == 440){
                    window.location.href = "/login";
                }
            })
        };

        this.downvote = function(questionId){
            voteService.downvote("question", questionId).then(function(){
                question.vote--;
            }, function(data){
                if (data.status == 440){
                    window.location.href = "/login";
                }
            })
        }
    }]);

(function($){
    $(function(){

        //$('#question .card, #answer .card').hover(function(){
        //    $(this).find('.fixed-action-btn > .btn-floating').click();
        //}, function(){
        //    $(this).find('.fixed-action-btn > .btn-floating').click();
        //})

        function getParameterByName(name) {
            name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
            var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
                results = regex.exec(location.search);
            return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
        }

        setTimeout(function(){
            $('#question .card, #answer .card').find('.fixed-action-btn > .btn-floating').click();
        }, 200);

        var save_mode = false;

        $('.edit-btn').click(function(e){
            e.preventDefault()
            e.stopPropagation()

            if (!save_mode){
                console.log("editing")

                $card = $(this).closest('.card')
                var text = $card.find('.input-content').text()
                console.log(text)

                $card.find('.card-content:eq(1)').empty().append('<div class="input-field"><textarea id="text-edit" class="materialize-textarea">' + text + '</textarea></div>')
                $('#text-edit').focus().trigger('autoresize')

                save_mode = true
            } else {

                console.log("saving")

                var id = $(this).closest('.card').attr('data-id')
                var content = $('#text-edit').val()
                var title = $('#topic-title h4').text()

                $('<form action="/question/edit?token=' + getParameterByName('token') + '" method="POST">' +
                    '<input type="hidden" name="id" value="' + id + '"/>' +
                    '<input type="hidden" name="content" value="' + content + '"/>' +
                    '<input type="hidden" name="title" value="' + title + '"/>' +
                '</form>').appendTo('body').submit();
            }
        })

        if (getParameterByName('token')){
            $('#add-answer-form').attr('action', '/answer/add?token=' + getParameterByName('token'));
        } else {
            $('#add-answer-form').attr('action', '/answer/add');
        }

        $('.question-close').click(function(){
            var id = $(this).closest('.card').attr('data-id')
            $('<form action="/question/delete?token=' + getParameterByName('token') + '" method="POST">' +
            '<input type="hidden" name="id" value="' + id + '"/></form>').appendTo('body').submit();
        })


    }); // end of document ready
})(jQuery); // end of jQuery name space