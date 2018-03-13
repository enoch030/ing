<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/top.jsp" %>
<h2>로그인</h2>
<form action="${pageContext.request.contextPath }/login" method="post">
	<input type="text" name="idmail">
	<input type="password" name="pass">
	<button>로그인</button>
</form>

<%@ include file="/WEB-INF/layout/btm.jsp" %>