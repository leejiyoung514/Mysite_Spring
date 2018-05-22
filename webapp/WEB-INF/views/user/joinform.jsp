<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

<body>

	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp"></jsp:include>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp"></jsp:include>


		<div id="wrapper">
			<div id="content">
				<div id="user">

					<form id="join-form" name="joinForm" method="post"
						action="${pageContext.request.contextPath}/user/join">
						<label class="block-label" for="name">이름</label> <input id="name"
							name="name" type="text" value=""> 
						
						<label class="block-label" for="email">이메일</label> 
						<input id="email"
							name="email" type="text" value=""> <input
							id="btnEmailCheck" type="button" value="id 중복체크">
						<div id="msg_email"></div>

						<label class="block-label">패스워드</label> <input id="password" name="password"
							type="password" value="">

						<fieldset>
							<legend>성별</legend>
							<label>여</label> <input class="gender" type="radio" name="gender" value="female"
								class="gender" checked="checked"> <label>남</label> <input class="gender" type="radio"
								name="gender" value="male">
						</fieldset>

						<fieldset>
							<legend>약관동의</legend>
							<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
							<label>서비스 약관에 동의합니다.</label>
						</fieldset>

						<input type="submit" value="가입하기" id="submitCheck">

					</form>

				</div>
				<!-- /user -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->


		<jsp:include page="/WEB-INF/views/includes/footer.jsp"></jsp:include>


	</div>
	<!-- /container -->

</body>

<script type="text/javascript">
  
       $("#btnEmailCheck").on("click", function(){
    	   console.log("이메일체크");
    	   var email=$("#email").val();
    	   console.log(email);
    	   
    		$.ajax({
			url : "${pageContext.request.contextPath}/user/emailcheck",		
			type : "post",
			data : {email : email},

			dataType : "json",
			success : function(isExist){
				if(isExist==true){
					$("#msg_email").html("사용 가능한 아이디 입니다.");
					$("#msg_email").css("color","blue");
				}else{
					$("#msg_email").html("사용중인 아이디 입니다.");
					$("#msg_email").css("color","red");
				}				
				console.log(isExist);
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});


   $("#join-form").on("submit", function(){
	   var status = finalCheck();//입력 폼에 입력한 상황체크
	   
	   if(status == true){
		   return true;
	    }else{
		    console.log("aaaa");
		    return false;
	     }
      });
  
   
   //사용자가 입력 폼에 입력한 상황을 체크
   function finalCheck(){
	    
	   if(!$("#name").val()){ //name을 입력하지 않으면 수행
		   alert("name을 입력해주세요");
           $("#name").focus(); //입력 포커스 이동
           return false; //함수종료
        }
	   if(!$("#email").val()){ //email을 입력하지 않으면 수행
		   alert("email을 입력해주세요");
           $("#email").focus(); //입력 포커스 이동
           return false; //함수종료
        }
	   if(!$("#password").val()){ //password을 입력하지 않으면 수행
		   alert("password를 입력해주세요");
           $("#password").focus(); //입력 포커스 이동
           return false; //함수종료
        }
	   if(!$(".gender").val()){ //gender을 입력하지 않으면 수행
		   alert("gender를 선택해주세요");
           $(".gender").focus(); //입력 포커스 이동
           return false; //함수종료
        }
	   if($("#agree-prov:checked").length<1){ //agree-prov을 입력하지 않으면 수행
		   alert("약관동의를 선택해주세요");
           $("#agree-prov").focus(); //입력 포커스 이동
           return false; //함수종료
        }
	   return true;
   }
   
   
</script>

</html>

