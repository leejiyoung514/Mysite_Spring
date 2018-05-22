package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
    
	@Autowired
	private GalleryDao galleryDao;
	
	public int restore(MultipartFile file, int user_no) {
		String saveDir = "D:\\spring\\upload";
		//오리지널 파일명
		String orgName=file.getOriginalFilename();
		System.out.println("orgName: "+ orgName);
		//확장자
		String exName=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		System.out.println("exName: "+exName);
		//저정파일이름
		String saveName=System.currentTimeMillis()+UUID.randomUUID().toString()+exName;
		//파일경로
		String filePath = saveDir + "\\" +saveName;
		System.out.println("filePath: "+filePath);
		//파일사이즈
		long fileSize=file.getSize();
		System.out.println("fileSize: "+fileSize);
		
		GalleryVo galleryVo= new GalleryVo(filePath, orgName, saveName, fileSize, exName, user_no);
		System.out.println("서비스 :"+galleryVo.toString());
		
		try {
			byte[] fileData=file.getBytes();
			OutputStream out=new FileOutputStream(saveDir +"/"+ saveName);
			BufferedOutputStream bout=new BufferedOutputStream(out);
			
			bout.write(fileData);
			if(bout!=null) {
				bout.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return galleryDao.insert(galleryVo);
	}

	public List<GalleryVo> getImages(){
		return galleryDao.getImages();
	}
	
	public boolean delete(Map<String, Object> map) {
		boolean flag=false;
		int result=galleryDao.delete(map);
		System.out.println(result+"건 삭제(서비스에서 확인)");
		if(result==0) {//삭제실패
			flag=false;
		}else{
			flag=true;
		}
		return flag;
	}
	
	
	
}
