package com.hrd.productservice.controller;

import com.hrd.productservice.model.reponse.ProductResponse;
import com.hrd.productservice.model.request.ProductRequest;
import com.hrd.productservice.service.ProductService;
import com.hrd.productservice.utils.ApiResponse;
import com.hrd.productservice.utils.AuthHelper;
import com.hrd.productservice.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Validated
public class ProductController extends BaseResponse {

    private final ProductService productService;

    @PostMapping
    @Operation(description = "Create a new product")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest productRequest
            ){
        return responseEntity(
                true,
                "Succ",
                HttpStatus.OK,
                productService.createNewProduct(productRequest));
    }
}
