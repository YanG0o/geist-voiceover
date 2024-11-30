package com.kigi.commonutil;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class StringUtil {
    public static String priceFormat(String priceStr) {
        try {
            double price = Double.parseDouble(priceStr)/100;
            DecimalFormat df = new DecimalFormat(",##0");
            df.setRoundingMode(RoundingMode.DOWN);
            String decimalStr = "";
            if (priceStr.contains(".")) {
                decimalStr = priceStr.substring(priceStr.lastIndexOf("."));
            }
            return df.format(price) + decimalStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return priceStr;
    }
}
