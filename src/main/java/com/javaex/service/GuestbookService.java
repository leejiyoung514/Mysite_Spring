package com.javaex.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
  
	@Autowired
	private GuestbookDao guestbookDao;
	
	public int insert(GuestbookVo guestbookVo) {
		return guestbookDao.insert(guestbookVo);
	}

	public int delete(Map<String, Object> map) {
		return guestbookDao.delete(map);
	}
	
	public List<GuestbookVo> getlist(){
		return guestbookDao.getlist();
	}
	
}

