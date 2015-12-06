<%-- 
    Document   : ask
    Created on : Nov 16, 2015, 11:23:02 PM
    Author     : chairuniaulianusapati
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href='css/style.css'/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">    
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>    
        <nav class="light-blue lighten-1" role="navigation">
            <div class="nav-wrapper container">
                
            <% out.write("<a id='logo-container' href='index.jsp' class='brand-logo'>Home</a>");%>
            <%
                Cookie[] cookieArray = request.getCookies();
                String theCookie = "null";
                if (cookieArray != null){
                    for (int i = 0; i < cookieArray.length; i++){
                        if(cookieArray[i].getName().equals("access_token")){
                            theCookie = cookieArray[i].getValue();
                            break;
                        }
                    }
                }
                if (theCookie.equals("null")){
                    String border = "<ul class='right hide-on-med-and-down'>"
                                        + "<li><a href='login.jsp'>Login</a></li>"
                                        + "<li><a href='register.jsp'>Register</a></li>"
                                    + " </ul>" + 
                                    "<ul id='nav-mobile' class='side-nav'>"
                                        + "<li><a href='login.jsp'>Login</a></li>"
                                        + "<li><a href='register.jsp'>Register</a></li>"
                                    + " </ul>";
                    out.write(border);
                } else {
                    com.wbd.rgs.RegisterWS_Service service = new com.wbd.rgs.RegisterWS_Service();
                    com.wbd.rgs.RegisterWS port = service.getRegisterWSPort();
                    
                    java.lang.String accessToken = theCookie;
                    java.lang.String result = port.getUsername(accessToken);
                    String border = "<ul class='right hide-on-med-and-down'>"
                                        + "<li>" + result + "</li>"
                                        + "<li><a href='signout.jsp'>Sign Out</a></li>"
                                    + " </ul>" + 
                                    "<ul id='nav-mobile' class='side-nav'>"
                                        + "<li>" + result + "</li>"
                                        + "<li><a href='signout.jsp'>Sign Out</a></li>"
                                    + " </ul>";
                    out.write(border);
                }
            %>
            <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
            </div>
        </nav>  
        <script>
            $(document).ready(function(){

              // Initialize collapse button
              $(".button-collapse").sideNav();
            });
        </script>
        <title>Simple StackExchange</title>
    </head>
    <body>
        <div class="container">
            <br><br>
            <h1 class="header center orange-text">Update your Request!</h1>
            <br><br>
        </div>
       
    <%
    try {
	com.wbd.qst.QuestionWS_Service service = new com.wbd.qst.QuestionWS_Service();
	com.wbd.qst.QuestionWS port = service.getQuestionWSPort();
	 // TODO initialize WS operation arguments here
        int qid = Integer.parseInt(request.getParameter("id"));
        String question_idLama = "";
        String topicLama = "";
        String contentLama = "";
        if(request.getParameter("id") != null){
            java.util.List<com.wbd.qst.Question> result = port.getQuestionbyID(qid);
            question_idLama = String.valueOf(result.get(0).getIDQ());
            topicLama = result.get(0).getQuestionTopic();
            contentLama = result.get(0).getContent();
        }
        String askForm =  
            "<div class='row'>" +
            "<form name='editForm' action='updateQuestion.jsp?id=" + question_idLama +"' onsubmit='return validateQuestion()' method='post'>"
                +"<input value='"+question_idLama+"' type='hidden' name='question_id'>"
                + "<div class='row'><div class='input-field col s12'>" 
                +"<input value='"+topicLama+"' type='text' class='form-text' name='topic' placeholder='The question topic goes here...' required><br>"
                + "<label for='topic' data-error='wrong' data-success='right'>Question Topic</label></div></div>"
                + "<div class='row'><div class='input-field col s12'>" 
                +"<textarea name='textarea1' class='materialize-textarea' placeholder='The content goes here...' required>" + contentLama +"</textarea><br>"
                + "<label for='textarea1' data-error='wrong' data-success='right'>Content</label> </div> </div>"
                +"<button id='button-post' class='btn waves-effect waves-light' type='submit'>Edit<i class='material-icons right'>send</i></button>"
            +"</form>"
        ;
        out.write(askForm);
    } catch (Exception ex) {
        out.write("exception");
	// TODO handle custom exceptions here
    }
    %>

    </body>
</html>



