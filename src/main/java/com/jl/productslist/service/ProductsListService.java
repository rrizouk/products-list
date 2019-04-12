package com.jl.productslist.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.jl.productslist.domain.LabelType;
import com.jl.productslist.domain.Product;
import com.jl.productslist.gateway.ProductsListGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsListService {

    private final ProductsListGateway productsListGateway;

    @Autowired
    public ProductsListService(ProductsListGateway productsListGateway) {
        this.productsListGateway = productsListGateway;
    }

    public List<Product> getProductsListing(LabelType labelType) {
        JsonNode result = productsListGateway.getProducts();
        List<Product> products = new ProductListMapper().getProducts(result, labelType);
        List<Product> filteredProducts = products.parallelStream().filter(p -> p.getPercentageDiscount().compareTo(BigDecimal.ZERO) > 0).collect(Collectors.toList());
        Collections.sort(filteredProducts, ( p1,  p2) -> p2.getPercentageDiscount().compareTo(p1.getPercentageDiscount()));
        return filteredProducts;
    }

}
