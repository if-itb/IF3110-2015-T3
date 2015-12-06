<%-- 
    Document   : mismatch
    Created on : Nov 27, 2015, 3:34:55 PM
    Author     : Luqman A. Siswanto
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Login | Overflow48</title>
    <link rel="stylesheet" type="text/css" href="../assets/css/style.css">
    <link rel="icon" type="image/png" href="../assets/white-icon.jpg">
    
  </head>
  <body>
    <div class="container">
      <h1 class="text-center"><a href="/StackExchangeclient">OVERFLOW48</a></h1>
      <form id="search" action="search.jsp" action="GET">
        <table>
        <tr>
          <td width="200%"> <input id="q" placeholder="What are you looking for?" type="text" class="form" name="q"></td>
          <td width="20%"> <button class="button" type="submit">Search</button> </td>
        </tr>
        </table>
      </form>
      <p class="text-right"><a href="login.jsp" class="link">Login</a> | <a href="register.jsp" class="link">Register</a></p>

      <p>Fill in the form to login into Overflow 48.</p><br>
        <div class="small_container">
          <h5>Login into Overflow 48</h5><br>
          <form id="login" action="../LoginAuth" method="POST">
          <input id="email" placeholder="Email" class="form" type="text" name="email" value="">
          <input id="pass" placeholder="Password" class="form" type="password" name="pass" value="">
          <div class="text-right">
              <button class="button" type="submit">Login</button>
          </div>
          </form>
          <p><div class="text-right">User and password mismatch. Try again.</div></p>
        </div>
    </div>
    
  </body>
  <footer> <br><br> </footer>
</html>