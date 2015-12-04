
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML">
	<%
	import com.yangnormal.sstackex.ws.WebServiceImpl;
	if (request.getParameter("id")!=null){
		int qid = Integer.parseInt(request.getParameter("id"));
		URL url = new URL ("http://localhost:8080/ws/registration?wsdl");
		QName qname = new QName("http://ws.yangnormal.com/","RegistrationImplService");
		Service service = Service.create(url,qname);
		WebServiceImpl ws = service.getPort(WebService.class);
		Question status = ws.getQuestion(id); 

	%>
	<html>
	<head>

		<title>'.$rows[0]['topic'].'</title>
		<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet" type="text/css">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		</head>
		<body>
			<div class="container">
				<a class="homelink" href="http://mystackexchange.dev"><h1 id="title">My StackExchange</h1></a>
				<div class="content">
					<h2>'.$rows[0]['topic'].'</h2>
					<hr>
					<div class="stackquestion">
						<div class="votes"><div class="arrow-up" onclick="voteupquestion('.$_GET['id'].')"></div><div id="votequestion">'.$rows[0]['vote'].'</div><div class="arrow-down"  onclick="votedownquestion('.$_GET['id'].')"></div></div>
						<div class="content">'.$rows[0]['content'].'</div>
						<div class="detail">asked by <a class="linkname">'.$rows[0]['name'].' ('.$rows[0]['email'].')</a> at '.$rows[0]['date'].' | <a class="linkedit" href="editpost.php?id='.$_GET['id'].'">edit</a> | <a class="linkdelete" onclick="return validatedelete()" href="deletepost.php?id='.$_GET['id'].'">delete</a></div>
					</div>
					<br>
					<h2>'.$id[0].' Answers</h2>
					<hr>
					<% 
					
					//REQUEST ANSWER DARI WEB SERVICE
					//MENERIMA RESPONSE LIST ANSWER DARI WEB SERVICE
					ArrayList<Answer> ListAnswer = Arrays.asList(ws.getListAnswer(qid)); //Berarti boleh return array kosong kalau ga ada answers
					for (Answer answer : ListAnswer){
					%>
					<div class="stackanswer">
						<br>
						<div class="votes"><div class="arrow-up" onclick="voteupanswer('.$val['id'].','.$_GET['id'].')"></div><div id="voteanswer'.$val['id'].'">'.$val['vote'].'</div><div class="arrow-down" onclick="votedownanswer('.$val['id'].','.$_GET['id'].')"></div></div>
						<div class="content">'.$val['content'].'</div>
						<div class="detail">answered by <a class="linkname">'.$val['name'].'</a> at '.$val['date'].'</div>
					</div>
					<br>
					<hr>
					<% 
					}
					%>
				</div>
				<div class="content question">
					<h2 class="title2">Your Answer</h2>
					<hr>
					<form action="answer.php?qid='.$_GET['id'].'" method="post" onsubmit="return validateanswer()">
					<input class="textbox" type="text", name="name", id="name" placeholder="Name">
					<br>
					<input class="textbox" type="text", name="email", id="email" placeholder="Email">
					<br>
					<textarea class="textarea", name="content", id="content" placeholder="Content" ></textarea>
					<br>
					<input type="submit" id="post" value="Post">
					</form>	
				</div>	
			</div>
			<script src="js/script.js"></script>
		</body>
	<%
	</html>