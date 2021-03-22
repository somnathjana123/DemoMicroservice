package com.demo.OrganizationMicroService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.OrganizationMicroService.entity.OrgEntity;
import com.demo.OrganizationMicroService.repository.OrgRepo;
@Service
public class OrgServiceImpl implements OrgService{

	@Autowired
	OrgRepo orgRepo;
	@Override
	public long addUpdateOrganization(OrgEntity orgEntity) throws Exception {
		orgEntity=orgRepo.save(orgEntity);
		return orgEntity.getOrgId();
	}

	@Override
	public OrgEntity getOrganization(long orgId) throws Exception {
	  try {
		Optional<OrgEntity> entityDtls=orgRepo.findById(orgId);
		if(entityDtls.isPresent()) {
			OrgEntity entity=entityDtls.get();
			return entity;
		}
		else
			throw new Exception("No data Found");
	  }catch(Exception ex) {
		  System.out.println(ex);
		  throw new Exception("Exception occured fetching data");
	  }
	}

	@Override
	public void deleteOrganization(long orgid) throws Exception {
		try {
			orgRepo.deleteById(orgid);
		}catch(Exception ex) {
			System.out.println(ex);
			throw new Exception("Exception occured when delete");
		}
		
	}

}
