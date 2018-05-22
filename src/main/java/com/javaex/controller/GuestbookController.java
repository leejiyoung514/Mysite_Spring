package com.javaex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
@RequestMapping(value = "/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("insert");
		System.out.println(guestbookVo.toString());
		int count = guestbookService.insert(guestbookVo);
		System.out.println(count + " 건 등록");
		return "redirect:/guestbook/list";
	}

	@RequestMapping(value = "/deleteform")
	public String deleteform() {
		System.out.println("deleteform");
		return "guestbook/deleteform";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("password", password);
		System.out.println("delete");
		System.out.println("넘버출력" + no + " " + password);
		boolean result = guestbookService.delete(map);
		System.out.println(result + "건 삭제");
		return "redirect:/guestbook/list";
	}

	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("list");
		List<GuestbookVo> list = guestbookService.getlist();
		System.out.println(list.toString());
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/list-ajax", method=RequestMethod.GET)
	public String ajaxList() {
		System.out.println("list-ajax");
		return "guestbook/ajax-list";
	}

}
