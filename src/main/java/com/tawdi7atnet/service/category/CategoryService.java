package com.tawdi7atnet.service.category;

import com.tawdi7atnet.dto.CategoryDto;
import com.tawdi7atnet.entities.Category;

import java.util.List;

public interface CategoryService {
	
    List<CategoryDto> findAllCategories();
    
    CategoryDto saveCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto);
    
    void deleteCategoryById(Long id);
    
    CategoryDto findCategoryById(Long id);

    CategoryDto addImgToCategory(byte[] img, Long id);

    byte[] findImgCategoryById(Long id);
    
    Category getCategoryById(Long id);

}
