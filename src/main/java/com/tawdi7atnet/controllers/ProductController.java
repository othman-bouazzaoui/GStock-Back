package com.tawdi7atnet.controllers;

import com.tawdi7atnet.dto.product.ProductRequestDto;
import com.tawdi7atnet.dto.product.ProductResponseDto;
import com.tawdi7atnet.service.category.CategoryService;
import com.tawdi7atnet.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    private ProductService productService;
    
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<ProductResponseDto>> findAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable("id") Long id) {
    	return ResponseEntity.ok(productService.findProductById(id));
    }

    @PostMapping
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody ProductRequestDto product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    @PutMapping("update")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductRequestDto product) {
        return ResponseEntity.accepted().body(productService.updateProduct(product));
    }

    @DeleteMapping("delete/{id}")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().header("deletedId", String.valueOf(id)).build();
    }


}
