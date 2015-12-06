<%--
  Created by IntelliJ IDEA.
  User: elvan_owen
  Date: 11/10/15
  Time: 1:25 PM
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
    <link href='/assets/css/materialize.min.css' rel="stylesheet">
    <link href="/assets/css/index.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>
<body>
<nav class="red lighten-1" role="navigation">
    <div class="nav-wrapper"><a id="logo-container" href="#" class="brand-logo">Stack Exchange</a>
        <ul class="right hide-on-med-and-down">
            <c:if test = "${login != 'true'}">
                <li><a class="waves-effect" href="/register">Register</a></li>
                <li><a class="waves-effect" href="/login">Login</a></li>
            </c:if>
            <c:if test = "${login == 'true'}">
                <li><img src="/assets/images/user-icon.png" alt="" class="circle responsive-img"></li>
            </c:if>
        </ul>
    </div>
</nav>

<section id="search">
    <div class="row">
        <div class="offset-s2 col s8">
            <div class="row">
                <form class="col s10">
                    <div class="input-field">
                        <%--<i class="material-icons prefix">search</i>--%>
                        <input id="input-search" type="text" class="validate">
                        <label class="red-text" for="input-search">Search</label>
                    </div>
                    <div class="center-align">
                        <i class="red-text">Cannot find what you are looking for?</i> &nbsp;&nbsp;<a class="waves-effect waves-light btn red lighten-1 white-text modal-trigger" data-target="modal1">Ask here</a>
                    </div>
                </form>
                <div class="col s2" id="search-btn-wrapper">
                    <a class="btn-floating btn-large waves-effect waves-light red lighten-1"><i class="material-icons">search</i></a>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="question-title">
    <h5 class="red-text">Top Questions</h5>
    <div class="divider"></div>
</section>

<section id="recent-post">
    <c:forEach items="${questions}" var="question">
        <div class="row">
            <div class="col s10 offset-s1">
                <div class="card red lighten-1" data-id="${question.id}">
                    <div class="card-content white-text">
                        <span class="clearfix">
                            <span class="card-title">${question.title}</span>
                            <span class="card-title right create-date">${question.createDate}</span>
                        </span>
                        <div class="divider"></div>
                        <br/>
                        <p>${question.content}</p>
                    </div>

                    <div class="fixed-action-btn horizontal click-to-toggle">
                        <a class="btn-floating btn-large red">
                            <i class="large mdi-navigation-menu"></i>
                        </a>
                        <ul>
                            <li class="thumb-btn-wrapper">
                                <a class="btn-floating red lighten-2 thumbs-num">
                                        ${question.vote}
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</section>

<!-- Modal Structure -->
<div id="modal1" class="modal">
    <form method="POST" id="add-question-form">
        <div class="modal-content">
            <h4 class="red-text">Ask Your Question</h4>

            <div class="row">
                <div class="col s12">
                    <div class="row">
                        <div class="input-field col s12">
                            <input id="title" type="text" class="validate" name="title">
                            <label class="red-text" for="title">Title</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12">
                            <textarea id="textarea1" class="materialize-textarea" name="content"></textarea>
                            <label class="red-text" for="textarea1">Question</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button id="add-question-btn" class="waves-effect red lighten-1 white-text btn-flat" type="submit" value="Send">Send</button>
        </div>
    </form>
</div>

<!--  Scripts-->
<script src="/assets/js/jquery.min.js"></script>
<script src="/assets/js/materialize.min.js"></script>
<script src="/assets/js/index.js"></script>

</body>
</html>