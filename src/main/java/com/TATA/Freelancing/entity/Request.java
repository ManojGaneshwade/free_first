package com.TATA.Freelancing.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Request {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reqId;
	
	private String status;
	
	@ManyToOne
	@JoinColumn(name="serviceId")
	private Services service;
	
	@ManyToOne
	@JoinColumn(name="custId")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="techId")
	@JsonIgnore
	private TechExpert techExpert;
	
	@ManyToOne
	@JoinColumn(name="categoryId")
	@JsonIgnore
	private Category category;
	
	
	
	

	public Long getReqId() {
		return reqId;
	}

	public void setReqId(Long reqId) {
		this.reqId = reqId;
	}

	public Services getService() {
		return service;
	}

	public void setService(Services service) {
		this.service = service;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public TechExpert getTechExpert() {
		return techExpert;
	}

	public void setTechExpert(TechExpert techExpert) {
		this.techExpert = techExpert;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
