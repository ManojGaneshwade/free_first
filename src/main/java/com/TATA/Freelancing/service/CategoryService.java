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
import com.TATA.Freelancing.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
    
	//create category
	public ResponseEntity<?> createCategory(Category category) 
	{
			Category savedCategory=categoryRepository.save(category);
			return new ResponseEntity<>(savedCategory,HttpStatus.CREATED);
		/*	if(savedCategory!=null)
			{
				return new ResponseEntity<>(savedCategory,HttpStatus.CREATED);
			}
			else
			{
			//throw new custom exception.
				return new ResponseEntity<>("record not saved",HttpStatus.CREATED);
			}  */
	}

	
	//get all resource
	public ResponseEntity<?> getAllCategory() 
	{
		List<Category> allCategory=categoryRepository.findAll();
		if(allCategory.isEmpty())
		{
			//return new ResponseEntity<>("no records present.",HttpStatus.OK);
			throw new ResourceNotFoundException("no records present.");
		}
		else
		{
			return new ResponseEntity<>(allCategory,HttpStatus.OK);
		
		}
	}
	
	//get single category by id
	@Cacheable(value = "category" ,key="#id")
		public ResponseEntity<?> getCategoryById(Long id) 
		{
			Optional<Category> opCategory=categoryRepository.findById(id);
			if(opCategory.isPresent())
			{
				Category present=opCategory.get();
				return new ResponseEntity<>(present,HttpStatus.OK);
			}
			else
			{
				//return new ResponseEntity<>("record is not present with id "+id,HttpStatus.NOT_FOUND);
			    throw new ResourceNotFoundException("record is not present with id "+id);
			}
		}

	
	//update category by id
	@CachePut(value="category",key="#id")
	public ResponseEntity<?> updateCategory(Category category,Long id) 
	{
		Optional<Category> opCategory=categoryRepository.findById(id);
		
		if(opCategory.isPresent())
		{
			Category existingCategory=opCategory.get();
			
			if(category.getCategoryId()!=null)
			{
				existingCategory.setCategoryId(category.getCategoryId());
			}
			if(category.getCategoryName()!=null)
			{
				existingCategory.setCategoryName(category.getCategoryName());
			}
			if(category.getCategoryDesc()!=null)
			{
				existingCategory.setCategoryDesc(category.getCategoryDesc());
			}
				
			Category saved=categoryRepository.save(existingCategory);
			return new ResponseEntity<>(saved,HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("record not present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("record not present with id "+id);
		}
	}

	
    //delete category by id
	@CacheEvict(value="category",key="#id")
	public ResponseEntity<?> deleteCategory(Long id) {
		Optional<Category> opCategory=categoryRepository.findById(id);
		if(opCategory.isPresent())
		{
			categoryRepository.deleteById(id);
			return new ResponseEntity<>("record is deletd.",HttpStatus.OK);
		}
		else
		{
			//return new ResponseEntity<>("record is not present with id "+id,HttpStatus.NOT_FOUND);
			throw new ResourceNotFoundException("record is not present with id "+id);
		}
	}
	
	
	//----------------------------------------search--------------------------------------//
	
	public Long findByCatName(String categoryName)
	{
		return categoryRepository.findByCatName(categoryName);
	}

	
	

}
