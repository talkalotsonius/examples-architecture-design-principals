package com.examples.architectures.hexagonal.port;

import com.examples.architectures.hexagonal.domain.model.Product;

import java.util.List;

/**
 * The repository interface is an outbound port that enables communication from the core application to a database
 */
public interface ProductRepository {

    List<Product> getProducts();

    Product getProductById(Integer productId);

    Product addProduct(Product product);

    Product removeProduct(Integer productId);
}
