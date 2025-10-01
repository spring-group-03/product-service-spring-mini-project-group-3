package com.hrd.productservice.model.enitity;

import com.hrd.productservice.model.reponse.AppUserResponse;
import com.hrd.productservice.model.reponse.CategoryResponse;
import com.hrd.productservice.model.reponse.ProductResponse;
import com.hrd.productservice.model.request.ProductRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false ,scale = 2 )
    private BigDecimal price;

    private Integer quantity;

    @Column(nullable = false)
    private UUID categoryId;

    @Column(nullable = false)
    private UUID userId;

    public ProductResponse toResponse(AppUserResponse userResponse, CategoryResponse categoryResponse){
        return ProductResponse.builder()
                .user(userResponse)
                .category(categoryResponse)
                .productId(this.productId)
                .name(this.name)
                .price(this.price)
                .quantity(this.quantity)
                .build();
    }

    public void updateForm(ProductRequest productRequest){
        this.setName(productRequest.getName());
        this.setQuantity(productRequest.getQuantity());
        this.setPrice(productRequest.getPrice());
        this.setQuantity(productRequest.getQuantity());
        this.setCategoryId(productRequest.getCategoryId());
    }
}
