package com.tawdi7atnet.dto.commande;

import java.util.List;

import com.tawdi7atnet.dto.client.ClientResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommandeResponseDto {
	
	private Long id;
	private ClientResponseDto client;
	private List<DetailsCommandeDto> detailCommande;

}
