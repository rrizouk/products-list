package com.jl.productslist.gateway;


import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpMethod.GET;

@Component
public class ProductsListGateway {


    private final RestTemplate restTemplate;

    @Value("${products.baseUrl}")
    private String productsUrl;


    @Autowired
    public ProductsListGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JsonNode getProducts() {
        HttpHeaders multiHeaders = new HttpHeaders();
        multiHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        Map<String, String> params = new HashMap<>();

        HttpEntity<?> request = new HttpEntity<>(multiHeaders);
        ResponseEntity<JsonNode> response = restTemplate.exchange(productsUrl , GET, request, JsonNode.class,params);
        return response.getBody();
    }


}
