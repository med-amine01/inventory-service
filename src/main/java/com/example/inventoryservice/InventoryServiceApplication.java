package com.example.inventoryservice;

import com.example.inventoryservice.model.Product;
import com.example.inventoryservice.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataInitializer(ProductRepository productRepository) {
		return args -> {
			// Create sample products
			List<Product> sampleProducts = Arrays.asList(
					Product.builder()
						.id(UUID.randomUUID().toString())
						.name("Laptop Dell XPS 13")
						.price(1299.99)
						.quantity(25)
						.build(),
					Product.builder()
						.id(UUID.randomUUID().toString())
						.name("iPhone 15 Pro")
						.price(999.99)
						.quantity(50)
						.build(),
					Product.builder()
						.id(UUID.randomUUID().toString())
						.name("Samsung 4K Smart TV")
						.price(799.99)
						.quantity(15)
						.build(),
					Product.builder()
						.id(UUID.randomUUID().toString())
						.name("Nike Air Max 270")
						.price(129.99)
						.quantity(100)
						.build(),
					Product.builder()
						.id(UUID.randomUUID().toString())
						.name("Adidas Ultraboost 22")
						.price(179.99)
						.quantity(75)
						.build(),
					Product.builder()
						.id(UUID.randomUUID().toString())
						.name("Levi's 501 Original Jeans")
						.price(59.99)
						.quantity(200)
						.build(),
					Product.builder()
						.id(UUID.randomUUID().toString())
						.name("Uniqlo Cotton T-Shirt")
						.price(19.99)
						.quantity(300)
						.build(),
					Product.builder()
						.id(UUID.randomUUID().toString())
						.name("KitchenAid Stand Mixer")
						.price(399.99)
						.quantity(30)
						.build(),
					Product.builder()
						.id(UUID.randomUUID().toString())
						.name("Instant Pot Duo 7-in-1")
						.price(89.99)
						.quantity(45)
						.build(),
					Product.builder()
						.id(UUID.randomUUID().toString())
						.name("Dyson V15 Detect")
						.price(699.99)
						.quantity(20)
						.build());

			// Save all products
			productRepository.saveAll(sampleProducts);
		};
	}

}
