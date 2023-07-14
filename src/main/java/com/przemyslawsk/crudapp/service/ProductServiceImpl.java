package com.przemyslawsk.crudapp.service;

import com.przemyslawsk.crudapp.dto.ProductDTO;
import com.przemyslawsk.crudapp.dto.ProductMapper;
import com.przemyslawsk.crudapp.exception.NotFoundException;
import com.przemyslawsk.crudapp.model.Product;
import com.przemyslawsk.crudapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(ProductMapper::mapToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()) {
            ProductDTO productDTO = ProductMapper
                    .mapToProductDTO(product.get());
            return productDTO;
        } else {
            throw new NotFoundException("Product with id " + id + " not found.");
        }
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.mapToProduct(productDTO);
        Product savedProduct = productRepository.save(product);
        ProductDTO savedProductDTO = ProductMapper.mapToProductDTO(savedProduct);

        return savedProductDTO;
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + " not found."));

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());
        Product updatedProduct = productRepository.save(existingProduct);

        return ProductMapper.mapToProductDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException("Product with id " + id + " not found."));

        productRepository.delete(product);
    }

}
