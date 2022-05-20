package com.tawdi7atnet.service.category;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tawdi7atnet.dao.CategorieRepository;
import com.tawdi7atnet.dto.CategoryDto;
import com.tawdi7atnet.entities.Category;
import com.tawdi7atnet.exception.EntityNotFoundException;
import com.tawdi7atnet.util.ImagesClassUtil;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private CategorieRepository categorieRepository;
	private ModelMapper modelMapper;
	public static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	public CategoryServiceImpl(CategorieRepository categorieRepository, ModelMapper modelMapper) {
		this.categorieRepository = categorieRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<CategoryDto> findAllCategories() {

		return categorieRepository.findAll().stream().map(cat -> modelMapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDto saveCategory(CategoryDto categoryDto) {

		Category savedCat = modelMapper.map(categoryDto, Category.class);
		return modelMapper.map(categorieRepository.save(savedCat), CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) {

		Category updatedCat = modelMapper.map(categoryDto, Category.class);
		return modelMapper.map(categorieRepository.save(updatedCat), CategoryDto.class);
	}

	@Override
	public void deleteCategoryById(Long id) {
		categorieRepository.deleteById(id);
	}

	@Override
	public CategoryDto findCategoryById(Long id) {

		Category category = getCategoryById(id);
		
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto addImgToCategory(byte[] img, Long id) {
		
		LOGGER.info("{} : {}","Length of image File" , img.length);

		Category category = getCategoryById(id);
		
		category.setImg(ImagesClassUtil.compressZLib(img));
		return modelMapper.map(categorieRepository.save(category), CategoryDto.class);
	}

	@Override
	public byte[] findImgCategoryById(Long id) {
		
		Category category = getCategoryById(id);
		
		if (category.getImg() != null) {
			return ImagesClassUtil.decompressZLib(category.getImg());
		} else {
			return new byte[0];
		}
	}

	@Override
	public Category getCategoryById(Long id) {
		
		LOGGER.info("find CategoryById"); 
		
		return categorieRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Category id '" + id + " Not Found"));
	}

}
