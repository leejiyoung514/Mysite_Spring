package com.javaex.vo;

public class JSONResult {

	private String result; // "success" or "fail"
	private String failMsg;
	private Object data; // 번호로 보냄

	public JSONResult() {

	}

	public JSONResult(String result, String failMsg, String data) {
		this.result = result;
		this.failMsg = failMsg;
		this.data = data;
	}
	
	public void sucess(Object data) {
		this.result="sucess";
		this.failMsg=null;
	    this.data = data;
	}
	
    public void fail(String failMsg) {
    	this.result="fail";
    	this.failMsg=failMsg;
    	this.data=null;
	}
	

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


}
