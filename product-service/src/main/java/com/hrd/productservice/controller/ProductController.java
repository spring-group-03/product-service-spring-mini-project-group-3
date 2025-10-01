package com.hrd.productservice.controller;

import com.hrd.productservice.model.enums.ProductProperties;
import com.hrd.productservice.model.reponse.ProductResponse;
import com.hrd.productservice.model.request.ProductRequest;
import com.hrd.productservice.service.ProductService;
import com.hrd.productservice.utils.ApiResponse;
import com.hrd.productservice.utils.ApiResponseWithPagination;
import com.hrd.productservice.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Validated
public class ProductController extends BaseResponse {

    private final ProductService productService;

    @GetMapping
    @Operation(summary = "Get All Products (paginated)")
    public ResponseEntity<ApiResponse<ApiResponseWithPagination<ProductResponse>>> getProductsPaginated(
            @RequestParam(defaultValue = "1") @Positive Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam ProductProperties productProperties,
            @RequestParam Sort.Direction direction
    ){
        return responseEntity(
                true,
                "Products retrieved successfully",
                HttpStatus.OK,
                productService.getAllProduct(page,size,productProperties,direction));
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get Product By Id")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(
            @PathVariable("productId") UUID productId
            ){
        return responseEntity(
                true,
                "Get product successfully",
                HttpStatus.OK,
                productService.getProductById(productId));
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(
            @Valid @RequestBody ProductRequest productRequest
            ){
        return responseEntity(
                true,
                "Create product successfully",
                HttpStatus.OK,
                productService.createNewProduct(productRequest));
    }

    @PutMapping("/{productId}")
    @Operation(summary = "Update Product")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProductById(
            @PathVariable("productId") UUID productId,
            @Valid @RequestBody ProductRequest productRequest
    ){
        return responseEntity(
                true,
                "Updated product successfully",
                HttpStatus.OK,
                productService.updateProductById(productId,productRequest));
    }

    @DeleteMapping("/{productId}")
    @Operation(summary = "Delete Product")
    public ResponseEntity<ApiResponse<ProductResponse>> deleteProductById(
            @PathVariable("productId") UUID productId
    ){
        productService.deleteByProductId(productId);
        return responseEntity(
                true,
                "Product deleted successfully",
                HttpStatus.OK,
                null);
    }

    @DeleteMapping("/category/{categoryId}")
    @Operation(summary = "Delete Product By CategoryId ")
    public ResponseEntity<ApiResponse<ProductResponse>> deleteProductByCategoryId(
            @PathVariable("categoryId") UUID categoryId
    ){
        productService.deleteByCategoryId(categoryId);
        return responseEntity(
                true,
                "Product deleted By CategoryId successfully",
                HttpStatus.OK,
                null);
    }
}
