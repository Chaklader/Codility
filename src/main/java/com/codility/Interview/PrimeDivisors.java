package com.codility.Interview;

import java.util.ArrayList;
import java.util.List;

public class PrimeDivisors {
    
    public static List<Integer> solution(int x) {

        List<Integer> list = new ArrayList<>();
        primeFactors(x, list);

        return list;
    }

    public static void primeFactors(int n, List<Integer> list) {

        while (n % 2 == 0) {
            list.add(2);
            n /= 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2) {

            while (n % i == 0) {
                list.add(i);
                n /= i;
            }
        }

        if (n > 2) {
            list.add(n);
        }
    }

}