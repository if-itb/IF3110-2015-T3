<%--
  Created by IntelliJ IDEA.
  User: elvan_owen
  Date: 11/16/15
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>StackExchange</title>

    <link href='/assets/css/materialize.min.css' rel="stylesheet">
    <link href='/assets/css/register.css' rel="stylesheet">

    <!-- CSS  -->
    <%--<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">--%>
</head>
<body>
<nav class="red lighten-1 center-align" role="navigation">
    <a id="logo-container" href="/">Stack Exchange</a>
</nav>

<div class="valign-wrapper register-card-wrapper">
    <form action="/register" class="valign" method="POST">
        <div class="row">
            <div class="col s12 m8 offset-m2">
                <div class="card">
                    <div class="card-content red-text">
                        <span class="card-title">Register</span>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field">
                                    <input id="user-register-name" type="text" class="validate" name="name">
                                    <label class="red-text" for="user-register-name">Name</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field">
                                    <input id="user-register-email" type="text" class="validate" name="email">
                                    <label class="red-text" for="user-register-email">Email</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col s12">
                                <div class="input-field">
                                    <input id="user-register-pwd" type="password" class="validate" name="password">
                                    <label class="red-text" for="user-register-pwd">Password</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="card-action center-align">
                        <button type="submit" class="waves-effect btn red lighten-1">Sign Me Up !</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!--  Scripts-->
<script type="text/javascript" src="/assets/js/jquery.min.js"></script>
<script type="text/javascript" src="/assets/js/materialize.min.js"></script>
<script type="text/javascript" src="/assets/js/init.js"></script>

</body>
</html>
