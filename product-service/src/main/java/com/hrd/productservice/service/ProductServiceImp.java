package com.hrd.productservice.service;

import com.hrd.productservice.client.CategoryClient;
import com.hrd.productservice.client.UserClient;
import com.hrd.productservice.exception.NotFoundException;
import com.hrd.productservice.model.enitity.Product;
import com.hrd.productservice.model.enums.ProductProperties;
import com.hrd.productservice.model.reponse.AppUserResponse;
import com.hrd.productservice.model.reponse.CategoryResponse;
import com.hrd.productservice.model.reponse.ProductResponse;
import com.hrd.productservice.model.request.ProductRequest;
import com.hrd.productservice.repository.ProductRepository;
import com.hrd.productservice.utils.ApiResponseWithPagination;
import com.hrd.productservice.utils.AuthHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final UserClient userClient;
    private final CategoryClient categoryClient;


    @Override
    @Transactional
    public ProductResponse createNewProduct(ProductRequest productRequest) {
        UUID userId = UUID.fromString(AuthHelper.getCurrentUserId());
        Product product = productRequest.toEntity();
        AppUserResponse user = userClient.getCurrentUserProfile().getBody().getPayload();
        CategoryResponse category = categoryClient.getCategoryById(UUID.fromString(String.valueOf(productRequest.getCategoryId()))).getBody().getPayload();

        product.setUserId(userId);
        product.setProductId(product.getProductId());
        product.setCategoryId(category.getCategoryId());
        productRepository.save(product);

        return product.toResponse(user,category);
    }

    @Override
    public ApiResponseWithPagination<ProductResponse> getAllProduct(Integer page, Integer size, ProductProperties productProperties, Sort.Direction direction) {
        AppUserResponse user = userClient.getCurrentUserProfile().getBody().getPayload();
        Sort.Direction sortedDirection = direction == null ? Sort.Direction.ASC : direction;

        Sort sortBy = (productProperties == null || productProperties.getProperty() == null || productProperties.getProperty().isEmpty())
                ? Sort.by(sortedDirection, ProductProperties.PRODUCT_ID.getProperty())
                : Sort.by(sortedDirection, productProperties.getProperty());


        Page<Product> pageOfProduct = productRepository
                .findAllByUserId(UUID.fromString(AuthHelper.getCurrentUserId()),PageRequest.of(page - 1, size, sortBy));
        Page<ProductResponse> responsePage = pageOfProduct.map(
                product -> product.toResponse(user, categoryClient.getCategoryById(product.getCategoryId()).getBody().getPayload())
        );

        return ApiResponseWithPagination.itemsAndPaginationResponse(responsePage);
    }

    @Override
    public ProductResponse getProductById(UUID productId) {
        UUID userId = UUID.fromString(AuthHelper.getCurrentUserId());
        AppUserResponse userResponse = userClient.getCurrentUserProfile().getBody().getPayload();
        Product product =  productRepository.findByProductIdAndUserId(productId,userId).orElseThrow( () -> new NotFoundException("Product Id : " + productId + " Not Found!"));
        CategoryResponse categoryResponse = categoryClient.getCategoryById(product.getCategoryId()).getBody().getPayload();
        return product.toResponse(userResponse,categoryResponse);
    }

    @Override
    @Transactional
    public ProductResponse updateProductById(UUID productId, ProductRequest productRequest) {
        UUID userId = UUID.fromString(AuthHelper.getCurrentUserId());
        AppUserResponse userResponse = userClient.getCurrentUserProfile().getBody().getPayload();
        Product product =  productRepository.findByProductIdAndUserId(productId,userId).orElseThrow( () -> new NotFoundException("Product Id : " + productId + " Not Found!"));
        product.updateForm(productRequest);
        CategoryResponse categoryResponse = categoryClient.getCategoryById(product.getCategoryId()).getBody().getPayload();
        productRepository.save(product);
        return product.toResponse(userResponse,categoryResponse);
    }


    @Override
    @Transactional
    public void deleteByCategoryId(UUID categoryId) {
        UUID userId = UUID.fromString(AuthHelper.getCurrentUserId());
        List<Product> products = productRepository.findAllByCategoryIdAndUserId(categoryId, userId).get();
        if (products.isEmpty()) {
            throw new NotFoundException("No products found for this category");
        }
        productRepository.deleteAll(products);
    }

    @Override
    @Transactional
    public void deleteByProductId(UUID productId) {
        UUID userId = UUID.fromString(AuthHelper.getCurrentUserId());
        Product product = productRepository.findByProductIdAndUserId(productId, userId).orElseThrow(() -> new NotFoundException("Product Id : " + productId + " Not Found!"));
        productRepository.delete(product);
    }


}
