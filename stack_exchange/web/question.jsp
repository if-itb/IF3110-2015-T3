<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE HTML>
<html>
<head>
    <title>StackExchange</title>
    <link rel="stylesheet" href="../assets/css/style.css">
</head>
<body>
<div class="container">
    <div class="header">
        <h1 class="center">StackExchange</h1>
    </div>
    <div id="question-page">
        <h2 class="underline">TES</h2>
        <div class="question" id="question-   ">
            <div class="row">
                <div class="col_vote">
                    <a title="Click to Upvote" href="     " onclick="   " id="increase-vote">
                        <img src="../img/up.png" width="32" height="32"><br>
                    </a>
                    <span id="question-vote-count-    "><font size = "5" color ="blue">10</font></span><br>
                    <a title="Click to Downvote" href="    " onclick="   " id="decrease-vote">
                        <img src="../img/down.png" width="32" height="32">
                    </a>
                </div>
                <div class="col_content">
                    <p>
                        CONTENT ASDASDASD
                    </p>
                </div>
            </div>
            <div class="controls" style="border-bottom:0px" align="right">
                asked by <span class="name"><font color="blue">ALEXANDER SUKONO</font></span> &lt;<span class="email">TES@GMAIL.COM</span>
                at <span class="create-date">12-10-1965</span> |
                <span class="link_edit"><a class="link_edit" title="Click here to edit" href="      ">edit</a></span> |
                <span class="link_delete"><a class="link_delete" title="Click here to delete" href="      " onclick="      ">delete</a></span>
            </div>
        </div>
    </div>

    <div class="answers">
        <h2 class="underline">2 Answer  </h2>
        <!-- <?php foreach($answers as $answer) : ?> -->
        <div class="answer underline" style="width:100%" id="answer-    ">
            <div class="row">
                <div class="col_vote">
                    <a title="Click to Upvote" href="    " onclick="       " id="increase-vote">
                        <img src="../img/up.png" width="32" height="32"><br>
                    </a>
                    <span id="answer-vote-count-    "><font size = "5" color ="blue">5</font></span><br>
                    <a title="Click to Downvote" href="    " onclick="   " id="decrease-vote">
                        <img src="../img/down.png" width="32" height="32">
                    </a>
                </div>
                <div class="col_content">
                    <p>
                        CONTENT AAAA
                    </p>
                </div>
            </div>
            <div class="controls" style="border-bottom:0px" align="right">
                answered by <span class="name"><font color="blue">JAWABAN</font></span> &lt;<span class="email">JAWAB@GMAIL.COM</span>
                at <span class="create-date">12-19-1999</span>
            </div>
        </div>
        <!--<?php endforeach; ?> -->
    </div>


    <div class="answer_form">
        <h2>Your Answer</h2>
        <form action="   " method="POST" onsubmit="  ">
            <input type="text" class="form" placeholder="Name" name="name" />
            <input type="text" class="form" placeholder="Email" name="email" />
            <textarea class="form" name="content"  rows="5" placeholder="Content"></textarea>
            <div class="right" style="margin-bottom:50px">
                <input type="submit" value="Post" >
            </div>
            <input type="hidden" name="question_id" value="   " />
        </form>
    </div>
</div>
</body>
</html>

