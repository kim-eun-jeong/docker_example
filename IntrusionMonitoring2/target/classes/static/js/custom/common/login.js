$(document).ready(function(){
	$('#loginId').focus();
});


function login() {
	if(!checkLoginForm()){
		return;
	}
	var param = {
		"id" : $("#loginId").val(),
		"password" : $("#loginPassword").val()
		,"_csrf" : $("#_csrf").val()
	};
	
	rAjaxCall.async("/actionLogin", 'POST', param, function(r) {
		if(r.result) {
			location.href = '/monitoring'; 
		} else {
			alert(r.content);
		}
	});	
}

function checkLoginForm() {
	if($("#loginId").val() == "") {
		alert("아이디를 입력 해 주세요.");
		$("#loginId").focus();
		return false;
	}
	
	if($("#loginPassword").val() == "") {
		alert("비밀번호를 입력 해 주세요.");
		$("#loginPassword").focus();
		return false;
	}
	
	return true;
}

function enterkey() {
    if (window.event.keyCode == 13) {
         login();
    }
}



