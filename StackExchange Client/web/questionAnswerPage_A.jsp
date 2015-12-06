<%-- 
    Document   : questionAnswerPage_A
    Created on : Nov 24, 2015, 9:48:38 AM
    Author     : Marco Orlando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
        <%-- start web service invocation --%>
    <%-- start web service invocation --%>
    <%

	wsmodel.WS_Service service = new wsmodel.WS_Service();
	wsmodel.WS port = service.getWSPort();
	 // TODO initialize WS operation arguments here
	int questionId = Integer.parseInt(request.getParameter("q_id"));
	// TODO process result here
	java.util.List<wsmodel.AnswerClass> answerList = port.getAnswerByQID(questionId);
	int answerNumber = answerList.size();
    %>
    <%-- end web service invocation --%>



    
  
    <div ng-controller="answerCtrl">
        <div class='answerItem' ng-repeat="answer in Answers">
            <div class ='questionAnswerBody'>
                <div class ='questionAnswerVote' >
                    <div class='questionAnswerVoteUpArrow'>
                        <a ng-click="voteUpAnswer(answer.answer_id)" href=""><img src='icons/upArrow.png' width='30' height='30'></a> 
                    </div>
                    <div class='questionAnswerVoteNumber' <%--id='voteAnswer$answerId'--%>>
                           {{answer.vote}} 
                    </div>
                    <div class='questionAnswerVoteDownArrow'>
                        <a  ng-click="voteDownAnswer(answer.answer_id)" href=""><img src='icons/downArrow.png' width='30' height='30'></a> 
                    </div>
                </div>
                <div class ='questionAnswerContent'>
                    {{ answer.content }}
                </div>
            </div>
            <span class='questionAnswerFooter'>answered by {{answer.userID}} at {{answer.date}}</span>
        </div>
    </div>
