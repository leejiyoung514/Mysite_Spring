<%@page import="java.util.List"%>
<%@page import="com.javaex.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <% List<GuestbookVO> list=(List<GuestbookVO>)request.getAttribute("list");%> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>


		<div id="wrapper">
			<div id="content">
				<div id="guestbook">

					<form action="${pageContext.request.contextPath}/guestbook/insert"
						method="post">
						<input type="hidden" name="a" value="insert"><br>
						<table border=1 width=500>
							<table>
								<tr>
									<td>이름</td>
									<td><input type="text" name="name" /></td>
									<td>비밀번호</td>
									<td><input type="password" name="password" /></td>
								</tr>
								<tr>
									<td colspan=4><textarea name="content" id="content"></textarea></td>
								</tr>
								<tr>
									<td colspan=4 align=right><input type="submit"
										VALUE=" 확인 " /></td>
								</tr>
							</table>
							</form>
							<ul>
								<li><c:forEach items="${list}" var="GuestbookVO">
										<table width=510 border=1>
											<tr>
												<td>${GuestbookVO.no}</td>
												<td>${GuestbookVO.name}</td>
												<td>${GuestbookVO.reg_date}</td>
												<td><a
													href="${pageContext.request.contextPath}/guestbook/deleteform?no=${GuestbookVO.no}">삭제</a></td>
											</tr>
											<tr>
												<td colspan=4>${GuestbookVO.content}</td>
											</tr>
										</table>
									</c:forEach> <br /></li>
							</ul>
							</div>
							<!-- /guestbook -->
							</div>
							<!-- /content -->
							</div>
							<!-- /wrapper -->

							<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

							</div>
							<!-- /container -->
</body>
</html>