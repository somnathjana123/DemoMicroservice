package com.demo.EmployeeMicroService.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.demo.EmployeeMicroService.entity.DepartmentDto;
import com.demo.EmployeeMicroService.entity.Employee;
import com.demo.EmployeeMicroService.entity.EmployeeDto;
import com.demo.EmployeeMicroService.repository.EmployeeRepo;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	EmployeeServiceImpl employService;
	
	@Mock
	EmployeeRepo employeeRepo;
	
	@Mock
    private RestTemplate restTemplate;
	
	@Test
	public void addUpdateEmployeePositive() {
	try {
		EmployeeDto empDto=new EmployeeDto();
		Employee empEntity=new Employee();
		empEntity.setId(7L);
		ResponseEntity<Map> resp=new ResponseEntity<Map>(HttpStatus.CREATED);
		empDto.setDepartmentId(5);
		empDto.setDeptName("testdept");
		empDto.setEmpName("TestEmpName");
		empDto.setId(3);
		empDto.setOrgAddress("testAddress");
		empDto.setOrgId(4);
		empDto.setOrgName("TestOrgName");
		when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<Map>>any()))
             .thenReturn(resp);
		when(employeeRepo.save(empEntity)).thenReturn(empEntity);
		long result=employService.addUpdateEmployee(empDto);
		assertEquals(7, result);
	
	}catch(Exception ex) {
		System.out.println(ex);
	}
  }
	@Test(expected = Exception.class)
	public void addUpdateEmployeeException() {
	try {
		EmployeeDto empDto=new EmployeeDto();
		Employee empEntity=new Employee();
		empEntity.setId(7L);
		empDto.setDepartmentId(5);
		empDto.setDeptName("testdept");
		empDto.setEmpName("TestEmpName");
		empDto.setId(3);
		empDto.setOrgAddress("testAddress");
		empDto.setOrgId(4);
		empDto.setOrgName("TestOrgName");
		when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<Map>>any())).thenThrow(new Exception("Exception thrown"));
		when(employeeRepo.save(empEntity)).thenThrow(new Exception("Exception thrown"));
		long result=employService.addUpdateEmployee(empDto);
	
	}catch(Exception ex) {
		System.out.println(ex);
	}
  }
	@Test
	public void getEmployeePositiveTest() {
	  try {
		Optional<Employee> opEmp = null;
		ResponseEntity<DepartmentDto> resp=new ResponseEntity<DepartmentDto>(HttpStatus.OK);
		when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<DepartmentDto>>any()))
             .thenReturn(resp);
		when(employeeRepo.findById(5L)).thenReturn(opEmp);
		EmployeeDto empdto=employService.getEmployee(5L);
		assertNotNull(empdto);
	  }catch(Exception ex) {
		  System.out.println(ex);
	  }
	}
	
	@Test(expected = Exception.class)
	public void getEmployeeExceptionTest() {
	  try {
		Optional<Employee> opEmp = null;
		when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<DepartmentDto>>any())).thenThrow(new Exception("Exception thrown"));
		when(employeeRepo.findById(5L)).thenThrow(new Exception("Exception thrown"));
		EmployeeDto empdto=employService.getEmployee(5L);
	  }catch(Exception ex) {
		  System.out.println(ex);
	  }
	}
	
	@Test
	public void deleteEmployeePositiveTest() {
	  try {
		Optional<Employee> opEmp = null;
		ResponseEntity<DepartmentDto> resp=new ResponseEntity<DepartmentDto>(HttpStatus.OK);
		when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<DepartmentDto>>any()))
             .thenReturn(resp);
		when(employeeRepo.findById(5L)).thenReturn(opEmp);
		employService.deleteEmployee(5L);
		
	  }catch(Exception ex) {
		  System.out.println(ex);
	  }
	}
	
	@Test(expected = Exception.class)
	public void deleteEmployeeExceptionTest() {
	  try {
		Optional<Employee> opEmp = null;
		when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<DepartmentDto>>any())).thenThrow(new Exception("Exception thrown"));
		when(employeeRepo.findById(5L)).thenThrow(new Exception("Exception thrown"));
		employService.deleteEmployee(5L);
	  }catch(Exception ex) {
		  System.out.println(ex);
	  }
	}
}
