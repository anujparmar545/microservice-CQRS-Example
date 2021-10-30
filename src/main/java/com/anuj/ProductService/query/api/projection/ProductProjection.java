package com.anuj.ProductService.query.api.projection;

import com.anuj.ProductService.command.api.data.Product;
import com.anuj.ProductService.command.api.data.ProductRepository;
import com.anuj.ProductService.command.api.model.ProductRestModel;
import com.anuj.ProductService.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery) {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> ProductRestModel
                    .builder()
                    .name(product.getName())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .build())
                .collect(Collectors.toList());
    }
}
