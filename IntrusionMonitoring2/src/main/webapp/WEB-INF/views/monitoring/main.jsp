<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<section class="main_content">
	
	<div class="main_row">
		<div class="full_box"  id="eventCntListDiv">
			<jsp:include page="./content/monitoring.jsp" /> 
		</div>
	</div>
	
 	<div class="main_row">
		<div class="left_box">
			<div class="map_wrap">
				<div id="map" class="H100"></div>
				<!-- 지도타입 컨트롤 div 입니다 -->
			    <div class="custom_typecontrol radius_border">
			        <span id="btnRoadmap" class="selected_btn" onclick="setMapType('roadmap')">지도</span>
			        <span id="btnSkyview" class="btn" onclick="setMapType('skyview')">스카이뷰</span>
			    </div>
			</div>
			<%-- <jsp:include page="./content/monitoring.jsp" /> --%>
		</div>
		<div class="right_box">
			<div class="row_box">
				<div class="row_box_header">
					<div class="row_box_header_title">이벤트 목록</div>
					<div class="row_box_header_search">
						<select id="eventSearch" class="form-control Wd150" onChange="fncEventSearch();">
							<option value="" >전체</option>
							<c:forEach var="data" items="${eventCodeList}" varStatus="status">
				            	<option value="<c:out value="${data.codeId}" />" <c:out value="${commonVO.eventCode eq data.codeId ? 'selected=selected':'' }" />>
				            		<c:out value="${data.codeName}" />
				            	</option>
					        </c:forEach>
						</select>
					</div>
				</div>
				<div class="row_box_body"  id="eventListDiv">	
					<jsp:include page="./content/event_list.jsp" />
				</div>
			</div>
		</div>
	</div>
	<!-- <div class="main_row">
		<div class="full_box">
			<div id="eventChart" class="eventChart"></div>
		</div>
	</div> -->
</section>
<script src='<c:out value="${pageContext.request.contextPath}" />/resources/js/custom/monitoring/main.js'></script>