package com.accpunt.management.util;

public class Response {
	
	private String code;
	private String reasson;
	public String message;
	public String status;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getReasson() {
		return reasson;
	}
	
	public void setReasson(String reasson) {
		this.reasson = reasson;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Response [code=" + code + ", reasson=" + reasson + ", message=" + message + ", status=" + status + "]";
	}

	public Response(String code, String reasson, String message, String status) {
		super();
		this.code = code;
		this.reasson = reasson;
		this.message = message;
		this.status = status;
	}

	public Response() {
		super();
		// TODO Auto-generated constructor stub
	}

}
