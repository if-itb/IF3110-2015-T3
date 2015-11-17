<% 
	forEach($answers as $answer)
	List answer = (List) request.getAttribute("answers");
	iterator i = answer.iterator();
	while (i.hasNext())
	{
%>
	<div class='container card'>
		<div class='votecell'>
			<a class='voteup' answer-id= <% out.print(answer.id); %> title='This answer is useful'></a>
			<span class='count'><% out.print(answer.votes); %></span>
			<a class='votedown' answer-id= <% out.print(answer.id); %>  title='This answer is not useful'></a>
		</div>
		<div class='answer-detail'>
			<div class='detail'> <% out.print(answer.content); %> </div>
		</div>
		<span class='control'>
			<span>
				asked by
				<span class='username'><% out.print(answer.name); %></span>
				(<span class='email'><% out.print(answer.email); %></span>)
				at <% out.print(answer.created_at); %>
			</span>
		</span>
	</div>
<% } %>

