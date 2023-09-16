package com.example.demo.repositories;




import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.EnableAccount;

@Repository
public interface EnableMongoRepository  extends MongoRepository<EnableAccount,Integer> {

	boolean existsByEmail(String email);
	boolean existsByEmpId(int empId);
	boolean existsByEmpIdAndEmpType(int empId,String empType);
	boolean existsByEmailAndPassword(String username,String password);
	boolean existsByEmailAndEmpType(String username, String empType);
	EnableAccount findByEmail(String email);
	List<EnableAccount> findByEmpType(String string);
	EnableAccount findByEmailAndEmpType(String email, String string);
	}
