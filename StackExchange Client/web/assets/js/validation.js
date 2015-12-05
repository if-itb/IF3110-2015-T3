function validateRegisterForm() {
   var name = document.forms["register"]["name"].value;
    var email = document.forms["register"]["email"].value;
    var pass = document.forms["register"]["pass"].value;
    var passcheck = document.forms["register"]["passcheck"].value;
  
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    
    if (name === null || name === "") {
        alert("Name must be filled out.");
        return false;
    } else if (email === null || email === "") {
        alert("Email must be filled out.");
        return false;
    } else if (pass === null || pass === "") {
        alert("Password must be filled out.");
        return false;
    } else if (passcheck === null || passcheck === "") {
        alert("Please confirm the password.");
        return false;
    }
    else if (!filter.test(email)) {
        alert('Please provide a valid email address');
        return false;
    }
        
    else if (pass !== passcheck){
        alert('Password does not match');
        return false;
    }
    
    else {
        return true;
    }
}

function validateLoginForm() {
    var email = document.forms["login"]["email"].value;
    var pass = document.forms["login"]["pass"].value;
    
    if (email === null || email === "") {
        alert("Email must be filled out.");
        return false;
    } else if (pass === null || pass === "") {
        alert("Password must be filled out.");
        return false;
    }
    else {
        return true;
    }
}

function validateAnswerForm() {
    var content = document.forms["answer"]["content"].value;

    if (content === null || content === "") {
        alert("Answer content must be filled out.");
        return false;
    }
    else {
        return true;
    }
}

function validateQuestionForm() {
    var content = document.forms["question"]["content"].value;
    var topic = document.forms["question"]["topic"].value;

    if (content === null || content === "") {
        alert("Question content must be filled out.");
        return false;
    }
    else if (topic === null || topic === ""){
        alert("Question topic must be filled out.");
        return false;
    }
    else {
        return true;
    }
}