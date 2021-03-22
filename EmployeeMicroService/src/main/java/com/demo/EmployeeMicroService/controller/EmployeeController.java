package com.demo.EmployeeMicroService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.EmployeeMicroService.entity.EmployeeDto;
import com.demo.EmployeeMicroService.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/add-employee")
	public ResponseEntity<?> addDepartment(@RequestBody EmployeeDto employee){
		try {
		   long employeeId=employeeService.addUpdateEmployee(employee);
		   return new ResponseEntity<>(employeeId, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/update-employee")
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employee){
		try {
			   long employeeId=employeeService.addUpdateEmployee(employee);
			   return new ResponseEntity<>(employeeId, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@GetMapping("/get-employee/{id}")
	public ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") Long empId){
		try {
			EmployeeDto empDetails=employeeService.getEmployee(empId);
			   return new ResponseEntity<>(empDetails, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}
	@DeleteMapping("/delete-employee/{id}")
	public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") Long deptId){
		try {
			employeeService.deleteEmployee(deptId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		   
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/hello")
	public String hello() {
		return "Hello world Employee";
	}
}
