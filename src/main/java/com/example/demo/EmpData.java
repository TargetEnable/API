package com.example.demo;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "empData")
public class EmpData {
	@Id
	private long empId;
	private String empName;
	private String empType;
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	@Override
	public String toString() {
		return "EmpData [empId=" + empId + ", empName=" + empName + ", empType=" + empType + "]";
	}
	
	
}
