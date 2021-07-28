<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="board_info">
	<form action="/eventStats" name="year_form" method="post">
		<input type="hidden" name="mode" value="year" />
		<input type="hidden" name="searchDay" value="${commonVO.searchDay}" />
		<input type="hidden" name="searchDayCode" value="${commonVO.searchDayCode}" />
		<input type="hidden" name="searchMonth" value="${commonVO.searchMonth}" />
		<input type="hidden" name="searchMonthCode" value="${commonVO.searchMonthCode}" />
		
		<div class="search_area">
			<div class="search_area_row">
    			<label class="Wd50 ClearWS" for="searchYear">날짜</label>
                <input type="text" class="Wd80 yearPicker" name="searchYear" id="searchYear" value="<c:out value="${commonVO.searchYear}" />"> 
                &nbsp;&nbsp;&nbsp; ~ &nbsp;
    			<input type="text" class="Wd80 yearPicker" name="searchYearEnd"  id="searchYearEnd" value="<c:out value="${commonVO.searchYearEnd}" />">
    			
    			<label for="searchYearCode">이벤트명</label>
				<select name="searchYearCode">
    					<option value="">전체</option>
    				<c:forEach var="data" items="${eventCodeList}" varStatus="status">
		            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.searchYearCode eq data.codeId ? 'selected=selected':'' }" />>
		            		<c:out value="${data.codeName}" />
		            	</option>
			        </c:forEach>
				</select>
				
    			<button class="btn_search" type="button"  onclick="fncSearch('year_form')" >검색</button>
			</div>
		</div>
    </form>
</div>
    
<div id="yearContainer" class="chartDiv"></div>
<c:if test="${yearData.data.size() > 0}">
	<button class="btn_down" type="button" onclick="excelDown('year');">다운로드</button>
</c:if>

<div class="statsTable">
	<table class="tstyle_list3">
		<caption>선로 이벤트 통계 월별 목록입니다.</caption>
		<colgroup>
	       	<col width="5%">
	       	<col width="10%">
		</colgroup>
		<thead>
			<tr>
					<th>NO</th>
	          		<th>날짜</th>
	          		<c:forEach var="data" items="${yearData.data}" varStatus="status">
	          			<th><c:out value="${data.name}" /></th>
	          		</c:forEach>
	        </tr>
		</thead>
		<tbody>
			<c:choose>
	           	<c:when test="${yearData.data.size() > 0}">
	            	<c:set var="num" value="1"/>
	            	<c:forEach var="data" items="${yearData.categories}" varStatus="status">
				        <tr class="noData">
				        	<td><c:out value="${num}" /></td>
				            <td>
				            	<c:out value="${data}년" />
				            </td>
				            <c:forEach var="yearData" items="${yearData.data}" varStatus="status2">
			          			<td><c:out value="${yearData.ListData[status.index]}" /></td>
			          		</c:forEach>
				        </tr>
				        <c:set var="num" value ="${num+1}"/>
					</c:forEach>
	        	</c:when>
	        	<c:otherwise>
	       			<tr class="noData">
						<td colspan='<c:out value="${yearData.data.size() +2}" />' class="TxtC">데이터가 없습니다.</td>
					</tr>
	        		</c:otherwise>
	       	</c:choose>
		</tbody>
	</table>
</div>