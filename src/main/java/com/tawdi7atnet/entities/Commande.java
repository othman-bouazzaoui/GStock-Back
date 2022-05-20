/**
 * 
 */
package com.tawdi7atnet.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * @author Othman BOUAZZAOUI
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Commande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	Date dateCommand;
	
//	@ManyToOne
//	@JoinColumn(name = "PRODCUT_ID")
//	Product product;
	
	@ManyToOne
	Client client;

	@OneToMany(mappedBy = "commande")
	List<DetailCommande> detailCommandes;

	@Override
	public String toString() {
		return "Commande [id=" + id + ", dateCommand=" + dateCommand + ", client=" + client + "]";
	}

	public Commande(Date dateCommand, Client client) {
		this.dateCommand = dateCommand;
		this.client = client;
	}

	
	

}
