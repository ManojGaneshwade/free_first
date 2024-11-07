package com.TATA.Freelancing.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class TechExpert {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Long techId;
	
	private String fname;
	
	private String lname;
	
	private String shopName;
	
	private String email;
	
	private String city;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "techExpert")
	@JsonIgnore
	private List<Services> services;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "techExpert")
	@JsonIgnore
	private List<Request> request;
	
	
	public List<Request> getRequest() {
		return request;
	}
	public void setRequest(List<Request> request) {
		this.request = request;
	}
	public List<Services> getServices() {
		return services;
	}
	public void setServices(List<Services> services) {
		this.services = services;
	}
	
	
	
	public Long getTechId() {
		return techId;
	}
	public void setTechId(Long techId) {
		this.techId = techId;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	

}
