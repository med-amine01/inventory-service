package com.example.inventoryservice.service;

import com.example.inventoryservice.model.Product;
import java.util.List;

public interface ProductService {

	Product getProductById(String id);

	List<Product> getAllProducts();

}
