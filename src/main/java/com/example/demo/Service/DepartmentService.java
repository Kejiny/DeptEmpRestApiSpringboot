package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.DepartmentRepository;
import com.example.demo.DAO.DepartmentRepositoryCustomImpl;
import com.example.demo.Entity.Department;
import com.example.demo.Entity.Employee;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private DepartmentRepositoryCustomImpl departmentRepositoryCustomImpl;

	public ResponseEntity<List<Department>> getAllDepts() {
		try {
		    List<Department> departments = new ArrayList<Department>();
		    departmentRepository.findAll().forEach(departments::add);
		
		    if (departments.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
		    return new ResponseEntity<>(departments, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<Department> createDept(Department department) {
		try {
			Department departmentNew = departmentRepository.save(new Department(department.getId(), department.getName(), 
					department.getDescription(), department.getEmployees()));
		    return new ResponseEntity<>(departmentNew, HttpStatus.CREATED);
		} catch (Exception e) {
		    return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<Department> getDeptById(String id) {
		Optional<Department> departmentData = departmentRepository.findById(id);

		if (departmentData.isPresent()) {
			return new ResponseEntity<>(departmentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Department> updateDept(String id, Department department) {
		Optional<Department> departmentData = departmentRepository.findById(id);

		if (departmentData.isPresent()) {
			Department departmentOld = departmentData.get();
			departmentOld.setName(department.getName());
			departmentOld.setDescription(department.getDescription());
			departmentOld.setEmployees(department.getEmployees());
		    return new ResponseEntity<>(departmentRepository.save(departmentOld), HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<HttpStatus> deleteDept(String id) {
		Optional<Department> department = departmentRepository.findById(id);
		try {
			if (department.isPresent()) {
				departmentRepository.deleteById(id);
			    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<List<Department>> findDeptByName(String deptName) {
		try {
		    List<Department> departments = departmentRepository.findDepartmentByName(deptName);

		    if (departments.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		    return new ResponseEntity<>(departments, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	public ResponseEntity<Department> findDeptByEmpName(String empName) {		
		Optional<Department> departmentData = departmentRepository.findDepartmentByEmployeeName(empName);

		if (departmentData.isPresent()) {
			return new ResponseEntity<>(departmentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<Employee>> findEmployeesByDeptName(String deptName) {
		try {
		    List<Employee> employees = departmentRepositoryCustomImpl.findEmployeesByDeptName(deptName);

		    if (employees.isEmpty()) {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		    return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
		    return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
