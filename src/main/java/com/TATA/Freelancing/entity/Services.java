package com.TATA.Freelancing.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

@Entity
public class Services {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long serviceId;

	private String serviceName;
	
	private String serviceDesc;
	
	private Long price;
	
	@ManyToOne
	@JoinColumn(name="categoryId",nullable = false)
	private Category category;
	

	@ManyToOne
	@JoinColumn(name="techId",nullable = false)
	private TechExpert techExpert;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "service")
	@JsonIgnore
	private List<Request> request;
	
	
	public List<Request> getRequest() {
		return request;
	}
	public void setRequest(List<Request> request) {
		this.request = request;
	}
	
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceDesc() {
		return serviceDesc;
	}
	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}
	
	////----
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public TechExpert getTechExpert() {
		return techExpert;
	}
	
	public void setTechExpert(TechExpert techExpert) {
		this.techExpert = techExpert;
	}
	////----
	
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	

}
