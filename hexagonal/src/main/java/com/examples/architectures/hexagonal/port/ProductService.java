package com.examples.architectures.hexagonal.port;

import com.examples.architectures.hexagonal.domain.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The interface is an inbound port provides the flow and the application functionality to the outside
 */
public interface ProductService {

    List<Product> getProducts();

    Product getProductById(Integer productId);

    Product addProduct(Product product);

    Product removeProduct(Integer productId);
}
