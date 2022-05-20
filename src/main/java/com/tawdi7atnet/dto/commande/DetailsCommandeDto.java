package com.tawdi7atnet.dto.commande;

import com.tawdi7atnet.dto.product.ProductResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DetailsCommandeDto {
	
	private ProductResponseDto product;
	
	private double quantite;
	
	

}
