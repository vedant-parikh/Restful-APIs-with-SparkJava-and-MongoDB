package com.sparkjava.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AddressVO implements validable{
	private String street;
	private String city;
	private String zip;
	private String state;
	private String country;
	
	public String getStreet(){
		return street;
	}
	public void setStreet(String street){
		this.street = street;
	}
	public String getCity(){
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getZip(){
		return zip;
	}
	public void setZip(String zip){
		this.zip = zip;
	}
	public String getState(){
		return state;
	}
	public void setState(String state){
		this.state = state;
	}
	public String getCountry(){
		return country;
	}
	public void setCountry(String country){
		this.country = country;
	}
	
	@JsonIgnore
	public boolean isValid(){
		return true;
	}
}