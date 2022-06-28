

public class sortedString {

    
    /*
    String s = "listen is it silent";

        String[] arr = {"listen", "is", "it", "silent"};
        List<String> wordSet = Arrays.asList(arr);


        long result = test(wordSet,s);
    * */

    static Long test(List<String> wordSet, String sentence) {

        Map<String, Long> wordSetMap = getFrequencyMap(wordSet);

        String[] arr = sentence.split("\\s+");
        List<String> words = Arrays.asList(arr);

        Map<String, Long> wordsMap = getFrequencyMap(words);

        long result = 0L;

        for (Map.Entry<String, Long> entry: wordsMap.entrySet()){

            String key = entry.getKey();
            Long value = entry.getValue();

            Long count = wordSetMap.get(key);

            if(count == null || count==1){
                continue;
            }

            result += count* value;
        }


        return result;
    }


    static Map<String, Long> getFrequencyMap(List<String> wordSet) {

        Map<String, Long> map = new HashMap<>();

        for (String word : wordSet) {

            char[] ch = word.toCharArray();
            Arrays.sort(ch);

            String sortedWord = String.valueOf(ch);

            long count = map.containsKey(sortedWord)? map.get(sortedWord)+1:1;
//            long count = map.getOrDefault(sortedWord, 1L);
            map.put(sortedWord, count);
        }

        return map;
    }


    public static void main(String[] args) {

        String s = "listen is it silent";

        String[] arr = {"listen", "is", "it", "silent"};
        List<String> wordSet = Arrays.asList(arr);


        long result = test(wordSet,s);

        System.out.println("Hello " + result);

    }
}
