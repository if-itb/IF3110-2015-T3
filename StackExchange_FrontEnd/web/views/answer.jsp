<%--
  Created by IntelliJ IDEA.
  User: elvan_owen
  Date: 11/11/15
  Time: 12:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>StackExchange</title>

    <link href="/assets/css/materialize.min.css" rel="stylesheet">
    <link href="/assets/css/answer.css" rel="stylesheet">
    <%--<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">--%>
    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <%--<script>--%>
        <%--<%@ include file="/assets/js/angular.min.js"%>--%>
    <%--</script>--%>
    <%--<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0-beta.1/angular.min.js"></script>--%>
</head>
<body ng-app="stackexchangeApp">

<nav class="red lighten-1" role="navigation">
    <div class="nav-wrapper"><a id="logo-container" href="/" class="brand-logo">Stack Exchange</a>
        <ul class="right hide-on-med-and-down">
            <li><a class="waves-effect" href="/register">Register</a></li>
            <li><a class="waves-effect" href="/login">Login</a></li>
        </ul>

        <%--<ul class="right hide-on-med-and-down">--%>
            <%--<li><a class="waves-effect" href="/register">user.name</a></li>--%>
        <%--</ul>--%>

        <ul id="nav-mobile" class="side-nav">
            <li><a href="#">Navbar Link</a></li>
        </ul>
        <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
</nav>

<section id="question" ng-controller="QuestionController as QuestionCtrl">
    <section id="topic-title">
        <h4 class="red-text center-align">{{QuestionCtrl.question.title}}</h4>
        <br/>
    </section>
    <section id="question-title">
        <h5 class="red-text">Question</h5>
        <div class="divider"></div>
    </section>
    <div class="row">
        <div class="col s10 offset-s1">
            <div style="margin-bottom: 0;margin-top:30px" class="card not-comment" data-id="{{QuestionCtrl.question.id}}">
                <a class="btn-floating waves-effect red question-close"><i class="material-icons">cancel</i></a>
                <div class="card-content red-text clearfix">
                    <span class="card-name left"><b>{{QuestionCtrl.question.author_name}}</b></span>
                    <span class="card-date right"><i>{{QuestionCtrl.question.create_time}}</i></span>
                </div>
                <br/>
                <div class="card-content red-text">
                    <p class="input-content">{{QuestionCtrl.question.content}}</p>
                </div>
                <div class="fixed-action-btn horizontal click-to-toggle">
                    <a class="btn-floating btn-large red">
                        <i class="large mdi-navigation-menu"></i>
                    </a>
                    <ul>
                        <li class="thumb-btn-wrapper">
                            <a class="btn-floating red lighten-2 thumbs-num" id="question-vote-num">
                                <b>{{QuestionCtrl.question.vote}}</b>
                            </a>
                        </li>
                        <li class="thumb-btn-wrapper">
                            <a class="btn-floating waves-effect green question-upvote-btn" ng-click="QuestionCtrl.upvote(QuestionCtrl.question.id)">
                                <i class="material-icons">thumb_up</i>
                            </a>
                        </li>
                        <li class="thumb-btn-wrapper">
                            <a class="btn-floating waves-effect red question-downvote-btn" ng-click="QuestionCtrl.downvote(QuestionCtrl.question.id)">
                                <i class="material-icons">thumb_down</i>
                            </a>
                        </li>
                        <li><a class="btn-floating waves-effect blue edit-btn"><i class="material-icons">mode_edit</i></a></li>
                    </ul>
                </div>
            </div>
            <div ng-controller="QuestionCommentController as CommentCtrl" class="comment-wrapper" ng-init="CommentCtrl.getComments(QuestionCtrl.question.id)">
                <div ng-repeat="comment in comments" class="card comments" style="margin: 0 auto;">
                    <div class="card-panel red lighten-2 white-text" style="margin:0;position:absolute;left:0px;top:0;padding:10px 20px;text-transform: uppercase">
                        Comments
                    </div>
                    <div class="card-content red-text">
                        <span>{{comment.content}}</span>
                        <div class="clearfix">
                            <div class="right" style="position:relative;top:-5px;">
                                <a class="btn-floating waves-effect red" ng-click="CommentCtrl.downvote(comment.comment_id)">
                                    <i class="material-icons">thumb_down</i>
                                </a>
                            </div>
                            <div class="right" style="position:relative;top:-5px;margin-right:10px">
                                <a class="btn-floating waves-effect green" ng-click="CommentCtrl.upvote(comment.comment_id)">
                                    <i class="material-icons">thumb_up</i>
                                </a>
                            </div>
                            <div class="chip right white-text" style="position:relative;top:-5px;margin-right:10px">
                                {{comment.user_name}}
                            </div>
                            <div class="right" style="margin-right:10px">
                                <a class="red lighten-2 white-text" style="padding: 5px 10px">
                                    {{comment.vote}}
                                </a>
                            </div>
                            <div class="right" style="margin-right:10px">
                                {{comment.create_date}}
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>

                <div class="card" style="margin: 0 auto;padding-bottom: 5px">
                    <div class="card-content red-text">
                        <div class="input-field">
                            <input ng-model="newComment" type="text" />
                            <label class="red-text">Type your comment here...</label>
                        </div>
                        <a class="btn red lighten-1 right-align" ng-click="CommentCtrl.addComment(QuestionCtrl.question.id)">Send</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="answer-title">
    <h5 class="red-text">Answers</h5>
    <div class="divider"></div>
    <br/>
</section>

<section id="answer" ng-controller="AnswerController as AnswerCtrl">
    <div class="row" ng-if="AnswerCtrl.answers.length == 0">
        <div class="col s10 offset-s1">
            <div class="card" style="padding-bottom: 0;height: 100px;">
                <div class="valign-wrapper" align="center" style="height: 100%;">
                    <h5 class="valign" style="width:100%"><em class="red-text">No answers yet ...</em></h5>
                </div>
            </div>
        </div>
    </div>
    <div class="row" ng-repeat="answer in AnswerCtrl.answers">
        <div class="col s10 offset-s1">
            <div class="card not-comment" data-id="{{answer.id}}" style="margin-bottom: 0">
                <div class="card-content red-text clearfix">
                    <span class="card-name left"><b>{{answer.author_name}}</b></span>
                    <span class="card-date right"><i>{{answer.create_time}}</i></span>
                </div>
                <br/>
                <div class="card-content red-text">
                    <p class="input-content">{{answer.content}}
                    </p>
                </div>
                <div class="fixed-action-btn horizontal click-to-toggle">
                    <a class="btn-floating btn-large red">
                        <i class="large mdi-navigation-menu"></i>
                    </a>
                    <ul>
                        <li class="thumb-btn-wrapper">
                            <a class="btn-floating red lighten-2 thumbs-num">
                                    <b>{{answer.vote}}</b>
                            </a>
                        </li>
                        <li class="thumb-btn-wrapper">
                            <a class="btn-floating waves-effect green answer-upvote-btn" ng-click="AnswerCtrl.upvote(answer.id)">
                                <i class="material-icons">thumb_up</i>
                            </a>
                        </li>
                        <li class="thumb-btn-wrapper">
                            <a class="btn-floating waves-effect red answer-downvote-btn" ng-click="AnswerCtrl.downvote(answer.id)">
                                <i class="material-icons">thumb_down</i>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

            <div ng-controller="AnswerCommentController as CommentCtrl" class="comment-wrapper" ng-init="CommentCtrl.getComments(answer.id)">
                <div ng-repeat="comment in comments[answer.id]" class="card comments" style="margin: 0 auto;">
                    <div class="card-panel red lighten-2 white-text" style="margin:0;position:absolute;left:0px;top:0;padding:10px 20px;text-transform: uppercase">
                        Comments
                    </div>
                    <div class="card-content red-text">
                        <span>{{comment.content}}</span>
                        <div class="clearfix">
                            <div class="right" style="position:relative;top:-5px;">
                                <a class="btn-floating waves-effect red" ng-click="CommentCtrl.downvote(answer.id, comment.comment_id)">
                                    <i class="material-icons">thumb_down</i>
                                </a>
                            </div>
                            <div class="right" style="position:relative;top:-5px;margin-right:10px">
                                <a class="btn-floating waves-effect green" ng-click="CommentCtrl.upvote(answer.id, comment.comment_id)">
                                    <i class="material-icons">thumb_up</i>
                                </a>
                            </div>
                            <div class="chip right white-text" style="position:relative;top:-5px;margin-right:10px">
                                {{comment.user_name}}
                            </div>
                            <div class="right" style="margin-right:10px">
                                <a class="red lighten-2 white-text" style="padding: 5px 10px">
                                    {{comment.vote}}
                                </a>
                            </div>
                            <div class="right" style="margin-right:10px">
                                {{comment.create_date}}
                            </div>
                        </div>
                        <br/>
                    </div>
                </div>

                <div class="card" style="margin: 0 auto;padding-bottom: 5px">
                    <div class="card-content red-text">
                        <div class="input-field">
                            <input ng-model="newComment" type="text" />
                            <label class="red-text">Type your comment here...</label>
                        </div>
                        <a class="btn red lighten-1 right-align" ng-click="CommentCtrl.addComment(answer.id)">Send</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="answer-title">
    <h5 class="red-text">My Answer</h5>
    <div class="divider"></div>
    <br/>
</section>

<section ng-controller="QuestionController as QuestionCtrl">
    <form method="POST" id="add-answer-form">
        <div class="row">
            <div class="col s10 offset-s1">
                <div class="card red lighten-1">
                    <div class="card-content">
                        <div class="row">
                            <div class="col s12">
                                <div class="row">
                                    <div class="input-field col s12">
                                        <textarea id="textarea1" ng-model="QuestionCtrl.newAnswer" class="white-text materialize-textarea" name="content"></textarea>
                                        <label for="textarea1" class="white-text">My Answer</label>
                                        <input type="hidden" name="question_id"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="right-align">
                        <button type="submit" id="answer-btn" class="btn waves-effect red lighten-2" ng-click="QuestionCtrl.addAnswer(QuestionCtrl.question.id)">Send</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</section>

<!--  Scripts-->
<script>
    <%@ include file="/assets/js/jquery.min.js"%>
    <%@ include file="/assets/js/materialize.min.js"%>
    <%@ include file="/assets/js/angular.min.js"%>
    <%@ include file="/assets/js/answer.js"%>
</script>

</body>
</html>