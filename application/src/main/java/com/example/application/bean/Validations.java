package com.example.application.bean;

/**
 * 
 * @author Srinivas Nangana
 *
 */
public class Validations {
	private String message;
	private String errCode;

	public Validations() {
	}

	public Validations(String message, String errCode) {
		this.message = message;
		this.errCode = errCode;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrCode() {
		return this.errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
}