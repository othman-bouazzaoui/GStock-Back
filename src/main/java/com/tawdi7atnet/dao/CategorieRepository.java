package com.tawdi7atnet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tawdi7atnet.entities.Category;

@Repository
public interface CategorieRepository extends JpaRepository<Category, Long> {

	@Query(value = "SELECT c FROM Category c where c.name=:name")
	Category findCategoryByName(@Param("name") String name);
	
	List<Category> findByNameContaining(String name);
}
