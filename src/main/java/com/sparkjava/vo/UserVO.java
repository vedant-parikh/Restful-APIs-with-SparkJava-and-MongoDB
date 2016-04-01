package com.sparkjava.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserVO implements validable{
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private AddressVO address;
	private String dateCreated;
	private CompanyVO company;
	private String profilePic;
	
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public AddressVO getAddress(){
		return address;
	}
	public void setAddress(AddressVO address){
		this.address = address;
	}
	public String getDateCreated(){
		return dateCreated;
	}
	public void setDateCreated(String dateCreated){
		this.dateCreated = dateCreated;
	}
	public CompanyVO getCompany(){
		return company;
	}
	public void setCompany(CompanyVO company){
		this.company = company;
	}
	public String getProfilePic(){
		return profilePic;
	}
	public void setProfilePic(String profilePic){
		this.profilePic = profilePic;
	}
	
	@JsonIgnore
	public boolean isValid(){
		return id != null && !id.isEmpty();
	}
}