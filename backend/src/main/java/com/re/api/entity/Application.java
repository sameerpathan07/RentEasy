package com.re.api.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Application {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "image_id")
   private Image image;

    @ManyToOne
    @JoinColumn(name = "tenant_name")
    private User tenant;   // applicant

    @ManyToOne
    @JoinColumn(name = "landlord_name")
    private User landlord; // property owner

    private String address;
    private LocalDate meetingDate;

    private String status;
    private String phoneNo;
    private String email;

    private LocalDateTime createdAt = LocalDateTime.now();
    
    
    public Application() {
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}


	public User getTenant() {
		return tenant;
	}


	public void setTenant(User tenant) {
		this.tenant = tenant;
	}


	public User getLandlord() {
		return landlord;
	}


	public void setLandlord(User landlord) {
		this.landlord = landlord;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public LocalDate getMeetingDate() {
		return meetingDate;
	}


	public void setMeetingDate(LocalDate meetingDate) {
		this.meetingDate = meetingDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public String toString() {
		return "Application [id=" + id + ", image=" + image + ", tenant=" + tenant + ", landlord=" + landlord
				+ ", address=" + address + ", meetingDate=" + meetingDate + ", status=" + status + ", phoneNo="
				+ phoneNo + ", email=" + email + ", createdAt=" + createdAt + "]";
	}
	
	
	
	
}

