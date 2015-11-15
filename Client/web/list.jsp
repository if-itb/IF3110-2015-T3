<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF - 8' >
    <title>Stack Exchange</title>
    <link href='https://fonts.googleapis.com/css?family=Josefin+Slab:400,700italic,300' rel='stylesheet' type='text/css'>
    <link rel='stylesheet' href='style.css'>
</head>

<body>
<div class='header'><a href='Home'><h1>Simple StackExhange</h1></a></div>
<div class='container clearfix'>
<form class='searchForm clearfix' action='php/Search.php' method='POST'>
        <div class='searchInput'>
        <input  name='keyword' type='text' placeholder='Keyword Pencarian'/>
        <p class='askHere'>Cannot find what you are looking for ? <a href='Create.html'>Ask here</a></p>
        </div>
        <button class='searchBtn' type='submit'>Search</button>
    </form>
<h4>Recently Answered Questions</h4>
<%=(String)request.getAttribute("username")%>

<!--<div class='table'>
    <div class='row clearfix'>
for(Question question : questions) {
    <div class='elemValue'>
        <span>" + question.getVotes() + "</span>
        <span class='vote'>Votes</span>
    </div>

    <div class='elemAnswer'>
        <span>" + question.getAnswerCount() + "</span>
        <span class='ans'>Answers</span>
    </div>

    <div class='elemQ'>
        <div class='elemQuestion'>
            <a href='detail?idDetail="+question.getQid()+"'><span class='topic'>" + question.getQtopic() + "</span></
    );
    uestion.getQcontent(
        </div>

        <div class='elemAuthor'>
            <span class='askedBy'>Asked By:</span>
            <div class='author'>
                <span class='name'>" + question.getName() + "</span>
                <a href='edit?idEdited="+ question.getQid() +"&fromDetail=0'> <span class='edit'>Edit</span></a>
                <a href='delete?idDeleted="+ question.getQid() +"'> <span class='delete'>Delete</span></a>
            </div>
        </div>
    </div>
}-->
</div>
</div>
</body>
</html>