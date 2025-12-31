package com.re.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.re.api.dto.ApplicationRequest;
import com.re.api.dto.MailRequest;
import com.re.api.entity.Application;
import com.re.api.entity.Image;
import com.re.api.service.ApplicationService;

@RestController
@RequestMapping("/application")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationController {

    @Autowired
    ApplicationService service;


    @PostMapping("/apply/{propertyId}")
    public void apply( @PathVariable int propertyId, @RequestBody ApplicationRequest dto) {
        service.apply(propertyId, dto);
        System.setProperty("java.net.useSystemProxies", "false");
    }
    
    
    @PutMapping("/update/{appId}")
    public void updateStatus( @PathVariable int appId, @RequestParam  String status) {
        service.updateStatus(appId, status);
    }


    @GetMapping("/user/{userName}")
    public List<Application> getForLandlord(@PathVariable String userName) {
        return service.getForLandlord(userName);
    }
    

    @GetMapping("/tenant/{userName}")
    public List<Application> getForTenant(@PathVariable String userName) {
        return service.getForTenant(userName);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteApplication(@PathVariable int id) {
        service.deleteApplication(id);
        
    }
    
	
	@GetMapping("/sendMail/{id}")
	public String sendEmail(@PathVariable int id) {
		service.sendSimplMail(id);
		System.setProperty("java.net.useSystemProxies", "false");

		return "Email send Successfully!!!";
	}
}

