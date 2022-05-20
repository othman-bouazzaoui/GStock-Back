package com.tawdi7atnet.service.commande;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tawdi7atnet.dao.CommandeRepository;
import com.tawdi7atnet.dao.DetailsCommandeRepository;
import com.tawdi7atnet.dto.client.ClientResponseDto;
import com.tawdi7atnet.dto.commande.CommandeRequestDto;
import com.tawdi7atnet.dto.commande.CommandeResponseDto;
import com.tawdi7atnet.dto.commande.DetailsCommandeDto;
import com.tawdi7atnet.dto.product.ProductResponseDto;
import com.tawdi7atnet.entities.Client;
import com.tawdi7atnet.entities.Commande;
import com.tawdi7atnet.entities.DetailCommande;
import com.tawdi7atnet.entities.Product;
import com.tawdi7atnet.exception.EntityNotFoundException;
import com.tawdi7atnet.service.client.ClientService;
import com.tawdi7atnet.service.product.ProductService;

@Service
public class CommandeServiceImpl implements CommandeService {

	private CommandeRepository commandeRepository;
	private DetailsCommandeRepository detailsCommandeRepository;
	private ClientService clientService;
	private ProductService productService;
	private ModelMapper modelMapper;

	public CommandeServiceImpl(CommandeRepository commandeRepository,
			DetailsCommandeRepository detailsCommandeRepository, ClientService clientService,
			ProductService productService, ModelMapper modelMapper) {
		this.commandeRepository = commandeRepository;
		this.detailsCommandeRepository = detailsCommandeRepository;
		this.clientService = clientService;
		this.productService = productService;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<CommandeResponseDto> findAllCommandes() {

		List<CommandeResponseDto> response = new ArrayList<CommandeResponseDto>();

		commandeRepository.findAll().stream().forEach(cmd -> {
			ClientResponseDto client = modelMapper.map(cmd.getClient(), ClientResponseDto.class);
			List<DetailsCommandeDto> listDetailCommande = new ArrayList<DetailsCommandeDto>();

			cmd.getDetailCommandes().stream().forEach(dt -> {
				ProductResponseDto product = modelMapper.map(dt.getProduct(), ProductResponseDto.class);
				DetailsCommandeDto dtMap = new DetailsCommandeDto(product, dt.getQuantite());
				listDetailCommande.add(dtMap);
			});

			response.add(new CommandeResponseDto(cmd.getId(), client, listDetailCommande));
		});
		return response;
	}

	@Override
	public Commande addCommande(Commande commande) {
		Commande savedCmd = commandeRepository.save(commande);
		return savedCmd;
	}

	@Override
	public Commande addCommande(CommandeRequestDto commandeRequest) {

		Long productId = commandeRequest.getIdProduct();
		Long clientId = commandeRequest.getIdClient();
		double quantite = commandeRequest.getQuantie();

		Product product = productService.getProductById(productId);

		Client client = clientService.getClientById(clientId);

		Commande savedCmd = commandeRepository.save(new Commande(new Date(), client));

		List<DetailCommande> detailCommande = Arrays.asList(new DetailCommande(product, savedCmd, quantite));

		detailsCommandeRepository.saveAll(detailCommande);

		Commande response = commandeRepository.findById(savedCmd.getId()).get();

		return response;
	}

	@Override
	public Commande updateCommande(CommandeRequestDto commandeRequest) {

		Long productId = commandeRequest.getIdProduct();
		Long clientId = commandeRequest.getIdClient();
		double quantite = commandeRequest.getQuantie();

		Product product = productService.getProductById(productId);

		Client client = clientService.getClientById(clientId);

		Commande savedCmd = commandeRepository.save(new Commande(new Date(), client));

		List<DetailCommande> detailCommande = Arrays.asList(new DetailCommande(product, savedCmd, quantite));

		detailsCommandeRepository.saveAll(detailCommande);

		Commande response = commandeRepository.findById(savedCmd.getId()).get();

		return response;
	}

	@Override
	public void deleteCommandeById(Long id) {
		commandeRepository.deleteById(id);
	}

	@Override
	public CommandeResponseDto findCommandeById(Long id) {
		Commande commande = getCommandeById(id);
		
		ClientResponseDto client = modelMapper.map(commande.getClient(), ClientResponseDto.class);
		
		List<DetailsCommandeDto> listDetailCommande = new ArrayList<DetailsCommandeDto>();
		
		commande.getDetailCommandes().stream().forEach(dt -> {
			ProductResponseDto product = modelMapper.map(dt.getProduct(), ProductResponseDto.class);
			DetailsCommandeDto dtMap = new DetailsCommandeDto(product, dt.getQuantite());
			listDetailCommande.add(dtMap);
		});
		
		return new CommandeResponseDto(commande.getId(), client, listDetailCommande);
	}

	@Override
	public Commande getCommandeById(Long id) {

		return commandeRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Commande Id '" + id + "' Not Exists"));
	}

}
