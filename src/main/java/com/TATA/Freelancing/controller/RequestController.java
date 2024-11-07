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

import com.TATA.Freelancing.entity.Request;
import com.TATA.Freelancing.service.RequestService;

@RestController
public class RequestController {
	
	@Autowired
	RequestService requestService;
	
	//create request
	@PostMapping("/request/create")
	public ResponseEntity<?> createRequest(@RequestBody Request request)
	{
		return requestService.createRequest(request);
	}
	
	//get all request
	@GetMapping("/request/fetchAll")
	public ResponseEntity<?> fetchAllRequest()
	{
		return requestService.fetchAllRequest();
	}
	
	
	//update status only
	@PatchMapping("/request/updateStatus")
	public ResponseEntity<?> updateRequestStatus(@RequestParam("reqId") Long id,@RequestBody Request request)
	{
		return requestService.updateRequestStatus(request,id);
	}
	
	
	//delete request
	@DeleteMapping("/request/delete")
	public ResponseEntity<?> deleteRequest(@RequestParam("reqId") Long id)
	{
		return requestService.deleteRequest(id);
	}
	
	//get single request by req id
	@GetMapping("/request/fetchOne")
	public ResponseEntity<?> fetchSingleRequest(@RequestParam("reqId") Long id)
	{
		return requestService.fetchSingleRequest(id);
	}
	
	//-------------------------------------Search------------------------------------------//
	
	
	
	//search request by custId - native query
	@GetMapping("/request/findByCustId")
	public ResponseEntity<?> findbyCustId(@RequestParam("custId") Long custId)
	{
		return requestService.findbyCustId(custId);
	}
	
	
	//search request by categoryId - native query
	@GetMapping("/request/findByCategoryId")
	public ResponseEntity<?> findbyCategoryId(@RequestParam("categoryId") Long categoryId)
	{
		return requestService.findbyCategoryId(categoryId);
	}
	
	
	//search request by techId - native query
	@GetMapping("/request/findByTechId")
	public ResponseEntity<?> findbyTechId(@RequestParam("techId") Long techId)
	{
		return requestService.findbyTechId(techId);
	}
	
	
	//search request by status - native query
	@GetMapping("/request/findByStatus")
	public ResponseEntity<?> findbyStatus(@RequestParam("status") String status)
	{
		return requestService.findbyStatus(status);
	}
	
	
	//search request by categoryName - native query
	@GetMapping("/request/findByCategoryName")
	public ResponseEntity<?> findByCategoryName(@RequestParam("categoryName") String categoryName)
	{
		return requestService.findByCategoryName(categoryName);
	}
	
	
	

}
