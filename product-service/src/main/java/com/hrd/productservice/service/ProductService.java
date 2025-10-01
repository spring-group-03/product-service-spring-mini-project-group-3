package com.hrd.productservice.service;

import com.hrd.productservice.model.enums.ProductProperties;
import com.hrd.productservice.model.reponse.ProductResponse;
import com.hrd.productservice.model.request.ProductRequest;
import com.hrd.productservice.utils.ApiResponseWithPagination;
import org.springframework.data.domain.Sort;

import java.util.UUID;

public interface ProductService {

    ProductResponse createNewProduct(ProductRequest productRequest);
    ApiResponseWithPagination<ProductResponse> getAllProduct(Integer page, Integer size, ProductProperties productProperties, Sort.Direction direction);
    ProductResponse getProductById(UUID productId);
    ProductResponse updateProductById(UUID productId ,ProductRequest productRequest);
    void deleteByCategoryId(UUID categoryId);
    void deleteByProductId(UUID productId);
}
