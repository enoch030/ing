<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Spring Project</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
</head>
<body>
<!-- <nav class="navbar navbar-default"> -->
<!--   <div class="container-fluid"> -->
<!--     <div class="navbar-header"> -->
<!--       <a class="navbar-brand" href="#">WebSiteName</a> -->
<!--     </div> -->
<!--     <ul class="nav navbar-nav"> -->
<!--       <li class="active"><a href="#">Home</a></li> -->
<!--       <li><a href="#">Page 1</a></li> -->
<!--       <li><a href="#">Page 2</a></li> -->
<!--       <li><a href="#">Page 3</a></li> -->
<!--     </ul> -->
<!--   </div> -->
<!-- </nav> -->

	<div align="center" >
		<div style="width:850px">
		<h2>Spring Project</h2>
	<hr/>
		<div style="width: 800px" align="right">
		<c:if test="${logon == null }">
			<a href="${pageContext.request.contextPath }/join">회원가입</a> |
		</c:if>
		<c:choose>
			<c:when test="${logon == null }">
			  <a href="${pageContext.request.contextPath }/login"> 로그인</a> |
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath }/logout">로그아웃</a> |
				<a href="${ pageContext.request.contextPath}/friend">친구 관리</a> |
				<script>
					var ws = new WebSocket("ws://${pageContext.request.serverName}/logonWS");
					
					ws.onmessage = function(rst) {
						var obj = JSON.parse(rst.data);
						switch(obj.mode) {
						case "fr_req":
							window.alert(obj.from+" 님으로부터 친구 요청이 발생하였습니다.");
							break;
						}
					}
				</script>
			</c:otherwise>
		</c:choose>
		</div>
		
		
<hr/>