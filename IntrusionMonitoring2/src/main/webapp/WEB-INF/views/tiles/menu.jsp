<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>
<header id="header">
	<div class="home">
		<a href="/monitoring"></a>
	</div>
	<div class="hearder">
		<ul>
			<li class="alarm">
				<a href="javascript:showAlarmList('eventCnt')" class="alarm">
		        	이벤트 : <b></b>개
		        	<span></span>
		        </a>
		        <ul class="eventCnt DiNone">
		        </ul>
			</li>
			<li class="clock"></li>
			<li class="user">
				<a href="/member?mode=mypage" class="user"><c:out value="${sessionScope['loginName']}" />님 </a>
			</li>
			<li class="line"></li>
			<li class="login">
				<c:choose>
					<c:when test="${not empty sessionScope['loginName']}">
						<a href="${pageContext.request.contextPath}/logout">로그아웃</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/login">로그인</a>
					</c:otherwise>
				</c:choose>
			</li>
		</ul>
	</div>
	<script type="text/javascript" src="/resources/js/custom/common/common.js"></script>		
</header>