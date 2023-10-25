package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EnableAccount;
import com.example.demo.repositories.EmpMongoRepository;

@CrossOrigin("*")
@RestController
public class EmpController {

@Autowired
	
	EmpMongoRepository mongoRepository;

	@PostMapping("/check")
	public ResponseEntity<String> openAccount(@RequestBody EnableAccount ca) {
		long empId = ca.getEmpId();
		String empName = ca.getName();
		String empType = ca.getEmpType();
		boolean isEmployee = mongoRepository.existsByEmpIdAndEmpNameAndEmpType(empId,empName,empType);
		if(isEmployee) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Employee exists");
		}
		else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("No such employee");
		}
	}
	}
