package com.example.inventoryservice.controller;

import com.example.inventoryservice.dto.ProductRequest;
import com.example.inventoryservice.model.Product;
import com.example.inventoryservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping("/products")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return ResponseEntity.ok(products);
	}

	@GetMapping("/products/{id}")
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}

	@PostMapping("/products")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductRequest request) {
		Product createdProduct = productService.createProduct(request);
		return ResponseEntity.created(URI.create("/api/products/" + createdProduct.getId())).body(createdProduct);
	}

	@PutMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") String id,
			@Valid @RequestBody ProductRequest request) {
		Product updatedProduct = productService.updateProduct(id, request);
		return ResponseEntity.ok(updatedProduct);
	}

	@DeleteMapping("/products/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/auth")
	public Authentication authentication(Authentication authentication) {
		return authentication;
	}

}