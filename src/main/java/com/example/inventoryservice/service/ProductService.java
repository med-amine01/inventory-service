package com.example.inventoryservice.service;

import com.example.inventoryservice.dto.ProductRequest;
import com.example.inventoryservice.model.Product;

import java.util.List;

public interface ProductService {

	Product getProductById(String id);

	List<Product> getAllProducts();

	Product createProduct(ProductRequest request);

	Product updateProduct(String id, ProductRequest request);

	void deleteProduct(String id);

}