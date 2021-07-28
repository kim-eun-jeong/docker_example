<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="leftTitle">
</div>

<nav id="gnb">
	<ul>
    	<li class="<c:out value="${menuVO.menuTitle eq 'monitoring' ? 'selected' : ''}" />" onclick="location.href='/monitoring' ">
			<span class="dashboard">모니터링</span> 
		</li>
		<li class="<c:out value="${menuVO.menuTitle eq 'eventHistory' ? 'selected' : ''}" />" onclick="location.href='/eventHistory' ">
			<span class="event">이력</span> 
		</li>
		<li class="<c:out value="${menuVO.menuTitle eq 'eventStats' ? 'selected' : ''}" />" onclick="location.href='/eventStats' ">
			<span class="stats">통계</span> 
		</li>
		<%-- <c:if test="${loginAuth eq 'AUTH_00'}">
			<li class="<c:out value="${menuVO.menuTitle eq 'member' ? 'selected' : ''}" />"  onclick="location.href='/member' ">
				<span class="member">사용자 관리</span> 
			</li>
		</c:if> --%>
	</ul>
</nav>

<ul class="left-footer">
	<li><img alt="enitt" src="/resources/css/lib/images/enitt.png" /></li> 
	<li>Copyright 2020 ENITT Co., Ltd. All right reserved</li> 
</ul>