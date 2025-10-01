package com.hrd.productservice.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum ProductProperties {
    PRODUCT_ID("productId"),
    NAME("name"),
    PRICE("price"),
    QUANTITY("quantity");

    private final String property;
}
