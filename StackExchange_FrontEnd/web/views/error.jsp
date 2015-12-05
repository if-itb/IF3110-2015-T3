<%--
  Created by IntelliJ IDEA.
  User: elvan_owen
  Date: 11/18/15
  Time: 2:56 AM
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
    <link href='/assets/css/error.css' rel="stylesheet">

    <!-- CSS  -->
    <%--<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">--%>
</head>
<body>

<nav class="red lighten-1" role="navigation">
    <div class="nav-wrapper"><a id="logo-container" href="/" class="brand-logo">Stack Exchange</a>
        <ul class="right hide-on-med-and-down">
            <li><img src="/assets/images/user-icon.png" alt="" class="circle responsive-img"></li>
        </ul>
    </div>
</nav>

<div class="valign-wrapper register-card-wrapper center-align">
    <div class="card-panel red lighten-1">
          <h1 class="white-text">${error}</h1>
    </div>
</div>
<!--  Scripts-->
<script type="text/javascript" src="/assets/js/jquery.min.js"></script>
<script type="text/javascript" src="/assets/js/materialize.min.js"></script>
<script type="text/javascript" src="/assets/js/init.js"></script>

</body>
</html>