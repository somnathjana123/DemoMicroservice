package com.demo.DepartmentMicroService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.DepartmentMicroService.DTO.DepartmentDto;
import com.demo.DepartmentMicroService.service.DepartmentService;



@RestController
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;
	
	@PostMapping("/add-department")
	public ResponseEntity<?> addDepartment(@RequestBody DepartmentDto departmentDto){
		try {
		   long departmentId=departmentService.addUpdateDepartment(departmentDto);
		   return new ResponseEntity<>(departmentId, HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	@GetMapping("/get-department/{id}")
	public ResponseEntity<?> getDepartment(@PathVariable("id") Long deptId){
		try {
			DepartmentDto deptDto=departmentService.getDepartment(deptId);
			   return new ResponseEntity<>(deptDto, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		
	}
	@DeleteMapping("/delete-department/{id}")
	public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long deptId){
		try {
			departmentService.deleteDepartment(deptId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		   
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/hello")
	public String hello() {
		return "Hello world Department";
	}
}
