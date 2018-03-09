<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>회원가입</title>
</head>
<body>
	<div align="center">
	<h2>회원가입</h2>
	<hr/>
	<c:if test="${!empty error }">
		<span style="color: red; font-weight: bold;">${error }</span>
	</c:if>
	<form method="post">
		<p>	<input type="text" name="id" value="${id }" placeholder="ID"  required></p>
		<p>	<input type="password" name="pass" placeholder="PASS" required>	</p>
		<p>	<input type="text" name="email" value="${email }"placeholder="EMAIL"   required></p>
		<p>	<button>가입</button></p>
	</form>
	</div>
</body>
</html>