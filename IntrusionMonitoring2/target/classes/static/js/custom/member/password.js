$(document).ready(function(){
});

// 비밀번호 변경
function updatePassword(id){
	var password = $("#password").val();
	var newPassword = $("#newPassword").val();
	var newPasswordChk = $("#newPasswordChk").val();
	var reg_pwd = /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;
	
	if(password == ""){
		alert("현재 비밀번호를 입력해주세요.");
		$("#password").focus();
		return;
	}else if(newPassword == ""){
		alert("새 비밀번호를 입력해주세요.");
		$("#newPassword").focus();
		return;
	}else if(newPasswordChk == ""){
		alert("새 비밀번호 확인을 입력해주세요.");
		$("#newPasswordChk").focus();
		return;
	}else if (!reg_pwd.test(newPassword)) {
		alert("비밀번호 형식이 바르지않습니다.\n(영문,숫자를 혼합하여 6~20자 이내)");
		$("#newPassword").val("");
		$("#newPasswordChk").val("");
		$("#newPassword").focus();
		return;
	}else if(password == newPassword){
		alert("기존비밀번호와 같은비밀번호는 사용할 수 없습니다.");
		$("#newPassword").val("");
		$("#newPasswordChk").val("");
		$("#newPassword").focus();
		return;
	}else if(newPassword != newPasswordChk){
		alert("새비밀번호가 일치하지않습니다.\n다시 입력해 주십시오.");
		$("#newPassword").focus();
		return;
	}
	
	var params = {
			"id" : id
			,"password" : password
			,"newPassword" : newPassword
			,"passwordChangeYn" : "Y"
	}
	rAjaxCall.async("/member/ajaxUpdatePasssword", 'POST', params, function(r) {
		if(r.result){
			alert(r.content);
			location.href = '/dashboard';
		}else{
			alert(r.content);
			$("#password").val("");
			$("#newPassword").val("");
			$("#newPasswordChk").val("");
			$("#password").focus();
		}
	});
}