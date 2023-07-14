package com.przemyslawsk.crudapp.dto;

import com.przemyslawsk.crudapp.model.Product;

public class ProductMapper {
    public static ProductDTO mapToProductDTO(Product product) {
        return ProductDTO.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static Product mapToProduct(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
    }
}
