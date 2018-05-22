package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) { //정의해주는것
	   return userDao.insert(userVo);
	}
	
	public UserVo login(String email, String password) {
		return userDao.getUser(email, password);
	}
	
	public UserVo getUser(int no) {
		System.out.println("서비스 "+no);
		return userDao.getUser(no);
	}
	
	public int update(UserVo userVo) {
		return userDao.update(userVo);
	}

	public Boolean emailCheck(String email) {
		
		email=userDao.emailCheck(email);
		System.out.println("이메일체크"+email);
		Boolean isExist=true;
		if(email==null) {
			isExist=true;
		}else{
			isExist=false;
		}
		return isExist;
	}
	
	
}
