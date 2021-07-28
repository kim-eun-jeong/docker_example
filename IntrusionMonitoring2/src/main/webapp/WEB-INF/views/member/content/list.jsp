<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="board_info">
	<form action="/member" id="searchForm" name="search_form" method="post">
       	<input type="hidden" name="pageNo" value="1" />
		<input type="hidden" name="pageSize" value="<c:out value="${commonVO.pageSize}" />" />
		<input type="hidden" name="mode" value="list" />
		
		<div class="search_area">
              	<div class="search_area_row">
                <select name="searchType">
                	<option value="">전체</option>
                	<%-- <option value="DEPARTMENT" <c:out value="${commonVO.searchType eq 'DEPARTMENT' ? 'selected':''}" />>소속</option> --%>
                	<option value="ID" <c:out value="${commonVO.searchType eq 'ID' ? 'selected':''}" />>ID</option>
                	<option value="NAME" <c:out value="${commonVO.searchType eq 'NAME' ? 'selected':''}" />>이름</option>
				</select>
				<input type="text" class="Wd150" name="searchWord" value="<c:out value="${commonVO.searchWord}" />" maxlength="15" />
                
				<button type="submit"  class="btn_search">검색</button>
			</div>
		</div>
    </form>
</div>

<p class="page_info">
	전체 <strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.totalCount}" /></strong>건,
	현재 페이지 <strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.pageNo}" /></strong>/<strong><fmt:formatNumber type="number" maxFractionDigits="3" value="${commonVO.finalPageNo}" /></strong>
</p>
<button class="btn"  type="button" onclick="getDetail('no','no');">추가</button>

<table class="tstyle_list" id="listTable">
	<caption>선로 이벤트 관리 목록 입니다.</caption>
	<colgroup>
       	<col width="8%">
		<col width="*">
		<!-- <col width="30%"> -->
		<col width="46%">
	</colgroup>
	<thead>
		<tr>
			<th>NO</th>
          	<!-- <th>소속</th> -->
            <th>ID</th>
            <th>이름</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
           	<c:when test="${commonVO.totalCount > 0}">
				<c:set var="num" value ="${commonVO.totalCount - ((commonVO.pageNo - 1) * commonVO.pageSize) }"/> 
				<c:set var="cnt" value ="0"/>         			
				<c:forEach var="data" items="${memberList}" varStatus="status">
	               	<tr onClick="getDetail('<c:out value="${data.id}" />','${cnt}')">
	               		<td><c:out value="${num}"/></td>
	                	<%-- <td><c:out value="${data.departmentNm}" /></td> --%>
	                  	<td><c:out value="${data.id}" /></td>
	                  	<td><c:out value="${data.name}" /></td>
	                </tr>
	                <c:set var="num" value ="${num-1}"/>
	                <c:set var="cnt" value ="${cnt+1}"/>   
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

<div class="board_pager Mt10">
	<c:if test="${commonVO.totalCount > 0}">
       	${commonVO.pageHtml}
     </c:if>
</div>	

<form action="/member" name="list_form" method="post">
	<input type="hidden" name="pageNo" value="<c:out value="${commonVO.pageNo}" />" />
	<input type="hidden" name="pageSize" value="<c:out value="${commonVO.pageSize}" />" />
	<input type="hidden" name="searchType" value="<c:out value="${commonVO.searchType}" />" />
	<input type="hidden" name="searchWord" value="<c:out value="${commonVO.searchWord}" />" />
	<input type="hidden" name="mode" value="list" />
</form>
