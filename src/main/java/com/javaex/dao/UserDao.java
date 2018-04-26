package com.javaex.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession; 
	
	public int insert(UserVo userVo) {	
		return sqlSession.insert("user.insert", userVo); 
		//쿼리문 이름 정해줌
	}
	
	public UserVo getUser(String email, String password) {
		Map<String, String> userMap=new HashMap<String, String>();
		userMap.put("email", email);
		userMap.put("password", password);
	
		return sqlSession.selectOne("user.selectUserByEmailPw",userMap);
	}
	
	public UserVo getUser(int no) {
		System.out.println("다오 "+no);
		return sqlSession.selectOne("user.selectUserByno", no);
	}
	
	public int update(UserVo userVo) {
		System.out.println("다오 "+userVo.toString());
		return sqlSession.update("user.update", userVo);
		
	}
	
	
}
