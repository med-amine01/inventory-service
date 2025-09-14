package com.example.inventoryservice.service.impl;

import com.example.inventoryservice.dto.ProductRequest;
import com.example.inventoryservice.exception.DataNotFoundException;
import com.example.inventoryservice.model.Product;
import com.example.inventoryservice.repository.ProductRepository;
import com.example.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public Product getProductById(String id) {
		return productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Product", id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product createProduct(ProductRequest request) {
		Product product = Product.builder()
			.id(UUID.randomUUID().toString())
			.name(request.name())
			.price(request.price())
			.quantity(request.quantity())
			.build();

		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(String id, ProductRequest request) {
		Product existingProduct = productRepository.findById(id)
			.orElseThrow(() -> new DataNotFoundException("Product", id));

		Product updatedProduct = existingProduct.toBuilder()
			.name(request.name())
			.price(request.price())
			.quantity(request.quantity())
			.build();

		return productRepository.save(updatedProduct);
	}

	@Override
	public void deleteProduct(String id) {
		productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Product", id));
		productRepository.deleteById(id);
	}

}