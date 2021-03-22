package com.demo.DepartmentMicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.DepartmentMicroService.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department,Long>{

}
