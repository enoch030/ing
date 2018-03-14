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
			</c:otherwise>
		</c:choose>
		</div>
		<div class="alert alert-warning alert-dismissible" id="warn" style="display: none">
		  <a href="#" class="close" data-dismiss="alert" aria-label="close" >&times;</a>
		  <strong>Warning!</strong> Indicates a warning that might need attention.
		</div>
		<script>
			var ws = new WebSocket("ws://${pageContext.request.serverName}/alert");
			
			ws.onopen = function() {
				console.log("ws opened...");
			}
			ws.onmessage = function(resp) {
				var obj = JSON.parse(resp.data);
				if(obj.alert != null) {
					$("#warn").show();
				}
				
			}
		
		</script>
		
		
		
	<hr/>
