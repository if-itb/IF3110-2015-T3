<%-- 
    Document   : header
    Created on : Nov 17, 2015, Nov 17, 2015 4:51:58 PM
    Author     : Fikri-PC
--%>

<%@page import="java.util.*" %>
<html>
<head>
	<meta charset="utf-8">
    <title>Simple Stack Exchange</title>
    <link rel="stylesheet" media="screen" href="css/style.css" >
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<script type="text/javascript" src="js/coppeng.js"></script>
</head>
<body>
<div id = "wrapper">
	<h1 class = "center"><a href="index.jsp">Simple Stack Exchange</a></h1>
        <%!
            class question
            {
                public int id;
                public String name;
                public String email;
                public String topic;
                public String content;
                public int vote;
                public question(int _id, String _name, String _email, String _topic, String _content, int _vote){
                    id = _id;
                    name = _name;
                    email = _email;
                    topic = _topic;
                    content = _content;
                    vote = _vote;
                }
            }
        %>
        <%!
            class answer
            {
                public int id;
                public int q_id;
                public String name;
                public String email;
                public String content;
                public int vote;
                public answer(int _id, int _q_id, String _name, String _email, String _content, int _vote){
                    id = _id;
                    q_id = _q_id;
                    name = _name;
                    email = _email;
                    content = _content;
                    vote = _vote;
                }
            }
        %>
        
        <% 
            question[] dbq = new question[10];
                for(int i=0; i<10; i++){
                    dbq[i] = new question(i,"nama"+i, "email" +i,"topic" +i,"content" +i,1);
                }
            answer[] dba = new answer[10];
                for(int i=0; i<10; i++){
                    dba[i] = new answer(i,i, "nama" +i,"email" +i,"content" +i,1);
                }
        %>

            
