<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<div class="pop_overlay" id="pop_state">
	<div class="pop_layer">
		<div class="header">
      		<div class="tit">비밀번호 변경</div>
   		</div>
   		<div class="con">
			<div class="pop_con_wrap basic_div_box"  style="height:210px; width:300px;">
				 <div class="pop_con Mb10">
				 	<div class="table_st2">
		        		<table>
		        			<colgroup>
								<col width="*"> 
							</colgroup>
					 		<tbody>
								<tr>
									<td> 
					            		<input type="password" id="password" class="W100" placeholder="현재 비밀번호" />	
			              			</td>
					        	</tr>
					        	<tr>
									<td> 
					            		<input type="password" id="newPassword" class="W100" placeholder="새 비밀번호 " />	
			              			</td>
					        	</tr>
					        	<tr>
									<td> 
					            		<input type="password" id="newPasswordChk" class="W100" placeholder="새 비밀번호 확인" />
					   				</td>
					        	</tr>
							</tbody>
		        		</table>
		      		</div>
				 </div>
				 <p class="BtnArea">
					<button type="button" class="btn Mr15" onclick="updatePassword('<c:out value="${id}" />'); return false;">변경</button>
				</p>
			</div>
		</div>
	</div>
</div>
	
<script src="${pageContext.request.contextPath}/resources/js/custom/member/password.js"></script>
