package com.tawdi7atnet.service.client;

import com.tawdi7atnet.dto.client.ClientRequestDto;
import com.tawdi7atnet.dto.client.ClientResponseDto;
import com.tawdi7atnet.entities.Client;

import java.util.List;

public interface ClientService {
	
    List<ClientResponseDto> findAllClients();
    
    ClientResponseDto findClientById(Long clientId);
    
    Client getClientById(Long clientId);
    
    ClientResponseDto addClient(ClientRequestDto c);
    
    ClientResponseDto updateClient(ClientRequestDto c);
    
    void deleteClientById(Long id);
}
