<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>

<html>

	<head>
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="resource" />
	</head>
	
	<body>
		
		<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath" />
		<div class="wrap">
			<tiles:insertAttribute name="menu" />
			
			<div id="container">
				<div class="left">
					<tiles:insertAttribute name="left" />
				</div>
				<div class="main-wrap"> 
					<div class="contents-wrap">
						<tiles:insertAttribute name="content" />
					</div>
				</div>
			</div>
			
		</div>
		
	</body>
	
</html>


