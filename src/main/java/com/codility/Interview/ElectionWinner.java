package com.codility.Interview;



/*
 * Q: determine the person which get the most votes from provided data. If more than one person gets
 * the highest number of votes, the person who's name starts with decreasing lexicographical order
 * should be selected (ie between veronica and maria, veronica should be the correct answer)
 * */

import java.util.*;

/**
 * Created by Chaklader on 7/5/18.
 */
public class ElectionWinner {


    /*
     * solution - a
     * */
    public static String solution(String[] votes) {


        Map<String, Integer> map = new TreeMap<String, Integer>();

        for (String vote : votes) {

            int value = map.containsKey(vote) ? map.get(vote) + 1 : 1;
            map.put(vote, value);
        }

        List<String> keys = new ArrayList<String>();        
        int max = Collections.max(map.values());

        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            if (entry.getValue() == max) {
                keys.add(entry.getKey());
            }
        }

        /*
         * the list is already sorded in the lexicographically
         * due to the use of TreeMap
         * */
        String result = keys.get(keys.size() - 1);

        return result;
    }


    /*
     * solution - b
     * */
    public static String solution1(String[] votes) {

        TreeMap<String, Integer> map = new TreeMap<String, Integer>();

        for (String vote : votes) {

            String key = vote;
            Integer value = map.containsKey(key) ? map.get(key) + 1 : 1;

            map.put(key, value);
        }

        int max = Collections.max(map.values());
        
        Iterator it = map.entrySet().iterator();

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();

            if (!(entry.getValue()).equals(max)) {
                it.remove();
            }
        }

        // System.out.println(map.lastEntry());
        return map.lastKey();
    }

    public static void printMap(Map<String, Integer> map) {

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
        }
    }


    public static void main(String[] args) {

        String[] votes = {"Victor", "Veronica", "Ryan", "Dave", "Maria",
                "Maria", "Farah", "Farah", "Ryan", "Veronica"};

    }
}
