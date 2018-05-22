<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/gallery.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>

<style>
.card {
	border: 1px solid black;
	float: left;
	margin-right: 5px;
	margin-bottom: 5px;
}

.cardImg {
	width: 100%;
	max-width: 128px;
	vertical-align: middle"
}
</style>

<title>Mysite</title>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="wrapper">
			<div id="content">

				<div id="gallery">
					<form method="post"
						action="${pageContext.request.contextPath}/gallery/upload"
						enctype="multipart/form-data">
						<table>
							<tr>
								<td>첨부파일</td>
								<td><input  class="btn btn-default" type="file" name="file"></td>
								<td><input  class="btn btn-default" type="submit" value="파일업로드"></td>
							</tr>
						</table>
					</form>


					<ul id="gallery-list" >
					 <c:forEach items="${list}" var="galleryVo">
						 	<li id="${galleryVo.no}">
								<div>
									<img id="" src="${pageContext.request.contextPath}/upload/${galleryVo.saveName}" data-user_no="${galleryVo.user_no}" 
									 data-delno="${galleryVo.no}" data-imgview="${pageContext.request.contextPath}/upload/${galleryVo.saveName}">
								</div>
							</li> 
						</c:forEach> 
					</ul>
				</div>
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
					<h4 class="modal-title">이미지</h4>
				</div>
				<div class="modal-body" >
				<!-- 	<label>비밀번호</label> <input type="password" name="modalPassword"
						id="modalPassword"><br> -->
						
					<img id="imgview" src="" ><br>
					<input type="text" name="modalNo" value="" id="modalNo">이미지번호
					<input type="text" name="user_no" value="" id="user_no">사용자번호
					<br> 
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
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
    	  //이미지클릭시
    	$("#gallery-list").on("click","img",function(){
    		console.log("모달창");
    		var imgview=$(this).data("imgview");
    		var imgno=$(this).data("delno");
    		var user_no=$(this).data("user_no");
    		console.log(imgview);
    		console.log(imgno);
    		console.log(user_no);
    		//모달창에 이미지 및 값 넣고 창 띄우기
    		$("#imgview").attr("src", imgview);
    		$("#modalNo").val(imgno);
    		$("#user_no").val(user_no);
    		
    		$("#del-pop").modal();
    		$("#del-pop").show();
    		console.log(user_no);
    	});
 
    //모달 삭제버튼 클릭시
	$("#btn_del").on("click",function(){
		 console.log("팝업창 삭제버튼 클릭")
		 var $modalNo = $("#modalNo").val();
		 var $user_no= $("#user_no").val();
		 console.log($modalNo);
		 console.log($user_no);
		 
		 $.ajax({
				url : "${pageContext.request.contextPath}/gallery/delete",		
				type : "post",
				data : {
						no: $modalNo,  
						user_no: $user_no
				},
				dataType : "json",
				success : function(result){
					/*성공시 처리해야될 코드 작성*/
				    if(result==true){ 
					  $("#"+$modalNo).remove(); 
					   /* $("#del-pop").modal("hide");  */ 
					  $("#del-pop").hide(); 
					}else{
						console.log("error");
					}
				},
				error : function(XHR, status, error) {
					console.error(status + " : " + error);
				}
			});
	});
</script>

</html>

