package com.demo.EmployeeMicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.EmployeeMicroService.entity.Employee;


public interface EmployeeRepo extends JpaRepository<Employee,Long>{


}
