package com.shopping.AppEcommerce.controller;

import com.shopping.AppEcommerce.common.ApiResponse;
import com.shopping.AppEcommerce.model.Category;
import com.shopping.AppEcommerce.service.CategoryService;
import com.shopping.AppEcommerce.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
        if (Helper.notNull(categoryService.readCategory(category.getCategoryName()))){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exists"),
                    HttpStatus.CONFLICT);
        }
         categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "created the category"),HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Category> categoryList() {
     return categoryService.getAllCategories();
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryID,@Valid @RequestBody Category category) {
        if(! categoryService.findById(categoryID)){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false,"Category NotFound."),HttpStatus.NOT_FOUND);
        }

         categoryService.updateCategory(categoryID, category);
         return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Category has been updated."),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") int categoryId) {
             categoryService.deleteCategoryById(categoryId);
             return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Category has been deleted."),HttpStatus.OK);
    }

}
