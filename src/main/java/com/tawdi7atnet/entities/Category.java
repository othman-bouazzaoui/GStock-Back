package com.tawdi7atnet.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 *
 * {@link Product}}
 *
 */

@SuppressWarnings("serial")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	String name;

	@Column( length = 600000)
	byte[] img;
	
	@OneToMany(mappedBy = "category")
	List<Product> products;
	
	public Category(String name, byte[] img) {
		this.name = name;
		this.img = img;
	}
	
	public Category(Long id,String name, byte[] img) {
		this.id = id;
		this.name = name;
		this.img = img;
	}
	
}
