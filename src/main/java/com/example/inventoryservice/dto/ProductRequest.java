package com.example.inventoryservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(@NotBlank(message = "Product name cannot be blank") String name,

		@NotNull(message = "Product price cannot be null") @Min(value = 0,
				message = "Product price cannot be negative") BigDecimal price,

		@NotNull(message = "Product quantity cannot be null") @Min(value = 0,
				message = "Product quantity cannot be negative") Integer quantity) {
}
