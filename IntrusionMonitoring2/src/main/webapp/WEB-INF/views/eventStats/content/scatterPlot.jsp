<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="pop_overlay">
	<div class="pop_layer W65">
		<div class="header">
      		<div class="tit">분포도</div>
      		<div class="close" onclick="popClose('scatterPlot');"> X</div>
   		</div>
   		<div class="con">
			<div class="pop_con_wrap"  style="height:700px;">
				 <div class="pop_con">
				 	<div class="table_st2">
		        		<table>
		        			<colgroup>
								<col width="*">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">
										<p class="title" id="scatterTitle"></p>
									</th>
								</tr>
							</thead>
					 		<tbody>
								<tr>
									<td> 
										<div id="scatterContainer" class="chartDiv"></div>
					            	</td>
					        	</tr>
					        	<tr>
									<td>
										<div class="scatterPlotTable">
											<table class="tstyle_list3">
												<colgroup>
													<col width="5%">
													<col width="*%">
													<col width="15%">
													<col width="15%">
													<col width="15%">
													<col width="15%">
													<col width="15%">
												</colgroup>
										 		<thead>
													<tr>
										            	<th>NO</th>
										              	<th>시간</th>
										              	<th>발생단계</th>
										              	<th>구간(m)</th>
										              	<th>중요지점</th>
										              	<th>전력선</th>
										              	<th>온도℃</th>
										        	</tr>
												</thead>
										        <tbody>
											        <c:choose>
										            	<c:when test="${dtsEventList.size() > 0}">
										            		<c:set var="num" value ="1"/>   
												           	<c:forEach var="data" items="${dtsEventList}" varStatus="status">
												               	<tr>
												               		<td><c:out value="${num}" /></td>
												                	<td><c:out value="${data.dtsEventTime}" /></td>
												                  	<td><c:out value="${data.dtsEventCodeNm}" /></td>
												                  	<td><c:out value="${data.dtsEventLocation}" /></td>
												                  	<td><c:out value="${data.dtsEventZoneNm}" /></td>
												                  	<td><c:out value="${data.dtsEventLineNm}" /></td>
												                  	<td><fmt:formatNumber value="${data.dtsEventTemp}" pattern=".0"/></td>
												                </tr>
												                <c:set var="num" value ="${num+1}"/>   
															</c:forEach>
														</c:when>
														<c:otherwise>
															<tr class="noData">
																<td colspan="7" class="TxtC">데이터가 없습니다.</td>
															</tr>
														</c:otherwise>
													</c:choose>
										   		</tbody>
											</table>
										</div>
					            	</td>
					        	</tr> 
							</tbody>
		        		</table>
		      		</div>
				 </div>
			</div>
		</div>
	</div>
</div>