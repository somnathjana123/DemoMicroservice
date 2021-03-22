package com.demo.EmployeeMicroService.service;

import com.demo.EmployeeMicroService.entity.EmployeeDto;

public interface EmployeeService {

	public long addUpdateEmployee(EmployeeDto empDto) throws Exception;
	public EmployeeDto getEmployee(long deptId) throws Exception;
	public void deleteEmployee(long deptId) throws Exception;
}
