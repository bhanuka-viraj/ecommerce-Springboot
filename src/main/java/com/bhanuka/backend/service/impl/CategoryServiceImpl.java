package com.bhanuka.backend.service.impl;

import com.bhanuka.backend.entity.Category;
import com.bhanuka.backend.reository.CategoryRepository;
import com.bhanuka.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category existinCategory=categoryRepository.findById(id).orElse(null);

        if(existinCategory!=null){
            existinCategory.setName(category.getName());
            return categoryRepository.save(existinCategory);
        }else{
            return null;
        }
    }
}
