package com.example.blog_app_apis.controllers;

import com.example.blog_app_apis.payloads.ApiResponse;
import com.example.blog_app_apis.payloads.CategoryDTO;
import com.example.blog_app_apis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    //post
    @PostMapping("/")
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO createdCategoryDTO = categoryService.addCategory(categoryDTO);
        return new ResponseEntity<>(createdCategoryDTO, HttpStatus.CREATED);
    }

    //put
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable int catId){
        CategoryDTO updatedCategory = categoryService.updateCategory(categoryDTO,catId);
        return  new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }


    //delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<?> deleteCategory(@PathVariable int catId){
        categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted succesfully",true),HttpStatus.OK);
    }

    //get
    @GetMapping("/")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        return new ResponseEntity<>(categoryService.getAllCategory(),HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDTO> getSingleCategory(@PathVariable int catId){
        return new ResponseEntity<>(categoryService.getCategoryById(catId),HttpStatus.OK);
    }
}
