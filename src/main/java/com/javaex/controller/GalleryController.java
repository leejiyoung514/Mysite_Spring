package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value="/gallery")
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;

/*	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String form(HttpSession session, Model model) {
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		int user_no=authUser.getNo();
		//System.out.println(authUser.toString());
		System.out.println("gallery: list");
		List<GalleryVo> list=galleryService.getImages();
		System.out.println(list.toString());
		model.addAttribute("list", list);
		
		return"gallery/list";
	}*/

	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(HttpSession session, Model model) {
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		int user_no=authUser.getNo();
		//System.out.println(authUser.toString());
		System.out.println("gallery: list");
		List<GalleryVo> list=galleryService.getImages();
		System.out.println(list.toString());
		model.addAttribute("list", list);
		
		return"gallery/list";
	}
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, GalleryVo gallerVo, Model model, HttpSession session) {
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		System.out.println(authUser.toString());
		int user_no=authUser.getNo();
		System.out.println(file.getOriginalFilename());
		int result=galleryService.restore(file, user_no);
		System.out.println("업로드성공 건수: "+result);
		return "redirect:/gallery/list";
	}
    @ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public boolean delete(@RequestParam("no") int no, @RequestParam("user_no") int user_no) {
		System.out.println("컨트롤러"+"delete");
		System.out.println(no +" "+user_no);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("no", no);
		map.put("user_no", user_no);
		boolean result=galleryService.delete(map);
		System.out.println(result);
		return result;
	}
	
	
}
