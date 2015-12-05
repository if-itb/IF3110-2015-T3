<!-- CSS -->
        <link rel="stylesheet" href="assets/fonts/Roboto">
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="assets/css/form-elements.css">
        <link rel="stylesheet" href="assets/css/style.css">
        
        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
        
         <!-- Javascript -->
        <script src="assets/js/jquery-1.11.1.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/jquery.backstretch.min.js"></script>
        <script src="assets/js/scripts.js"></script>
        
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
				break;
			}
		}

		if((access_token != null) && (access_token.length()>0)){
			//check access_token validity to server
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
		            if(token == null){
		            	
		            } else {
		            	alert ("Token berhasil di regenerate");
		            }
		        }
		    });
		}
		function checkToken(){
			var tokenData = {access_token:"<%= access_token %>"}
			var checkTokenUrl = "http://localhost:8081/REST-WS/rest/token-validity";
			$.ajax({
                url: checkTokenUrl,
                data: tokenData,
                dataType: "json",
                type: "POST",
                crossDomain: true,
                success: function(data) {
                    var valid = data.valid;
                    if(valid == 1){
                    	window.location.href = "index.jsp";
                    } else if (valid == 0){
                    	alert("Token Expired");
                	  	regenerateToken();
                	  	window.location.href = "index.jsp";
                    }
                },
                error: function(jqxhr, status, errorMsg) {
                    alert(status + ": " + errorMsg);
                }
            });
		}
		$(document).ready(function(){
		    checkToken();
		});
		</script>
		
		
		<%
	}
      
  }else{
      out.println("<h2>No cookies founds</h2>");
  }
%>
        <script>
            $(document).ready(function(){
                var url = "http://localhost:8081/REST-WS/rest/token";
                $("#submitBtn").click(function(e) {
                    e.preventDefault();
                    var formData = $("#loginForm").serialize();
                    $.ajax({
                        url: url,
                        data: formData,
                        dataType: "json",
                        type: "POST",
                        crossDomain: true,
                        success: function(data) {
                            var token = data.access_token;
                            document.cookie="access_token="+token+"; expires=null";
                            if(token == null)
                            	$(".error").replaceWith( "Username and password not match" );
                            else {
                            	alert ("Selamat Anda berhasil sign in");
                            	window.location.href = "index.jsp";
                            }
                            	

                        },
                        error: function(jqxhr, status, errorMsg) {
                            alert(status + ": " + errorMsg);
                        }
                    });
                });
            });
        </script>