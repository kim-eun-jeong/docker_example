<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="row_event_box" >
	<c:choose>
    	<c:when test="${eventCntList.size() > 0}">
		  	<c:forEach var="data" items="${eventCntList}" varStatus="status">
		  		<div class="event_box">
		  			<div class="<c:out value="${data.codeId}"/>"></div>
		  			<p><c:out value="${data.codeName}" /></p>
		  			<p><c:out value="${data.eventCnt}" /></p>
		  		</div>
		  	</c:forEach>
	 	</c:when>
	 	<c:otherwise>
	 	</c:otherwise>
	 </c:choose>
</div>
