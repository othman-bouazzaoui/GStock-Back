package com.tawdi7atnet.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tawdi7atnet.entities.Category;

@DataJpaTest
class CategorieRepositoryTest {

	@Autowired
	private CategorieRepository categorieRepositoryUnderTest;

	public static Logger LOG = LoggerFactory.getLogger(CategorieRepositoryTest.class);
	
	Category catToSave = null;

	@AfterAll
	static void afterAllMethod() {
		LOG.info("I am afterAllMethode *************** ");
	}

	@BeforeAll
	 static void beforeAllMethod() {
		LOG.info("I am beforeAllMethod *************** ");
		
	}

	@AfterEach
	void afterEachMethod() {
		LOG.info("I am afterEachMethod *************** ");
	}

	@BeforeEach
	void beforeEachMethod() {
		LOG.info("I am beforeEachMethod ************** ");
		categorieRepositoryUnderTest.deleteAll();
	}

	@Test
	void testShouldReturnCategoryById() {
		
		LOG.info("I am testShouldReturnCategoryById ");
		
		//given 
		catToSave = categorieRepositoryUnderTest.save(new Category(1L,"Category Test", null));
		
		//when
		Category savedCat = categorieRepositoryUnderTest.findById(1L).get();
		
		//then
		assertThat(catToSave).isEqualTo(savedCat);
	}
	
	@Test
	void testShouldReturnCategoryByName() {
		
		LOG.info("I am testShouldReturnCategoryByName ");
		
		//given 
		catToSave = categorieRepositoryUnderTest.save(new Category(1L,"Category Test", null));
		
		//when
		Category savedCat = categorieRepositoryUnderTest.findCategoryByName("Category Test");
				
		//then
		assertThat(catToSave).isEqualTo(savedCat);
	}
	
	@Test
	void testShouldReturnCategoriesByContainingValueInName() {
		
		LOG.info("I am testShouldReturnCategoryByContainingValueInName ");
		
		List<Category> catgoriesToSave = Arrays.asList(new Category("Category Test", null), new Category("Category Test", null));
		
		//given 
		List<Category> catsToSave = categorieRepositoryUnderTest.saveAll(catgoriesToSave);
		
		//when
		List<Category> savedCats = categorieRepositoryUnderTest.findByNameContaining("Category Test");
		
		System.out.println(catsToSave);
		System.out.println(catsToSave);
		//then
		assertThat(catsToSave).isEqualTo(savedCats);
	}

}
