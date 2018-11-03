package com.example.application.utill;


import java.util.ArrayList;
import java.util.List;

import com.example.application.bean.Validations;
/**
 * 
 * @author Srinivas Nangana
 *
 */
public class ResponseData {
	private Object data;
	private String responseCode;
	private String responseMessage;
	private List<Validations> validations;

	public ResponseData() {
	}

	public ResponseData(String responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public ResponseData(String responseCode, String responseMessage, Object data) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.data = data;
	}

	public void addValidations(String errCode, String errMessage) {
		if (this.validations == null) {
			this.validations = new ArrayList<>();
		}

		this.validations.add(new Validations(errMessage, errCode));
	}

	public String getResponseCode() {
		return this.responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return this.responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public List<Validations> getValidations() {
		return this.validations;
	}

	public void setValidations(List<Validations> validations) {
		this.validations = validations;
	}

	public Object getData() {
		return this.data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toString() {
		return "ResponseMapper [data=" + this.data + ", responseCode=" + this.responseCode + ", responseMessage="
				+ this.responseMessage + ", validations=" + this.validations + "]";
	}
}