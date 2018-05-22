package com.javaex.vo;

public class GalleryVo {

	private int no;
	private String filePath;
	private String orgName;
	private String saveName;
	private long fileSize;
	private String exName;
	// 확장자는 안담음 화면에 보여질게 아니기 때문에 보여줄 경우
	private String regdate;
	private int user_no;

	public GalleryVo() {

	}

	public GalleryVo(String filePath, String orgName, String saveName, long fileSize, String exName, int user_no) {
	
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.exName = exName;
		this.user_no = user_no;
	}



	public GalleryVo(int no, String filePath, String orgName, String saveName, long fileSize, String exName,
			String regdate, int user_no) {
		this.no = no;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.exName = exName;
		this.regdate = regdate;
		this.user_no = user_no;
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

	public String getExName() {
		return exName;
	}

	public void setExName(String exName) {
		this.exName = exName;
	}

	public String getReg_date() {
		return regdate;
	}

	public void setReg_date(String regdate) {
		this.regdate = regdate;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", filePath=" + filePath + ", orgName=" + orgName + ", saveName=" + saveName
				+ ", fileSize=" + fileSize + ", exName=" + exName + ", regdate=" + regdate + ", user_no=" + user_no
				+ "]";
	}

}
