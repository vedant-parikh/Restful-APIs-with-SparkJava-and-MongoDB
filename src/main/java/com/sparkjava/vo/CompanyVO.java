package com.sparkjava.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompanyVO implements validable{
	private String name;
	private String website;
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getWebsite(){
		return website;
	}
	public void setWebsite(String website){
		this.website = website;
	}
	
	@JsonIgnore
	public boolean isValid(){
		return true;
	}
}