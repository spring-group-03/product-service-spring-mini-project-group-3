package com.hrd.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        name = "category-service",
        url = "http://localhost:8080/api/v1",
        path = "/category"
)
public interface CategoryClient {
}
