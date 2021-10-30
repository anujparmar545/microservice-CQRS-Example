package com.anuj.ProductService.query.api.controller;

import com.anuj.ProductService.command.api.model.ProductRestModel;
import com.anuj.ProductService.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getAllProducts() {
        GetProductsQuery getProductsQuery = new GetProductsQuery();

        List<ProductRestModel> productRestModelList =
                queryGateway.query(getProductsQuery, ResponseTypes.multipleInstancesOf(
                        ProductRestModel.class)).join();
        return productRestModelList;
    }
}
