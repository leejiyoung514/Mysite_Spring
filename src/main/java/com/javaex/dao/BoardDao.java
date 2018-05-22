package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	//글 삽입기능
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
	//레코드 갯수 계산
	public int countArticle() {
		return sqlSession.selectOne("board.countArticle");
	}
	//글 검색하기
	public List<BoardVo> search(int start, int end, String kwd){
		Map<String, Object> map=new HashMap<>();
		map.put("start", start); //맵에 자료 저장
		map.put("end", end);
		map.put("kwd", kwd);
		return sqlSession.selectList("board.searchList", map);
	}
	//리스트 출력
	public List<BoardVo> listAll(int start, int end){
		Map<String, Object> map=new HashMap<>();
		map.put("start", start); //맵에 자료 저장
		map.put("end", end);
		//mapper에는 2개 이상의 파라미터 값을 전달 할 수 없음 (vo 또는 map사용)
		return sqlSession.selectList("board.listAll", map);
	}
	
/*	//글 검색하기
	public List<BoardVo> search(String kwd){
		return sqlSession.selectList("board.search",kwd);
	}*/
	
/*	
	//리스트 출력
		public List<BoardVo> getlist(){
			return sqlSession.selectList("board.list");
		}
*/
}
