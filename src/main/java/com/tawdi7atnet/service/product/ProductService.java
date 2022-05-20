package com.tawdi7atnet.service.product;

import java.util.List;

import com.tawdi7atnet.dto.product.ProductRequestDto;
import com.tawdi7atnet.dto.product.ProductResponseDto;
import com.tawdi7atnet.entities.Product;

public interface ProductService {
	
    List<ProductResponseDto> findAllProducts();
    
    ProductResponseDto findProductById(Long productId);
    
    Product getProductById(Long productId);
    
    ProductResponseDto saveProduct(ProductRequestDto p);
    
    ProductResponseDto updateProduct(ProductRequestDto p);
    
    void deleteProductById(Long id);
    
    ProductRequestDto addImgToProduct(byte[] img, Long id);
    
    byte[] findImgProductById(Long id);
}
