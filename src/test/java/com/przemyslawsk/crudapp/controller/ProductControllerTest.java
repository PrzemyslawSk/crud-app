package com.przemyslawsk.crudapp.controller;

import com.przemyslawsk.crudapp.dto.ProductDTO;
import com.przemyslawsk.crudapp.exception.NotFoundException;
import com.przemyslawsk.crudapp.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ProductControllerTest {
    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;

    //TODO
    /*@Test
    public void getAllProducts_ShouldReturnListOfProducts() {
        // given
        List<ProductDTO> products = new ArrayList<>();
        products.add(new ProductDTO("Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1)));
        products.add(new ProductDTO("Protein bar", "Nutrition bar that contain a high proportion of protein.", BigDecimal.valueOf(0.6)));

        when(productService.getAllProducts()).thenReturn(products);

        // when
        ResponseEntity<List<ProductDTO>> response = productController.getAllProducts();

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }

    @Test
    public void getProductById_ExistingId_ShouldReturnProduct() {
        // given
        Long productId = 1L;
        ProductDTO product = new ProductDTO("Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));

        when(productService.getProductById(productId)).thenReturn(product);

        // when
        ResponseEntity<ProductDTO> result = productController.getProductById(productId);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Milk", result.getBody().getName());
        assertEquals("White liquid food produced by mammals.", result.getBody().getDescription());
        assertEquals(BigDecimal.valueOf(2.1), result.getBody().getPrice());
    }

    @Test
    public void getProductById_NonExistingId_ShouldReturnNotFound() {
        // given
        Long productId = 100L;

        when(productService.getProductById(productId)).thenThrow(new NotFoundException("Product with id " + productId + " not found."));

        // when
        ResponseEntity<ProductDTO> response = productController.getProductById(productId);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createProduct_ValidProduct_ShouldReturnCreatedProduct() {
        // given
        ProductDTO product = new ProductDTO("Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));
        ProductDTO savedProduct = new ProductDTO("Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));

        when(productService.createProduct(product)).thenReturn(savedProduct);

        // when
        ResponseEntity<ProductDTO> result = productController.createProduct(product);

        // then
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals("Milk", result.getBody().getName());
        assertEquals("White liquid food produced by mammals.", result.getBody().getDescription());
        assertEquals(BigDecimal.valueOf(2.1), result.getBody().getPrice());
    }

    @Test
    public void updateProduct_ExistingIdAndValidProduct_ShouldReturnUpdatedProduct() {
        // given
        Long productId = 1L;
        ProductDTO product = new ProductDTO("Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));
        ProductDTO updatedProduct = new ProductDTO("Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));

        when(productService.updateProduct(productId, product)).thenReturn(updatedProduct);

        // when
        ResponseEntity<ProductDTO> response = productController.updateProduct(productId, product);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Milk", response.getBody().getName());
        assertEquals("White liquid food produced by mammals.", response.getBody().getDescription());
        assertEquals(BigDecimal.valueOf(2.1), response.getBody().getPrice());
    }

    @Test
    public void updateProduct_NonExistingId_ShouldReturnNotFound() {
        // given
        Long productId = 100L;
        ProductDTO productDTO = new ProductDTO("Milk", "White liquid food produced by mammals.", BigDecimal.valueOf(2.1));

        when(productService.updateProduct(productId, productDTO)).thenThrow(new NotFoundException("Product with id " + productId + " not found."));

        // when
        ResponseEntity<ProductDTO> response = productController.updateProduct(productId, productDTO);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void deleteProduct_ExistingId_ShouldReturnNoContent() {
        // given
        Long productId = 1L;

        // when
        ResponseEntity<Void> response = productController.deleteProduct(productId);

        // then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deleteProduct_NonExistingId_ShouldReturnNotFound() {
        // given
        Long productId = 100L;

        doThrow(new NotFoundException("Product with id " + productId + " not found."))
                .when(productService).deleteProduct(productId);

        // when
        ResponseEntity<Void> response = productController.deleteProduct(productId);

        // then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }*/
}
