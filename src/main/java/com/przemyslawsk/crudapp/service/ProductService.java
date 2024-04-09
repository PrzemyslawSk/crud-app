package com.przemyslawsk.crudapp.service;

import com.przemyslawsk.crudapp.dto.ProductDTO;
import com.przemyslawsk.crudapp.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(Product product);
    ProductDTO updateProduct(Long id, Product product);
    void deleteProduct(Long id);
}
