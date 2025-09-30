package com.hrd.productservice.service;

import com.hrd.productservice.model.reponse.ProductResponse;
import com.hrd.productservice.model.request.ProductRequest;

public interface ProductService {

    ProductResponse createNewProduct(ProductRequest productRequest);

}
