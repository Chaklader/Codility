package com.codility.Interview;


/*
 * write a program to merge two strings letter by letter. In case of unequal strings,
 * you should continue adjoining the larger string after reaching the end of the smaller
 * string
 * */


/**
 * Created by Chaklader on 7/5/18.
 */
public class MergeStrings {


    /*
     * solution - a
     * */
    public static String solution(String a, String b) {


        boolean eql = a.length() == b.length();
        boolean less = a.length() < b.length();

        int min = less ? a.length() : b.length();

        String rst = "";

        for (int i = 0; i < min; i++) {

            // rst +=   new StringBuilder().append(a.charAt(i)).append(b.charAt(i)).toString();
            // rst += String.valueOf(a.charAt(i)) + String.valueOf(b.charAt(i));

            rst += "" + a.charAt(i) + b.charAt(i);
        }

        if (eql) {
            return rst;
        } else if (less) {
            return rst += b.substring(min);
        }

        return rst += a.substring(min);
    }
}
