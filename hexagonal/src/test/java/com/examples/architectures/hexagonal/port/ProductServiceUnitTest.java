package com.examples.architectures.hexagonal.port;

import com.examples.architectures.hexagonal.domain.model.Product;
import com.examples.architectures.hexagonal.domain.service.ProductServiceImplementation;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.context.junit5.SpringRunner;

import java.util.Arrays;
import java.util.List;

//import static org.junit.jupiter.api.Assertions.assertTh;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
public class ProductServiceUnitTest {

    @TestConfiguration
    static class ProductServiceTestConfig {
        @Bean
        public ProductService productService() {
            return new ProductServiceImplementation();
        }
    }

    @MockitoBean
    private ProductRepository productRepository;


    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    //@Inject ProductService productService;
    private ProductService productService;

    @Before
    public void setUp() {
        Product mobilePhone = new Product(1, "Mobile Phone", "Samsung Galaxy S9");
        Product book = new Product(2, "Book", "Kite Runner");
        Product laptop = new Product(3, "Laptop", "Macbook Pro");
        Product electronics = new Product(4, "Electronics", "Bluetooth Speaker");

        List<Product> products = Arrays.asList(mobilePhone, book, laptop);

        when(productRepository.getProducts()).thenReturn(products);
        when(productRepository.getProductById(mobilePhone.getProductId())).thenReturn(mobilePhone);
        when(productRepository.getProductById(5)).thenReturn(null);
        when(productRepository.addProduct(electronics)).thenReturn(electronics);
        when(productRepository.removeProduct(laptop.getProductId())).thenReturn(laptop);
    }

    @Test
    public void whenValidProductId_thenProductShouldBeFound() {
        Integer id = 1;
        Product product = productService.getProductById(1);

        assertEquals(id,product.getProductId());
        verifyGetByProductIdIsCalledOnce();
    }

    @Test
    public void whenInValidProductId_thenProductShouldNotBeFound() {
        Product product = productService.getProductById(5);

        assertNull(product);
        verifyGetByProductIdIsCalledOnce();
    }

    @Test
    public void givenThreeProducts_whenGetAllProducts_thenThreeProductsReturned() {
        Product mobilePhone = new Product(1, "Mobile Phone", "Samsung Galaxy S9");
        Product book = new Product(2, "Book", "Kite Runner");
        Product laptop = new Product(3, "Laptop", "Macbook Pro");

        List<Product> allProducts = productService.getProducts();

        assertEquals(3,allProducts.size());
        assertEquals(mobilePhone,allProducts.getFirst());
        assertEquals(book,allProducts.get(1));
        assertEquals(laptop,allProducts.get(2));
        verifyGetProductsIsCalledOnce();
    }

    @Test
    public void whenAddProduct_thenProductTypeIsMatched() {
        Product electronics = new Product(4, "Electronics", "Bluetooth Speaker");

        assertEquals(electronics, productService.addProduct(electronics));
    }

    @Test
    public void whenRemoveProductById_thenTwoProductReturned() {
        Product laptop = new Product(3, "Laptop", "Macbook Pro");

        assertNull(productService.removeProduct(3));
    }

    private void verifyGetByProductIdIsCalledOnce() {
        verify(productRepository, VerificationModeFactory.times(1)).getProductById(anyInt());
        reset(productRepository);
    }

    private void verifyGetProductsIsCalledOnce() {
        verify(productRepository, VerificationModeFactory.times(1)).getProducts();
        reset(productRepository);
    }
}
