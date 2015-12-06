function validateEmail(email) {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return re.test(email);
};

function validate_AForm(){
    var a = document.forms["q_form"]["content"].value;
    console.log(a);
    if(a==null || a==''){
        alert("Content must be filled");
        return false;
    }
};

function validate_Register(){
	var a = document.forms["registerForm"]["fullname"].value;
	var b = document.forms["registerForm"]["username"].value;
	var c = document.forms["registerForm"]["email"].value;
	var d = document.forms["registerForm"]["password"].value;
    if(a==null || a==''){
        alert("Name must be filled");
        return false;
    }
    if(b==null || b==''){
        alert("Username must be filled");
        return false;
    }
    if(c==null || c==''){
        alert("Email must be filled");
        return false;
    }else if(!validateEmail(y)){
        alert("Email is not a valid email");
        return false;
    }
    if(d==null || d==''){
        alert("Password must be filled");
        return false;
    }
};

function validate_QForm(){
    var x = document.forms["q_form"]["name"].value;
    var y = document.forms["q_form"]["email"].value;
    var z = document.forms["q_form"]["topic"].value;
    var a = document.forms["q_form"]["content"].value;
    if(x==null || x==''){
        alert("Name must be filled");
        return false;
    }
    if(y==null || y==''){
        alert("Email must be filled");
        return false;
    }else if(!validateEmail(y)){
        alert("Email is not a valid email");
        return false;
    }
    if(z==null || z==''){
        alert("Topic must be filled");
        return false;
    }
    if(a==null || a==''){
        alert("Content must be filled");
        return false;
    }
};