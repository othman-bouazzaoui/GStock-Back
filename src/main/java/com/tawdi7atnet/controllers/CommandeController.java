package com.tawdi7atnet.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tawdi7atnet.dto.commande.CommandeRequestDto;
import com.tawdi7atnet.dto.commande.CommandeResponseDto;
import com.tawdi7atnet.entities.Commande;
import com.tawdi7atnet.service.commande.CommandeService;

@RestController
@RequestMapping("api/v1/commandes")
public class CommandeController {

	private CommandeService commandeService;

	public CommandeController(CommandeService commandeService) {
		this.commandeService = commandeService;
	}
	
	@GetMapping
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<CommandeResponseDto>> findAllCommandes(){
		return ResponseEntity.ok(commandeService.findAllCommandes());
	}
	
	@GetMapping("/{id}")
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<CommandeResponseDto> findCommandeById(@PathVariable("id") Long id){
		return ResponseEntity.ok(commandeService.findCommandeById(id));
	}
	
	@PostMapping("add")
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Commande> addCommande(@RequestBody CommandeRequestDto commandeRequest){
		Commande cmd = commandeService.addCommande(commandeRequest);
		
		return ResponseEntity.accepted().body(cmd);
	}
	
	@PutMapping("update")
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Commande> updateCommande(@RequestBody CommandeRequestDto commandeRequest){
		Commande cmd = commandeService.updateCommande(commandeRequest);
		
		return ResponseEntity.ok(cmd);
	}
	
	@DeleteMapping("delete/{id}")
	@PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<?> deleteCommandeById(@PathVariable("id") String id){
		return ResponseEntity.noContent().build();
	}

}
