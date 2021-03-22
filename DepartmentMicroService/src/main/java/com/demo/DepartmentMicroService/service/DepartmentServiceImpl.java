package com.demo.DepartmentMicroService.service;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.demo.DepartmentMicroService.DTO.DepartmentDto;
import com.demo.DepartmentMicroService.DTO.OrgDto;
import com.demo.DepartmentMicroService.entity.Department;
import com.demo.DepartmentMicroService.repository.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final String ADD_ORGANIZATION_URL="http://localhost:8091/add-organization";
	private static final String GET_ORGANIZATION_URL="http://localhost:8091/get-organization/";
	private static final String DELETE_ORGANIZATION_URL="http://localhost:8091/delete-organization/";
	@Override
	public long addUpdateDepartment(DepartmentDto departmentDto) throws Exception {
		
		 OrgDto orgDto=new OrgDto();
		
		try {
				orgDto.setOrgId(departmentDto.getOrganizationId());
				orgDto.setOrgName(departmentDto.getOrgName());
				orgDto.setOrgaddress(departmentDto.getOrgAddress());
				HttpHeaders headers = new HttpHeaders();
			      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			      HttpEntity<OrgDto> entity = new HttpEntity<OrgDto>(orgDto,headers);
				ResponseEntity<Long> orgId=restTemplate.exchange(ADD_ORGANIZATION_URL, HttpMethod.POST,entity, Long.class);
				orgId.getBody();
				Department department=new Department();
				department.setId(departmentDto.getDeptId());
				department.setName(departmentDto.getDeptName());
				department.setOrganizationId(departmentDto.getOrganizationId());
				
				department=departmentRepo.save(department);
				return department.getId();
		}catch(Exception ex) {
			System.out.println(ex);
			throw new Exception("Exception occured when add or delete");
		}
		
	}


	@Override
	public DepartmentDto getDepartment(long deptId) throws Exception {
		DepartmentDto departmentDto=new DepartmentDto();
	    try {
		Optional<Department> deptDtls=departmentRepo.findById(deptId);
		if(deptDtls!=null) {
			Department dept=deptDtls.get();
			OrgDto orgDto= restTemplate.getForObject(GET_ORGANIZATION_URL+dept.getOrganizationId(), OrgDto.class);
			departmentDto.setDeptId(deptId);
			departmentDto.setDeptName(dept.getName());
			departmentDto.setOrgAddress(orgDto.getOrgaddress());
			departmentDto.setOrganizationId(dept.getOrganizationId());
			departmentDto.setOrgName(orgDto.getOrgName());
			return departmentDto;
		}
		else
			throw new Exception("No data Found");
		
	    }
	    catch(Exception ex) {
	    	System.out.println(ex);
	    	throw new Exception("Exception while getting data");
	    }
	}

	@Override
	public void deleteDepartment(long deptId) throws Exception {
		try {
			Optional<Department> deptDtls=departmentRepo.findById(deptId);
			if(deptDtls!=null) {
				Department dept=deptDtls.get();
				restTemplate.delete(DELETE_ORGANIZATION_URL+dept.getOrganizationId());
				departmentRepo.deleteById(deptId);
			}
		
		}
		catch(Exception ex) {
			System.out.println(ex);
			throw new Exception("Exception occured when delete");
		}
	}
		
		
	

}
