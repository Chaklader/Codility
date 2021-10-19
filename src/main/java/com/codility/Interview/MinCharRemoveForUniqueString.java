import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MinCharRemove {



    public static int minCntCharDeletionsfrequency(char[] str,int N) {

        Map<Character, Integer> map = new HashMap<>();
        
        // PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());


        int cntChar = 0;

        for (int i = 0; i < N; i++) {

            int value = map.containsKey(str[i]) ? map.get(str[i]) + 1 : 1;
            map.put(str[i], value);
        }

        for (Map.Entry<Character, Integer> it : map.entrySet()) {

            priorityQueue.add(it.getValue());
        }

        while (!priorityQueue.isEmpty()) {

            int frequent = priorityQueue.peek();

            priorityQueue.remove();

            if (priorityQueue.isEmpty()) {

                return cntChar;
            }

            if (frequent == priorityQueue.peek()) {

                if (frequent > 1) {

                    priorityQueue.add(frequent - 1);
                }

                cntChar++;
            }
        }

        return cntChar;
    }


    public static void main(String[] args) {

        String str = "abbbcccd";

        int N = str.length();

        int result = minCntCharDeletionsfrequency(str.toCharArray(), N);
        System.out.println(result);
    }
}

