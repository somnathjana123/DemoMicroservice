package com.demo.EmployeeMicroService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="EMPLOYEE")
@Data
public class Employee {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Emp_id", nullable=false)
	private long id;
	@Column(name="Emp_name")
	private String name;
	@Column(name="Dept_id")
	private long departmentId; 
}
