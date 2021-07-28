<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<section class="content">
<ul>
	<c:forEach var="data" items="${list}" varStatus="status">
		<li <c:out value="${data.codeId eq commonVO.searchWord ? 'class=selected':'' }" /> onclick="javascript:goDetail('${data.codeId}')">
			${data.codeName}
		</li>
	</c:forEach>
</ul>
</section>

<script src="${pageContext.request.contextPath}/resources/js/custom/code/code.js"></script>