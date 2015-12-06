<!DOCTYPE HTML>
<html>
<head>
<title>Login Status</title>
<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="container">
		<a class="homelink" href="index.jsp"><h1 id="title">My StackExchange</h1></a>
		<div class="content">
			<h1 class="center">
				<%@ page import="org.json.JSONObject" %>
				<%@ page import="java.io.*" %>
				<%@ page import="org.apache.http.impl.client.HttpClientBuilder" %>
				<%@ page import="org.apache.http.client.methods.HttpPost" %>
				<%@ page import="org.apache.http.entity.StringEntity" %>
				<%@ page import="org.apache.http.HttpResponse" %>
				<%@ page import="org.apache.http.client.HttpClient" %>
				<%@ page import="org.apache.commons.codec.digest.DigestUtils" %>
				<%

					String email = request.getParameter("email");
					String password = request.getParameter("password");
					String userAgent = DigestUtils.md5Hex(request.getHeader("User-Agent"));
					System.out.println(userAgent);
					if ((email!=null)&&(password!=null))
					{
						HttpClient httpClient = HttpClientBuilder.create().build();

						try {
							HttpPost req = new HttpPost("http://localhost:8083/v1/login");
							StringEntity params =new StringEntity("{\"email\":\""+email+"\",\"password\":\""+password+"\"} ");
							req.addHeader("content-type", "application/x-www-form-urlencoded");
							req.setHeader("User-Agent",userAgent);
							req.setEntity(params);
							HttpResponse res = httpClient.execute(req);
							BufferedReader br = new BufferedReader(new
									InputStreamReader(res.getEntity().getContent()));
							String line;
							StringBuffer sb = new StringBuffer();
							while ((line = br.readLine()) != null) {
								sb.append(line);
								sb.append('\r');
							}
							String responseString = new String(sb);
							JSONObject responseJSON = new JSONObject(responseString);
							int status = Integer.parseInt((String)responseJSON.get("status"));
							if (status==1){
								String token = (String) responseJSON.get("token");
								out.println(token);
								Cookie TokenCookie = new Cookie("token",token);
								out.println(TokenCookie.getValue());
								TokenCookie.setMaxAge(1800);
								response.addCookie(TokenCookie);
								out.println("Login Success!");
							} else {
								out.println("Login Failed, redirecting to login page");
								response.sendRedirect("login.jsp");
							}
						}catch (Exception ex) {
						}
					} else {
						response.sendRedirect("index.jsp");
					}
				%>
			</h1>
		</div>	
	</div>
	<script type="text/javascript" src="js/script.js"></script>
</body>
</html>