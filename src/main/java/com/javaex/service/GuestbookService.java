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

	public GuestbookVo write(GuestbookVo guestbookVo) {
		// insert
		// 넘버없고
		int no = guestbookDao.insert2(guestbookVo);
		// 넘버 있고
		// select
		return guestbookDao.selectGuestBook(no);
	}

	public boolean delete(Map<String, Object> map) {
		int result = guestbookDao.delete(map);
		boolean flag=false;
		if (result == 0) { //삭제실패
			flag = false;
		} else if (result == 1){ //삭제성공
			flag = true;
		}
		return flag;
	}
	
	// 레코드 갯수 계산
	public int countArticle() {
		return guestbookDao.countArticle();
	}

	public List<GuestbookVo> getlist() {
		return guestbookDao.getlist();
	}
	
	public List<GuestbookVo> AllList(int start, int end) {
		return guestbookDao.AllList(start, end);
	}


}
