package com.demo.OrganizationMicroService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.OrganizationMicroService.entity.OrgEntity;


public interface OrgRepo extends JpaRepository<OrgEntity,Long>{
	
}
