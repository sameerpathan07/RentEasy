package com.re.api.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import com.re.api.dto.ApplicationRepo;
import com.re.api.dto.ApplicationRequest;
import com.re.api.dto.ImageRepo;
import com.re.api.dto.MailRequest;
import com.re.api.dto.UserRepo;
import com.re.api.entity.Application;
import com.re.api.entity.Image;
import com.re.api.entity.User;

import jakarta.transaction.Transactional;

@Repository
public class ApplicationDao {
	
	@Autowired
	private JavaMailSender mailsender;

    @Autowired
    private ApplicationRepo repo;

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private UserRepo userRepo;

    @Transactional
    public void apply(int imageId, ApplicationRequest dto) {

        Image image = imageRepo.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        User tenant = userRepo.findById(dto.getTenant())
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        // landlord comes from property ownership
        User landlord = image.getUser();

        // Prevent duplicate application
        boolean exists =
                repo.existsByImage_IdAndTenant_UserName(imageId, tenant.getUserName());

        if (exists) {
            throw new RuntimeException("Already applied");
        }
        
        SimpleMailMessage message=new SimpleMailMessage();

        Application app = new Application();
        app.setImage(image);
        app.setTenant(tenant);
        app.setLandlord(landlord);
        app.setAddress(dto.getAddress());
        app.setMeetingDate(dto.getDate());
        app.setEmail(dto.getEmail());      
        app.setPhoneNo(dto.getPhoneNo());
        app.setStatus("PENDING");

        repo.save(app);
        
message.setTo(landlord.getEmail());
		
		message.setSubject("Property Rental Request");
		
		message.setText("I hope you are doing well.\r\n"
				+ "\r\n"
				+ "My name is "+dto.getName()+", and I am interested in renting the property located at the address mentioned below:\r\n"
				+ "\r\n"
				+ "Property Address:\r\n"
				+ app.getAddress()+"\r\n"
				+ "\r\n"
				+ "I would like to schedule a meeting on "+app.getMeetingDate()+" to discuss the rental details, availability, and further process.\r\n"
				+ "\r\n"
				+ "You can contact me at:\r\n"
				+ "Phone Number: "+app.getPhoneNo()+"\r\n"
				+ "Email Address: "+app.getEmail()+"\r\n"
				+ "\r\n"
				+ "Thank you for your time and consideration. I look forward to your response.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+dto.getName()+"");
		
		mailsender.send(message);
    }
    
    public void updateStatus( int appId, String status) {
    	Application app = repo.findById(appId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
    	
    	   if (!status.equalsIgnoreCase("APPROVED")
    	            && !status.equalsIgnoreCase("REJECTED")
    	            && !status.equalsIgnoreCase("PENDING")) {
    	        throw new RuntimeException("Invalid status");
    	    }
    	   app.setStatus(status);
    	   
    	   repo.save(app);
			
		
    }

    //LANDLORD VIEW
    public List<Application> getForLandlord(String userName) {
        return repo.findByLandlord_UserNameOrderByCreatedAtDesc(userName);
    }

    // TENANT VIEW
    public List<Application> getForTenant(String userName) {
        return repo.findByTenant_UserNameOrderByCreatedAtDesc(userName);
    }
    
    @Transactional
    public void deleteApplication(int appId) {
        Application app = repo.findById(appId)
            .orElseThrow(() -> new RuntimeException("Application not found"));

        repo.delete(app);
    }
    
    
    public void sendSimplMail(int id) {
    	
    	SimpleMailMessage message=new SimpleMailMessage();
    	
    	Application app=repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));

		
		message.setTo(app.getEmail());
		
		message.setSubject("Property Rental Request");
		
		message.setText("I hope you are doing well.\r\n"
				+ "\r\n"
				+ "My name is "+app.getTenant()+", and I am interested in renting the property located at the address mentioned below:\r\n"
				+ "\r\n"
				+ "Property Address:\r\n"
				+ app.getAddress()+"\r\n"
				+ "\r\n"
				+ "I would like to schedule a meeting on "+app.getMeetingDate()+" to discuss the rental details, availability, and further process.\r\n"
				+ "\r\n"
				+ "You can contact me at:\r\n"
				+ "Phone Number: "+app.getPhoneNo()+"\r\n"
				+ "Email Address: "+app.getEmail()+"\r\n"
				+ "\r\n"
				+ "Thank you for your time and consideration. I look forward to your response.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+app.getTenant()+"");
		
		mailsender.send(message);
    }
    
    
}
