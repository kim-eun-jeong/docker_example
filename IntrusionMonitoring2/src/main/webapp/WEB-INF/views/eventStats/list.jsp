<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<section class="content">  
	<input type="hidden" id="mode" value="${commonVO.mode}" />
	<div class="stab_dth">
        <div class="line"></div>
        <ul id="statsDepth">
          <li onClick="tabSetting('day')">일별</li>
          <li onClick="tabSetting('month')">월별</li>
          <li onClick="tabSetting('year')">년별</li>
        </ul>
   </div>
   
    <div id="eventDay" class="DiNone">
    	<jsp:include page="./content/day.jsp" />
    </div>
    <div id="eventMonth" class="DiNone">
    	<jsp:include page="./content/month.jsp" />
    </div>
    <div id="eventYear" class="DiNone">
    	<jsp:include page="./content/year.jsp" />
	</div>
</section>

<script src="${pageContext.request.contextPath}/resources/js/custom/eventStats/eventStats.js"></script>