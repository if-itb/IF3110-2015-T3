function validateDelete(){
	if(confirm("Are you sure wanna delete this question?")){
		return true;
	}else{
		return false;
	}
}

function validasi(form){
	if(form.name.value){
		form.name.setAttribute("class", "textForm");
		if(validateEmail(form.email.value)){
			form.email.setAttribute("class", "textForm");
			if(form.topic.value){
				form.topic.setAttribute("class", "textForm");
				if(form.content.value){
					form.content.setAttribute("class", "textArea");
					return true;
				}else{
					form.content.setAttribute("class", "textArea errorForm");
					form.content.setAttribute("placeholder","Your Content blank");
					return false;
				}
			}else{
				form.topic.setAttribute("class", "textForm errorForm");
				form.topic.setAttribute("placeholder","Your Topic blank");
				return false;
			}
		}else{
			form.email.setAttribute("class", "textForm errorForm");
			form.email.value= "";
			form.email.setAttribute("placeholder","Your Email invalid");
			return false;
		}
	}else{
		form.name.setAttribute("class", "textForm errorForm");
		form.name.setAttribute("placeholder","Your Name blank");
		return false;
	}
}

function validateEmail(email) {
    var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
    return re.test(email);
}

var x = document.getElementById('closePopUp');
if (x != null){
	x.addEventListener("click",function(){
		var myElement = document.querySelector(".popUp");
		myElement.style.display = "none";
	});
}

var el = document.querySelector('.js-fade');
if(el != null){
    if (el.classList.contains('is-paused')){
        el.classList.remove('is-paused');
    }
}














