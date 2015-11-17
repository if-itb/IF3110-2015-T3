<!DOCTYPE html>
<html>
	<head>
		<link href='https://fonts.googleapis.com/css?family=Tangerine|Roboto' rel='stylesheet' type='text/css'>
		<%! 
			String title = request.getAttribute("title");
		%>
		<title><%= title %></title>
	</head>
	<body>
		<div>
			<div class="header">
				<h1 id="title"><a href='/'>Asklyz</a></h1>
			</div>
			<% 
				List search = (List) request.getAttribute("search");
				Iterator i = search.iterator();
				while (i.hasNext())
				{
					String value = (String) i.next();
					out.print(value);
				}
			%>

			<div class="content">
				<div class='card'>
					<%! 
						String headline = request.getAttribute("headline");
					%>

					<h2><%= headline %></h2>
				</div>
				<%! 
					String content = request.getAttribute("content");
				%>
				<%= content %>
			</div>
			<div class="footer"></div>
		</div>
	</body>
</html>
