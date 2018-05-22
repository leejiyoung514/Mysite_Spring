package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/joinform", method = RequestMethod.GET)
	public String joinform() {
		System.out.println("joinform");
		return "user/joinform";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("join");
		System.out.print("회원가입정보전달"+userVo.toString());
		userService.join(userVo); // 서비스에 넘기겠다
		return "user/joinsuccess";
	}
   //회원가입시 post로 넘어와야함
	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginform() {
		System.out.println("loginform");
		return "user/loginform";  //viewresolver가 확인해서 추가주소달아서 보내줌
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam("email") String email,
			   @RequestParam("password") String password,
			   HttpSession session //섹션 주소 받음
			) { //디스패처 서블릿에 파람 (이메일, 패스워드)요청 
		UserVo authUser= userService.login(email,password);
	   
		//조건을 줘서 authUser가 있으면 메인페이지로
		if(authUser !=null) {
			session.setAttribute("authUser", authUser); //섹션에 담음			
		    return "redirect:/main";
		  //조건을 줘서 authUser가 있으면 로그인폼으로
		}else {
			return "redirect:/user/loginform?result=fail"; //리턴 생략하면 오류남 
		}		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
	    session.removeAttribute("authUser");
	    session.invalidate();
		return"redirect:/main";
	}
	
	@RequestMapping(value="/modifyform", method=RequestMethod.GET)
	public String modifyform(HttpSession session, Model model) {
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		int no=authUser.getNo();
		System.out.println(no);
		UserVo userVo= userService.getUser(no);
	    System.out.println(userVo.toString());
	    model.addAttribute("uservo", userVo);
		return "user/modifyform";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
    	UserVo authUser = (UserVo)session.getAttribute("authUser");
    	int no=authUser.getNo();
    	System.out.println(no);
    	userVo.setNo(no);
    	int count= userService.update(userVo);
		System.out.println(count+" 건 업데이트");
		authUser.setName(userVo.getName());
		return"redirect:/main";
	}
 
    @ResponseBody
	@RequestMapping(value="/emailcheck", method=RequestMethod.POST)
	public Boolean isExist(@RequestParam("email")String email ) {
		System.out.println(email);
		Boolean isExist=userService.emailCheck(email);
		System.out.println("컨트롤로"+isExist);
		return isExist;
	}	
	

}
