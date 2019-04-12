package com.jl.productslist.domain;

import java.util.HashMap;
import java.util.Map;

public class BasicColorRBGMMapper {

    private static final Map<String,String> colorMap = new HashMap();

    static{
        colorMap.put("BLACK","000000");
        colorMap.put("WHITE","FFFFFF");
        colorMap.put("RED","FF0000");
        colorMap.put("GREEN","008000");
        colorMap.put("BLUE","0000FF");
    }

    public static String getRbhColor(String basicColor) {
        if(!colorMap.containsKey(basicColor.toUpperCase())){
            return "";
        }
        return colorMap.get(basicColor.toUpperCase());
    }
}
