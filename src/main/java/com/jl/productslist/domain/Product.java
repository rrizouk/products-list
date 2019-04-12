package com.jl.productslist.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.List;

@JsonInclude
public class Product {

    private String productId;
    private String title;
    private String nowPrice;
    private String priceLabel;
    private List<ColorSwatches> colorSwatches;
    @JsonIgnore
    private BigDecimal percentageDiscount;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(String nowPrice) {
        this.nowPrice = nowPrice;
    }



    public List<ColorSwatches> getColorSwatches() {
        return colorSwatches;
    }

    public void setColorSwatches(List<ColorSwatches> colorSwatches) {
        this.colorSwatches = colorSwatches;
    }



    public BigDecimal getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(BigDecimal percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public String getPriceLabel() {
        return priceLabel;
    }

    public void setPriceLabel(String priceLabel) {
        this.priceLabel = priceLabel;
    }

}
