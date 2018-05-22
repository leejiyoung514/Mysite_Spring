<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript">
 function list(page){
	 location.href="${pageContext.request.contextPath}/board/list?curPage="+page;
 }

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="content">
			<div id="board">
				<form id="search_form"
					action="${pageContext.request.contextPath}/board/list"
					method="post">
					<select  id="kwd" name="search_option">
					<option value="all"
						<c:out value="${map.search_option=='all'?'selected':''}"/>>이름+내용+제목</option>
					<option value="name"
						<c:out value="${map.search_option=='name'?'selected':''}"/>>이름</option>
					<option value="content"
						<c:out value="${map.search_option=='content'?'selected':''}"/>>내용</option>
					<option value="title"
						<c:out value="${map.search_option=='title'?'selected':''}"/>>제목</option>
                    </select>
					
					<input type="text" id="kwd" name="kwd" value=""> <input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
				
					<c:forEach items="${map.list}" var="boardVO">
						<tr>
							<td>${boardVO.no}</td>
							<td><a
								href="${pageContext.request.contextPath}/board/read?no=${boardVO.no}">${boardVO.title}</a></td>
							<td>${boardVO.user_name}</td>
							<td>${boardVO.hit}</td>
							<td>${boardVO.reg_date}</td>
							<td><c:if
									test="${sessionScope.authUser.no == boardVO.user_no}">
									<a
										href="${pageContext.request.contextPath}/board/delete?no=${boardVO.no}"
										class="del">삭제</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</table>

				<div class="pager">
					<ul>
						<c:if test="${map.pager.curBlock > 1}">
							<a href="#" onclick="list('1')">◀◀</a>
						</c:if>
						<c:if test="${map.pager.curBlock > 1}">
							<a href="#" onclick="list('${map.pager.prevPage}')">◀</a>
						</c:if>
						
						<c:forEach var="num" begin="${map.pager.blockBegin}"     
							end="${map.pager.blockEnd}">
							<c:choose>
								<c:when test="${num == map.pager.curPage}">
								<!--현재 페이지인 경우 하이퍼링크 제거  -->
									<span style="color:red;">${num}</span>
								</c:when>
								<c:otherwise>
									<a href="#" onclick="list('${num}')">${num}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<c:if test="${map.pager.curBlock < map.pager.totBlock}">
							<a href="#" onclick="list('${map.pager.nextPage}')">▶</a>
						</c:if>
						<c:if test="${map.pager.curPage < map.pager.totPage}">
							<a href="#" onclick="list('${map.pager.totPage}')">▶▶</a>
						</c:if>
						
					</ul>
				</div>




				<c:if test="${! empty sessionScope.authUser}">
					<div class="bottom">
						<a href="${pageContext.request.contextPath}/board/writeform"
							id="new-book">글쓰기</a>
					</div>
				</c:if>
			</div>
		</div>

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>
</body>
</html>