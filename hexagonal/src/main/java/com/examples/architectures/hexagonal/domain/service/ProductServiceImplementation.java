package com.examples.architectures.hexagonal.domain.service;

import com.examples.architectures.hexagonal.domain.model.Product;
import com.examples.architectures.hexagonal.port.ProductRepository;
import com.examples.architectures.hexagonal.port.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The class is an use case implementation of the inbound port.
 */
@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public Product getProductById(Integer productId) {
        return productRepository.getProductById(productId);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    @Override
    public Product removeProduct(Integer productId) {
        return productRepository.removeProduct(productId);
    }
}
