
	<title><%= request.getParameter("pageTitle") %> | StackExchanges</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<script src="assets/js/jquery-1.11.3.min.js"></script>
    <script src="assets/js/jquery-migrate-1.2.1.min.js"></script>
    
    <!-- Favicon and touch icons -->
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
    
    <!-- js -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.dropotron.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/jquery.scrollgress.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="assets/js/main.js"></script>

	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
	<% if((request.getParameter("includeMainCss") == null) || ((request.getParameter("includeMainCss") != null) && (request.getParameter("includeMainCss").equals("true")))){ %>
	<link rel="stylesheet" href="assets/css/main.css" />
	<!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
	<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
	<% } %>
    <%
    if((request.getParameter("isNeedCookieCheck") == null) ||  ((request.getParameter("isNeedCookieCheck") != null) && (request.getParameter("isNeedCookieCheck").equals("true")))){
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
					break;
				}
			}
			
			if((access_token != null) && (access_token.length()>0)){
				String user_agent = request.getHeader("User-Agent");
				String ip_adr = request.getRemoteAddr();
				access_token += "#" + user_agent + "#" + ip_adr;
				//check access_token validity to server
				if((request.getParameter("needDeleteQuestion") != null) && (request.getParameter("needDeleteQuestion").equals("true"))){
					%>
					<jsp:include page="question_delete.jsp" flush="true">
						<jsp:param name="pageTitle" value="Simple StackExchange" />
						<jsp:param name="access_token" value="<%= access_token %>" />
					</jsp:include><%
				}
%>

<script>
function regenerateToken(){
	var regenerateTokenUrl = "http://localhost:8081/REST-WS/rest/token/regenerateToken";
	var tokenData = {access_token:'<%= access_token %>'}
	$.ajax({
        url: regenerateTokenUrl,
        data: tokenData,
        dataType: "json",
        type: "POST",
        success: function(data) {
        	var token = data.access_token;
            document.cookie="access_token="+token+"; expires=null";
            console.log(token);
            if(token == null){
            	console.log("null");
            } else {
            	alert ("Token berhasil di regenerate");
            }
        }
    });
}

function checkToken(check,allow){	
	var checkTokenUrl;
	var user_buttons = document.getElementsByClassName("user");
	var tokenData;
	if(check){
		console.log("masuk");
		checkTokenUrl= "http://localhost:8081/REST-WS/rest/token-validity/getQuestionAccessValidity"
		tokenData = {access_token:"<%= access_token %>",id_question: <%= request.getParameter("q_id") %>}
	}else{
		console.log("keluar");
		checkTokenUrl = "http://localhost:8081/REST-WS/rest/token-validity";
		tokenData = {access_token:"<%= access_token %>"}
	}
	
	//special case
	if(check && !allow){
		console.log("keluar");
		checkTokenUrl = "http://localhost:8081/REST-WS/rest/token-validity";
		tokenData = {access_token:"<%= access_token %>"}
		allow = true;
	}
	<%
	if(request.getParameter("validityUrl")!=null){%>
		checkTokenUrl += request.getParameter("validityUrl");
	<%}
	%>
	$.ajax({
              url: checkTokenUrl,
              data: tokenData,
              dataType: "json",
              type: "POST",
              success: function(data) {
                  var valid = data.valid;
                  console.log("valid = "+valid);
                  var id = data.id_user;
                  if (valid == 0){
              	  	alert("Token Expired");
              	  	regenerateToken();
                  }
                  if((valid == 1) || (valid == 0)){
                	   for (var i = 0; i < user_buttons.length; i ++) {
                		   user_buttons[i].style.display='block';
                		}
	               	   var element = document.getElementById('signin');
	               	   element.setAttribute('href','signout.jsp');
	               	   var str = $('a#signin').text('Sign Out');
	               	   $('a#register').remove();
	               	   $('.modify_'+id).show();
					   $('#navPanel nav a:nth-child(2)').remove();
					   $('#navPanel nav a:nth-child(2)').text('Sign Out');
					   $('#navPanel nav a:nth-child(2)').attr("href", "signout.jsp");
                  }
                  
                  if(valid == -1 && allow){
        	  		window.location.href = "index.jsp";
        	  		$('body').remove();
					alert("Anda tidak berhak mengakses");
                  }
                  
                  <% if((request.getParameter("needRedirectWhenNotValid") != null) && (request.getParameter("needRedirectWhenNotValid").equals("true"))){ %>
        	  			if(!valid){
        	  				window.location.href = "index.jsp";
	        	  			<% if((request.getParameter("messagetWhenNotValid") != null)){ %>
	        	  				alert("<%= request.getParameter("messagetWhenNotValid") %>");
	        	  			<% } %>
        	  			}
        	   	 <%}%>  
              }
          });
}
$(document).ready(function(){
    var check = '<%= request.getParameter("check") %>';
    console.log(check);
    var a = false;
    var c = false;
    if(check==1){
    	console.log("tipe 1");
    	a = false;
    	c = false;
    }
    else if(check==2){
    	console.log("tipe 2");
    	a = true;
    	c = true;
    }
    else if(check==3){
    	console.log("tipe 3");
    	a = true;
    	c = false;
    }
    else if(check==4){
    	a = false;
    	c = true;
    }
	checkToken(c,a);
});
</script>
	<%	} else {
			if(request.getParameter("errorCookieError") != null){
				String redirectUrl = request.getParameter("redirectUrlAuthorization");
				//redirect
				String site = new String(redirectUrl);
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site); 
			}
		}
	} else {
		if(request.getParameter("errorCookieEmpty") != null){
			String redirectUrl = request.getParameter("redirectUrlTokenEmpty");
			//redirect
			String site = new String(redirectUrl);
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site); 
		}
	}
}%>
