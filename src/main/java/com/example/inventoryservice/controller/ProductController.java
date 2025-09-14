package com.example.inventoryservice.controller;

import com.example.inventoryservice.model.Product;
import com.example.inventoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	// Get all products
	@GetMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	// Get product by ID
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
		Product product = productService.getProductById(id);
		return ResponseEntity.ok(product);
	}

	@GetMapping("/auth")
	public Authentication authentication(Authentication authentication) {
		return authentication;
	}

}
