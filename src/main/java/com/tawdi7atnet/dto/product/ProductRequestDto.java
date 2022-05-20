package com.tawdi7atnet.dto.product;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class ProductRequestDto implements Serializable {
    private Long id;

    private String name;

    private Double price;
    
    private Long availableQuantity;

    @NotBlank(message = "categories est obligatoire si vide mettez la valeur la valeur par défaut 1")
    private Long category;

    public ProductRequestDto(Long id, String name, Double price, Long availableQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }
}
