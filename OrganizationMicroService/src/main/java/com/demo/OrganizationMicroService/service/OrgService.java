package com.demo.OrganizationMicroService.service;

import com.demo.OrganizationMicroService.entity.OrgEntity;

public interface OrgService {

	public long addUpdateOrganization(OrgEntity orgEntity) throws Exception;
	public OrgEntity getOrganization(long orgId) throws Exception;
	public void deleteOrganization(long orgid) throws Exception;
}
