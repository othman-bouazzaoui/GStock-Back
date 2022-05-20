package com.tawdi7atnet.service.client;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tawdi7atnet.dao.ClientRepository;
import com.tawdi7atnet.dto.client.ClientRequestDto;
import com.tawdi7atnet.dto.client.ClientResponseDto;
import com.tawdi7atnet.entities.Client;
import com.tawdi7atnet.exception.EntityAlreadyExistException;
import com.tawdi7atnet.exception.EntityNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;
	private ModelMapper modelMapper;

	public ClientServiceImpl(ClientRepository clientRepository, ModelMapper modelMapper) {
		this.clientRepository = clientRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ClientResponseDto> findAllClients() {
		return clientRepository.findAll().stream().map(client -> modelMapper.map(client, ClientResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ClientResponseDto addClient(ClientRequestDto c) {

		boolean clientExiste = clientRepository.findById(c.getId()).isPresent();

		if (!clientExiste) {
			Client client = modelMapper.map(c, Client.class);
			ClientResponseDto savedClient = modelMapper.map(clientRepository.save(client), ClientResponseDto.class);

			return savedClient;

		} else {
			throw new EntityAlreadyExistException("This Client Already Exists, you can try to update it");
		}
	}

	@Override
	public ClientResponseDto updateClient(ClientRequestDto c) {

		getClientById(c.getId());

		Client client = modelMapper.map(c, Client.class);

		ClientResponseDto updatedClient = modelMapper.map(clientRepository.save(client), ClientResponseDto.class);

		return updatedClient;

	}

	@Override
	@Transactional
	public void deleteClientById(Long id) {
		
		getClientById(id);
		
		clientRepository.deleteById(id);
	}

	@Override
	public Client getClientById(Long clientId) {
		
		return clientRepository.findById(clientId)
				.orElseThrow(() -> new EntityNotFoundException("This client Id '" + clientId + "' Not Existe "));
	}

	@Override
	public ClientResponseDto findClientById(Long clientId) {
		Client client = getClientById(clientId);
		
		return modelMapper.map(client, ClientResponseDto.class);
	}
}
