package com.tawdi7atnet.dto.commande;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CommandeRequestDto {
	
	private Long id;
	private Long idClient;
	private Long idProduct;
	private double quantie;
}
