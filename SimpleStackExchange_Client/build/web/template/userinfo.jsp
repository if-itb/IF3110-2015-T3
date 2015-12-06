
<%
    Boolean isLogin = false;
    if(request.getParameter("isLogin").equalsIgnoreCase("true")){
        isLogin = true;
    }
%>

<section class="container">
    <div class="row jumbotron">
        <h2><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Hello, 
            <% if (!isLogin) { %>
            Guest!
            <% }else{%>
            <%=tool.Util.getNameByUid(tool.Util.getUid(request))%>
            <%}%>
        </h2>
        <div class="row">
            <% if (!isLogin) { %>
                <span>
                    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/register.jsp" role="button">Go Signup!</a>
                </span>
                <span>
                    for getting access to all features.
                </span>
            <% }else{%>

                <span>
                    Welcome back! Have some question?
                </span>
                <span>
                    <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/questionform.jsp" role="button">Ask Now!</a>
                </span>
            <%}%>

        </div>
    </div>
</section>