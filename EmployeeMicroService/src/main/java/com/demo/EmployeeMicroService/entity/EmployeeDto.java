package com.demo.EmployeeMicroService.entity;

import lombok.Data;

@Data
public class EmployeeDto {

	private long id;
	private String empName;
	private long departmentId;
	private String deptName;
	private long orgId;
	private String orgName;
	private String orgAddress;
	
	
}
