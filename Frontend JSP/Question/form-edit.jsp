<% 
	question = request.getAttribute("questions");
%>
<form class='question-form' action='/questions/edit' method='POST'>
	<input type='hidden' name='question_id' value='<% out.print(question.qid); %>'>
	<input class='form-input' type='text' name='name' placeholder='Name' value='<% out.print(question.name); %>'>
	<input class='form-input' type='text' name='email' placeholder='Email' value='<% out.print(question.email); %>'>
	<input class='form-input' type='text' name='topic' placeholder='Question Topic' value='<% out.print(question.qtopic); %>'>
	<textarea class='form-input' name='content' placeholder='Content' rows='8'><% out.print(question.qcontent); %></textarea>
	<button type='submit' onclick="return validateForm()">Post</button>
</form>
