package com.javaex.vo;

public class FileVo { // 서비스에따라 필드는 변동할 수 있음
	// 꺼내와야하기때문에 번호도 필요할 수 있음
	private int no;
	private String filePath;
	private String orgName;
	private String saveName;
	private long fileSize;
	private String exName;
	// 확장자는 안담음 화면에 보여질게 아니기 때문에 보여줄 경우 

	
	public FileVo() {

	}
 
	
	public FileVo(String filePath, String orgName, String saveName, long fileSize) {
	
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}


	public FileVo(int no, String filePath, String orgName, String saveName, long fileSize) {

		this.no = no;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}

	
	
	public FileVo(int no, String filePath, String orgName, String saveName, long fileSize, String exName) {
		this.no = no;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.exName = exName;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}


	@Override
	public String toString() {
		return "FileVo [no=" + no + ", filePath=" + filePath + ", orgName=" + orgName + ", saveName=" + saveName
				+ ", fileSize=" + fileSize + ", exName=" + exName + "]";
	}




}
