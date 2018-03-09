<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Spring Project</title>
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
			 	로그인 |
			</c:when>
			<c:otherwise>
				로그아웃 |
			</c:otherwise>
		</c:choose>
		</div>
	<hr/>
