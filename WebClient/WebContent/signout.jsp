<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Sign Out</title>
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
	
	<%
	
	Cookie cookie = null;
	Cookie[] cookies = null;
	String access_token = null;
	// Get an array of Cookies associated with this domain
	cookies = request.getCookies();
	if( cookies != null ){
		for (int i = 0; i < cookies.length; i++){
			cookie = cookies[i];
			if(cookie.getName().equals("access_token")){
				access_token = cookie.getValue();
				String user_agent = request.getHeader("User-Agent");
				String ip_adr = request.getRemoteAddr();
				access_token += "#" + user_agent + "#" + ip_adr;
				break;
			}
		}
	
		if((access_token != null) && (access_token.length()>0)){
			//hapus token di server
%>
	
	
	<script>
		function deleteToken(){
			var signOutUrl = "http://localhost:8081/REST-WS/rest/token/signout";
			var tokenData = {access_token:"<%= access_token %>"};
			
			
			$.ajax({
	              url: signOutUrl,
	              data: tokenData,
	              dataType: "json",
	              type: "POST",
	              success: function(data) {
	                  
	              }
	          });
			
			
		}
	    $(document).ready(function(){
	    	deleteToken();
	    	//document.cookie = "access_token =;expires=Thu, 01 Jan 1970 00:00:00 UTC";
	    	window.location.href = "index.jsp";
	    });
	</script>
	
	<%
	
		} else {//Cookie tidak valid
			String redirectUrl = request.getParameter("index.jsp");
			//redirect
			String site = new String(redirectUrl);
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site); 
		}
	} else {//Tidak punya token
		String redirectUrl = request.getParameter("index.jsp");
		//redirect
		String site = new String(redirectUrl);
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site); 
	} %>
</head>
<body>
</body>
</html>