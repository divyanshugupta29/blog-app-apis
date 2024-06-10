package com.example.blog_app_apis.services;

import com.example.blog_app_apis.entity.Category;
import com.example.blog_app_apis.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    //Post
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    //Put
    CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer categoryId);
    //delete
    void deleteCategory(Integer categoryId);
    //getAll
    List<CategoryDTO> getAllCategory();
    //get by id
    CategoryDTO getCategoryById(Integer categoryId);
}
