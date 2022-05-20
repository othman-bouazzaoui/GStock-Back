package com.tawdi7atnet.controllers;

import com.tawdi7atnet.dto.CategoryDto;
import com.tawdi7atnet.service.category.CategoryService;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/categories")
public class CategoryController {
	
    private CategoryService categorieService;
    
    public static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

    public CategoryController(CategoryService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<List<CategoryDto>> findAllCategories(){
    	//how to add a header values
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("username", "Othman");
    	ResponseEntity<List<CategoryDto>> response = ResponseEntity.ok()
    			.headers(headers)
    			.body(categorieService.findAllCategories());
        
    	return response;
    }
    
    @PostMapping("add")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<CategoryDto> saveCategory(@Valid @RequestBody CategoryDto cat){

        CategoryDto createdCat = categorieService.saveCategory(cat);
    	return ResponseEntity.status(HttpStatus.CREATED).body(createdCat);
    }

    //Example to Save Image in DB
    @PostMapping(value = "saveImage", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<?> saveImgCategory(@RequestPart(name = "imageFile", required = false) MultipartFile file, @RequestPart("catId") String catId) throws IOException {

    	/*
        Map<String,String> data = new HashMap<>();
        data.put("Image Name", file.getOriginalFilename());
        data.put("Category ID", catId);
        
        LOGGER.info("Image Name" + file.getOriginalFilename());
        LOGGER.info("Category ID " + Long.parseLong(catId));
        */
    	
    	LOGGER.info(" Start  : Save Category Image ");
    	
        CategoryDto categorieDto = categorieService.addImgToCategory(file.getBytes(), Long.parseLong(catId));
        
    	LOGGER.info(" FInish : Save Category Image ");
    	
    	return ResponseEntity.ok(categorieDto);
    }

    //Example To retrieve Image from DB
    @GetMapping(path = { "/img/{id}" }, produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public byte[] getImageCategory(@PathVariable("id") String id) throws IOException {
        return categorieService.findImgCategoryById(Long.parseLong(id));
    }
    
    @PutMapping("update")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categorieDto){
    	
    	CategoryDto updatedCat = categorieService.saveCategory(categorieDto);
    	//how to add a header values
    	HttpHeaders headers = new HttpHeaders();
    	headers.add("username", "Othman");
    	
    	return ResponseEntity.accepted().headers(headers).body(updatedCat);
    }
    
    @DeleteMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<?> deleteCategoryById(@PathVariable(name = "id") Long id){
    	categorieService.deleteCategoryById(id);
    	return ResponseEntity.noContent().header("deletedId", String.valueOf(id)).build();
    }
    
    /*
     * Example with exception
     */
    @GetMapping("/{id}")
    @PostAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable(name = "id") Long id){
    	return ResponseEntity.ok(categorieService.findCategoryById(id));
    }
}
