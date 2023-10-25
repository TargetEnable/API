package com.example.demo.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.EnableAccount;
import com.example.demo.repositories.EnableMongoRepository;

@CrossOrigin("*")
@RestController
public class EnableController {
	
	@Autowired
	
	EnableMongoRepository mongoRepository;
	
	@CrossOrigin("*")
	@PostMapping("/")
	public ResponseEntity<String> login(@RequestBody EnableAccount ca) {
	    System.out.println("Received request with email: " + ca.getEmail());
	    String username = ca.getEmail();
	    String empType = ca.getEmpType();

	    // Retrieve the hashed password from the database
	    EnableAccount user = mongoRepository.findByEmail(username);
	    String hashedPassword = user.getPassword();

	    // Assuming you have a method to verify the provided password with the hashed password
	    if (verifyPassword(ca.getPassword(), hashedPassword) && mongoRepository.existsByEmailAndEmpType(username, empType)) {
	        return ResponseEntity.ok("Login successful");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid login credentials");
	    }
	}

	// Method to verify the provided password with the hashed password
	private boolean verifyPassword(String plainPassword, String hashedPassword) {
	    return BCrypt.checkpw(plainPassword, hashedPassword);
	}
	    

	@PostMapping("/register")
	 public ResponseEntity<String> openAccount(@RequestBody EnableAccount ca) {
		long empId = ca.getEmpId();
		String email = ca.getEmail();
		String empType = ca.getEmpType();
		boolean isDuplicateEmpId = mongoRepository.existsByEmpIdAndEmpType(empId,empType);

		boolean isDuplicateEmail = mongoRepository.existsByEmailAndEmpType(email,empType);

		if (isDuplicateEmpId) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee ID already exists");
		}

		else if (isDuplicateEmail) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
		}
		else {
		mongoRepository.save(ca);
		return ResponseEntity.status(HttpStatus.CREATED).body("Account opened successfully");
		}
	}
	
	@GetMapping("/Empname/{email}")
	public ResponseEntity<String> getName(@PathVariable String email) {
	   
	        System.out.println("Received request to get name for email: " + email);

	        // Check if a user with the specified email exists
	        EnableAccount account = mongoRepository.findByEmail(email);

	        if (account != null) {
	            String name = account.getName();
	            return ResponseEntity.ok(name);
	        } else {
	            System.out.println("No matching user found for email: " + email);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
	        }
	    } 
	
	
	
	@GetMapping("/IncidentEmail/{email}")
	public ResponseEntity<String> getNameGeneral(@PathVariable String email) {
        EnableAccount account = mongoRepository.findByEmail(email);
        
        String name = account.getName();
        return ResponseEntity.ok(name);
    }
	
	@GetMapping("/admin/getsupport")
	public String[] getSupport() {
List<EnableAccount> accounts = mongoRepository.findByEmpType("support");
       
	String[] names = new String[accounts.size()];

// Extract names from EnableAccount objects and add to the array
	for (int i = 0; i < accounts.size(); i++) {
		names[i] = accounts.get(i).getName();
	}

        return names;
    }
}
