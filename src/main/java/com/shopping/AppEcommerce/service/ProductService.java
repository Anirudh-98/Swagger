package com.shopping.AppEcommerce.service;

import com.shopping.AppEcommerce.dto.ProductDto;
import com.shopping.AppEcommerce.model.Category;
import com.shopping.AppEcommerce.model.Product;
import com.shopping.AppEcommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setImageURL(productDto.getImageURL());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setCategory(category);
        productRepository.save(product);
    }

    public ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setImageURL(product.getImageURL());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setCategoryId(product.getCategory().getId());
        productDto.setId(product.getId());
        return productDto;
    }

    public  List<ProductDto> getAllProducts() {
      List<Product> allProducts = (List<Product>) productRepository.findAll();
      List<ProductDto> productDtos = new ArrayList<>();
      for(Product product: allProducts ) {
          productDtos.add(getProductDto(product));
      }
      return productDtos;
    }

    public void updateProduct(ProductDto productDto,Integer productId) throws Exception {
       Optional<Product> optionalProduct= productRepository.findById(productId);
       //throw an exception if product does not exits.
        if(!optionalProduct.isPresent()) {
            throw new Exception("product not present");
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());
        product.setImageURL(productDto.getImageURL());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }
}
