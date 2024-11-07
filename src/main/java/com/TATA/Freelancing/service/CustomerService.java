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
import com.TATA.Freelancing.entity.Customer;
import com.TATA.Freelancing.entity.TechExpert;
import com.TATA.Freelancing.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	//create
	public ResponseEntity<?> createCustomer(Customer cust)
	{
		Customer saved=customerRepository.save(cust);
		return new ResponseEntity<>(saved,HttpStatus.CREATED);
	}

	//fetch all
	public ResponseEntity<?> fetchAllCustomer()
	{	
		List<Customer> cust=customerRepository.findAll();
		if(cust.isEmpty())
		{
			//return new ResponseEntity<>("no records present.",HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no records present.");
		}
		else
		{
			return new ResponseEntity<>(cust,HttpStatus.OK);
		}
	}

	//delete cust
	@CacheEvict(value="customer",key="#id")
	public ResponseEntity<?> deleteCust(Long id) 
	{
		Optional<Customer> opCust=customerRepository.findById(id);
		if(opCust.isPresent())
		{
			customerRepository.deleteById(id);
			return new ResponseEntity<>("record deleted successfully.",HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("no record present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no record present with id "+id);
		}
	}

	//fetch single cust
	@Cacheable(value="customer",key="#id")
	public ResponseEntity<?> fetchSingleCust(Long id) 
	{
		Optional<Customer> opCust=customerRepository.findById(id);
		if(opCust.isPresent())
		{
			Customer cust=opCust.get();
			return new ResponseEntity<>(cust,HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("no record present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no record present with id "+id);
		}
	}

	//update cust
	@CachePut(value="customer",key="#id")
	public ResponseEntity<?> updateCust(Customer cust, Long id) {
		Optional<Customer> opCust=customerRepository.findById(id);
		if(opCust.isPresent())
		{
			Customer existance=opCust.get();
			if(cust.getCustId()!=null)
			{
				existance.setCustId(cust.getCustId());
			}
			if(cust.getFname()!=null)
			{
				existance.setFname(cust.getFname());
			}
			if(cust.getLname()!=null)
			{
				existance.setLname(cust.getLname());
			}
			if(cust.getEmail()!=null)
			{
				existance.setEmail(cust.getEmail());
			}
			if(cust.getMobile()!=null)
			{
				existance.setMobile(cust.getMobile());
			}
			if(cust.getCity()!=null)
			{
				existance.setCity(cust.getCity());
			}
			Customer saved=customerRepository.save(existance);
			return new ResponseEntity<>(saved,HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("no record present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no record present with id "+id);
		}
		
	}

}
