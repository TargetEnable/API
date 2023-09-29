package com.example.demo.controllers;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.EnableIncident;
import com.example.demo.repositories.IncidentMongoRepository;
import com.example.demo.repositories.EnableMongoRepository;

@CrossOrigin("*")
@RestController
public class IncidentController {
	
	@Autowired
	IncidentMongoRepository mongoRepository;
	EnableMongoRepository accountRepository;
	 

	@PostMapping("/incident")
	public ResponseEntity<String> incidentForm(@RequestBody EnableIncident i) {
		
       // Get the current server-side time stamp as a java.util.Date
		
		Date currentDateTime = new Date();
	    i.setDateOfIncident(currentDateTime);
	    
	mongoRepository.save(i);
	 
	return ResponseEntity.status(HttpStatus.CREATED).body("Incident Details submitted successfully");
	}
	@GetMapping("/incidents/{email}")
    public ResponseEntity<List<EnableIncident>> getIncidentsByEmployeeId(@PathVariable String email) {
        List<EnableIncident> incidents = mongoRepository.findByEmail(email);
        
        if (incidents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(incidents);
        
    }
	
	@GetMapping("/support/{name}")
    public ResponseEntity<List<EnableIncident>> getIncidentsByAssignedTo(@PathVariable String name) {
        List<EnableIncident> incidents = mongoRepository.findByAssignedTo(name);
        
        if (incidents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
       
        
        return ResponseEntity.ok(incidents);
    }
	@PatchMapping("/incidents/support/{id}")
	public ResponseEntity<String> updateIncidentById(@PathVariable int id, @RequestBody EnableIncident i) {
		
	    // First, check if an incident with the given ID exists in the repository
	    Optional<EnableIncident> existingIncidentOptional = mongoRepository.findById(id);

	    if (existingIncidentOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incident with ID " + id + " not found");
	    }

	    // Get the existing incident from the optional
	    EnableIncident existingIncident = existingIncidentOptional.get();

	    // Update the fields of the existing incident with the values from the updatedIncident
	    // Note: You should implement the logic to update specific fields as needed
	    existingIncident.setStatus(i.getStatus());
	    existingIncident.setResolutionDescription(i.getResolutionDescription());
	    existingIncident.setResolutionDate(new Date());
	     // Update the assignedBy field
	    // ... Update other fields as necessary

	    // Save the updated incident back to the repository
	    mongoRepository.save(existingIncident);

	    return ResponseEntity.ok("Incident with ID " + id + " updated successfully");
	}
	@GetMapping("/support")
    public ResponseEntity<List<EnableIncident>> getIncidents() {
        List<EnableIncident> incidents = mongoRepository.findAll();
        
        if (incidents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(incidents);
    }
	@PutMapping("/incidents/assign/{id}")
	public ResponseEntity<String> AssignById(@PathVariable int id, @RequestBody EnableIncident i) {
	    // First, check if an incident with the given ID exists in the repository
	    Optional<EnableIncident> existingIncidentOptional = mongoRepository.findById(id);

	    if (existingIncidentOptional.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incident with ID " + id + " not found");
	    }

	    // Get the existing incident from the optional
	    EnableIncident existingIncident = existingIncidentOptional.get();

	    // Update the fields of the existing incident with the values from the updatedIncident
	    // Note: You should implement the logic to update specific fields as needed
	    existingIncident.setAssignedTo(i.getAssignedTo());
	    existingIncident.setStatus(i.getStatus());
	    
	    // ... Update other fields as necessary

	    // Save the updated incident back to the repository
	    mongoRepository.save(existingIncident);

	    return ResponseEntity.ok("Incident updated successfully");
	}
	
	 
	
	@GetMapping("/count")
    public ResponseEntity<Map<String, Integer>> getIncidentCounts() {
        Map<String, Integer> incidentCounts = new HashMap<>();
        
        // Fetch counts based on status from your database
        int openCount = mongoRepository.countByStatus("Open");
        int closedCount = mongoRepository.countByStatus("Closed");
        int inProgressCount = mongoRepository.countByStatus("In Progress");
        
        incidentCounts.put("openCount", openCount);
        incidentCounts.put("closedCount", closedCount);
        incidentCounts.put("inProgressCount", inProgressCount);
        
        return ResponseEntity.ok(incidentCounts);

	}
	
	
	@GetMapping("/incident-counts/{email}")
    public ResponseEntity<Map<String, Integer>> getIncidentCountsByEmail(@PathVariable String email) {
        Map<String, Integer> incidentCounts = new HashMap<>();

        // Fetch counts based on status and email from your database
        long openCount = mongoRepository.countByStatusAndEmail("Open", email);
        long closedCount = mongoRepository.countByStatusAndEmail("Closed", email);
        long inProgressCount = mongoRepository.countByStatusAndEmail("In Progress", email);

        incidentCounts.put("openCount", (int) openCount);
        incidentCounts.put("closedCount", (int) closedCount);
        incidentCounts.put("inProgressCount", (int) inProgressCount);

        return ResponseEntity.ok(incidentCounts);
    }
	
	
	
	  
	
}
