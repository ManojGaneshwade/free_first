package com.TATA.Freelancing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.TATA.Freelancing.controller.customException.ResourceNotFoundException;
import com.TATA.Freelancing.entity.Category;
import com.TATA.Freelancing.entity.Customer;
import com.TATA.Freelancing.entity.Request;
import com.TATA.Freelancing.entity.Services;
import com.TATA.Freelancing.entity.TechExpert;
import com.TATA.Freelancing.repository.CategoryRepository;
import com.TATA.Freelancing.repository.CustomerRepository;
import com.TATA.Freelancing.repository.RequestRepository;
import com.TATA.Freelancing.repository.ServicesRepository;
import com.TATA.Freelancing.repository.TechRepository;

@Service
public class RequestService {
	
	@Autowired
	RequestRepository requestRepository;
	
	@Autowired
	ServicesRepository servicesRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	TechRepository techRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	//create request
	public ResponseEntity<?> createRequest(Request request) 
	{
		Customer customer=customerRepository.findById(request.getCustomer().getCustId()).get();
		Services service=servicesRepository.findById(request.getService().getServiceId()).get();
		//service.getTechExpert().getTechId()
		Category category=categoryRepository.findById(service.getCategory().getCategoryId()).get();
		TechExpert techExpert=techRepository.findById(service.getTechExpert().getTechId()).get();
		
		
		request.setService(service);
		request.setCustomer(customer);
		request.setCategory(category);
		request.setTechExpert(techExpert);
		
		Request saved=requestRepository.save(request);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	
	}

	//fetch all request
	public ResponseEntity<?> fetchAllRequest() 
	{
		List<Request> requestList=requestRepository.findAll();
		if(requestList.isEmpty())
		{
			//return new ResponseEntity<>("no records present. ",HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no records present. ");
		}
		else
		{
			return new ResponseEntity<>(requestList,HttpStatus.OK);
		}
	}

	
	//update request status
	@CachePut(value="request",key="#id")
	public ResponseEntity<?> updateRequestStatus(Request request,Long id) 
	{
		Optional<Request> opRequest=requestRepository.findById(id);
		if(opRequest.isPresent())
		{
			Request existance=opRequest.get();
			if(request.getStatus()!=null)
			{
				existance.setStatus(request.getStatus());
			}
			
			Request saved=requestRepository.save(existance);
			return new ResponseEntity<>(saved,HttpStatus.OK);
			
		}
		else
		{
			//return new ResponseEntity<>("no records present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no records present with id "+id);
		}
	}

	
	//delete request
	@CacheEvict(value="request",key="#id")
	public ResponseEntity<?> deleteRequest(Long id)
	{
		Optional<Request> opRequest=requestRepository.findById(id);
		if(opRequest.isPresent())
		{
			requestRepository.deleteById(id);
			return new ResponseEntity<>("request deleted successfully with id "+id,HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("no request present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no request present with id "+id);
		}
	}

	//fetch single request by id
	@Cacheable(value="request",key="#id")
	public ResponseEntity<?> fetchSingleRequest(Long id) 
	{
		Optional<Request> opRequest=requestRepository.findById(id);
		if(opRequest.isPresent())
		{
			Request request=opRequest.get();
			return new ResponseEntity<>(request,HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("no request present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no request present with id "+id);
		}

	}
	
	//-------------------------------------Search------------------------------------------//
	
	//search request by custId - native query
	public ResponseEntity<?> findbyCustId(Long custId)
	{
		Optional<List<Request>> opRequest=requestRepository.findbyCustId(custId);
		if(opRequest.isPresent())
		{
			List<Request> requestList=opRequest.get();
			if(!requestList.isEmpty())
			{
				return new ResponseEntity<>(requestList,HttpStatus.NOT_FOUND);
			}
			else
			{
				//return new ResponseEntity<>("records not present with cust id "+custId,HttpStatus.NOT_FOUND);
				throw new ResourceNotFoundException("records not present with cust id "+custId);
			}
		}
		else
		{
			//return new ResponseEntity<>("records not present.",HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("records not present.");
		}
	}
	
	
		//search request by categoryId - native query
		public ResponseEntity<?> findbyCategoryId(Long categoryId)
		{
			Optional<List<Request>> opRequest=requestRepository.findbyCategoryId(categoryId);
			if(opRequest.isPresent())
			{
				List<Request> requestList=opRequest.get();
				if(!requestList.isEmpty())
				{
					return new ResponseEntity<>(requestList,HttpStatus.NOT_FOUND);
				}
				else
				{
					//return new ResponseEntity<>("records not present with category id "+categoryId,HttpStatus.NOT_FOUND);
					throw new ResourceNotFoundException("records not present with category id "+categoryId);
				}
			}
			else
			{
				//return new ResponseEntity<>("records not present.",HttpStatus.NOT_FOUND);
				throw new ResourceNotFoundException("records not present.");
			}
		}
		
		
		//search request by techId - native query
		public ResponseEntity<?> findbyTechId(Long techId)
		{
			Optional<List<Request>> opRequest=requestRepository.findbyTechId(techId);
			if(opRequest.isPresent())
			{
				List<Request> requestList=opRequest.get();
				if(!requestList.isEmpty())
				{
					return new ResponseEntity<>(requestList,HttpStatus.NOT_FOUND);
				}
				else
				{
					//return new ResponseEntity<>("records not present with tech id "+techId,HttpStatus.NOT_FOUND);
					throw new ResourceNotFoundException("records not present with tech id "+techId);
				}
			}
			else
			{
				//return new ResponseEntity<>("records not present.",HttpStatus.NOT_FOUND);
				throw new ResourceNotFoundException("records not present.");
			}
		}
		
		
		//search request by status - native query
		public ResponseEntity<?> findbyStatus(String status)
		{
			Optional<List<Request>> opRequest=requestRepository.findbyStatus(status);
			if(opRequest.isPresent())
			{
				List<Request> requestList=opRequest.get();
				if(!requestList.isEmpty())
				{
					return new ResponseEntity<>(requestList,HttpStatus.NOT_FOUND);
				}
				else
				{
					//return new ResponseEntity<>("records not present with status "+status,HttpStatus.NOT_FOUND);
					throw new ResourceNotFoundException("records not present with status "+status);
				}
			}
			else
			{
				//return new ResponseEntity<>("records not present.",HttpStatus.NOT_FOUND);
				throw new ResourceNotFoundException("records not present.");
			}
		}
		
		
		@Autowired
		CategoryService categoryService;
		
		//search request by category name  - native query
		public ResponseEntity<?> findByCategoryName(String categoryName)
		{
			Long catId=categoryService.findByCatName(categoryName);
			Optional<List<Request>> opService=requestRepository.findbyCategoryId(catId);
			if(opService.isPresent())
			{
				List<Request> requestList=opService.get();
				if(!requestList.isEmpty())
					return new ResponseEntity<>(requestList,HttpStatus.OK);
				else
					//return new ResponseEntity<>("records not present with category name= "+categoryName,HttpStatus.NOT_FOUND);
					throw new ResourceNotFoundException("records not present with category name= "+categoryName);
			}
			else
			{
				//return new ResponseEntity<>("records not present.",HttpStatus.NOT_FOUND);
				throw new ResourceNotFoundException("records not present.");
			}
		}	

}
