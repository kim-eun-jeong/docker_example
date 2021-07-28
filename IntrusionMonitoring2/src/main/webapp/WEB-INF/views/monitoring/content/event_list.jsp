<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>
<div>
	<table class="tstyle_list2" id="eventList">
		<colgroup>
			<col width="*">
			<col width="35%">
		</colgroup>
			<thead>
			<tr>
	           	<th>발생시간</th>
	           	<th>이벤트명</th>
	            <th>구간</th>
	           	 <!-- <th>위치(m)</th> -->
	       	</tr>
		</thead>
	       <tbody>
	        <c:choose>
	           	<c:when test="${eventList.size() > 0}">
            		<c:set var="num" value ="0"/>   
	           		<c:forEach var="data" items="${eventList}" varStatus="status">
		               	<tr onmouseover="eventOver(<c:out value="${num}" />);" onmouseout="eventOut(<c:out value="${num}" />);" >
		                	<td><c:out value="${data.eventTime}" /></td>
		                  	<td><c:out value="${data.eventCodeNm}" /></td>
		                  	<td><c:out value="${data.eventZoneNm}" /></td>
		                  	<%-- <td><c:out value="${data.eventLocation}" /></td> --%>
		                </tr>
		                <c:set var="num" value ="${num+1}"/>   
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="noData">
						<td colspan="3" class="TxtC">데이터가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
	  		</tbody>
	</table>
</div>