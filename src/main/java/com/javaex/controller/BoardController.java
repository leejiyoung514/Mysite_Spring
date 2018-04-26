package com.javaex.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	//게시물 작성페이지를 보내기
	@RequestMapping("/writeform")
	public String writeform() {
		return "board/write";
	}
	//글쓰기
	@RequestMapping("/write")
	public String write(@ModelAttribute BoardVo boardVo, HttpSession session) {
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		int no=authUser.getNo();
		System.out.println(no);
		boardVo.setUser_no(no);
		System.out.println("write");
		System.out.println(boardVo.toString());
		int count=boardService.insert(boardVo);
		System.out.println(count + " 건 등록");
		return "redirect:/board/list";
	}
	
	@RequestMapping("/read")
	public String read(@RequestParam("no")int no, Model model, HttpSession session) {
		int hit=boardService.increaseHit(no);
		System.out.println(hit+" 회 조회수 증가");
		BoardVo boardVo=boardService.getArticles(no);
		System.out.println("글 가져오기 "+boardVo.toString());
		model.addAttribute("boardVo", boardVo);
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		session.setAttribute("authUser", authUser);
		return "board/view";
	}
	
	@RequestMapping("/modifyform")
	public String modifyform(@RequestParam("no")int no, Model model) {
		System.out.println(no);
		BoardVo boardVo=boardService.getArticles(no);
		System.out.println("글 가져오기 "+boardVo.toString());
		model.addAttribute("boardVo", boardVo);
		return "board/modify";
	}
	//글수정기능
	@RequestMapping("/modify")
	public String modify(@ModelAttribute BoardVo boardVo, Model model) {
		int count=boardService.update(boardVo);
	    System.out.println(count+" 건 업데이트");
		return "redirect:/board/list";
	}
	//삭제기능
	@RequestMapping("/delete")
	public String delete(@RequestParam("no")int no) {
        int result=boardService.delete(no); 
		System.out.println(result+" 건 삭제");
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam("kwd") String kwd, Model model) {
		List<BoardVo> list=boardService.search(kwd);
		model.addAttribute("list", list);
		return "board/list";
	}
	
	//게시물 가져오기
	@RequestMapping("/list")
	public String list(Model model, HttpSession session){
		
		System.out.println("list");
		List<BoardVo> list=boardService.getlist();
		System.out.println(list.toString());
		model.addAttribute("list", list);
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		session.setAttribute("authUser", authUser);
		return "board/list";
	}


	
	
}
