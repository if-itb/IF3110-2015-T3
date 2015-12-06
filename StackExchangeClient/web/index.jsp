<%-- 
    Document   : index
    Created on : Nov 12, 2015, 12:00:30 PM
    Author     : User
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/style.css"/>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">    
        <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.3/js/materialize.min.js"></script>    
        <nav class="light-blue lighten-1" role="navigation">
            <div class="nav-wrapper container">

            <% out.write("<a id='logo-container' href='index.jsp' class='brand-logo'>LeeMinCha©</a>");%>
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
                    //out.println("COOKIENYAH: "+theCookie);
                    String border = "<ul class='right hide-on-med-and-down'>"
                                        + "<li>Welcome, " + result + "</li>"
                                        + "<li><a href='signout.jsp?token=" + accessToken +"'>Sign Out</a></li>"
                                    + " </ul>" + 
                                    "<ul id='nav-mobile' class='side-nav'>"
                                        + "<li>Welcome, " + result + "</li>"
                                        + "<li><a href='signout.jsp?token="+ accessToken + "'>Sign Out</a></li>" //Jelek, ntar diganti
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
            <h1 class="header center orange-text">Simple Stack Exchange</h1>
            <div class="row center">
                <h5 class="header col s12 light">Cannot find what you're looking for?</h5>
            </div>
            <div class="row center">
                <% out.write("<a href='ask.jsp' id='download-button' class='btn-large waves-effect waves-light orange'>Ask Here</a>");%>
            </div>
            <div class="row center">
                <h5 class="header col s12 light">or</h5>
            </div>
        </div>
  
        <div class="row">
            <div class="orange col s8 push-s2">
                <% out.write("<form name='searchForm' action='index.jsp' method='POST'>");%>
                    <div class="input-field">
                        <input name="search_key" id="search" type="search" placeholder="Search here" required>
                        <i class="material-icons">close</i>
                    </div>
               </form>
            </div>       
        </div>
        <br><br>

        <%
    try {
        
	com.wbd.qst.QuestionWS_Service service = new com.wbd.qst.QuestionWS_Service();
	com.wbd.qst.QuestionWS port = service.getQuestionWSPort();
        com.wbd.ans.AnswerWS_Service service2 = new com.wbd.ans.AnswerWS_Service();
	com.wbd.ans.AnswerWS port2 = service2.getAnswerWSPort();
        com.wbd.qst.QuestionWS_Service service3 = new com.wbd.qst.QuestionWS_Service();
	com.wbd.qst.QuestionWS port3 = service3.getQuestionWSPort();
	// TODO process result here
        java.util.List<com.wbd.qst.Question> result;
        //if(request.getParameter("search_key") != null){
            //result = port.searchQ(request.getParameter("search_key"), request.getParameter("search_key"));
            //out.write("<h4 class='header center-align orange-text'> Search Result </h2>");
        //}
        //else{
            result = port.retrieveQ();
            out.write("<h4 class='header center-align cyan-text'> Recently Asked Questions </h2>");
        //}
    out.write("<ul class='collection'>");
    for(int i = 0; i < result.size() ; i++){
            String vote;
            java.util.List<com.wbd.ans.Answer> result2 = port2.getAnswerByQID(result.get(i).getIDQ());
            java.lang.String result3 = port3.getNama(result.get(i).getIDQ());
            int count = result2.size();
            if(result.get(i).getVote() > 1 || result.get(i).getVote() < -1){
                vote = "Votes";
            }
            else{
                vote = "Vote";
            }
            String Content = result.get(i).getContent();
            if(Content.length()>300){
                Content = Content.substring(0, 299) + "...";
            }
            String question ="<div class='bquestion-vote'>" 
                        +result.get(i).getVote()
                        +"<br>"
                +vote
                    +"</div>"
                    +"<div class='bquestion-answer'>" 
                + count
                +"<br>"
                +"Answer"
            +"</div>"
            +"<div class='bquestion-content'>" 
                        +"<a id='color-black' href=question.jsp?id=" + result.get(i).getIDQ() + ">" + result.get(i).getQuestionTopic() + "</a>"
                        +"<br>"
                        + Content
                        +"<br><br>"
                    +"</div>"
                    +"<div class='bquestion-identity'>" 
                        +"asked by "
                        +"<a id='color-blue'>"
                            +result3
                        +"</a>"
                        +" | "
                        +"<a id='color-orange' href=edit.jsp?id=" + result.get(i).getIDQ() 
                            + ">edit"
                        +"</a>"
            +" | "
                        +"<a id='color-red' href=delete.jsp?id=" + result.get(i).getIDQ()  +">"
                            +"delete"
            +"</a>"+"</div><br>";


        String start = "<li class='collection-item avatar'><i class='material-icons circle'>folder</i>";
        String end = "<br><br><br><a href='' class='secondary-content'><i class='material-icons'>grade</i></a></li>";

            out.write(start + "<br>" + question + end);
        }
        out.write("</ul>");
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    %>
    <script>
            $(document).ready(function(){
                        console.log("COOKIE BARU : " + getCookie("access_token"));
            });

        function getCookie(cname) {
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for(var i=0; i<ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0)==' ') c = c.substring(1);
                if (c.indexOf(name) == 0) return c.substring(name.length,c.length);
            }
            return "";
        }

                                
    </script>
    </body>
</html>