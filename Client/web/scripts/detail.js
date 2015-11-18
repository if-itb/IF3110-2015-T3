(function(){

    // Handler For Vote Question
    var qVote =document.getElementsByClassName("qVote");
    for(var i=0;i<qVote.length;i++) {
        qVote[i].onclick = function () {
            // AJAX request
            var $this = this;
            var http = new XMLHttpRequest();
            var url = "VoteQuestion";
            var qid = this.id;
            console.log("Id voted : " + qid);
            var params;
            // Set parameter AJAX
            if ($this.className.indexOf("up")!==-1)
            {
                params = "qid=" + qid + "&operation=up";
            }
            else
            {
                params = "qid=" + qid + "&operation=down";
            }

            http.open("POST", url, true);
            http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            http.setRequestHeader("Content-length", params.length);
            http.setRequestHeader("Connection", "close");

            http.onreadystatechange = function () {
                if (http.readyState === 4) {
		    if (http.status === 200){
			var newVote = http.responseXML.getElementsByTagName("new-vote")[0].childNodes[0].nodeValue;
			getSibling($this,"qVoteVal").innerHTML = newVote;
		    }
		    else
		    {
			window.location.href = "auth.html";
		    }
                }
		
            };
            http.send(params);
        };
    }
 
    
    // Handler For Vote Answer
    var aVote =document.getElementsByClassName("aVote");
    for(var i=0;i<aVote.length;i++) {
        aVote[i].onclick = function () {
            
	    // AJAX request
            var $this = this;
            var http = new XMLHttpRequest();
            var url = "VoteAnswer";
            var aid = this.id;
            console.log("Id voted : " + aid);
	    var params;

	    // Set parameter AJAX
            if ($this.className.indexOf("up")!==-1)
            {
                params = "aid=" + aid + "&operation=up";
            }
            else
            {
                params = "aid=" + aid + "&operation=down";
            }

            http.open("POST", url, true);
            http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            http.setRequestHeader("Content-length", params.length);
            http.setRequestHeader("Connection", "close");

            http.onreadystatechange = function () {
                if (http.readyState === 4 && http.status === 200) {
		    if(http.responseXML){
			var newVote = http.responseXML.getElementsByTagName("new-vote")[0].childNodes[0].nodeValue;
			getSibling($this,"voteVal").innerHTML = newVote;
		    }
                }
		else
		{
		    window.location.href = "auth.html";
		}
            };
            http.send(params);
        };
    }
    
    function getSibling (node, className) {
        var siblings = node.parentNode.children;
        for (var i = 0; i < siblings.length; i++)
        {
            if (siblings[i].className === className) {
                return siblings[i];
            }
        }
        return "None";
    };
    
}());

