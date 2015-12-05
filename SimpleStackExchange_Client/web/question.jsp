<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="tool.Util" %>

<html lang="en"><head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="assets/img/favicon.ico">

    <title>Simple StackExchange</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">

  </head>

  <body>
      
      <jsp:include page="template/navbar.jsp">
        <jsp:param name="isLogin" value="<%=Util.isLogin(request)%>"/>
    </jsp:include>
    
    <jsp:include page="template/userinfo.jsp">
        <jsp:param name="isLogin" value="<%=Util.isLogin(request)%>"/>
        <jsp:param name="userEmail" value="<%="test"%>"/>
    </jsp:include>
      
      <div class="container">
        <c:if test="${statustoken == -1}" >
            <div class="alert alert-danger" role="alert">Your token has been expired!</div>
        </c:if>

        <c:if test="${statustoken == 0}" >
            <div class="alert alert-danger" role="alert">Your token is invalid!</div>
        </c:if>
        <div class="row">
             
            <div class="col-sm-12">
                <h2>
                    <a href="${pageContext.request.contextPath}/question?qid=${question.getKey().getQid()}">
                    ${question.getKey().getTopic()}
                    </a>
                </h2>
                <hr>
            </div>
        </div>
           
            <div class="question-item row">
            <div class="col-sm-1 ">
                <c:set var="uid" value="${question.getKey().getUid()}"/>
                <c:set var="qid" value="${question.getKey().getQid()}"/>
                <% if (Util.isLogin(request)) { %>
                    <div class="btn-group-vertical" role="group" aria-label="...">
                        <form action="QuestionVote" method="POST">
                        <input name="qid" type="hidden" value="${question.getKey().getQid()}" />
                        <input name="value" type="hidden" value="1" />
                        <button type="submit" class="btn btn-success
                                <% 
                                int qid = (Integer)pageContext.getAttribute("qid");
                                int uid = Util.getUid(request);
                                if(Util.hasVotedQuestion(qid, uid, 1)) {
                                out.print("disabled");
                                }
                                %>
                                ">
                            <span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span>
                        </button>
                        </form>
                        
                        <div class="text-center well-lg">${question.getKey().getCountvotes()}</div>
                        <form action="QuestionVote" method="POST">
                        <input name="qid" type="hidden" value="${question.getKey().getQid()}" />
                        <input name="value" type="hidden" value="-1" />
                        <button type="submit" class="btn btn-danger
                                 <% 
                                if(Util.hasVotedQuestion(qid, uid, -1)) {
                                out.print("disabled");
                                }
                                %>
                                ">
                            <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
                        </button>
                        </form>
                    </div>
                <%} else {%>
                    <div class="btn-group-vertical" role="group" aria-label="...">
                        <button type="submit" class="btn btn-success disabled">
                            <span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span>
                        </button>
                        <div class="text-center well-lg">${question.getKey().getCountvotes()}</div>
                        <button type="submit" class="btn btn-danger disabled">
                            <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
                        </button>
                    </div>
                <%}%>
                    
            </div><!-- end of col-sm-1 -->
          
            <div class="col-sm-11">
            <div class="panel panel-default">
                <div class="panel-body">
                    <p>
                        ${question.getKey().getContent()}
                    </p>
                  <span class="pull-right">
                      <button type="button" class="btn btn-default transparent">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${question.getValue()} at ${question.getKey().getCreatedtime()}
                      </button>
                      
                      <c:set var="uid" value="${question.getKey().getUid()}"/>
                    <% if(Util.isAuthUser(request,(Integer)pageContext.getAttribute("uid"))){ %>  
                      <button type="button" class="btn btn-warning " aria-label="Edit">
                        <a class="glyphicon glyphicon-pencil white" aria-hidden="true" href="edit?qid=${question.getKey().getQid()}&save=0"></a>
                      </button>
                      <button type="button" class="btn btn-danger" aria-label="Delete">
                        <a class="glyphicon glyphicon-trash white" aria-hidden="true" href="delete?qid=${question.getKey().getQid()}"></a>
                      </button>
                  <% }%>
                  </span>
                </div>
               
            </div>
        </div><!-- end of col-sm-11 -->
        <div class="col-sm-12">
            <h2>
                ${question.getKey().getCountanswers()} Answers
            </h2>
            <hr>
        </div>
        <c:forEach items="${answers}" var="answer">
        <div class="row">
            <div class="col-sm-1 col-sm-offset-1">
                
                <c:set var="aid" value="${answer.getKey().getAid()}"/>
                <% if (Util.isLogin(request)) { %>
                
                    <div class="btn-group-vertical" role="group" aria-label="...">
                        <form method="POST" action="AnswerVote">
                        <input type="hidden" name="aid" value="${answer.getKey().getAid()}" />
                        <input type="hidden" name="qid" value="${answer.getKey().getQid()}" />
                        <input type="hidden" name="value" value="1" />
                        <button type="submit" class="btn btn-success
                                <%
                                int aid = (Integer)pageContext.getAttribute("aid");
                                int uid = Util.getUid(request);
                                if(Util.hasVotedAnswer(aid, uid, 1)) {
                                out.print("disabled");
                                }
                                %>
                                ">
                            <span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span>
                        </button>
                        </form>
                        <div class="text-center well-lg">${answer.getKey().getCountvotes()}</div>
                        <form method="POST" action="AnswerVote">
                        <input type="hidden" name="aid" value="${answer.getKey().getAid()}" />
                        <input type="hidden" name="qid" value="${answer.getKey().getQid()}" />
                        <input type="hidden" name="value" value="-1" />
                        <button type="submit" class="btn btn-danger
                                 <%
                                if(Util.hasVotedAnswer(aid, uid, -1)) {
                                out.print("disabled");
                                }
                                %>
                                ">
                            <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
                        </button>
                        </form>
                    </div>
                <%} else {%>
                    <div class="btn-group-vertical" role="group" aria-label="...">
                        <button type="submit" class="btn btn-success disabled">
                            <span class="glyphicon glyphicon-chevron-up" aria-hidden="true"></span>
                        </button>
                        <div class="text-center well-lg">${answer.getKey().getCountvotes()}</div>
                        <button type="submit" class="btn btn-danger disabled">
                            <span class="glyphicon glyphicon-chevron-down" aria-hidden="true"></span>
                        </button>
                    </div>
                <%}%>
            </div><!-- end of col-sm-1 -->
            <div class="col-sm-10">
            <div class="panel panel-default">
                <div class="panel-body">
                    <p>
                        ${answer.getKey().getContent()}
                    </p>
                  <span class="pull-right">
                      <button type="button" class="btn btn-default transparent">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${answer.getValue()} at ${answer.getKey().getCreatedtime()}
                      </button>
                  </span>
                </div>
               
            </div>
        </div><!-- end of col-sm-10 -->
        </div>
        </div><!-- end of question-item -->

        </c:forEach>
        
      <div class="col-sm-12">
            <h2>
                Answer Question!
            </h2>
            <hr>
        </div>
      
          <% if (Util.isLogin(request)) { %>
          <form class="form-horizontal" role="form" action="AnswerCreate" method="POST">
                <div class="form-group">
                    
                    <div class="col-md-12">
                    <textarea class="form-control" rows="5" id="answer" name="answer"></textarea>
                    </div>
                  </div>
              <input name="qid" value="${question.getKey().getQid()}" type="hidden" />
                <div class="form-group"> 
                  <div class="col-md-12">
                    <button type="submit" class="btn btn-info btn-block">Post</button>
                  </div>
                </div>
              </form>
            <%} else {%>
                <div class="col-md-12 alert alert-danger" role="alert">
                    Please
                    <span>
                        <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/login.jsp" role="button">Login now!</a>
                    </span> to answer the question!
                </div>
            <%}%>
      
      <footer class="col-md-12">
          <p class="text-center">© Simple StackExchage 2015</p>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>

</body></html>
