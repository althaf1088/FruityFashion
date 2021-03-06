package com.example.fruityfashion.entities;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;


import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Retailer implements Serializable{
	private static final long serialVersionUID = 1L;

	@SerializedName("splashImage")
	String splashImage;

	@SerializedName("poweredBy")
	String poweredBy;

	@SerializedName("headerColor")
	String headerColor;

	@SerializedName("retailerTextColor")
	String retailerTextColor;

	@SerializedName("retailerName")
	String retailerName;

	@SerializedName("retailerFileType")
	String retailerFileType;

	@SerializedName("retailerFile")
	String retailerFile;
	@SerializedName("companyLogo")
	String companyLogo;
	@SerializedName("siteFont")
	String siteFont;
	@SerializedName("backdropType")
	String backdropType;
	@SerializedName("backdropColor1")
	String backdropColor1;
	@SerializedName("backdropColor2")
	String backdropColor2;
	@SerializedName("retailerStores")
	List<RetailerStores> retailerStores;
	 @JsonIgnore
	 @Expose
	Bitmap logoBit;
	 @JsonIgnore
	
	public Bitmap getLogoBit() {
		return logoBit;
	}
	 @JsonIgnore
	public void setLogoBit(Bitmap logoBit) {
		this.logoBit = logoBit;
	}

	private Retailer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getSplashImage() {
		return splashImage;
	}

	public void setSplashImage(String splashImage) {
		this.splashImage = splashImage;
	}

	public String getPoweredBy() {
		return poweredBy;
	}

	public void setPoweredBy(String poweredBy) {
		this.poweredBy = poweredBy;
	}

	
	public String getHeaderColor() {
		return headerColor;
	}

	public void setHeaderColor(String headerColor) {
		this.headerColor = headerColor;
	}

	public String getRetailerTextColor() {
		return retailerTextColor;
	}

	public void setRetailerTextColor(String retailerTextColor) {
		this.retailerTextColor = retailerTextColor;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getSiteFont() {
		return siteFont;
	}

	public void setSiteFont(String siteFont) {
		this.siteFont = siteFont;
	}

	public String getBackdropType() {
		return backdropType;
	}

	public void setBackdropType(String backdropType) {
		this.backdropType = backdropType;
	}

	public String getBackdropColor1() {
		return backdropColor1;
	}

	public void setBackdropColor1(String backdropColor1) {
		this.backdropColor1 = backdropColor1;
	}

	public String getBackdropColor2() {
		return backdropColor2;
	}

	public void setBackdropColor2(String backdropColor2) {
		this.backdropColor2 = backdropColor2;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getRetailerFileType() {
		return retailerFileType;
	}

	public void setRetailerFileType(String retailerFileType) {
		this.retailerFileType = retailerFileType;
	}

	public String getRetailerFile() {
		return retailerFile;
	}

	public void setRetailerFile(String retailerFile) {
		this.retailerFile = retailerFile;
	}

	public List<RetailerStores> getRetailerStores() {
		return retailerStores;
	}

	public void setRetailerStores(List<RetailerStores> retailerStores) {
		this.retailerStores = retailerStores;
	}
}
