package com.demo.OrganizationMicroService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.OrganizationMicroService.entity.OrgEntity;
import com.demo.OrganizationMicroService.service.OrgService;

@RestController
public class OrgController {

	@Autowired
	OrgService orgService;
	
	@PostMapping("/add-organization")
	public ResponseEntity<?> addUpdateOrganization(@RequestBody OrgEntity orgEntity){
		System.out.println("Input::"+orgEntity);
		try {
		   long orgId=orgService.addUpdateOrganization(orgEntity);
		   return new ResponseEntity<>(orgId, HttpStatus.CREATED);
		}catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	@GetMapping("/get-organization/{id}")
	public ResponseEntity<?> getOrganization(@PathVariable("id") Long orgId){
			try {
				OrgEntity orgDto=orgService.getOrganization(orgId);
				   return new ResponseEntity<>(orgDto, HttpStatus.OK);
				} catch (Exception e) {
					return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			
		}
	
	@DeleteMapping("/delete-organization/{id}")
	public ResponseEntity<?> deleteOrganization(@PathVariable("id") Long orgId){
		try {
			orgService.deleteOrganization(orgId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		   
		}catch(Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	@GetMapping("/hello")
	public String hello() {
		return "Hello world Organization";
	}
}
