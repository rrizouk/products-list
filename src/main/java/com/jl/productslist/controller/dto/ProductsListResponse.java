package com.jl.productslist.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jl.productslist.domain.Product;

import java.util.List;

@JsonInclude
public class ProductsListResponse {

    @JsonProperty("products")
    private List<Product> products;

    public ProductsListResponse(){}

    public ProductsListResponse(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

}
