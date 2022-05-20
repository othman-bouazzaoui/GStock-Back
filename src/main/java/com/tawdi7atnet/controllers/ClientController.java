package com.tawdi7atnet.controllers;

import com.tawdi7atnet.dto.client.ClientRequestDto;
import com.tawdi7atnet.dto.client.ClientResponseDto;
import com.tawdi7atnet.service.client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/clients")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<ClientResponseDto>> findAllClients() {
        return ResponseEntity.ok(clientService.findAllClients());
    }
    
    @GetMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ClientResponseDto> findClientById(@PathVariable("id") Long id) {
    	return ResponseEntity.ok(clientService.findClientById(id));
    }

    @PostMapping("add")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ClientResponseDto> addClient(@RequestBody ClientRequestDto client){
        return ResponseEntity.ok(clientService.addClient(client));
    }

    @PutMapping("update")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<ClientResponseDto> updateClient(@RequestBody ClientRequestDto client){
        return ResponseEntity.ok(clientService.updateClient(client));
    }

    @DeleteMapping("delete/{id}")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<?> deleteClientById(@PathVariable("id") Long id){
        clientService.deleteClientById(id);
        return ResponseEntity.noContent()
                .header("deletedID",String.valueOf(id))
                .build();
    }

}
