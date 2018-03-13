<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/top.jsp" %>
<c:choose>
	<c:when test="${logon == null}">
		<small>${ment }</small>
	</c:when>
	<c:otherwise>
		<p>
		${logon }님, 환영합니다!
		</p>
		<p>
		<a href="${pageContext.request.contextPath }/new">채팅방 개설</a>
		</p>
	</c:otherwise>
</c:choose>	
	
	
<%@ include file="/WEB-INF/layout/btm.jsp" %>