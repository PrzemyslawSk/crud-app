package com.przemyslawsk.crudapp.service;

import com.przemyslawsk.crudapp.dto.ProductDTO;
import com.przemyslawsk.crudapp.exception.NotFoundException;
import com.przemyslawsk.crudapp.model.Product;
import com.przemyslawsk.crudapp.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void getAllProducts_ShouldReturnListOfProducts() {
        // given
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1)));
        products.add(new Product(2L, "Protein bar", "Nutrition bar that contain a high proportion of protein.", BigDecimal.valueOf(0.6)));
        when(productRepository.findAll()).thenReturn(products);

        // when
        List<ProductDTO> result = productService.getAllProducts();

        // then
        assertEquals(2, result.size());
    }

    @Test
    public void getProductById_ExistingId_ShouldReturnProduct() {
        // given
        Long productId = 1L;
        Product product = new Product(1L, "ProductNr1", "Some product", BigDecimal.valueOf(5.5));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // when
        ProductDTO result = productService.getProductById(productId);

        // then
        assertNotNull(result);
        assertEquals("ProductNr1", result.getName());
        assertEquals("Some product", result.getDescription());
        assertEquals(BigDecimal.valueOf(5.5), result.getPrice());
    }

    @Test()
    public void getProductById_NonExistingId_ShouldThrowNotFoundException() {
        // given
        Long productId = 100L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // when then
        assertThrows(NotFoundException.class, () -> productService.getProductById(productId));
    }

    @Test
    public void createProduct_ShouldReturnSavedProduct() {
        // given
        ProductDTO productDTO = ProductDTO.builder()
                .name("Milk")
                .description("White liquid food produced by mammals.")
                .price(BigDecimal.valueOf(2.1))
                .build();
        Product product = Product.builder()
                .id(1L)
                .name("Milk")
                .description("White liquid food produced by mammals.")
                .price(BigDecimal.valueOf(2.1))
                .build();

        when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        // when
        ProductDTO result = productService.createProduct(productDTO);

        // then
        assertNotNull(result);
        assertEquals("Milk", result.getName());
        assertEquals("White liquid food produced by mammals.", result.getDescription());
        assertEquals(BigDecimal.valueOf(2.1), result.getPrice());
    }

    @Test
    public void updateProduct_ExistingId_ShouldReturnUpdatedProduct() {
        // given
        Long productId = 1L;
        ProductDTO productDTO = new ProductDTO("Soy milk", "Plant-based drink produced by soaking and grinding soybeans.", BigDecimal.valueOf(4.5));
        Product existingProduct = new Product(productId, "Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));
        Product updatedProduct = new Product(productId, "Soy milk", "Plant-based drink produced by soaking and grinding soybeans.", BigDecimal.valueOf(4.5));

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);

        // when
        ProductDTO result = productService.updateProduct(productId, productDTO);

        // then
        assertNotNull(result);
        assertEquals("Soy milk", result.getName());
        assertEquals("Plant-based drink produced by soaking and grinding soybeans.", result.getDescription());
        assertEquals(BigDecimal.valueOf(4.5), result.getPrice());
    }

    @Test
    public void updateProduct_NonExistingId_ShouldThrowNotFoundException() {
        // given
        Long productId = 100L;
        ProductDTO productDTO = new ProductDTO("Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // when then
        assertThrows(NotFoundException.class, () -> productService.updateProduct(productId, productDTO));
    }

    @Test
    public void deleteProduct_ExistingId_ShouldDeleteProduct() {
        // given
        Long productId = 1L;
        Product existingProduct = new Product(productId, "Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));

        // when
        productService.deleteProduct(productId);

        // then
        verify(productRepository, times(1)).delete(existingProduct);
    }

    @Test
    public void deleteProduct_NonExistingId_ShouldThrowNotFoundException() {
        // given
        Long productId = 100L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // when then
        assertThrows(NotFoundException.class, () -> productService.deleteProduct(productId));
    }
}
