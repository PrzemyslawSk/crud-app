package com.przemyslawsk.crudapp.mapper;

import com.przemyslawsk.crudapp.dto.ProductDTO;
import com.przemyslawsk.crudapp.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductDTO map(Product product);
    List<ProductDTO> map(List<Product> products);
    Product map(ProductDTO productDTO);
}
