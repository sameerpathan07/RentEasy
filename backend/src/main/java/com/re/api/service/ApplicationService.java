package com.re.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.re.api.dao.ApplicationDao;
import com.re.api.dto.ApplicationRequest;
import com.re.api.entity.Application;

@Service
public class ApplicationService {

	@Autowired
	ApplicationDao dao;
	
	public void apply(int propertyId, ApplicationRequest request) {
       dao.apply(propertyId, request);
    }
	
    public void updateStatus( int appId, String status) {
        dao.updateStatus(appId, status);
    }

    public List<Application> getForLandlord(String userName) {
       return dao.getForLandlord(userName);
    }
    
    public List<Application> getForTenant(String userName) {
    	return dao.getForTenant(userName);
    }
    
    public void deleteApplication(int id) {
        dao.deleteApplication(id);
        
    }
    
  
	public void sendSimplMail(int id) {
		
		dao.sendSimplMail(id);
	}
}
