<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>
<c:if test="${menuVO.menuTitle ne 'monitoring'}">
<div id="sub_head">
	<div id="sub_title">
		<span class="<c:out value="${menuVO.menuTitle}" />"></span>
		<c:if test="${menuVO.parentMenuName ne '' and menuVO.parentMenuName ne null }" >
			 <c:out value="${menuVO.parentMenuName}" /> > 
		</c:if>
		<c:out value="${menuVO.menuName}" /> 
	</div>
</div>
</c:if>