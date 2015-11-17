<% 
	List question = (List) request.getAttribute("questions");
	iterator i = question.iterator();
	while (i.hasNext())
	{
%>
<div class='container card'>
	<div class='statistic'>
		<span class='count'><% out.print(question.votes); %></span>
		<span class='unit'>votes</span>
	</div>
	<div class='statistic'>
		<span class='count'><% out.print(question.answer_count); %></span>
		<span class='unit'>answers</span>
	</div>
	<div class='question-detail'>
		<div class='topic'><a href='/questions?id=<?= $question->id ?>'><% out.print(question.qtopic); %></a></div>
		<div class='detail'>
			<p><% out.print(question.qcontent); %></p>
		</div>
	</div>
	<span class='control'>
		<span>
			asked by 
			<span class='username'><% out.print(question.name); %></span>
			(<span class='email'><% out.print(question.email); %></span>)
			at <% out.print(question.created_at); %> |
			</span>
		<span><a href='/questions/edit?id=<?= $question->id ?>'>edit </a></span>|
		<span><a href='/questions/delete?id=<?= $question->id ?>' onclick="return deleteConfirm()">delete </a></span>
	</span>
</div>
<% } %>
