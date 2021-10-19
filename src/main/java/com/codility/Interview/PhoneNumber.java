package com.codility.Interview;


/*
A string of phone number is provided. Write a program that format the according to certain
rules provided below.


    i.   in the end if there are 3 digits, keep i as it is.

    ii.  in the end if there are 4 digits, part it as 2 groups of 2 digits

    ii.  in the end if there are 5 digits, part it as 2 groups of 3 digits and 2 digits


All the groups needs to be adjoined with dashes ('-')

    For example, '555372654' should be returned as '555-372-654'

    For example, '0 - 22 1985--324' should be returned as '022-198-53-24'

    For example, '00-44  48 5555 8361' should be returned as '004-448-555-583-61'
*/

import java.util.stream.IntStream;


/**
 * Created by Chaklader on 7/12/18.
 */
public class PhoneNumber {


    /*
    * solution - a
    */
    public static String solution(String S) {

        String result = "";

        S = S.replaceAll("[^0-9]", "");
        int N = S.length();

        int i = 0;
        int index = 0;


        while (i < N / 3) {

            int rest = N - 3 * i;

            if (rest == 4) {

                result += S.substring(index, index = index + 2) + "-";
                result += S.substring(index, index = index + 2) + "-";

                break;
            }

            //
            else if (rest == 5) {

                result += S.substring(index, index = index + 3) + "-";
                result += S.substring(index, index = index + 2) + "-";

                break;
            }

            result += S.substring(index, index = index + 3) + "-";
            i++;
        }

        result = result.substring(0, result.length() - 1);
        return result;
    }





    /*
     * solution - b
     * */
    public static String solution(String S) {

        if (S == null || S.isEmpty()) {
            return S;
        }

        String temp = "";

        for (int i = 0; i < S.length(); i++) {

            char value = S.charAt(i);

            if (Character.isDigit(value)) {
                temp += String.valueOf(Character.getNumericValue(value));
            }
        }

        int N = temp.length();

        if (N <= 3) {
            return temp;
        }

        String result = "";

        boolean bol = false;

        if (N % 3 == 1) {
            bol = true;
        }

        for (int i = 0; i < N; i++) {

            int r = N - (i + 1);

            if (r == 4 && bol) {

                result += String.valueOf(temp.charAt(i));
                String rest = temp.substring(i + 1);

                result += "-" + rest.substring(0, 2) + "-" + rest.substring(2);
                break;
            }

            if (i > 0 && i % 3 == 0) {
                result += "-";
            }

            result += String.valueOf(temp.charAt(i));
        }

        return result;
    }


    /*
     * solution - c
     * */
    public static String solution1(String s) {

        if (s == null) {
            return null;
        }

        return s.replaceAll("\\D", "")                // Discard all non-digits.
                .replaceAll("(\\d{2})(?=\\d{2}$)" +   // Final group of 4 digits
                                "|" +                     // ... or ...
                                "(\\d{3})(?!$)",          // non-final group of 3 digits,
                        "$1$2-");                 // insert separator.
    }


    /*
     * solution - d
     * */
    public static String solution2(String input) {

        if (input == null) {
            return input;
        }

        StringBuilder phone = new StringBuilder();

        IntStream.range(0, input.length())
                .filter(i -> Character.isDigit(input.charAt(i)))
                .forEachOrdered(i -> phone.append(Character.getNumericValue(input.charAt(i))));

        if (phone.length() <= 3) {
            return phone.toString();
        }

        int N = phone.length();
        int M = (N / 3) * 3;

        switch (N % 3) {

            case 0:
                break;

            /*
             * insert the dash making  2-2 groups instead of 3-1
             * */
            case 1:
                phone.insert(M - 1, '-');
                break;

            /*
             * end up with a 3-2 group
             * */
            case 2:
                phone.insert(M, '-');
                break;
        }

        while (M > 3) {
            M -= 3;
            phone.insert(M, '-');
        }

        return phone.toString();
    }


    public static void main(String[] args) {


        /*
        *
        * For example, '555372654' should be returned as '555-372-654'

    For example, '0 - 22 1985--324' should be returned as '022-198-53-24'

    For example, '00-44  48 5555 8361' should be returned as '004-448-555-583-61'
        * */

        String s = "555372654";

        String s1 = "0 - 22 1985--324";

        String s2 = "00-44  48 5555 8361";
    }
}
