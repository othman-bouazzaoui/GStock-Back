package com.tawdi7atnet.config;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tawdi7atnet.dao.ClientRepository;
import com.tawdi7atnet.dao.DetailsCommandeRepository;
import com.tawdi7atnet.dto.CategoryDto;
import com.tawdi7atnet.dto.client.ClientRequestDto;
import com.tawdi7atnet.dto.product.ProductRequestDto;
import com.tawdi7atnet.entities.Client;
import com.tawdi7atnet.entities.Commande;
import com.tawdi7atnet.entities.DetailCommande;
import com.tawdi7atnet.entities.Product;
import com.tawdi7atnet.service.category.CategoryService;
import com.tawdi7atnet.service.client.ClientService;
import com.tawdi7atnet.service.commande.CommandeService;
import com.tawdi7atnet.service.product.ProductService;

@Configuration
public class LoadData {

    @Bean
    CommandLineRunner run(CategoryService categorieService, 
    		ProductService productService, ClientService clientService, 
    		CommandeService commandeService,DetailsCommandeRepository dtDao,
    		ClientRepository clientRepository ) {
        return args -> {
        	
        	//Add Categories
        	categorieService.saveCategory(new CategoryDto(1L,"TestCat1",null));
        	categorieService.saveCategory(new CategoryDto(2L,"TestCat2",null));
        	categorieService.saveCategory(new CategoryDto(3L,"TestCat3",null));
        	
        	//Add Product With her category
        	productService.saveProduct(new ProductRequestDto(1L,"Dell",1000.00d,15L,1L));
        	productService.saveProduct(new ProductRequestDto(2L,"HP",500.00d,10L,2L));
        	productService.saveProduct(new ProductRequestDto(3L,"Samsung",2000.00d,25L,3L));
        	
        	//Add Clients
        	clientService.addClient(new ClientRequestDto(1L, "Othman","BOUAZZAOUI"));
        	clientService.addClient(new ClientRequestDto(2L, "Mohamed","Ahmed"));
        	clientService.addClient(new ClientRequestDto(3L, "Khaled","Amr"));
    
        	//Ressources
        	Client client = new Client(4L, "Othman","BOUAZZAOUI",null);
        	Product product = new Product(4L,"Dell",1000.00d,5L,null, null, null);
        	
        	//Add Commande
        	Commande commande = new Commande();
        	commande.setClient(clientRepository.save(client));
        	commande.setDateCommand(new Date());
        	commande = commandeService.addCommande(commande);
        
        	//Add DétailCommande
        	List<DetailCommande> dtsCommande = Arrays.asList(
        			new DetailCommande(product, commande, 2d),
        			new DetailCommande(product, commande, 3d));
        	dtDao.saveAll(dtsCommande);
 
        };
    }
}
