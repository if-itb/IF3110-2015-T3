<%
    Boolean isLogin = false;
    if(request.getParameter("isLogin").equalsIgnoreCase("true")){
        isLogin = true;
    }
%>
<nav class="navbar">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}">Simple <strong>StackExchange</strong></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
            <% if(!isLogin){ %>
            <form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/UserLogin" method="POST">
            <div class="form-group">
              <input type="text" name="email" placeholder="Email" class="form-control">
            </div>
            <div class="form-group">
              <input type="password" name="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
          </form>
            <% } else{%>
            <form class="navbar-form navbar-right" action="<%=response.encodeURL("UserLogout") %>" method="post">
                <input type="hidden" name="confirm" value="logout"/>
                <button type="submit" class="btn btn-danger">Logout</button>
            </form>
            <% } %>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
