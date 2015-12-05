<%-- 
    Document   : index
    Created on : Nov 10, 2015, 4:02:15 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TEST ANGULAR JS</title>
    </head>
    <body>
        <h1>Entry Form</h1>
      <div ng-app="">
          <p><input type="button" ng-model="comment" placeholder="" action="http://localhost:8082/WBD_IS/comment"></p>
 	<p>Name: <input type="text" ng-model="name"></p>
 	<p ng-bind="name"></p>
    </div>
    </body>
</html>
