import java.util.*;


public  class repeatingLetter{
	


    /*
    * solution - A 
    */
	public static String solution(int A, int B) {

        if (A == 1) {

            String aString = "a";
            String bString = IntStream.range(0, B).mapToObj(i -> "b").collect(Collectors.joining(""));

            int k = B / 2;

            String res =  bString.substring(0, k) + aString + bString.substring(k, bString.length());
            return res;

        } else if (B == 1) {

            String aString = IntStream.range(0, A).mapToObj(i -> "a").collect(Collectors.joining(""));
            String bString = "b";

            int k = A / 2;
            return aString.substring(0, k) + bString + aString.substring(k, aString.length());
        }

        if (A >= B) {

            return format(A, B);
        } else {

            return format(B, A);
        }
    }
    

    public static String format(int A, int B) {

        String aString = IntStream.range(0, A).mapToObj(i -> "a").collect(Collectors.joining(""));
        String bString = IntStream.range(0, B).mapToObj(i -> "b").collect(Collectors.joining(""));

        int pos = 1;



        for (int i = 0; i < B; i++) {

            String temp = String.valueOf(bString.charAt(i));

            aString = aString.substring(0, pos) + temp + aString.substring(pos, aString.length());
            pos += 2;
        }

        return aString;
    }


    /*
    * solution - B
    */

    public static String solution(int A, int B) {
        
        if (A >= B) {
            return formation(A, B);
        } 
        
        // 
        else {
            return formation(B, A);
        }
        
    }


    public static String formation(int A, int B){

        String aString = IntStream.range(0, A).mapToObj(i -> "a").collect(Collectors.joining(""));
        String bString = IntStream.range(0, B).mapToObj(i -> "b").collect(Collectors.joining(""));

        String result = "";

        int k = 0;

        List<String> pieces = getParts(aString, 2);

        for (String piece : pieces) {

            result += piece;

            if (k <= B - 1)
                result += bString.charAt(k);
            k++;
        }

        if (k <= B - 1)
            result += result + bString.substring(k);

        return result;
    }

    private static List<String> getParts(String s, int n) {

        List<String> parts = new ArrayList<String>();
        int N = s.length();

        for (int i = 0; i < N; i += n) {
            parts.add(s.substring(i, Math.min(N, i + n)));
        }

        return parts;
    }



    public static void main(String[] args) {

        System.out.println(solution(1, 4));        
    }
}