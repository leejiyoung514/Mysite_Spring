<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8">
	<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/assets/css/main.css" rel="stylesheet" type="text/css">
	<title>Mysite</title>
</head>
<body>
	<div id="container">
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
    <c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>				

		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<img style="width: 150px" id="profile" src="${pageContext.request.contextPath}/assets/images/aa.png">
					<h3>안녕하세요.<br> JOE의 블로그에 방문하신 것을 환영합니다.</h3>
					<p>
						이 블로그는 웹 프로그래밍 스터디 사이트입니다.
						<br>
						메뉴는 사이트 소개, 방명록, 게시판이 있구요. JAVA 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.
						<br>
						<br>
						<a href="${pageContext.request.contextPath}/board">방명록</a>에 글 남기기
						<br>
					</p>
				</div>
			</div>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>		
		
	</div>
</body>
</html>