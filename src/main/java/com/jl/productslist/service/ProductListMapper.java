package com.jl.productslist.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.jl.productslist.domain.BasicColorRBGMMapper;
import com.jl.productslist.domain.ColorSwatches;
import com.jl.productslist.domain.LabelType;
import com.jl.productslist.domain.Product;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductListMapper {

    public List<Product> getProducts(JsonNode jsonNode, LabelType labelType) {
        List<Product> products = new ArrayList<>();
        for(JsonNode productNode : jsonNode.findPath("products")){
            Product product = new Product();
            product.setProductId(productNode.findPath("productId").asText());
            product.setTitle(productNode.findPath("title").asText());
            product.setNowPrice(getNowPrice(productNode));
            product.setPriceLabel(getPriceLabel(productNode, labelType));
            product.setColorSwatches(getColorSwatches(productNode));
            product.setPercentageDiscount(getPercentageDiscount(productNode));

            products.add(product);
        }
        return products;
    }

    private String getNowPrice(JsonNode productNode) {
        return getPrice(productNode,"now");
    }

    private String getWasPrice(JsonNode productNode) {
        return getPrice(productNode,"was");
    }

    private String getThenPrice(JsonNode productNode) {
        String thenPrice =  getPrice(productNode,"then2");
        if(StringUtils.isEmpty(thenPrice)){
            return getPrice(productNode,"then1");
        }
        return thenPrice;
    }

    private String getPriceLabel(JsonNode productNode, LabelType labelType) {
        StringBuilder builder = new StringBuilder("");
        if(LabelType.ShowWasNow.equals(labelType)){
            builder.append("Was ");
            String wasPrice = getWasPrice(productNode);
            if(StringUtils.isEmpty(wasPrice)){
                wasPrice =  getNowPrice(productNode);
            }
            builder.append(wasPrice);
            builder.append(", now ");
            builder.append(getNowPrice(productNode));
        }
        else if(LabelType.ShowWasThenNow.equals(labelType)){
            builder.append("Was ");
            builder.append(getWasPrice(productNode));
            String thenPrice = getThenPrice(productNode);
            if(!StringUtils.isEmpty(thenPrice)){
                builder.append(", then ");
                builder.append(thenPrice);
            }
            builder.append(", now ");
            builder.append(getNowPrice(productNode));
        }
        else if(LabelType.ShowPercDscount.equals(labelType)){
            String percentageDiscount = String.valueOf(getPercentageDiscount(productNode).intValue()) ;
            builder.append(percentageDiscount);
            builder.append("% off - now ");
            builder.append(getNowPrice(productNode));
        }
        return builder.toString();
    }

    private BigDecimal getPercentageDiscount(JsonNode productNode) {
        Double previousPrice;
        if(!StringUtils.isEmpty(getThenPrice(productNode))){
            previousPrice =  Double.valueOf(getThenPrice(productNode).substring(1));
        }else{
            previousPrice =  Double.valueOf(getWasPrice(productNode).substring(1));
        }

        Double nowPrice = Double.valueOf(getNowPrice(productNode).substring(1));
        if(nowPrice == 0 || previousPrice == 0){
            return BigDecimal.ZERO;
        }
        BigDecimal percentageDiscount = BigDecimal.valueOf(previousPrice).subtract(BigDecimal.valueOf(nowPrice)).divide(BigDecimal.valueOf(previousPrice),2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        return percentageDiscount;
    }


    private String getPrice(JsonNode productNode, String nodeName) {
        if(nodeName.startsWith("then") && StringUtils.isEmpty( productNode.findPath("price").findPath(nodeName).asText())){
            return "";
        }
        StringBuilder builder = new StringBuilder("£");
        String price =  productNode.findPath("price").findPath(nodeName).asText();
        if(StringUtils.isEmpty(price)){
            price = "0";
        }
        Double thePrice = Double.valueOf(price);
        if(thePrice >= 10){
            builder.append(thePrice.intValue());
            return builder.toString();
        }
        DecimalFormat formatter = new DecimalFormat("#.00");
        String formattedPrice = formatter.format(Double.valueOf(price));
        builder.append(formattedPrice);
        return builder.toString();
    }

    private List<ColorSwatches> getColorSwatches(JsonNode productNode) {
        List<ColorSwatches> colors = new ArrayList<>();
        for(JsonNode colorSwatchesNode : productNode.findPath("colorSwatches")){
            ColorSwatches colorSwatches = new ColorSwatches();
            colorSwatches.setColor(colorSwatchesNode.findPath("color").asText());
            colorSwatches.setSkuid(colorSwatchesNode.findPath("skuId").asText());
            colorSwatches.setRgbColor(getRbgColor(colorSwatchesNode.findPath("basicColor").asText()));
            colors.add(colorSwatches);
        }

        return colors;
    }

    private String getRbgColor(String basicColor) {
        return BasicColorRBGMMapper.getRbhColor(basicColor);
    }
}
