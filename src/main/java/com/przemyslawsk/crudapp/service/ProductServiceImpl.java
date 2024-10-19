package com.przemyslawsk.crudapp.service;

import com.przemyslawsk.crudapp.dto.ProductDTO;
import com.przemyslawsk.crudapp.mapper.ProductMapper;
import com.przemyslawsk.crudapp.model.Product;
import com.przemyslawsk.crudapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.przemyslawsk.crudapp.error.Error.INVALID_REQUEST;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = repository.findAll();
        return mapper.map(products);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(INVALID_REQUEST::getError);

        return mapper.map(product);
    }

    @Override
    public ProductDTO createProduct(Product product) {
        product.setDateAdded(LocalDateTime.now());
        return mapper.map(repository.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long id, Product product) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(INVALID_REQUEST::getError);

        existingProduct.setName(product.getName())
                .setDescription(product.getDescription())
                .setPrice(product.getPrice());

        return mapper.map(repository.save(existingProduct));
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = repository.findById(id)
                        .orElseThrow(INVALID_REQUEST::getError);

        repository.delete(product);
    }

}
