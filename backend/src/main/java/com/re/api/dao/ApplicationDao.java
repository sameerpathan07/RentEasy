package com.re.api.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import com.re.api.dto.ApplicationRepo;
import com.re.api.dto.ApplicationRequest;
import com.re.api.dto.ImageRepo;
import com.re.api.dto.UserRepo;
import com.re.api.entity.Application;
import com.re.api.entity.Image;
import com.re.api.entity.User;

import jakarta.transaction.Transactional;

@Repository
public class ApplicationDao {

    // ✅ Logger is better than System.out.println for real projects
    private static final Logger logger = LoggerFactory.getLogger(ApplicationDao.class);

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

        User landlord = image.getUser();

        // Prevent duplicate application
        boolean exists = repo.existsByImage_IdAndTenant_UserName(imageId, tenant.getUserName());

        if (exists) {
            throw new RuntimeException("Already applied");
        }

        Application app = new Application();
        app.setImage(image);
        app.setTenant(tenant);
        app.setLandlord(landlord);
        app.setAddress(dto.getAddress());
        app.setMeetingDate(dto.getDate());
        app.setEmail(dto.getEmail());
        app.setPhoneNo(dto.getPhoneNo());
        app.setStatus("PENDING");

        // ✅ Save to DB first
        repo.save(app);

        // ✅ Wrap Email in Try-Catch so the app doesn't crash if email fails
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(landlord.getEmail());
            message.setSubject("Property Rental Request");
            message.setText("I hope you are doing well.\r\n"
                    + "\r\n"
                    + "My name is " + dto.getName() + ", and I am interested in renting the property located at:\r\n"
                    + app.getAddress() + "\r\n"
                    + "\r\n"
                    + "Meeting Date: " + app.getMeetingDate() + "\r\n"
                    + "Contact: " + app.getPhoneNo() + "\r\n"
                    + "Email: " + app.getEmail() + "\r\n"
                    + "\r\n"
                    + "Best regards,\r\n"
                    + dto.getName());

            mailsender.send(message);
            logger.info("Email sent successfully to Landlord: " + landlord.getEmail());

        } catch (Exception e) {
            // ⚠️ Log the error but DO NOT throw it. This keeps the data saved.
            logger.error("Failed to send email to landlord. Error: " + e.getMessage());
        }
    }

    public void updateStatus(int appId, String status) {
        Application app = repo.findById(appId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        if (!status.equalsIgnoreCase("APPROVED")
                && !status.equalsIgnoreCase("REJECTED")
                && !status.equalsIgnoreCase("PENDING")) {
            throw new RuntimeException("Invalid status");
        }
        app.setStatus(status);
        repo.save(app);
    }

    public List<Application> getForLandlord(String userName) {
        return repo.findByLandlord_UserNameOrderByCreatedAtDesc(userName);
    }

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
        // ✅ Wrap Email in Try-Catch
        try {
            Application app = repo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Application not found"));

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(app.getEmail());
            message.setSubject("Property Rental Request Update");
            message.setText("Hello " + app.getTenant().getUserName() + ",\r\n"
                    + "\r\n"
                    + "Your application status for the property at " + app.getAddress() + " has been updated.\r\n"
                    + "Current Status: " + app.getStatus() + "\r\n"
                    + "\r\n"
                    + "Best regards,\r\n"
                    + "RentEasy Team");

            mailsender.send(message);
            logger.info("Email sent successfully to Tenant: " + app.getEmail());

        } catch (Exception e) {
            logger.error("Failed to send email to tenant. Error: " + e.getMessage());
        }
    }
}
