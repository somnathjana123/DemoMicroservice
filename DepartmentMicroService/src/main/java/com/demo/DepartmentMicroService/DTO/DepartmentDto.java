package com.demo.DepartmentMicroService.DTO;

import lombok.Data;

@Data
public class DepartmentDto {
	private long deptId;
	private String deptName;
	private long organizationId;
    private String orgName;
    private String orgAddress;
}
