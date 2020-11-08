package com.example.demo.DAO;

import java.util.List;

import com.example.demo.Entity.Employee;

public interface DepartmentRepositoryCustom {
	List<Employee> findEmployeesByDeptName(String deptName);
}
