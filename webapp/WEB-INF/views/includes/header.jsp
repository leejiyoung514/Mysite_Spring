<%@page import="com.javaex.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- session���� ���� ������ uservo�������� �ּҰ� ����־� �ּҳѱ�°� 
     �α��εǸ� �ִ°Ű� �α��ξȵǸ� null-->
<%
	UserVo authUser = (UserVo) session.getAttribute("authUser");
%>
	
<div id="header">
	<h1> # JOE  </h1>
	<ul>
	<c:choose>
	   <c:when test="${empty authUser}" >
		<!-- �α��� �� -->
		<li><a href="${pageContext.request.contextPath}/user/loginform">�α���</a></li>
		<li><a href="${pageContext.request.contextPath}/user/joinform">ȸ������</a></li>
    	</c:when>
		<c:otherwise>
		<!-- �α��� �� -->
	    <li><a href="${pageContext.request.contextPath}/user/modifyform">ȸ����������</a></li>
		<li><a href="${pageContext.request.contextPath}/user/logout">�α׾ƿ�</a></li> 
		<li> ${authUser.name }�� �ȳ��ϼ���^^;</li>		
	   	</c:otherwise>
	</c:choose>
	
	
	
	
	
	
	
<%-- 	<%  
	    if(authUser==null){
	%>
		<!-- �α��� �� -->
		<li><a href="/mysite/user?a=loginform">�α���</a></li>
		<li><a href="/mysite/user?a=joinform">ȸ������</a></li>
    <%  
	    }else{
	%>
		<!-- �α��� �� -->
	    <li><a href="/mysite/user?a=modifyform&no=<%=authUser.getNo()%>">ȸ����������</a></li>
		<li><a href="/mysite/user?a=logout">�α׾ƿ�</a></li> 
		<li> <%=authUser.getName()%>�� �ȳ��ϼ���^^;</li>		
	<%  
	    }
	%> --%>
	</ul>
</div>
<!-- /header -->