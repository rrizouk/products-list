package com.jl.productslist.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude
public class ColorSwatches {

    private String color;
    private String rgbColor;
    private String skuid;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRgbColor() {
        return rgbColor;
    }

    public void setRgbColor(String rgbColor) {
        this.rgbColor = rgbColor;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }


}
