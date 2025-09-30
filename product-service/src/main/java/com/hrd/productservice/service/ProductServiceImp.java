package com.hrd.productservice.service;

import com.hrd.productservice.client.UserClient;
import com.hrd.productservice.model.enitity.Product;
import com.hrd.productservice.model.reponse.AppUserResponse;
import com.hrd.productservice.model.reponse.ProductResponse;
import com.hrd.productservice.model.request.ProductRequest;
import com.hrd.productservice.repository.ProductRepository;
import com.hrd.productservice.utils.AuthHelper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final UserClient userClient;

    @Override
    @Transactional
    public ProductResponse createNewProduct(ProductRequest productRequest) {
        Product product = productRequest.toEntity();
        AppUserResponse user = userClient.getCurrentUserProfile().getBody().getPayload();

        product.setUserId(AuthHelper.getCurrentUserId());
        product.setProductId(product.getProductId());
        productRepository.save(product);

        // CategoryResponse Need This

        return product.toResponse(user,null);
    }
}
