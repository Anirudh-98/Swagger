package com.shopping.AppEcommerce.controller;

import com.shopping.AppEcommerce.common.ApiResponse;
import com.shopping.AppEcommerce.dto.ProductDto;
import com.shopping.AppEcommerce.model.Category;
import com.shopping.AppEcommerce.repository.CategoryRepository;
import com.shopping.AppEcommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
   private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;
    @PostMapping("/createProduct")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
      Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
      if(!optionalCategory.isPresent()) {
          return new ResponseEntity<ApiResponse>(new ApiResponse(false,"category does not exists"), HttpStatus.BAD_REQUEST);
      }
      productService.createProduct(productDto,optionalCategory.get());
      return new ResponseEntity<ApiResponse>(new ApiResponse(true,"product has been created"),HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getProducts(){
     List<ProductDto> products = productService.getAllProducts();
     return new ResponseEntity<>(products,HttpStatus.OK);
    }

    //edit Product
    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> editProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if(!optionalCategory.isPresent()) {
            return new ResponseEntity<ApiResponse>(new ApiResponse(false,"category does not exists"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto,productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true,"product has been updated"),HttpStatus.OK);
    }
}
