<% 
	List question = (List) request.getAttribute("questions");
	iterator i = question.iterator();
	while (i.hasNext())
	{
%>
<div class='container'>
	<div class='votecell'>
		<a class='voteup' question-id=<% out.print(question.qid); %> title='This answer is useful'></a>
		<span class='count'><% out.print(question.votes); %></span>
		<a class='votedown' question-id=<% out.print(question.qid); %> title='This answer is not useful'></a>
	</div>
	<div class='question-detail'>
		<div class='detail'>
			<p><% out.print(question.qcontent); %></p>
		</div>
	</div>
	<span class='control'>
		<span>
			asked by
			<span class='username'><% out.print(question.name); %></span>
			(<span class='email'><% out.print(question.email); %>)
			at <% out.print(question.created_at); %> |
		</span>
		<span><a href='/questions/edit?id=<?= $question->id ?>'>edit </a></span>|
		<span><a href='/questions/delete?id=<?= $question->id ?>' onclick="return deleteConfirm()">delete </a></span>
	</span>
</div>

<div class='card'>
	<h2>
		<% if((question.answer_count) && question.answer_count > 0)
				out.print(question.answer_count); %> Answers
		<% else
			0 %> Answers
	</h2>
</div>
<% } %>