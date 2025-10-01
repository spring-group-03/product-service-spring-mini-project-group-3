package com.hrd.productservice.client;

import com.hrd.productservice.model.reponse.CategoryResponse;
import com.hrd.productservice.utils.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(
        name = "category-service",
        url = "http://localhost:8083/api/v1",
        path = "/categories"
)
public interface CategoryClient {

    @GetMapping("/{category_id}")
    ResponseEntity<ApiResponse<CategoryResponse>> getCategoryById (@PathVariable("category_id") UUID id);
}
