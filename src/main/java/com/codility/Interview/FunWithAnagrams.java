
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;

import java.util.*;
//import java.util.concurrent.atomic.AtomicReference






public class FunWithAnagrams {



    /**
    * solution - A
    */
    public static List<String> funWithAnagrams(List<String> text) {

        List<String> list = new ArrayList<>(text);

        int N = list.size();

        for (int i = 0; i < N - 1; i++) {

            String original = list.get(i);

            List<String> subList = list.subList(i + 1, N);
            ListIterator<String> itr = subList.listIterator();

            while (itr.hasNext()) {

                String removeIfAnagram = itr.next();
                boolean isAnagram = checkAnagram(original, removeIfAnagram);

                if (isAnagram) {
                    itr.remove();
                }
            }

            N = list.size();
        }

        Collections.sort(list);
        return list;
    }


    /**
     * solution - B
     */
    public static List<String> funWithAnagrams(List<String> text) {

        List<String> list = new ArrayList<>(text);

        int N = list.size();

        for (int i = 0; i < N - 1; i++) {

            String original = list.get(i);

            int j = i + 1;

            while (j < N) {

                String testStr = list.get(j);
                boolean isAnagram = checkAnagram(original, testStr);

                if (isAnagram) {

                    list.remove(j);

                    N = list.size();
                    j--;
                }

                j++;
            }
        }

        Collections.sort(list);
        return list;
    }

    public static boolean checkAnagram(String original, String test) {
        return sort(original).equalsIgnoreCase(sort(test));
    }

    public static String sort(String s) {

        char[] content = s.toCharArray();

        Arrays.sort(content);
        return new String(content);
    }



    public static void main(String[] args) {


        // "code", "framer", "frame"
//        String[] arr = {"code", "doce", "ecod", "framer", "frame"};

        // "aaagmnrs", "code"
        String[] arr = {"code", "aaagmnrs", "anagrams", "doce"};


        List<String> values = Arrays.asList(arr);

        List<String> v = funWithAnagrams(values);
        System.out.println(v.toString());
    }
}
