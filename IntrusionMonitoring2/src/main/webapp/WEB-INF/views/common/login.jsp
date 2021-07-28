<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="login_page">
	<div class="login_bg">
		<div class="login-box-body">
			<div class="login_title"></div>
			<form name="formLogin" method="post">
				<div class="loginForm">
					<label for="loginId">ID</label>
					<input type="text" class="form-control"  id="loginId" title=" ID 필수 항목입니다" maxlength="20" required>
					<label for="loginPassword">Password</label>
					<input type="password" class="form-control" id="loginPassword" title=" 비밀번호필수 항목입니다" maxlength="20" required onkeyup="enterkey();"> 
					<div onclick="javascript:login(); return false;">Login</div>
				</div>
			</form>
		</div>
	</div>

</div>
	


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/custom/common/login.js"></script>
