<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/top.jsp" %>
<c:choose>
	<c:when test="${logon == null}">
		<small>${ment }</small>
	</c:when>
	<c:otherwise>
		<div style="width: 100%" align="left">
			<div id="profile" style="width: 100px; border-style: solid;">
			${logon }님, 환영합니다!
			</div>
		</div>
	</c:otherwise>
</c:choose>	
	
	
<%@ include file="/WEB-INF/layout/btm.jsp" %>