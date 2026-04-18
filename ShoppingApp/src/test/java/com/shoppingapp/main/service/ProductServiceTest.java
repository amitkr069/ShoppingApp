package com.shoppingapp.main.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shoppingapp.main.dto.ProductRequestDTO;
import com.shoppingapp.main.dto.ProductResponseDTO;
import com.shoppingapp.main.entity.Product;
import com.shoppingapp.main.exception.ResourceNotFoundException;
import com.shoppingapp.main.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreateProduct_Success() {
        ProductRequestDTO dto = new ProductRequestDTO();
        dto.setProductName("Laptop");
        dto.setDescription("High-end laptop");
        dto.setCategory("Electronics");
        dto.setPrice(999.99);

        Product saved = new Product(1L, "Laptop", "High-end laptop", "Electronics", 999.99);

        when(productRepository.save(any(Product.class))).thenReturn(saved);

        ProductResponseDTO result = productService.createProduct(dto);

        assertNotNull(result);
        assertEquals("Laptop", result.getProductName());
        assertEquals(999.99, result.getPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testGetAllProducts() {
        Product p1 = new Product(1L, "Laptop", "Desc1", "Electronics", 999.99);
        Product p2 = new Product(2L, "Phone", "Desc2", "Electronics", 499.99);

        when(productRepository.findAll()).thenReturn(List.of(p1, p2));

        List<ProductResponseDTO> result = productService.getAllProducts();

        assertEquals(2, result.size());
        assertEquals("Laptop", result.get(0).getProductName());
        assertEquals("Phone", result.get(1).getProductName());
    }

    @Test
    void testGetProductById_Found() {
        Product product = new Product(1L, "Laptop", "Desc", "Electronics", 999.99);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductResponseDTO result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getProductId());
        assertEquals("Laptop", result.getProductName());
    }

    @Test
    void testGetProductById_NotFound_ThrowsException() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(99L));
    }

    @Test
    void testDeleteProduct_Success() {
        Product product = new Product(1L, "Laptop", "Desc", "Electronics", 999.99);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).delete(product);
    }
}
