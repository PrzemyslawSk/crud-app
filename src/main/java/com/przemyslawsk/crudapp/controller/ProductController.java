package com.przemyslawsk.crudapp.controller;

import com.przemyslawsk.crudapp.dto.ProductDTO;
import com.przemyslawsk.crudapp.model.Product;
import com.przemyslawsk.crudapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{productId}")
    public ProductDTO getProductById(@PathVariable Long productId) {
        return service.getProductById(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @PutMapping("/{productId}")
    public ProductDTO updateProduct(@PathVariable Long productId,
                                    @RequestBody Product product) {
        return service.updateProduct(productId, product);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteProduct(@PathVariable("id") Long productId) {
        service.deleteProduct(productId);
        return new HashMap<>() {{
            put("id", productId);
        }};
    }
}
