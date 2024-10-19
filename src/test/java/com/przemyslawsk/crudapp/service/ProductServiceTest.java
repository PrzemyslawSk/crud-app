package com.przemyslawsk.crudapp.service;

import com.przemyslawsk.crudapp.dto.ProductDTO;
import com.przemyslawsk.crudapp.mapper.ProductMapper;
import com.przemyslawsk.crudapp.model.Product;
import com.przemyslawsk.crudapp.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 public class ProductServiceTest {
    @Mock
    private ProductRepository repository;
    @Mock
    private ProductMapper mapper;
    @InjectMocks
    private ProductServiceImpl service;

    @Test
    public void getAllProducts_shouldReturnListOfProducts() {
        // given
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1), LocalDateTime.parse("2024-09-09T16:57:07.897311")));
        products.add(new Product(2L, "Protein bar", "Nutrition bar that contain a high proportion of protein.", BigDecimal.valueOf(0.6), LocalDateTime.parse("2024-09-09T16:57:07.897311")));

        List<ProductDTO> mappedProducts = new ArrayList<>();
        mappedProducts.add(new ProductDTO(1L, "Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1)));
        mappedProducts.add(new ProductDTO(2L, "Protein bar", "Nutrition bar that contain a high proportion of protein.", BigDecimal.valueOf(0.6)));

        when(repository.findAll()).thenReturn(products);
        when(mapper.map(products)).thenReturn(mappedProducts);

        // when
        List<ProductDTO> result = service.getAllProducts();

        // then
        assertEquals(mappedProducts, result);
    }

    @Test
    public void getProductById_existingId_shouldReturnProduct() {
        // given
        Long productId = 1L;
        Product product = new Product(1L, "ProductNr1", "Some product", BigDecimal.valueOf(5.5), LocalDateTime.parse("2024-09-09T16:57:07.897311"));

        ProductDTO mappedProduct = new ProductDTO(1L, "ProductNr1", "Some product", BigDecimal.valueOf(5.5));

        when(repository.findById(productId)).thenReturn(Optional.of(product));
        when(mapper.map(product)).thenReturn(mappedProduct);

        // when
        ProductDTO result = service.getProductById(productId);

        // then
        assertNotNull(result);
        assertEquals("ProductNr1", result.getName());
        assertEquals("Some product", result.getDescription());
        assertEquals(BigDecimal.valueOf(5.5), result.getPrice());
    }

    @Test()
    public void getProductById_NonExistingId_ShouldThrowNotFoundException() {
        //TODO
        /*// given
        Long productId = 100L;
        when(repository.findById(productId)).thenReturn(Optional.empty());

        // when then
        assertThrows(NotFoundException.class, () -> service.getProductById(productId));*/
    }

    @Test
    public void createProduct_ShouldReturnSavedProduct() {
        // given
        Product product = new Product(1L, "Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1), LocalDateTime.parse("2024-09-09T16:57:07.897311"));

        ProductDTO mappedProduct = new ProductDTO(1L, "Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));

        when(repository.save(product)).thenReturn(product);
        when(mapper.map(product)).thenReturn(mappedProduct);

        // when
        ProductDTO result = service.createProduct(product);

        // then
        assertNotNull(result);
        assertEquals("Milk", result.getName());
        assertEquals("White liquid food produced by mammals.", result.getDescription());
        assertEquals(BigDecimal.valueOf(2.1), result.getPrice());
    }

    @Test
    public void updateProduct_ExistingId_ShouldReturnUpdatedProduct() {
        //TODO
        /*// given
        Long productId = 1L;
        ProductDTO productDTO = new ProductDTO("Soy milk", "Plant-based drink produced by soaking and grinding soybeans.", BigDecimal.valueOf(4.5));
        Product existingProduct = new Product(productId, "Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));
        Product updatedProduct = new Product(productId, "Soy milk", "Plant-based drink produced by soaking and grinding soybeans.", BigDecimal.valueOf(4.5));

        when(repository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(repository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);

        // when
        ProductDTO result = service.updateProduct(productId, productDTO);

        // then
        assertNotNull(result);
        assertEquals("Soy milk", result.getName());
        assertEquals("Plant-based drink produced by soaking and grinding soybeans.", result.getDescription());
        assertEquals(BigDecimal.valueOf(4.5), result.getPrice());*/
    }

    @Test
    public void updateProduct_NonExistingId_ShouldThrowNotFoundException() {
        //TODO
        /*// given
        Long productId = 100L;
        ProductDTO productDTO = new ProductDTO("Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));

        when(repository.findById(productId)).thenReturn(Optional.empty());

        // when then
        assertThrows(NotFoundException.class, () -> service.updateProduct(productId, productDTO));*/
    }

    @Test
    public void deleteProduct_ExistingId_ShouldDeleteProduct() {
        // given
        Long productId = 1L;
        Product product = new Product(productId, "Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1), LocalDateTime.parse("2024-09-09T16:57:07.897311"));

        when(repository.findById(productId)).thenReturn(Optional.of(product));

        // when
        service.deleteProduct(productId);

        // then
        verify(repository, times(1)).delete(product);
    }

    @Test
    public void deleteProduct_NonExistingId_ShouldThrowNotFoundException() {
        //TODO
        /*// given
        Long productId = 100L;

        when(repository.findById(productId)).thenReturn(Optional.empty());

        // when then
        assertThrows(NotFoundException.class, () -> service.deleteProduct(productId));*/
    }
}
