package com.tawdi7atnet.dto.product;

import com.tawdi7atnet.dto.CategoryDto;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public final class ProductResponseDto implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private Long availableQuantity;
    private CategoryDto category;
    
}
