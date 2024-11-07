package com.TATA.Freelancing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TATA.Freelancing.entity.Customer;
import com.TATA.Freelancing.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	//create
	@PostMapping("/cust/create")
	public ResponseEntity<?> createCustomer(@RequestBody Customer cust)
	{
		return customerService.createCustomer(cust);
	}
	
	//fetch all
	@GetMapping("/cust/fetch")
	public ResponseEntity<?> fetchAllCustomer()
	{
		return customerService.fetchAllCustomer();
	}
	
	//fetch single
	@GetMapping("/cust/fetchone")
	public ResponseEntity<?> fetchSingleCust(@RequestParam("custId") Long id)
	{
		return customerService.fetchSingleCust(id);
	}
	
	//delete
	@DeleteMapping("/cust/delete")
	public ResponseEntity<?> deleteCust(@RequestParam("custId") Long id)
	{
		return customerService.deleteCust(id);
	}
	
	//update
	@PatchMapping("cust/update")
	public ResponseEntity<?> updateCust(@RequestBody Customer cust,@RequestParam("custId") Long id)
	{
		return customerService.updateCust(cust,id);
	}
	
	

}
