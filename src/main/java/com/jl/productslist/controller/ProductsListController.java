package com.jl.productslist.controller;

import com.jl.productslist.controller.dto.ProductsListResponse;
import com.jl.productslist.domain.LabelType;
import com.jl.productslist.domain.Product;
import com.jl.productslist.service.ProductsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.util.WebUtils.CONTENT_TYPE_CHARSET_PREFIX;

@RestController
public class ProductsListController {

    private static final String APPLICATION_JSON_VALUE_UTF8 = APPLICATION_JSON_VALUE + CONTENT_TYPE_CHARSET_PREFIX + "UTF-8";

    private final ProductsListService productsListService;

    @Autowired
    public ProductsListController(ProductsListService productsListService) {
        this.productsListService = productsListService;
    }

    @RequestMapping(value = "/products", method = GET, produces = APPLICATION_JSON_VALUE_UTF8)
    @ResponseBody
    public ProductsListResponse getProducts(@RequestParam(required = false) String labelTypeStr){
        LabelType labelType;
        if(!isValidLabelType(labelTypeStr)){
            labelType =  LabelType.ShowWasNow;
        }else{
            labelType = LabelType.valueOf(labelTypeStr);
        }
        List<Product> products =  this.productsListService.getProductsListing(labelType);
        return new ProductsListResponse(products);
    }

    private boolean isValidLabelType(String labelTypeStr) {
        for(LabelType type : LabelType.values()){
            if(type.name().equals(labelTypeStr)){
               return true;
            }
        }
        return false;

    }


}