package com.nkdark.util;


import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created with IntelliJ IDEA
 *
 * @Author: NKDark
 * @Date: Create in 2020/8/17
 * @Time: 14:41
 * @Description:
 */
@Component
public class DiceUtil {
    public static long roll(int count, int surface) {
        Random r = new Random();
        int sum = 0;
        for (int i = 1; i <= count; i++) {
            int v = r.nextInt(surface)+1;
            sum += v;
        }
        return sum;
    }

}
