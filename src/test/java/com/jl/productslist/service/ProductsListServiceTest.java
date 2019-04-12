package com.jl.productslist.service;

import com.fasterxml.jackson.databind.JsonNode;

import com.jl.productslist.data.TestData;
import com.jl.productslist.domain.LabelType;
import com.jl.productslist.domain.Product;
import com.jl.productslist.gateway.ProductsListGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.BDDMockito.*;


@RunWith(MockitoJUnitRunner.class)
public class ProductsListServiceTest {

    @InjectMocks
    private ProductsListService underTest;

    @Mock
    private ProductsListGateway productsListGateway;

    @Test
    public void shouldBeAbleToGetFilteredProductsInDescendingPriceReductionOrder()  {

        given(productsListGateway.getProducts()).willReturn(dummyProductsResponse());

        List<Product> products = underTest.getProductsListing(LabelType.ShowWasNow);
        assertNotNull(products);
        assertThat(products.size(),is(2));

        assertThat(products.get(0).getProductId(), is("3523279"));
        assertThat(products.get(0).getTitle(), is("Hobbs Darcie Polka Dot Print Dress, Red/White"));
        assertThat(products.get(0).getNowPrice(), is("£5.00"));
        assertThat(products.get(0).getPriceLabel(), is("Was £15, now £5.00"));

        assertThat(products.get(0).getColorSwatches().get(0).getColor(), is("Wine"));
        assertThat(products.get(0).getColorSwatches().get(0).getRgbColor(), is("FF0000"));
        assertThat(products.get(0).getColorSwatches().get(0).getSkuid(), is("237494589"));

        assertThat(products.get(0).getColorSwatches().get(1).getColor(), is("Midnight"));
        assertThat(products.get(0).getColorSwatches().get(1).getRgbColor(), is("0000FF"));
        assertThat(products.get(0).getColorSwatches().get(1).getSkuid(), is("237494562"));


        assertThat(products.get(1).getProductId(), is("3525081"));
        assertThat(products.get(1).getTitle(), is("hush Marble Panel Maxi Dress, Multi"));
        assertThat(products.get(1).getNowPrice(), is("£59"));
        assertThat(products.get(1).getPriceLabel(), is("Was £69, now £59"));

    }

    @Test
    public void shouldBeAbleToShowProductsWithShowWasNowLabelType()  {

        given(productsListGateway.getProducts()).willReturn(dummyProductsResponse());

        List<Product> products = underTest.getProductsListing(LabelType.ShowWasNow);
        assertNotNull(products);

        assertThat(products.get(0).getProductId(), is("3523279"));
        assertThat(products.get(0).getTitle(), is("Hobbs Darcie Polka Dot Print Dress, Red/White"));
        assertThat(products.get(0).getNowPrice(), is("£5.00"));
        assertThat(products.get(0).getPriceLabel(), is("Was £15, now £5.00"));

    }


    @Test
    public void shouldBeAbleToShowProductsWithShowWasThenNowLabelType()  {

        given(productsListGateway.getProducts()).willReturn(dummyProductsResponse());

        List<Product> products = underTest.getProductsListing(LabelType.ShowWasThenNow);
        assertNotNull(products);

        assertThat(products.get(0).getProductId(), is("3523279"));
        assertThat(products.get(0).getTitle(), is("Hobbs Darcie Polka Dot Print Dress, Red/White"));
        assertThat(products.get(0).getNowPrice(), is("£5.00"));
        assertThat(products.get(0).getPriceLabel(), is("Was £15, then £10, now £5.00"));

    }

    @Test
    public void shouldBeAbleToShowProductsWithShowPercDscountLabelType()  {

        given(productsListGateway.getProducts()).willReturn(dummyProductsResponse());

        List<Product> products = underTest.getProductsListing(LabelType.ShowPercDscount);
        assertNotNull(products);

        assertThat(products.get(0).getProductId(), is("3523279"));
        assertThat(products.get(0).getTitle(), is("Hobbs Darcie Polka Dot Print Dress, Red/White"));
        assertThat(products.get(0).getNowPrice(), is("£5.00"));
        assertThat(products.get(0).getPriceLabel(), is("50% off - now £5.00"));

    }

    private JsonNode dummyProductsResponse() {
        return TestData.createResponseFromJsonProducts().getBody();
    }
}