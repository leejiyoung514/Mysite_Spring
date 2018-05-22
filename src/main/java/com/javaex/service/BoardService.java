package com.javaex.service;

import java.util.List;

import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.SessionScope;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;

	// 글 삽입
    public int insert(BoardVo boardVo) {
		
		return boardDao.insert(boardVo);
	}


	// 글 목록 가져오기+ 조회수 증가 +키워드 검색

	public BoardVo read(int no) {
    	boardDao.increaseHit(no); 
    	BoardVo vo = boardDao.getArticles(no);
		
		return vo;
	}

	// 글 목록 가져오기+ 조회수 증가 +키워드 검색
   
/*	public BoardVo read(int no) {
		BoardVo vo = boardDao.getArticles(no);
		boardDao.increaseHit(no);                         
		return vo;
	}*/

	// 뷰페이지에 글 목록 가져가기
	public BoardVo getArticles(int no) {
		return boardDao.getArticles(no);
	}

	// 글 수정
	public int update(BoardVo boardVo) {
		return boardDao.update(boardVo);
	}

	// 글 삭제기능
	public int delete(int no) {
		return boardDao.delete(no);
	}

	// 레코드 갯수 계산
	public int countArticle() {
		return boardDao.countArticle();
	}

	// 검색 +리스트 출력
	@Transactional
	public List<BoardVo> listAll(int start, int end, String kwd) {
		List<BoardVo> list;
		if (kwd != "") {
			list = boardDao.search(start, end, kwd);
		} else {
			list = boardDao.listAll(start, end);
		}
		System.out.println("서비스다"+list.toString());
		return list;
	}

/*	// 검색 +리스트 출력
	public List<BoardVo> getlist(String kwd) {
		List<BoardVo> list;
		if (kwd != "") {
			list = boardDao.search(kwd);
		} else {
			list = boardDao.getlist();
		}
		return list;
	}*/

}
