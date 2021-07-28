$(document).ready(function(){
});

function goSave(){
	var name = $("[name=name]").val();
	var email = $("[name=email]").val();
	var email1 = $("#email1").val();
	var email2 = $("#email2").val();
	
	if(name == "" || name == null){
		alert("이름을 입력해주세요.");
		$("[name=name]").focus();
		return;
	}
	if(email == "" || email == null){
		alert("이메일을 입력해주세요.");
		$("#email1").focus();
		return;
	}
	if(email1 == "" || email1 == null){
		alert("이메일을 입력해주세요.");
		$("#email1").focus();
		return;
	}
	if(email2 == "" || email2 == null){
		alert("이메일을 입력해주세요.");
		$("#email2").focus();
		return;
	}
	var params = $("[name=detail_form]").serialize();
	rAjaxCall.async("/member/ajaxUpdateMember", 'POST', params, function(r) {
		alert(r.content);
		location.reload();
	});
}

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
	}
	rAjaxCall.async("/member/ajaxUpdatePasssword", 'POST', params, function(r) {
		if(r.result){
			alert(r.content);
			$("#password").val("");
			$("#newPassword").val("");
			$("#newPasswordChk").val("");
			popClose('pop_state');
		}else{
			alert(r.content);
			$("#password").val("");
			$("#newPassword").val("");
			$("#newPasswordChk").val("");
			$("#password").focus();
		}
	});
}
// 이메일 도메인 바꿔주기
function domainChange(){
	var domain = $("#emailDomain").val();
	if(domain == ""){
		$("#email2").css("background-color","");
		$("#email2").removeAttr("readonly");
		$("#email2").val("");
	}else{
		$("#email2").val(domain);
		$("#email2").css("background-color", "#EEEEEE");
		$("#email2").attr("readonly", true);
	}
	mailCheck();
}
function mailCheck(){
	var email1 = $("#email1").val();
	var email2 = $("#email2").val();
	var email = email1+"@"+email2;
	$("[name=email]").val(email);
}