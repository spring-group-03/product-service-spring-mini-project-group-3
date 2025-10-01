package com.hrd.productservice.model.reponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ProductResponse{
    private UUID productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private CategoryResponse category;
    private AppUserResponse user;
}


