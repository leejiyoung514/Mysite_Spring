package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	//글 삽입
	public int insert(BoardVo boardVo) {
		return sqlSession.insert("board.insert", boardVo);
	}
	
	//뷰페이지에 글 목록 가져가기
	public BoardVo getArticles(int no) {
		return sqlSession.selectOne("board.getArticles", no);
	}
	//조회수 증가
	public int increaseHit(int no) {
		return sqlSession.update("board.increaseHit", no);
	}
	//글 수정하기
	public int update(BoardVo boardVo) {
		return sqlSession.update("board.update", boardVo);
	}
	//글삭제하기
	public int delete(int no) {
		return sqlSession.delete("board.delete", no);
	}
	//글 검색하기
	public List<BoardVo> search(String kwd){
		return sqlSession.selectList("board.search",kwd);
	}
	//리스트 출력
	public List<BoardVo> getlist(){
		return sqlSession.selectList("board.list");
	}
	
}
