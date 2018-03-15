<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/layout/top.jsp" %>
	<c:if test="${! empty alert}">
		<script>
			window.alert("${alert}");		
		</script>
	</c:if>

	<c:choose>
		<c:when test="${ !empty friendList }">
			<c:if test="${!empty friendList.myWaitingList}">
			<p><b>[ 내가 친구 신청한 대기 목록 ]</b></p>
				<c:forEach var="w" items="${friendList.myWaitingList}" >
				<p>	${w.OTHER }	 (${w. REQDATE}) <a href="${pageContext.request.contextPath}/friend/cancle?no=${w.NO}">취소</a></p>
				</c:forEach>
			</c:if>
			<c:if test="${!empty friendList.reqToMe}">
			<p><b>[ 나를 친구 신청한 대기 목록 ]</b></p>
				<c:forEach var="r" items="${friendList.reqToMe}" >
				<p>	${r.ONE}	 (${r. REQDATE}) <a href="${pageContext.request.contextPath}/friend/refuse?no=${r.NO}">거절</a></p>
				</c:forEach>
			</c:if>
			<c:if test="${!empty friendList.friends}">
			<p><b>[ 친구 목록 ]</b></p>
				<c:forEach var="f" items="${friendList.friends}" >
				<p>	${f.OTHER == logon ? f.ONE : f.OTHER}	 (${f. REQDATE})</p>
				</c:forEach>
			</c:if>
		</c:when>
		<c:otherwise>
			<p> 친구를 추가해 보세요!</p>
		</c:otherwise>
	</c:choose>

	<div style="height: 100px;">
	<p>	아이디 혹은 메일 주소로 사용자를 검색할 수 있습니다.	</p>
	<br>
	<p>
		<input id="idmail" name="idmail" type="text" placeholder="검색어" onchange="find()"/> <button type="button" onclick="find()">검색</button>
	</p>
	</div>
	<br>
	<div style="width: 600px;">
		<hr style="width: 600px; color:#F4E619;"/>
		<p>
		<span id="result" style="font-size: 18px; font-weight: bold;">검 색 결 과</span>
		</p>
		<hr style="width: 600px; color:#F4E619;"/>
		<div align="left">
		<p>
		<span id="msg"></span>
		</p>
		</div>
	</div>
	<script>
		function find() {
			$.ajax("${pageContext.request.contextPath}/friend/search", {
				"data" : {
					"idmail" : $("#idmail").val()
				}
			}).done(function (json){
				var html = "";
				for(var i=0; i<json.length; i++) {
					html +=	"<p>▷ "+json[i].ID+"님 ("+json[i].EMAIL+")"+"<a href='${pageContext.request.contextPath}/friend/add?id="+json[i].ID+"'><button type='button'>+</button> </a></p>";
			}
			$("#result").html("검 색 결 과 ("+json.length+")");
			$("#msg").css("color", "black");
			$("#msg").html(html);
				});
		}
	</script>
	
<%@ include file="/WEB-INF/layout/btm.jsp" %>