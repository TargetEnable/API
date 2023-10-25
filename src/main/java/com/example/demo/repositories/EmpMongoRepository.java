package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.EmpData;
import com.example.demo.EnableAccount;

public interface EmpMongoRepository extends MongoRepository<EmpData,Long> {

	boolean existsByEmpIdAndEmpNameAndEmpType(long empId, String empName, String empType);

}
