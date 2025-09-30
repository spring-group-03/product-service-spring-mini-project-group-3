package com.hrd.productservice.config;

import com.hrd.productservice.utils.AuthHelper;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class OpenFeignConfig implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", "Bearer " + AuthHelper.getCurrentTokenUser());
    }
}
