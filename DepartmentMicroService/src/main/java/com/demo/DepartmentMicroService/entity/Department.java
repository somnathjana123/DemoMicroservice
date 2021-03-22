package com.demo.DepartmentMicroService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="DEPARTMENT")
@Data
public class Department {

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Dept_id", nullable=false)
	private long id;
	@Column(name="Dept_name")
	private String name;
	@Column(name="Org_id")
	private long organizationId;
}

