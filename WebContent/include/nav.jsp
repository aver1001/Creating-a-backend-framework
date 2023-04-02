<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<ul class="navbar-nav me-auto">
		<li class="nav-item"><a class="nav-link" href="#">핸드폰 목록</a></li>
		<li class="nav-item"><a class="nav-link" href="#">핸드폰 정보 등록</a></li>
	</ul>
	<c:choose>
		<%-- session에 userInfo 객체 없는 경우(로그인 X) --%>
		<c:when test="${empty userInfo}">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="#">로그인</a>
				</li>
			</ul>
		</c:when>
		<%-- session에 userInfo 객체 있는 경우(로그인 O) --%>
		<c:otherwise>
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link disabled">${userInfo.id}님 반갑습니다.</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="#">로그아웃</a>
				</li>
			</ul>
		</c:otherwise>
	</c:choose>
</nav>