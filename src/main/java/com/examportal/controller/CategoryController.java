package com.examportal.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.examportal.entities.exam.Category;
import com.examportal.services.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	// add categories
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category)
	{
		
		 Category category1= this.categoryService.addCategory(category);
		 return ResponseEntity.ok(category1);
		
	}
	
	// get Category
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable("categoryId") Long categoryId)
	{
		return this.categoryService.getCategory(categoryId);
		
	}
	
	// get all categories
	@GetMapping("/")
	public ResponseEntity<?> getAllCategory()
	{
		return ResponseEntity.ok(this.categoryService.getCategories());
	}
	
	// upading in the category
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category)
	{
		return this.categoryService.updateCategory(category);
	}
	
	// deleting category
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable("categoryId") Long  categoryId)
	{
	    this.categoryService.deleteCategory(categoryId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
