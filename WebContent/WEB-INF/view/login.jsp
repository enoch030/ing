<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/top.jsp" %>
<h2>로그인</h2>
<form action="${pageContext.request.contextPath }/login" method="post">
	<p>
	<input type="text" name="idmail">
	</p>
	<p>
	<input type="password" name="pass">
	</p>
	<p>
	<button>로그인</button>
	</p>
</form>

<%@ include file="/WEB-INF/layout/btm.jsp" %>