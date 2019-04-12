package com.jl.productslist.controller;


import com.google.common.collect.Lists;
import com.jl.productslist.domain.LabelType;
import com.jl.productslist.domain.Product;
import com.jl.productslist.service.ProductsListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ProductsListControllerTest {

    private static final String PRODUCTS_URL = "/products/";
    MockMvc mockMvc;

    @InjectMocks
    private ProductsListController underTest;

    @Mock
    ProductsListService productsListService;

    @Before
    public void setUp() {
       this.mockMvc = standaloneSetup(underTest).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }


    @Test
    public void shouldGetVenuesForGivenPlace() throws Exception {

        given(productsListService.getProductsListing(LabelType.ShowWasNow)).willReturn(dummyProductsList());
        mockMvc.perform(get(PRODUCTS_URL )).andExpect(status().isOk())
                                                        .andExpect(jsonPath("$.products").exists());
    }

    private List<Product> dummyProductsList() {
        return Lists.newArrayList();
    }
}