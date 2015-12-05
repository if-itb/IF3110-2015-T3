function prepare(){
    if (window.XMLHttpRequest) {
        // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    } else {  // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
};

function changeUp(status){
	if(status=="1"){
		return 0;
	}
	else{
		return 1;
	}
}

function changeDown(status){
	if(status=="-1"){
		return 0;
	}
	else{
		return -1;
	}
}

function VoteUp(bool,id,cookie){
    prepare();
    xmlhttp.onreadystatechange=function() {
        if (xmlhttp.readyState==4 && xmlhttp.status==200) {
            if(bool) {
            	var up = document.getElementById("q_up").src;
            	var res = up.substr(45);
            	var changed = changeUp(res.slice(0,-4));
                document.getElementById("q_vote"+id).innerHTML = xmlhttp.responseText;
                document.getElementById("q_up").src = "assets/img/up"+changed+".png";
                document.getElementById("q_down").src = "assets/img/down"+changed+".png";
            }
            else{
            	var up = document.getElementById("a_up"+id).src;
            	var res = up.substr(45);
            	var changed = changeUp(res.slice(0,-4));
                document.getElementById("vote"+id).innerHTML = xmlhttp.responseText;
                document.getElementById("a_up"+id).src = "assets/img/up"+changed+".png";
                document.getElementById("a_down"+id).src = "assets/img/down"+changed+".png";
            }
        }
    }
    if(bool){
    	xmlhttp.open("GET","vote/questionvoteup.jsp?id="+id,true);
    }
    else{
    	xmlhttp.open("GET","vote/answervoteup.jsp?id="+id,true);
    }
    xmlhttp.send();
};

function VoteDown(bool,id,cookie){
	prepare();
    xmlhttp.onreadystatechange=function() {
        if (xmlhttp.readyState==4 && xmlhttp.status==200) {
            if(bool) {
            	var down = document.getElementById("q_down").src;
            	var res = down.substr(47);
            	console.log(res.slice(0,-4));
            	var changed = changeDown(res.slice(0,-4));
                document.getElementById("q_vote"+id).innerHTML = xmlhttp.responseText;
                document.getElementById("q_up").src = "assets/img/up"+changed+".png";
                document.getElementById("q_down").src = "assets/img/down"+changed+".png";
            }
            else{
            	var down = document.getElementById("a_down"+id).src;
            	var res = down.substr(47);
            	console.log(res.slice(0,-4));
            	var changed = changeDown(res.slice(0,-4));
                document.getElementById("vote"+id).innerHTML = xmlhttp.responseText;
                document.getElementById("a_up"+id).src = "assets/img/up"+changed+".png";
                document.getElementById("a_down"+id).src = "assets/img/down"+changed+".png";
            }
        }
    }
    if(bool){
    	xmlhttp.open("GET","vote/questionvotedown.jsp?id="+id,true,cookie);
    }
    else{
    	xmlhttp.open("GET","vote/answervotedown.jsp?id="+id,true,cookie);
    }
    xmlhttp.send();
};