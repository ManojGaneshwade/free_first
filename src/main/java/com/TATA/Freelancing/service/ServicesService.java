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

import com.TATA.Freelancing.entity.Services;
import com.TATA.Freelancing.entity.TechExpert;
import com.TATA.Freelancing.controller.customException.ResourceNotFoundException;
import com.TATA.Freelancing.entity.Category;
import com.TATA.Freelancing.entity.Customer;
import com.TATA.Freelancing.repository.CategoryRepository;
import com.TATA.Freelancing.repository.ServicesRepository;
import com.TATA.Freelancing.repository.TechRepository;

@Service
public class ServicesService {
	
	@Autowired
	ServicesRepository servicesRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	TechRepository techRepository;
	
	@Autowired
	CategoryService categoryService;

	//create services
	public ResponseEntity<?> createServices(Services services)
	{
		Category excategory=categoryRepository.findById(services.getCategory().getCategoryId()).get();
		TechExpert extechExpert=techRepository.findById(services.getTechExpert().getTechId()).get();
		
		services.setCategory(excategory);
		services.setTechExpert(extechExpert);
		
		Services serv=	servicesRepository.save(services);
		return new ResponseEntity<>(serv,HttpStatus.CREATED);
	}

	//fetch all services
	public ResponseEntity<?> fetchAllServices() {
		List<Services> servicesList=servicesRepository.findAll();
		if(servicesList.isEmpty())
		{
			//return new ResponseEntity<>("no records present. ",HttpStatus.OK);
			throw new ResourceNotFoundException("no records present. ");
		}
		else
		{
			return new ResponseEntity<>(servicesList,HttpStatus.OK);
		}
	}

	//fetch single services
	@Cacheable(value="services",key="#id")
	public ResponseEntity<?> fetchOneService(Long id) 
	{
		Optional<Services> opService=servicesRepository.findById(id);
		if(opService.isPresent())
		{
			Services service=opService.get();
			return new ResponseEntity<>(service,HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("no record present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no record present with id "+id);
		}
	}

	//delete service
	@CacheEvict(value="services",key="#id")
	public ResponseEntity<?> deleteService(Long id) 
	{
		Optional<Services> opService=servicesRepository.findById(id);
		if(opService.isPresent())
		{
			servicesRepository.deleteById(id);
			return new ResponseEntity<>("service deleted successfully with id "+id,HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("no record present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no record present with id "+id);
		}
	}

	//update service
	@CachePut(value="services",key="#id")
	public ResponseEntity<?> updateService(Services services,Long id) 
	{
		Optional<Services> opService=servicesRepository.findById(id);
		if(opService.isPresent())
		{
			Services existance=opService.get();
			if(services.getServiceName()!=null)
			{
				existance.setServiceName(services.getServiceName());
			}
			if(services.getServiceDesc()!=null)
			{
				existance.setServiceDesc(services.getServiceDesc());
			}
			if(services.getPrice()!=null)
			{
				existance.setPrice(services.getPrice());
			}
			if(services.getCategory()!=null)
			{
				Category excategory=categoryRepository.findById(services.getCategory().getCategoryId()).get();
				existance.setCategory(excategory);
			}
			if(services.getTechExpert()!=null)
			{
				TechExpert extechExpert=techRepository.findById(services.getTechExpert().getTechId()).get();
				existance.setTechExpert(extechExpert);
			}
			Services saved=servicesRepository.save(existance);
			return new ResponseEntity<>(saved,HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("no record present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("no record present with id "+id);
		}
	}

	
	//------------------------------------SEARCH-------------------------------------------------------//

	
	
	//search services by cat Id  -native query
	public ResponseEntity<?> findbyCategoryId(Long catId)
	{
		Optional<List<Services>> opService=servicesRepository.findbyCategoryId(catId);
		if(opService.isPresent())
		{
			List<Services> serviceList=opService.get();
			if(!serviceList.isEmpty())
				return new ResponseEntity<>(serviceList,HttpStatus.OK);
			else
				//return new ResponseEntity<>("records not present with category "+catId,HttpStatus.NOT_FOUND);
				throw new ResourceNotFoundException("records not present with category "+catId);
		}
		else
		{
			//return new ResponseEntity<>("records not present.",HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("records not present.");
		}
	}
	
	//search services by tech Id -native query
	public ResponseEntity<?> findbyTechId(Long techId)
	{
		Optional<List<Services>> opService=servicesRepository.findbyTechId(techId);
		if(opService.isPresent())
		{
			List<Services> serviceList=opService.get();
			if(!serviceList.isEmpty())
				return new ResponseEntity<>(serviceList,HttpStatus.OK);
			else
				//return new ResponseEntity<>("records not present with techId "+techId,HttpStatus.NOT_FOUND);
				throw new ResourceNotFoundException("records not present with techId "+techId);
		}
		else
		{
			//return new ResponseEntity<>("records not present.",HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("records not present.");
		}
	}
	

	//search services by cat name
	public ResponseEntity<?> findByCatName(String categoryName)
	{
		Long catId=categoryService.findByCatName(categoryName);
		Optional<List<Services>> opService=servicesRepository.findbyCategoryId(catId);
		if(opService.isPresent())
		{
			List<Services> serviceList=opService.get();
			if(!serviceList.isEmpty())
				return new ResponseEntity<>(serviceList,HttpStatus.OK);
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
