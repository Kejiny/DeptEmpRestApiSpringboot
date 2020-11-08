package com.example.demo.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Department;
import com.example.demo.Entity.Employee;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String>{
	
	List<Department> findDepartmentByName(String name);

	@Query(value = "{'employees.name' : ?0}", fields = "{'employees' : 0}")
	Optional<Department> findDepartmentByEmployeeName(String empName);
	
	//@Query(value = "{'name' : ?0}", fields = "{'employees' : 1}")
	//List<Employee> findEmployeesByName(String name);
}
