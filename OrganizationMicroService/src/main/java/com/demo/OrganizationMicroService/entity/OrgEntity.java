package com.demo.OrganizationMicroService.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="ORGANIZATION")
@Data
@ToString
public class OrgEntity {
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Org_id", nullable=false)
	private long orgId;
	@Column(name="Org_name")
	private String orgName;
	@Column(name="Org_address")
	private String orgaddress; 

}
