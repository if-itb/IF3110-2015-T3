<%-- 
    Document   : search
    Created on : Nov 17, 2015, Nov 17, 2015 8:06:15 AM
    Author     : Fikri-PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="include/header.jsp" %>
<%
    String key = request.getParameter("key");
    int id = Integer.parseInt(key);
%>

<span class="page-title"><h2>Hasil untuk  '<% out.print(id); %>' : </h2></span><hr>
<% /*
<?php while($row = mysql_fetch_array($data)) : ?> */ %>
	<div class="row">
		<h2> <a href = view-question.jsp?id=<% out.print(id); %>>
                        <% out.print(dbq[id].topic); %>
                    </a>
                </h2>
		<p><% out.print(dbq[id].content); %></p>
		<br>
		<div class="col vote in-numbers">
			<div class = "flag">Vote</div>
			<div class = "number"> <% out.print(dbq[id].vote); %></div>
		</div>
                <% /*
		<?php
			$query2 = "SELECT COUNT(id) AS numAns FROM answer WHERE q_id = '".$row['id']."'";
			$data2 = mysql_query($query2);
			$row2 = mysql_fetch_array($data2);
		?> */ %>
		<div class = "col answers in-numbers">s
		<div class = "flag">Jawaban</div> <div class = "number">4</div>
		</div>
		<div class = "controls" align = "right">
                    ditanyakan oleh <span class="name"><% out.print(dbq[id].name); %></span>
			<span class="link edit"> <a href= "question.jsp?id=<% out.print(id); %>">Edit</a> </span>
			<span class="link delete"> <a href= "javascript:delete_question(<% out.print(id); %>)" >Delete</a></span>
		</div>
	</div>
	<br><hr>
        <% /*
<?php endwhile; ?>
<?php mysql_close($link); ?> */ %>
<%@include file="include/footer.jsp" %>
