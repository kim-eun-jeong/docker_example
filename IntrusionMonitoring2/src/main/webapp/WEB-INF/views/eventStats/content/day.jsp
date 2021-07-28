<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="board_info">
	<form action="/eventStats" name="day_form" method="post">
		<input type="hidden" name="mode" value="day" />
		<input type="hidden" name="searchMonth" value="${commonVO.searchMonth}" />
		<input type="hidden" name="searchMonthCode" value="${commonVO.searchMonthCode}" />
		<input type="hidden" name="searchYear" value="${commonVO.searchYear}" />
		<input type="hidden" name="searchYearEnd" value="${commonVO.searchYearEnd}" />
		<input type="hidden" name="searchYearCode" value="${commonVO.searchYearCode}" />
		
		<div class="search_area">
			<div class="search_area_row">
    			<label class="Wd50 ClearWS" for="searchDay">날짜</label>
                <input type="text" class="Wd100" name="searchDay" id="searchDay" value="<c:out value="${commonVO.searchDay}" />">
                
                <label for="searchDayCode">이벤트명</label>
				<select name="searchDayCode">
    					<option value="">전체</option>
    				<c:forEach var="data" items="${eventCodeList}" varStatus="status">
		            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.searchDayCode eq data.codeId ? 'selected=selected':'' }" />>
		            		<c:out value="${data.codeName}" />
		            	</option>
			        </c:forEach>
				</select>
					
				<button class="btn_search"  type="button"  onclick="fncSearch('day_form')">검색</button>
			</div>
		</div>
    </form>
</div>
    
<div id="dayContainer" class="chartDiv"></div>
<c:if test="${dayData.data.size() > 0}">
	<button class="btn_down" type="button" onclick="excelDown('day');">다운로드</button>
</c:if>
<div class="statsTable">
	<table class="tstyle_list3">
		<caption>선로 이벤트 통계 일별 목록입니다.</caption>
		<colgroup>
	       	<col width="5%">
			<col width="10%">
		</colgroup>
		<thead>
			<tr>
					<th>NO</th>
	          		<th>날짜</th>
	          		<c:forEach var="data" items="${dayData.data}" varStatus="status">
	          			<th><c:out value="${data.name}" /></th>
	          		</c:forEach>
	        </tr>
		</thead>
		<tbody>
			<c:choose>
	            <c:when test="${dayData.categories.size() > 0}">
	            	<c:set var="num" value="1"/>
	            	<c:forEach var="data" items="${dayData.categories}" varStatus="status">
				        <tr class="noData">
				        	<td><c:out value="${num}" /></td>
				            <td>
				            	<c:out value="${data}일" />
				            </td>
				            <c:forEach var="dayData" items="${dayData.data}" varStatus="status2">
			          			<td><c:out value="${dayData.ListData[status.index]}" /></td>
			          		</c:forEach>
				        </tr>
				        <c:set var="num" value ="${num+1}"/>
					</c:forEach>
	        	</c:when>
	        	<c:otherwise>
	       			<tr class="noData">
						<td colspan='<c:out value="${dayData.data.size() +2}" />' class="TxtC">데이터가 없습니다.</td>
					</tr>
	        		</c:otherwise>
	        </c:choose>
		</tbody>
	</table>
</div>