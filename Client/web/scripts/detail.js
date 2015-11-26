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
	
	    var invalid,expired,newVote, cantVote;
            http.onreadystatechange = function () {
                if (http.readyState === 4) {
		    if (http.status === 200){
			 invalid = http.responseXML.getElementsByTagName("invalid")[0];
			 expired = http.responseXML.getElementsByTagName("expired")[0];
			 cantVote = http.responseXML.getElementsByTagName("cantVote")[0];
			 newVote = http.responseXML.getElementsByTagName("new-vote")[0];
			if (newVote) {
			    getSibling($this, "qVoteVal").innerHTML = newVote.childNodes[0].nodeValue;
			} else if (expired) {
			    delete_cookie();
			    window.location.href = "expired";
			} else if (invalid) {
			    delete_cookie();
			    window.location.href = "invalid";
			} else {
			    console.log("Cant vote");
			}
		    }
		    else if(http.status === 401)
		    {
			delete_cookie();
			window.location.href = "auth";
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

            var invalid, expired, newVote, cantVote;
	    http.onreadystatechange = function () {
		if (http.readyState === 4) {
		    if (http.status === 200) {
			invalid = http.responseXML.getElementsByTagName("invalid")[0];
			expired = http.responseXML.getElementsByTagName("expired")[0];
			newVote = http.responseXML.getElementsByTagName("new-vote")[0];
			cantVote = http.responseXML.getElementsByTagName("cantVoteAnswer")[0];
			if (newVote) {
			    getSibling($this, "voteVal").innerHTML = newVote.childNodes[0].nodeValue;
			} else if (expired) {
			    delete_cookie();
			    window.location.href = "auth";
			} else if (invalid){
			    delete_cookie();
			    window.location.href = "auth";
			}

		    } else if (http.status === 401)
		    {
			delete_cookie();
			window.location.href = "auth";
		    }
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
    
    function delete_cookie() {

//	var name = "username=";
//	var cookies = document.cookie.split(';');
//	for (var i = 0; i < cookies.length; i++) {
//	    var cookie = cookies[i];
//	    console.log(cookies);
//	}
//	return "";
    } 

    
}());

