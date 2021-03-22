package com.demo.EmployeeMicroService.service;

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

import com.demo.EmployeeMicroService.entity.DepartmentDto;
import com.demo.EmployeeMicroService.entity.Employee;
import com.demo.EmployeeMicroService.entity.EmployeeDto;
import com.demo.EmployeeMicroService.repository.EmployeeRepo;
@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	private static final String ADD_DEPARTMENT_URL="http://localhost:8087/add-department";
	private static final String GET_DEPARTMENT_URL="http://localhost:8087/get-department/";
	private static final String DELETE_DEPARTMENT_URL="http://localhost:8087/delete-department/";
	@Override
	public long addUpdateEmployee(EmployeeDto empDto) throws Exception {
		DepartmentDto deptDto=new DepartmentDto();
		
		try {
				deptDto.setDeptId(empDto.getDepartmentId());
				deptDto.setDeptName(empDto.getDeptName());
				deptDto.setOrganizationId(empDto.getOrgId());
				deptDto.setOrgName(empDto.getOrgName());
				deptDto.setOrgAddress(empDto.getOrgAddress());
				HttpHeaders headers = new HttpHeaders();
			      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			      HttpEntity<DepartmentDto> entity = new HttpEntity<DepartmentDto>(deptDto,headers);
				ResponseEntity<Long> resp=restTemplate.exchange(ADD_DEPARTMENT_URL, HttpMethod.POST,entity, Long.class);
				if(resp.getStatusCodeValue()==201) {
				Employee empEntity=new Employee();
				empEntity.setDepartmentId(empDto.getDepartmentId());
				empEntity.setName(empDto.getEmpName());
				if(empDto.getId()>0)
					empEntity.setId(empDto.getId());
				Employee employee=employeeRepo.save(empEntity);
				return employee.getId();
				}
				else
					throw new Exception("Rest call not successfull");
		}catch(Exception ex) {
			System.out.println(ex);
			throw new Exception("Exception occured when add or delete");
		}
		
	}

	
	@Override
	public EmployeeDto getEmployee(long deptId) throws Exception {
		EmployeeDto employeeDto=new EmployeeDto();
		try {
		Optional<Employee> employee=employeeRepo.findById(deptId);
		if(employee.isPresent()) {
			Employee emp=employee.get();
			DepartmentDto deptDto= restTemplate.getForObject(GET_DEPARTMENT_URL+emp.getDepartmentId(), DepartmentDto.class);
			employeeDto.setDepartmentId(deptDto.getDeptId());
			employeeDto.setDeptName(deptDto.getDeptName());
			employeeDto.setEmpName(emp.getName());
			employeeDto.setId(emp.getId());
			employeeDto.setOrgAddress(deptDto.getOrgAddress());
			employeeDto.setOrgName(deptDto.getOrgName());
			return employeeDto;
		}
		else 
			throw new Exception("No Data found");
		}catch(Exception ex) {
			System.out.println(ex);
			throw new Exception("Exception occured while getting data");
		}
	}

	@Override
	public void deleteEmployee(long deptId) throws Exception {
		try {
			Optional<Employee> employee=employeeRepo.findById(deptId);
			if(employee.isPresent()) {
				Employee emp=employee.get();
				restTemplate.delete(DELETE_DEPARTMENT_URL+emp.getDepartmentId());
				employeeRepo.deleteById(deptId);
			}
		
		}
		catch(Exception ex) {
			System.out.println(ex);
			throw new Exception("Exception occured when delete");
		}
	}

}
