package com.example.inventoryservice.service.impl;

import com.example.inventoryservice.model.Product;
import com.example.inventoryservice.repository.ProductRepository;
import com.example.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public Product getProductById(String id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

}
