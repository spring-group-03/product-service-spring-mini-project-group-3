package com.hrd.productservice.model.request;

import com.hrd.productservice.model.enitity.Product;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequest {

    @NotBlank(message = "Product name Can't Be Empty")
    @Size(min = 3, message = "Product name a least 3 up characters")
    @Size(max = 255, message = "Product name max only 255 characters")
    private String name;

    @Min(value = 1, message = "Price must be at least 1")
    @Max(value = 1000000, message = "Max Price 1 000 000")
    private BigDecimal price;

    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 1000000, message = "Max quantity 1 000 000")
    private Integer quantity;

    @Positive(message = "Positive Number Only")
    private Long categoryId;

    public Product toEntity(){
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .quantity(this.quantity)
                .categoryId(this.categoryId)
                .build();
    }
}
