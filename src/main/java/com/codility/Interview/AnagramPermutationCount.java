



public class AnagramPermutationCount {




    public static List<Long> getAnagramPermutationCount(List<String> wordSet, List<String> sentences) {

        List<Long> result = new ArrayList<>();

        Map<String, Long> wordSetMap = getWordFrequency(wordSet);

        for (String sentence : sentences) {

            long permutationCount = getPermutationCount(sentence, wordSetMap);
            result.add(permutationCount);
        }

        return result;
    }


    public static long getPermutationCount(String sentence, Map<String, Long> map) {

        String[] words = sentence.split("\\s+");

        long result = 1L;

        for (String word : words) {

            String sortedWord = getSortWord(word);
            long count = map.getOrDefault(sortedWord, 1L);

            result *= count;
        }

        return result;
    }

    public static Map<String, Long> getWordFrequency(List<String> words) {

        Map<String, Long> map = new HashMap<>();

        for (String word : words) {

            String sortedWord = getSortWord(word);
            long frequency = map.containsKey(sortedWord) ? map.get(sortedWord) + 1 : 1;

            map.put(sortedWord, frequency);
        }

        return map;
    }

    public static String getSortWord(String str) {

        char[] ch = str.toCharArray();
        Arrays.sort(ch);

        return String.valueOf(ch);
    }





    public static void main(String[] args) {


        String[] wordSetArray = {"silent", "listen", "is", "it"};
        List<String> wordSet = Arrays.asList(wordSetArray);


        String sentence = "silent is it listen";

        List<String> sentences = new ArrayList<>();
        sentences.add(sentence);

        List<Long> counts = getAnagramPermutationCount(wordSet, sentences);
        System.out.println(counts.get(0));

    }
}
