package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Department;
import com.example.demo.Entity.Employee;
import com.example.demo.Service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	
	@GetMapping
	private ResponseEntity<List<Department>> listAllDepts() {
		return departmentService.getAllDepts();
	}
	
	@GetMapping(params = "deptId")
	private ResponseEntity<Department> getDeptById(@RequestParam String deptId) {
		return departmentService.getDeptById(deptId);
	}
	
	@PostMapping
	private ResponseEntity<Department> createDept(@RequestBody Department department) {
		return departmentService.createDept(department);
	}
	
	@PutMapping(value = "/{id}")
	private ResponseEntity<Department> updateDept(@PathVariable String id, @RequestBody Department department) {
		return departmentService.updateDept(id, department);
	}
	
	@DeleteMapping(value = "/{id}")
	private ResponseEntity<HttpStatus> deleteDept(@PathVariable String id) {
		return departmentService.deleteDept(id);
	}
	
	@GetMapping(params = "deptName")
	private ResponseEntity<List<Department>> listDeptByName(@RequestParam String deptName) {
		return departmentService.findDeptByName(deptName);
	}
	
	@GetMapping(params = "empName")
	private ResponseEntity<Department> listDeptByEmpName(@RequestParam String empName) {
		return departmentService.findDeptByEmpName(empName);
	}
	
	@GetMapping(value = "/{deptName}/employees")
	private ResponseEntity<List<Employee>> listEmployeesByDeptName(@PathVariable String deptName) {
		return departmentService.findEmployeesByDeptName(deptName);
	}
}
