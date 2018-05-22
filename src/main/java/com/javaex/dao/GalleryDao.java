package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insert(GalleryVo galleryVo) {
		System.out.println("다오 : "+galleryVo.toString());
		int count= sqlSession.insert("gallery.insert", galleryVo);
		System.out.println("다오 후 : "+galleryVo.toString());
		 System.out.println(count);
		 return count;
	}
	
	public List<GalleryVo> getImages(){
		return sqlSession.selectList("gallery.getImages");
	}

    public int delete(Map<String, Object> map) {
    	return sqlSession.delete("gallery.delete", map);
    }
	
	
	
}
