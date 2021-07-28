<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jstl/jstl.jsp"%>

<form action="/member" name="detail_form" method="post">
	<table class="tstyle_write">
		<caption>회원 상세 정보</caption>
		<colgroup>
			<col width="20%">
			<col width="*">
		</colgroup>
		<thead>
			<tr>
				<th scope="col" colspan="2">
					<p class="title"></p>
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>ID <span>*</span></th>
	             <td>
	             	<input type="text" name="id" value="<c:out value="${detail.id}" />" <c:out value="${detail.id ne null ? 'readonly' :''}" /> onChange="javascript:$('#idCheck').val(false);"/>
	             	<button class="nBtn" type="button"  onClick="goIdCheck('<c:out value="${detail.id}" />')"  <c:out value="${detail.id ne null ? 'disabled' :''}" /> >중복확인</button>
	             	<input type="hidden" id="idCheck" value="<c:out value="${detail.id ne null ? true : false}" />" />
	             </td>
	       	</tr>
	       	<tr>
				<th>이름 <span>*</span></th>
	             <td>
	             	<input type="text" name="name" value="<c:out value="${detail.name}" />" />
	             </td>
	       	</tr>
	       	<tr>
				<th>비밀번호</th>
	             <td>
	             	<input type="hidden" id="passwordYn" value="<c:out value="${detail.id}" />" />
	             	<input type="password" name="password" value="" />
	             </td>
	       	</tr>
	       	<tr>
				<th>비밀번호 확인</th>
	             <td>
	             	<input type="password" id="passwordCheck" value="" />
	             </td>
	       	</tr>
	       	<tr>
				<th>권한 <span>*</span></th>
	             <td>
	             	<select name="auth" class="Wd190">
	             		<c:forEach var="data" items="${authList}" varStatus="status">
			            	<option value="<c:out value="${data.codeId}" />" <c:out value="${detail.auth eq data.codeId ? 'selected=selected':'' }" />>
			            		<c:out value="${data.codeName}" />
			            	</option>
				        </c:forEach>
	                </select>
	             </td>
	       	</tr>
	       	<%-- 
	       	<tr>
				<th>소속 <span>*</span></th>
	             <td>
	             	<select name="department" class="Wd190">
	             		<c:forEach var="data" items="${departmentList}" varStatus="status">
			            	<option value="<c:out value="${data.codeId}" />" <c:out value="${detail.department eq data.codeId ? 'selected=selected':'' }" />>
			            		<c:out value="${data.codeName}" />
			            	</option>
				        </c:forEach>
	                </select>
	             </td>
	       	</tr> 
	       	--%>
	       	<tr>
				<th>이메일 <span>*</span></th>
	             <td>
	             	<input type="text" id="email1" class="ClearWS" value="<c:out value="${fn:substringBefore(detail.email,'@')}" />" onChange="mailCheck();" />
	           		@
	           		<input type="text" id="email2" class="ClearWS" value="<c:out value="${fn:substringAfter(detail.email,'@')}" />" onChange="mailCheck();" />
	             	<select id="emailDomain" class="ClearWS Wd190" onchange="domainChange()" >
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
				<th>사용여부 <span>*</span></th>
	             <td>
	             	<input type="radio" name="useYn" id="useYnY" value="Y" <c:out value="${detail.useYn ne 'N' ? 'checked=checked':''}" /> /><label for="useYnY"><span></span>사용</label>
	             	<input type="radio" name="useYn" id="useYnN" value="N" <c:out value="${detail.useYn eq 'N' ? 'checked=checked':''}" />  /><label for="useYnN"><span></span>미사용</label>
				 </td>
	       	</tr>
		</tbody>
	</table>
</form>

<p class="BtnArea">
	<button type="button"  class="btn" onclick="goSave();">저장</button>
	<%-- <button type="button" onclick="goDelete()" <c:out value="${detail.id ne null ? '' :'disabled'}" />>삭제</button> --%>
</p>
