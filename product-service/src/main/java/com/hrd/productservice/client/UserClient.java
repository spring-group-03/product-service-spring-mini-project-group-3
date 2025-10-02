package com.hrd.productservice.client;

import com.hrd.productservice.config.OpenFeignConfig;
import com.hrd.productservice.model.reponse.AppUserResponse;
import com.hrd.productservice.utils.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "auth-service",
        path = "api/v1/users",
        configuration = OpenFeignConfig.class
)
public interface UserClient {

    @GetMapping
    ResponseEntity<ApiResponse<AppUserResponse>> getCurrentUserProfile();
}
