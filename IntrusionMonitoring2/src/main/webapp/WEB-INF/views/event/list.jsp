<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<section class="content">  
	<div class="board_info">
		<form action="/eventHistory" id="searchForm" name="search_form" method="post">
        	<input type="hidden" name="pageNo" value="1"/>
			<input type="hidden" name="pageSize" value="<c:out value="${commonVO.pageSize}" />" />
			
			<div class="search_area">
				<div class="search_area_row">
	                <label for="eventTimeStart">시간</label>
	                <input type="text" class="Wd120" name="eventTimeStart" id="timeStart" value="<c:out value="${commonVO.eventTimeStart}" />" />
	                 &nbsp;&nbsp;&nbsp; ~ &nbsp;
	               	<input type="text" class="Wd120" name="eventTimeEnd" id ="timeEnd" value="<c:out value="${commonVO.eventTimeEnd}" />" />
					
					<label for="eventCode">이벤트명</label>
					<select name="eventCode">
	    					<option value="">전체</option>
	    				<c:forEach var="data" items="${eventCodeList}" varStatus="status">
			            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.eventCode eq data.codeId ? 'selected=selected':'' }" />>
			            		<c:out value="${data.codeName}" />
			            	</option>
				        </c:forEach>
					</select>
					
					<label for="eventZone">구간</label>
					<select name="eventZone">
	    					<option value="">전체</option>
	    				<c:forEach var="data" items="${eventZoneList}" varStatus="status">
			            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.eventZone eq data.codeId ? 'selected=selected':'' }" />>
			            		<c:out value="${data.codeName}" />
			            	</option>
				        </c:forEach>
					</select>
					
					<%-- 	
					<label for="eventLocationStart">위치</label>
	                <input type="text" class="Wd50" name="eventLocationStart" value="<c:out value="${commonVO.eventLocationStart}" />" maxlength="4" onKeyUp="onlyNumber(this);" />
	                m  ~  
	                <input type="text" class="Wd50" name="eventLocationEnd" value="<c:out value="${commonVO.eventLocationEnd}" />"  maxlength="4" onKeyUp="onlyNumber(this);" />
	                m 
	                --%>
	                
					<button type="button"  class="btn_search" onclick="goSearch();">검색</button>
	            </div>
			</div>
	    </form>
	</div>
	
	<p class="page_info">
		전체 <strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.totalCount}" /></strong>건,
		현재 페이지 <strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.pageNo}" /></strong>/<strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.finalPageNo}" /></strong>
	</p>
	<c:if test="${commonVO.totalCount > 0}">
		<button class="btn_down" type="button" onclick="excelDown();">다운로드</button>
	</c:if>
	
	<table class="tstyle_list">
		<caption>이벤트 목록 입니다.</caption>
		<colgroup>
        	<col width="5%">
			<col width="*">
			<col width="35%">
		</colgroup>
		<thead>
			<tr>
				<th>NO</th>
	          	<th>발생시간</th>
	            <th>이벤트명</th>
	            <th>구간</th>
	            <!-- <th>위치(m)</th> -->
			</tr>
		</thead>
		<tbody>
			<c:choose>
            	<c:when test="${commonVO.totalCount > 0}">
					<c:set var="num" value ="${commonVO.totalCount - ((commonVO.pageNo - 1) * commonVO.pageSize) }"/>          			
					<c:forEach var="data" items="${eventList}" varStatus="status">
		               	<tr>
		               		<td><c:out value="${num}"/></td>
		                	<td><c:out value="${data.eventTime}" /></td>
		                  	<td><c:out value="${data.eventCodeNm}" /></td>
		                  	<td><c:out value="${data.eventZoneNm}" /></td>
		                  	<%-- <td><c:out value="${data.eventLocation}" /></td> --%>
		                </tr>
		                <c:set var="num" value ="${num-1}"/>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="noData">
						<td colspan="4" class="TxtC">데이터가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>	
	<div class="board_pager Mt10">
		<c:if test="${commonVO.totalCount > 0}">
             ${commonVO.pageHtml}
        </c:if>
	</div>	
</section>

<form action="/eventHistory" name="list_form" method="post">
	<input type="hidden" name="pageNo" value="<c:out value="${commonVO.pageNo}" />" />
	<input type="hidden" name="pageSize" value="<c:out value="${commonVO.pageSize}" />" />
	<input type="hidden" name="eventTimeStart" value="<c:out value="${commonVO.eventTimeStart}" />" />
	<input type="hidden" name="eventTimeEnd" value="<c:out value="${commonVO.eventTimeEnd}" />" />
	<input type="hidden" name="eventLocationStart" value="<c:out value="${commonVO.eventLocationStart}" />" />
	<input type="hidden" name="eventLocationEnd" value="<c:out value="${commonVO.eventLocationEnd}" />" />
	<input type="hidden" name="eventCode" value="<c:out value="${commonVO.eventCode}" />" />
	<input type="hidden" name="eventZone" value="<c:out value="${commonVO.eventZone}" />" />
</form>


<script src="${pageContext.request.contextPath}/resources/js/custom/event/event.js"></script>