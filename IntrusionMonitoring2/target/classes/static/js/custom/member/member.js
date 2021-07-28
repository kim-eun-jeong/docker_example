$(document).ready(function(){
	 var getDetailStr = $('#listTable > tbody > tr').eq(0).attr("onClick");
	 if(typeof getDetailStr != "undefined"){
		 eval(getDetailStr,0);
	}
	 
});
function goPage(page) {
	$('[name=pageNo]').val(page);
	$('[name=list_form]').submit();
}

function getDetail(id,num){
	var tr = $("#listTable > tbody > tr");
	tr.removeClass("selected");
	if(num != "no"){
		tr.eq(num).attr("class","selected");
	}
	
	var param = {
		"id" : id
	};
	rAjaxCall.asyncH("/member/content/detail", 'POST', param, function(data) {
		$('#detailDiv').html("");
		$('#detailDiv').html(data);
		if(num != "no"){
			$(".tstyle_write > thead > tr > th > p").text("상세 정보");
		}else{
			$(".tstyle_write > thead > tr > th > p").text("회원 추가");
		}
	});	
}

function goIdCheck(id){
	var id = $("[name=id]").val();
	var validate_id = /^.*(?=.{5,15})(?=.*[0-9a-zA-Z]).*$/;
	if(id == "" || id == null){
		alert("ID를 입력해주세요.");
		$("[name=id]").focus();
		return;
	}
	if (!validate_id.test(id)) {
		alert("ID 형식이 바르지않습니다.\n(영문,숫자 5~15자 이내)");
		 $("[name=id]").focus();
		return;
	}
	
	var param = {
		"id" : id
	};
	rAjaxCall.async("/member/ajaxIdCheck", 'POST', param, function(r) {
		if(r.result){
			alert(r.content);
			$("#idCheck").val(true);
		}else{
			alert(r.content);
			$("[name=id]").val("");
			$("#idCheck").val(false);
		}
	});	
}

//비밀번호 변경
function resetPassword(id){
	if(confirm("초기화하시겠습니까 ?")){
		var params = {
				"id" : id
		}
		rAjaxCall.async("/member/ajaxResetPasssword", 'POST', params, function(r) {
			if(r.result){
				alert(r.content);
			}else{
				alert(r.content);
			}
		});
	}
	
}

//이메일 도메인 바꿔주기
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

function getList(mode){
	params = $("[name=list_form]").serialize();
	rAjaxCall.asyncH("/member/content/list", 'POST', params, function(data) {
		var cnt =$('#listTable > tbody ').children(".selected").index();
		
		$('#listDiv').html("");
		$('#listDiv').html(data);
		
		if(mode == "update"){
			var getDetailStr = $('#listTable > tbody > tr').eq(cnt).attr("onClick");
			 if(typeof getDetailStr != "undefined"){
				 eval(getDetailStr,0);
			}
		}else{
			var getDetailStr = $('#listTable > tbody > tr').eq(0).attr("onClick");
			if(typeof getDetailStr != "undefined"){
				 eval(getDetailStr,0);
			}
		}
	});	
}

function goSave(){
	var id = $("[name=id]").val();
	var idCheck = $("#idCheck").val();
	var validate_id = /^.*(?=.{5,15})(?=.*[0-9a-zA-Z]).*$/;
	var name = $("[name=name]").val();
	var password = $("[name=password]").val();
	var passwordCheck = $("#passwordCheck").val();
	var auth = $("[name=auth]").val();
//	var department = $("[name=department]").val();
	var email = $("[name=email]").val();
	var email1 = $("#email1").val();
	var email2 = $("#email2").val();
	var useYn = $("[name=useYn]").val();
	var mode = $("#passwordYn").val() == null || $("#passwordYn").val() == "" ? 'create' : 'update';
	
	if(id == "" || id == null){
		alert("ID를 입력해주세요.");
		$("[name=id]").focus();
		return;
	}
	if (!validate_id.test(id)) {
		alert("ID 형식이 바르지않습니다.\n(영문,숫자 5~15자 이내)");
		 $("[name=id]").focus();
		return;
	}
	if(idCheck == "false"){
		alert("아이디 중복확인이 필요합니다.");
		$("[name=id]").focus();
		return;
	}
	if(name == "" || name == null){
		alert("이름을 입력해주세요.");
		$("[name=name]").focus();
		return;
	}
	if(mode == "create"){
		if(password == "" || password == null){
			alert("비밀번호를 입력해주세요.");
			$("[name=password]").focus();
			return;
		}
	}
	
	if(password != "" && password != null){
		if(passwordCheck == "" || passwordCheck == null){
			alert("비밀번호 확인을 입력해주세요.");
			$("#passwordCheck").focus();
			return;
		}
		if(password  != passwordCheck){
			alert("비밀번호와 비밀번호 확인이 다릅니다. \n다시 입력해주세요.");
			$("#passwordCheck").focus();
			return;
		}
	}
	
	if(auth == "" || auth == null){
		alert("권한을 선택해주세요.");
		$("[name=auth]").focus();
		return;
	}
//	if(department == "" || department == null){
//		alert("소속을 선택해주세요.");
//		$("[name=department]").focus();
//		return;
//	}
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
	if(useYn == "" || useYn == null){
		alert("사용여부를 선택해주세요.");
		$("#useYn").focus();
		return;
	}
	if(confirm("저장하시겠습니까 ?")){
		var params = $("[name=detail_form]").serialize();
		rAjaxCall.async("/member/ajaxSaveMember", 'POST', params, function(r) {
			if(r.result){
				alert(r.content);
				getList(mode);
			}else{
				alert(r.content);
			}
		});
	}
}