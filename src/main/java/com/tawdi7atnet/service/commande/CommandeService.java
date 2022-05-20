package com.tawdi7atnet.service.commande;

import java.util.List;

import com.tawdi7atnet.dto.commande.CommandeRequestDto;
import com.tawdi7atnet.dto.commande.CommandeResponseDto;
import com.tawdi7atnet.entities.Commande;

public interface CommandeService {
	
	List<CommandeResponseDto> findAllCommandes();
	
	CommandeResponseDto findCommandeById(Long id);
	
	Commande getCommandeById(Long id);
	
	Commande addCommande(Commande c);
	
	Commande addCommande(CommandeRequestDto commandeRequestDto);
	
	Commande updateCommande(CommandeRequestDto commandeRequestDto);
	
	void deleteCommandeById(Long id);
	

}
