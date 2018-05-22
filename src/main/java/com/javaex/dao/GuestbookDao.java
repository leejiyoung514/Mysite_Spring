package com.javaex.dao;

import java.util.HashMap;
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
	
	public int insert2(GuestbookVo guestbookVo) {	
		System.out.println("전"+guestbookVo.toString()); //no와 시간이 없을꺼야
		sqlSession.insert("guestbook.insert2", guestbookVo); //select 키가있는 쿼리문을 한 후 
		System.out.println("후"+guestbookVo.toString());
		//쿼리문 이름 정해줌
		return guestbookVo.getNo();
	}
	
	public GuestbookVo selectGuestBook(int no) {
		return sqlSession.selectOne("guestbook.selectGuestBook", no);
	}
	
	//글 삭제하기
	public int delete(Map<String, Object> map) {
		System.out.println(map);
		
		return sqlSession.delete("guestbook.delete", map);
			
	}
	
	//레코드 갯수 계산
	public int countArticle() {
		return sqlSession.selectOne("guestbook.countArticle");
	}
	
	public List<GuestbookVo> getlist(){
		return sqlSession.selectList("guestbook.list");
	}
	
	public List<GuestbookVo> AllList(int start, int end){
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("start", start);
		map.put("end", end);
		return sqlSession.selectList("guestbook.listAll", map);
	}
	
}
