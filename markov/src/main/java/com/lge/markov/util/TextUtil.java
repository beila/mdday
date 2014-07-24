package com.lge.markov.util;

import java.util.List;

public class TextUtil {
    public static String join(List<String> value, String joinString) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < value.size(); i++) {
            if (i > 0) {
                sb.append(joinString);
            }
            sb.append(value.get(i));
        }
        return sb.toString();
    }
}
