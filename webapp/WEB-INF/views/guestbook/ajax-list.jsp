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

<link
	href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-ui.min.js"></script>


<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>


		<div id="wrapper">
			<div id="content">
				<div id="guestbook">

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
								<td colspan=4 align=right><input class="btn btn-default" type="button" id="btnAdd"
									VALUE=" 확인 " /></td>
							</tr>
						</table>
						<input id="btnModal" type="hidden" value="삭제창" />
						</form>

						<div id="scroll" style="overflow-y: scroll;">
							<ul id="guestbook-list">
								<!--방명록 리스트가 올자리  -->
								<%--  <li><c:forEach items="${list}" var="GuestbookVO">
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
									</c:forEach> <br /></li>  --%>
							</ul>
							<button class="btn">더보기</button>
						</div>
						<!-- /scroll -->
						</div>
						<!-- /guestbook -->
						</div>
						<!-- /content -->
						</div>
						<!-- /wrapper -->

						<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

						</div>
						<!-- /container -->

						<!-- 삭제팝업(모달)창 -->
						<div class="modal fade" id="del-pop">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title">방명록삭제</h4>
									</div>
									<div class="modal-body">
										<label>비밀번호</label> <input type="password"
											name="modalPassword" id="modalPassword"><br>
										<div id="modalmsg"></div>
										<input type="text" name="modalPassword" value=""
											id="modalNo"> <br>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">취소</button>
										<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->
</body>

<script type="text/javascript">   

$(document).ready(function(){ //페이지가 로드되면 데이터를 가져오고 page를 증가시킨다
     var start=1;
     var end=10;       
     fetchList(start, end);
	 

     $(window).on("scroll", function(){ //스크롤 최하단으로 내려가면 리스트를 조회하고 page를 증가시킨다.
        if($(window).scrollTop() >= $(document).height()-$(window).height()){
           start+=10;
           end+=10;
	      fetchList(start, end);
         }
    }); 
});

function fetchList(start, end){
	//리스트 요청
			$.ajax({
				
				url : "${pageContext.request.contextPath}/api/gb/list",		
				type : "post",
				data : {start: start, end:end},

				dataType : "json",
				success : function(list){
					/*성공시 처리해야될 코드 작성*/
					//list덩어리로 와서 꺼내야함, 꺼내기전에 찍어봄
					console.log(list);			
					//자바스크립트 문법
					for(var i=0; i<list.length; i++){
					    render(list[i],"down");			
					}
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
}

//그림그리는 함수 ---vo를 하나 받을것임--- 던저줘야함
function render(guestbookVo, updown){
	var str="";
	str +="<li id='gb-" + guestbookVo.no + "'>";
    str +="   <table>";
    str +="        <tr>";
    str +="             <td data-delno='"+guestbookVo.no+"'>["+ guestbookVo.no +"]</td>";
    str +="             <td>"+ guestbookVo.name +"</td>";
    str +="             <td>"+ guestbookVo.reg_date +"</td>";
    str +="             <td>"+"<input class='btn btn-danger' data-delno='"+guestbookVo.no+"' type='button' value='삭제'/></td>";
    str +="        </tr>";
    str +="        <tr>";
    str +="           <td colspan=4>";
    str +=             guestbookVo.content;
    str +="           </td>";
    str +="       </tr>";
    str +="    </table>";
    str +="    <br>";
    str +="</li>";          
	
	if(updown=="up"){
		$("#guestbook-list").prepend(str);	//내용물에 위에 붙는거 
	}else if(updown=="down"){
		$("#guestbook-list").append(str);	//내용물에 밑에 붙는거 
	}else{
		console.log(오류);
	}
		
}


//삭제버튼을 클릭했을때 
$("#guestbook-list").on("click", "input", function(){
     console.log("삭제");
     //여기서 id를 꺼내서 보내야함 
     /* var dom=$(this);
     var guestbookno=dom.attr("id"); */
     var guestbookno=$(this).data("delno");
     
     $("#modalNo").val(guestbookno);
     console.log(guestbookno);
     $("#del-pop").modal();        
});

//팝업창 삭제버튼 클릭
$("#btn_del").on("click",function(){
	 console.log("팝업창 삭제버튼 클릭")
	 var $modalNo = $("#modalNo").val(),
	 	 $password = $("#modalPassword").val(); 
	 console.log($password);      
     console.log($modalNo);
     
       $.ajax({
			url : "${pageContext.request.contextPath }/api/gb/delete",		
			type : "post",
			/* contentType : "application/json", */
			data : {
					no: $modalNo,  
					password : $password
			},
			dataType : "json",
			success : function(result){
				/*성공시 처리해야될 코드 작성*/
				if(result==false){ //실패
					$("#modalmsg").text("비밀번호를 다시 입력해주세요");
				}else{//성
				  $("#gb-"+$modalNo).remove();
				  $("#del-pop").hide(); 
				}			
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});  
});     

/* $("#btnModal").on("click", function(){
console.log("모달");
	$("#del-pop").modal();
});
*/

//저장 버튼을 클릭했을때
$("#btnAdd").on("click", function(){
            console.log("btnadd");
     var name=$("[name=name]").val();
     var password=$("[name=password]").val();
     var content=$("[name=content]").val();
     console.log(name);
     console.log(password);
     console.log(content);
 
            $.ajax({
                   url : "${pageContext.request.contextPath }/api/gb/add",              
                   type : "post",
                   /* contentType : "application/json", */
                   data : {name: name, password : password, content : content},
                   dataType : "json",
                   success : function(guestbookVo){
                         /*성공시 처리해야될 코드 작성*/
                         render(guestbookVo,"up");
                         $("[name=name]").val("");
                         $("[name=password]").val("");
                         $("[name=content]").val("");
                   },
                   error : function(XHR, status, error) {
                         console.error(status + " : " + error);
                   }
            });
});
 
</script>
</html>


