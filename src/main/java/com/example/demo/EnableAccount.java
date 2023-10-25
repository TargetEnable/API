package com.example.demo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "enableAccounts")
public class EnableAccount {
	
	private long empId;
	private String empType;
	private String name;
	private String email;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Account [name=" + name + ", empType=" + empType + ", empId=" + empId + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	

}
