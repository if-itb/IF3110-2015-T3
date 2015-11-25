<%-- 
    Document   : index
    Created on : Nov 14, 2015, 1:47:57 PM
    Author     : user
--%>

<%@page import="java.util.List"%>
<%@page import="org.wsdl.Question"%>
<%@page import="org.wsdl.QuestionArray"%>
<%@page import="org.wsdl.StackExchangeImplService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    
    
    <title>Simple StackExchange</title>
    <link rel="stylesheet" href="css/style.css" />
    <script src="js/delete_question.js"></script>
    <%
        StackExchangeImplService stackExchangeService = new StackExchangeImplService();
        org.wsdl.StackExchange stackExchange = stackExchangeService.getStackExchangeImplPort();
        QuestionArray allQuestion = stackExchange.getAllQuestion("");
        List<Question> items = allQuestion.getItem();
        String name = "";
        name = (String) session.getAttribute("name");
    %>
    
</head>
<body>
    <a href="index.jsp"><h1>Simple StackExchange</h1></a><br>
    <%if (name != null) { 
            out.println(name); %>
            <a href="logout.jsp">log out</a>
        <%}else{%>
            <a href="login_form.jsp">log in</a>
            <a href="reg.jsp">register</a>
        <%}%>
    <div class="search">
        <form method="post" action="search.jsp">
            <input class="search_form" type="text" name="keyword">
            <button class="button">Search</button>
        </form><br>
        <div class="search_new">Cannot find what you are looking for? <a class="yellow" href="new.jsp">Ask here</a><br></div>
    </div>
    <br><br>
    <div class="list">
    <div class="title">Recently Asked Questions</div>
    <ul>
    <%for(Question question : items){%>
        <li>
            <table>
                <tbody>
                    <tr>
                        <td><div class="votes"><%=question.getVote()%><br>Votes</div></td>
                        <td><div class="count"><%=question.getCount()%><br>Answers</div></td>
                        <td>
                            <div class="content"><a href="question.jsp?id=<%=question.getId()%>"><%=question.getTopic()%></a></div>
                            <div class="credential">asked by <div class="name"><%=question.getName()%></div> <%if(name != null && name.equals(question.getName())){%>| <a class="yellow" href="edit.jsp?id=<%=question.getId()%>">edit</a> | <a class="delete" href="javascript:confirmDelete(<%=question.getId()%>)">delete</a></div><%}%>
                        </td>
                    </tr>
                </tbody>
            </table>
        </li>
    <%}%>
    </ul>
    </div>
</body>