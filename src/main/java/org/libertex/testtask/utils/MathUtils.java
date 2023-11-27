package org.libertex.testtask.utils;

import java.util.Random;

public class MathUtils {
    public static long generateRandomNumber(int min, int max){
        return new Random().nextInt(max - min)+min;
    }
}
