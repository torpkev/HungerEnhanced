package com.ibexmc.hungerenhanced.util;

import com.ibexmc.hungerenhanced.HungerEnhanced;
import com.ibexmc.hungerenhanced.util.generic.ColorFunctions;
import com.ibexmc.hungerenhanced.util.generic.StringFunctions;

import java.util.Map;

public class Locale {

    private HungerEnhanced instance;

    public Locale(HungerEnhanced instance) {
        this.instance = instance;
    }

    public String localeText(String code, String defaultText, Map<String, String> placeHolder) {
        String returnValue = defaultText;

        if (instance.getData().getLocale().has(code)) {
            String newMessage = instance.getData().getLocale().get(code);
            if (!StringFunctions.isNullOrEmpty(newMessage)) {
                returnValue = newMessage;
            }
        }
        if (placeHolder != null) {
            for (Map.Entry<String, String> params : placeHolder.entrySet()) {
                returnValue = returnValue.replace(params.getKey(), params.getValue());
            }
        }
        return ColorFunctions.color(returnValue);
    }
}
