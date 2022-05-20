package com.tawdi7atnet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tawdi7atnet.entities.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long>{

}
