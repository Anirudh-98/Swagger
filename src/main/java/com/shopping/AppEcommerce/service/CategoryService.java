package com.shopping.AppEcommerce.service;

import com.shopping.AppEcommerce.model.Category;
import com.shopping.AppEcommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;

    public  void createCategory(Category category) {
        categoryRepo.save(category);
    }

    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepo.findAll();
    }

    public void updateCategory(Integer categoryId, Category newCategory) {
         Category category = categoryRepo.findById(categoryId).get();
         category.setCategoryName(newCategory.getCategoryName());
         category.setDescription(newCategory.getDescription());
         category.setImageUrl(newCategory.getImageUrl());
         categoryRepo.save(category);
    }

    public Category readCategory(String categoryName) {
        return categoryRepo.findByCategoryName(categoryName);
    }


    public boolean findById(int categoryID) {
        return categoryRepo.findById(categoryID).isPresent();
    }
    public void deleteCategoryById(int categoryID) {
        categoryRepo.deleteById(categoryID);
    }
}
