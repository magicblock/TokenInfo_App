package com.tokeninfo.util;

import java.math.BigDecimal;

public class MathUtil {

    public static float decimail(float amount, int size) {
        if (amount == 0) {
            return 0;
        }
        BigDecimal bd = new BigDecimal(amount);
        bd = bd.setScale(size, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
}
