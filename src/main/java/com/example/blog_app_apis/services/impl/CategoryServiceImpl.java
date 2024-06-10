package com.example.blog_app_apis.services.impl;

import com.example.blog_app_apis.entity.Category;
import com.example.blog_app_apis.exception.ResourceNotFoundException;
import com.example.blog_app_apis.payloads.CategoryDTO;
import com.example.blog_app_apis.repositories.CategoryRepo;
import com.example.blog_app_apis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    CategoryRepo categoryRepo;
    ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepo.save(categoryDTOToCategory(categoryDTO));
        return categoryToCategoryDto(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        category.setCategoryTitle(categoryDTO.getCategoryTitle());
        category.setCategoryDescription(categoryDTO.getCategoryDescription());
        Category updatedCategory = categoryRepo.save(category);
        return categoryToCategoryDto(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categoryList = categoryRepo.findAll();
        List<CategoryDTO> categoryDTOList=  new ArrayList<>();
        for(Category category:categoryList){
            categoryDTOList.add(categoryToCategoryDto(category));
        }
        return categoryDTOList;
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        return categoryToCategoryDto(category);
    }

    CategoryDTO categoryToCategoryDto(Category category){
        return  modelMapper.map(category,CategoryDTO.class);
    }
    Category categoryDTOToCategory(CategoryDTO categoryDTO){
        return modelMapper.map(categoryDTO,Category.class);
    }
}
