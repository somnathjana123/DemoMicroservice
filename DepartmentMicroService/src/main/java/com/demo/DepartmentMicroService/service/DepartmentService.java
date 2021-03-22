package com.demo.DepartmentMicroService.service;

import com.demo.DepartmentMicroService.DTO.DepartmentDto;

public interface DepartmentService {
	
	public long addUpdateDepartment(DepartmentDto departmentDto) throws Exception;
	public DepartmentDto getDepartment(long deptId) throws Exception;
	public void deleteDepartment(long deptId) throws Exception;

}
