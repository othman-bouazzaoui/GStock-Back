package com.tawdi7atnet.service.product;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tawdi7atnet.dao.CategorieRepository;
import com.tawdi7atnet.dao.ProductRepository;
import com.tawdi7atnet.dto.product.ProductRequestDto;
import com.tawdi7atnet.dto.product.ProductResponseDto;
import com.tawdi7atnet.entities.Category;
import com.tawdi7atnet.entities.Product;
import com.tawdi7atnet.exception.EntityAlreadyExistException;
import com.tawdi7atnet.exception.EntityNotFoundException;
import com.tawdi7atnet.util.ImagesClassUtil;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private ModelMapper modelMapper;
	private CategorieRepository categorieRepository;

	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper,
			CategorieRepository categorieRepository) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
		this.categorieRepository = categorieRepository;
	}

	@Override
	public List<ProductResponseDto> findAllProducts() {
		return productRepository.findAll().stream().map(p -> modelMapper.map(p, ProductResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ProductResponseDto saveProduct(ProductRequestDto p) {
		boolean productExist = productRepository.findById(p.getId()).isPresent();

		Long idCategory = p.getCategory() != null ? p.getCategory() : 1L;
		p.setCategory(idCategory);

		Category category = categorieRepository.findById(p.getCategory())
				.orElseThrow(() -> new EntityNotFoundException("Category Not Existe " + p.getCategory()));

		if (!productExist) {
			Product product = modelMapper.map(p, Product.class);
			product.setCategory(category);
			return modelMapper.map(productRepository.save(product), ProductResponseDto.class);
		} else {
			throw new EntityAlreadyExistException(" Entity Already Exist Exception ");
		}

	}

	@Override
	public ProductResponseDto updateProduct(ProductRequestDto p) {
		Product product = productRepository.findById(p.getId())
				.orElseThrow(() -> new EntityNotFoundException("Product Not Existe"));

		Long idCategory = p.getCategory() != null ? p.getCategory() : 1L;
		p.setCategory(idCategory);

		Category category = categorieRepository.findById(p.getCategory())
				.orElseThrow(() -> new EntityNotFoundException("Category Not Existe " + p.getCategory()));

		product.setCategory(category);

		return modelMapper.map(productRepository.save(product), ProductResponseDto.class);
	}

	@Override
	@Transactional
	public void deleteProductById(Long id) {
		productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product Not Found"));

		productRepository.deleteById(id);
	}

	/*
	 * this methode store the image of product
	 */
	@Override
	public ProductRequestDto addImgToProduct(byte[] img, Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product Not Found"));
		product.setImgProduct(ImagesClassUtil.compressZLib(img));

		return modelMapper.map(productRepository.save(product), ProductRequestDto.class);
	}

	/*
	 * this methode give you the image the product ID
	 */
	@Override
	public byte[] findImgProductById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product Not Found"));
		if (product.getImgProduct() != null)
			return ImagesClassUtil.decompressZLib(product.getImgProduct());
		else
			return new byte[0];
	}

	@Override
	public Product getProductById(Long productId) {
		return productRepository.findById(productId)
				.orElseThrow(() -> new EntityNotFoundException("This Product Id '" + productId + "' Not Existe "));
	}

	@Override
	public ProductResponseDto findProductById(Long productId) {
		
		Product product = getProductById(productId);
		
		ProductResponseDto productResponse = modelMapper.map(product, ProductResponseDto.class);
		
		return productResponse;
	}
}
