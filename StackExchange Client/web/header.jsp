        <%
            String token = "";
            Cookie[] cookies = request.getCookies();
            for(Cookie temp : cookies){
                if(temp.getName().equals("token")){
                    token = temp.getValue();
                }
            }
        %>
	
        <%
        if(request.getParameter("id") != null && !token.isEmpty()){
        %>
        <h1 class="center">
            <a href="index.jsp?id=<%=request.getParameter("id")%>">Simple StackExchange</a>
        </h1>
        <%
        } else {
        %>
        <h1 class="center">
            <a href="index.jsp">Simple StackExchange</a>
        </h1>
        <%
        }
        %>
