package com.bolsadeideas.springboot.vehicle.apirest.exception.handler;

public class StandarizedExceptionResponse {

	private String type = "error/uncategorized";
	private String title;
	private String detail;
	private int code;
	
	public StandarizedExceptionResponse(String title, String detail, int code) {
		this.title = title;
		this.detail = detail;
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
