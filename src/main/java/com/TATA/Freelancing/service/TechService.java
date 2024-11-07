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
import com.TATA.Freelancing.entity.TechExpert;
import com.TATA.Freelancing.repository.TechRepository;

@Service
public class TechService {
	
	@Autowired
	TechRepository techRepository;

	//create tech
	public ResponseEntity<?> createTech(TechExpert techExpert) 
	{
		TechExpert tech=techRepository.save(techExpert);
		return new ResponseEntity<>(tech,HttpStatus.CREATED);
	}

	//get all tech
	public ResponseEntity<?> getAllTech()
	{
		List<TechExpert> tech=techRepository.findAll();
		if(tech.isEmpty())
		{
			//return new ResponseEntity<>("no records present.",HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no records present.");
		}
		else
		{
			return new ResponseEntity<>(tech,HttpStatus.OK);
		}
	}
	
	
	//fetch one record
	@Cacheable(value="techExpert",key="#id")
		public ResponseEntity<?> getSingleTech(Long id) 
		{	
			Optional<TechExpert> opTech=techRepository.findById(id);
			if(opTech.isPresent())
			{
				TechExpert tech=opTech.get();
				return new ResponseEntity<>(tech,HttpStatus.OK);
			}
			else
			{
				//return new ResponseEntity<>("no record present with id "+id,HttpStatus.NOT_FOUND);
				throw new ResourceNotFoundException("no record present with id "+id+" enter valid id.");
			}
		}
	

	//update tech
	@CachePut(value="techExpert", key="#id")
	public ResponseEntity<?> updateTech(TechExpert tech, Long id) {
		Optional<TechExpert> opTech=techRepository.findById(id);
		if(opTech.isPresent())
		{
			TechExpert existance=opTech.get();
			if(tech.getTechId()!=null)
			{
				existance.setTechId(tech.getTechId());
			}
			if(tech.getFname()!=null)
			{
				existance.setFname(tech.getFname());
			}
			if(tech.getLname()!=null)
			{
				existance.setLname(tech.getLname());
			}
			if(tech.getShopName()!=null)
			{
				existance.setShopName(tech.getShopName());
			}
			if(tech.getEmail()!=null)
			{
				existance.setEmail(tech.getEmail());
			}
			if(tech.getCity()!=null)
			{
				existance.setCity(tech.getCity());
			}
			
			TechExpert saved=techRepository.save(existance);
			return new ResponseEntity<>(saved,HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("no record present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no record present with id "+id);
		}
	}

	//delete
	@CacheEvict(value="techExpert",key="#id")
	public ResponseEntity<?> deleteTech(Long id) 
	{	
		Optional<TechExpert> opTech=techRepository.findById(id);
		if(opTech.isPresent())
		{
			techRepository.deleteById(id);
			return new ResponseEntity<>("record deleted successfully.",HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("no record present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no record present with id "+id);
		}
	}

	
	

}
