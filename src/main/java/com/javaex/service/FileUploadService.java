package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.vo.FileVo;

@Service
public class FileUploadService {

	//파일저장
	public FileVo restore(MultipartFile file) {
		String saveDir = "D:\\spring\\upload";
		//경로가 바뀔수있어서 전역변수로 빼줌(문자열임)
				
		//오리지날 파일명 aaa.jpg
		  String orgName=file.getOriginalFilename();
		  System.out.println("orgName: "+ orgName);
		  
		//확장자
		  String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		     //담을 이름 .jsp이렇게 담김 / 확장자는 .으로 구분
		  System.out.println("exName: "+exName);
		
		//저장파일이름
		  String saveName =System.currentTimeMillis() + UUID.randomUUID().toString()+exName;
		       //지금시간을 나열해주는 애가 있음 (동시에 붙지 않는이상 똑같지 않지만)+ 만약을 위해 긴 난수를 줌
		       //저장파일만으로 구분할 수 없기 때문에 DB로 확인해야함 
		
		 //파일패스
		   String filePath = saveDir + "\\" + saveName;
		   System.out.println("filePath: "+filePath);
            //본인서버 하드디스크에 어디다저장할건지 , D:upload/ 라고 만들어서 저장할 것임  
		     //어느 디렉토리에 어느 파일명으로 저장햇는지 저정할껏임 (saveDir은 전역변수로 빼줌)
				   
		//파일사이즈
		   long fileSize=file.getSize();
		   System.out.println("fileSize: "+fileSize);
		   //return 형은 long임
		   
		   //파일을 넣어줌 no빼고
		   FileVo fileVo =new FileVo(filePath, orgName, saveName, fileSize);
		   System.out.println(fileVo.toString());
		   
		   //다오 연결 DB저장
		   
		   //파일 서버 복사 
		     try {
				byte[] fileData=file.getBytes(); //파일이 없을 수 있으니 예외처리
				OutputStream out=new FileOutputStream(saveDir +"/"+ saveName);
				BufferedOutputStream bout = new BufferedOutputStream(out);
				
				bout.write(fileData);
                //빨대가 있으면 닫아버려라 연결이 정상적으로 되면 닫아버려라    				
				if(bout != null) {
					bout.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  		   
		   return fileVo;	  
	}
	
	
	

	
	
}
