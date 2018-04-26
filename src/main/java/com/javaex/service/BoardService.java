package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	//글 삽입
	public int insert(BoardVo boardVo) {
		return boardDao.insert(boardVo);
	}
	
	//뷰페이지에 글 목록 가져가기
	public BoardVo getArticles(int no) {
		return boardDao.getArticles(no);
	}
	
	//조회수 증가 
	public int increaseHit(int no) {
		return boardDao.increaseHit(no);
	}
	
	//글 수정 
	public int update(BoardVo boardVo) {
		return boardDao.update(boardVo);
	}
	
	//글 삭제기능
	public int delete(int no) {
		return boardDao.delete(no);
	}
	
	//글 검색기능
	public List<BoardVo> search(String kwd){
		return boardDao.search(kwd);
	}
	
	//리스트 출력
	public List<BoardVo> getlist(){
		return boardDao.getlist();
	}
	
	
}
