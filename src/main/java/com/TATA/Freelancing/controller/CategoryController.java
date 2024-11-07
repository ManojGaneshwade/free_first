package com.TATA.Freelancing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.TATA.Freelancing.entity.Category;
import com.TATA.Freelancing.repository.CategoryRepository;
import com.TATA.Freelancing.service.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	//create category
	@PostMapping("/category/create")
	public ResponseEntity<?> createCategory(@RequestBody Category category)
	{
		return categoryService.createCategory(category);
	}
	
	
	//get all category
	@GetMapping("/category/fetch")
	public ResponseEntity<?> getAllCategory() 
	{
		return categoryService.getAllCategory();
	}
	
	
	//get single category
	@GetMapping("/category/fetchById")
	public ResponseEntity<?> getCategoryById(@RequestParam("categoryId")Long id) 
	{
		return categoryService.getCategoryById(id);
	}
	
	
	//update category
	@PatchMapping("/category/update/{id}")
	public ResponseEntity<?> updateCategoryById(@PathVariable("id") Long id,@RequestBody Category category)
	{
		return categoryService.updateCategory(category,id);
	}
	
	
	//delete catogory
	@DeleteMapping("/category/delete")
	public ResponseEntity<?> deleteCategory(@RequestParam("categoryId") Long id)
	{
		return categoryService.deleteCategory(id);
	}
	

	
}
