package com.javaex.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.JSONResult;

@Controller
@RequestMapping(value="/api/gb")
public class ApiGuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;

/*	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST) 
	public List<GuestbookVo> list() {
		System.out.println("ajax-list : guestbook");
		List<GuestbookVo> list=guestbookService.getlist();
		System.out.println(list.toString());
	    return list;
	}*/

	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.POST) 
	public List<GuestbookVo> listAll(@RequestParam("start") int start, @RequestParam("end") int end) {
		System.out.println("ajax-list : guestbook");
		int count=guestbookService.countArticle();
		System.out.println("방명록 글갯수: "+count);
		List<GuestbookVo> list=guestbookService.AllList(start, end);
		System.out.println(list.toString());
	    return list;
	}
	
/*	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST) 
	public JSONResult add(@ModelAttribute GuestbookVo guestbookVo) {
		JSONResult resultVo=new JSONResult(); 
		System.out.println("add");
		System.out.println(guestbookVo.toString());
	    GuestbookVo vo=guestbookService.write(guestbookVo);
		//3개 정보로 no, 시간까지 있는 애들 받아옴 
	    System.out.println("controller"+vo.toString());
	    return resultVo;
	}*/
	
	
	@ResponseBody
	@RequestMapping(value="/add", method=RequestMethod.POST) 
	public GuestbookVo add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("add");
		System.out.println(guestbookVo.toString());
	    GuestbookVo vo=guestbookService.write(guestbookVo);
		//3개 정보로 no, 시간까지 있는 애들 받아옴 
	    System.out.println("controller"+vo.toString());
	    return vo;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public boolean delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		System.out.println(no);
		System.out.println(password);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);
		boolean result = guestbookService.delete(map);
		System.out.println(result+"건 삭제");
		return result;
	}
		
	
	
}
