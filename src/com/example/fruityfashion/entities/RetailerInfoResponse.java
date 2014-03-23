package com.example.fruityfashion.entities;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class RetailerInfoResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SerializedName("errorCode")
	String errorCode;

	@SerializedName("data")
	Retailer data;

	public RetailerInfoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Retailer getData() {
		return data;
	}

	public void setData(Retailer data) {
		this.data = data;
	}

	

}
