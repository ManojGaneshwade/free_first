package com.TATA.Freelancing.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TATA.Freelancing.entity.Services;
import com.TATA.Freelancing.service.ServicesService;

@RestController
public class ServicesController {
	
	@Autowired
	ServicesService servicesService;
	
	//create services
	@PostMapping("/services/create")
	public ResponseEntity<?> createServices(@RequestBody Services services)
	{
		return servicesService.createServices(services);
	}
	
	//get all services
	@GetMapping("/services/fetch")
	public ResponseEntity<?> fetchAllServices()
	{
		return servicesService.fetchAllServices();
	}
	
	//get one service
	@GetMapping("/services/fetchone")
	public ResponseEntity<?> fetchOneService(@RequestParam("serviceId") Long id)
	{
		return servicesService.fetchOneService(id);
	}
	
	//delete service
	@DeleteMapping("/services/delete")
	public ResponseEntity<?> deleteService(@RequestParam("serviceId") Long id)
	{
		return servicesService.deleteService(id);
	}
	
	//update service
	@PatchMapping("/services/update")
	public ResponseEntity<?> updateService(@RequestBody Services services,@RequestParam("serviceId") Long id)
	{
		return servicesService.updateService(services,id);
	}
	
	
	//------------------------------------SEARCH-------------------------------------------------------//
	
	//search by catId -native query
	@GetMapping("/services/searchByCategoryId")
	public ResponseEntity<?> findbyCategoryId(@RequestParam("categoryId") Long catId)
	{
		return servicesService.findbyCategoryId(catId);
	}
	
	@GetMapping("/services/searchByTechId")
	public ResponseEntity<?> findbyTechId(@RequestParam("techId") Long techId)
	{
		return servicesService.findbyTechId(techId);
	}
	
	@GetMapping("/services/searchByCatName")
	public ResponseEntity<?> findByCatName(@RequestParam("categoryName") String catName)
	{
		return servicesService.findByCatName(catName);
	}
}
