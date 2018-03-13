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
			 <a href="${pageContext.request.contextPath }/login">	로그인</a> |
			</c:when>
			<c:otherwise>
				로그아웃 |
			</c:otherwise>
		</c:choose>
		<c:if test="${pageContext.request.requestURI }">
		</c:if>
		</div>
		<div class="alert alert-info alert-dismissible">
			<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
			<p><b>현재 접속자 수: <span id="cnt"></span></b></p>
					<strong>서버 알림 : </strong> <span id="info">-</span>
		</div>
		
		<script>
			var ws = new WebSocket("ws://192.168.10.95/handle"); 
			// http 대신 ws://서버 ip/spring 설정 파일에서 설정한 path (프로젝트가 /가 아닐 경우 프로젝트 명까지)
			// 페이지가 리로딩 되면 ws가 다시 연결함. (localhost로 접근하면 안 됨! ip치고 접속할 것!)
// 			ws = new WebSocket("ws://${pageContext.request.serverName}/handle" // 테스트 환경일 땐 서버ip가 매번 바뀔 수 있으니까
																																									// 이렇게 해두면 어느 아이피로 접속해도 됨!
																																									
			ws.onopen = function() { // 연결 성공시 작동
				console.log("opened..");
				console.log(this);
			}																																				
			
			ws.onmessage = function(resp) { // 메세지 들어올 때 작동
				console.log(resp);	
				var obj = JSON.parse(resp.data) // 해서 서버에서 전송한 문자 데이터 활용하면 됨!
				$("#cnt").html(obj.cnt);
				$("#info").html(obj.info);				
			}
			
			ws.onclose = function() { // 연결 끊길 때 작동 (서버 끄면 연결 끊김)
				window.alert("연결 해제");
			}
			
		</script>
	<hr/>
