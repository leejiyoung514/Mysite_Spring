package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;



@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession; 
	
	public int insert(GuestbookVo guestbookVo) {	
		return sqlSession.insert("guestbook.insert", guestbookVo); 
		//쿼리문 이름 정해줌
	}
	
	//글 삭제하기
	public int delete(Map<String, Object> map) {
		
		return sqlSession.delete("guestbook.delete", map);
			
	}
	
	public List<GuestbookVo> getlist(){
		return sqlSession.selectList("guestbook.list");
	}
	
}
