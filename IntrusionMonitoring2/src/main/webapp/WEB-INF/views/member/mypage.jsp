<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<section class="content">
	<form action="${pageContext.request.contextPath}/member/mypage" name="detail_form" method="post">
		<input type="hidden" name="mode" value="mypage" />
		<table class="tstyle_write">
			<caption>내정보</caption>
			<colgroup>
				<col width="15%">
				<col width="*">
			</colgroup>
			<thead>
				<tr>
					<th scope="col" colspan="2">
						<p class="title">내정보</p>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>ID</th>
	              	<td class="Pt9 Pb9">
	              		<c:out value="${detail.id}" />
	              		<input type="hidden" name="id" value="<c:out value="${detail.id}" />" />
	              	</td>
	        	</tr>
				<tr>
					<th>이름 <span>*</span></th>
	              	<td>
	              		<input type="text" name="name" value="<c:out value="${detail.name}" />" />
	              	</td>
	        	</tr>
				<tr>
					<th>권한</th>
	              	<td>
	              		<c:out value="${detail.authNm}" />
	              	</td>
	        	</tr>
				<tr>
					<th>이메일 <span>*</span></th>
	              	<td>
	              		<input type="text" id="email1" value="<c:out value="${fn:substringBefore(detail.email,'@')}" />" onChange="mailCheck();" />
	              		@
	              		<input type="text" id="email2" value="<c:out value="${fn:substringAfter(detail.email,'@')}" />" onChange="mailCheck();" />
	   
	              		<select id="emailDomain" onchange="domainChange()" class="Wd190">
		                	<option value="">직접입력</option>
		                	<option value="naver.com" <c:out value="${fn:substringAfter(detail.email,'@') eq 'naver.com' ? 'selected=selected':''}" /> >naver.com</option>
		                	<option value="hanmail.net" <c:out value="${fn:substringAfter(detail.email,'@') eq 'hanmail.net' ? 'selected=selected':''}" /> >hanmail.net</option>
		                	<option value="daum.net" <c:out value="${fn:substringAfter(detail.email,'@') eq 'daum.net' ? 'selected=selected':''}" /> >daum.net</option>
		                	<option value="gmail.com" <c:out value="${fn:substringAfter(detail.email,'@') eq 'gmail.com' ? 'selected=selected':''}" /> >gmail.com</option>
		                	<option value="nate.com" <c:out value="${fn:substringAfter(detail.email,'@') eq 'nate.com' ? 'selected=selected':''}" /> >nate.com</option>
		              	</select>
			              
	              		<input type="hidden" name="email" value="<c:out value="${detail.email}" />" />
	              		
	              	</td>
	        	</tr>
				<tr>
					<th>비밀번호변경</th>
	              	<td>
              			<button class="nBtn" type="button"  onClick="popOpen('pop_state')">변경</button>
              		</td>
	        	</tr>
			</tbody>
		</table>
	</form>
	<p class="BtnArea">
		<button class="btn"  type="button" onclick="goSave(); return false;">저장</button>
	</p>
	
	<div class="pop_overlay" id="pop_state" style="display:none;">
		<div class="pop_layer">
			<div class="header">
	      		<div class="tit">비밀번호 변경</div>
	      		<div class="close" onclick="popClose('pop_state');"> X</div>
	   		</div>
	   		<div class="con">
				<div class="pop_con_wrap basic_div_box"  style="height:245px;">
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
						<button class="" type="button" onclick="popClose('pop_state'); return false;">닫기</button>
						<button class="" type="button" onclick="updatePassword('<c:out value="${detail.id}" />'); return false;">수정</button>
					</p>
				</div>
			</div>
		</div>
	</div>
	
</section>

<script src="${pageContext.request.contextPath}/resources/js/custom/member/mypage.js"></script>