package com.hrd.productservice.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  CategoryResponse {
    private UUID categoryId;
    private String name;
    private String description;


}
