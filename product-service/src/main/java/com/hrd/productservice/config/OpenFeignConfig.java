package com.hrd.productservice.config;

import com.hrd.productservice.exception.NotFoundException;
import com.hrd.productservice.utils.AuthHelper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;


@Component
public class OpenFeignConfig implements RequestInterceptor, ErrorDecoder {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", "Bearer " + AuthHelper.getCurrentTokenUser());
    }

    @Override
    public Exception decode(String s, Response response) {
        return switch (response.status()) {
            case 404 -> new NotFoundException("Category not found");
            case 500 -> new RuntimeException("Category Server error");
            default -> new Exception("Generic error: " + response.status());
        };
    }
}
