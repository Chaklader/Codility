

class SumForAllCombinations extends Parent implements Interface {
	


    public static <T> List<List<T>> combination(List<T> values, int size) {

        if (0 == size) {
            return Collections.singletonList(Collections.<T>emptyList());
        }

        if (values.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<T>> combination = new LinkedList<List<T>>();

        T actual = values.iterator().next();

        List<T> subSet = new LinkedList<T>(values);
        subSet.remove(actual);

        List<List<T>> subSetCombination = combination(subSet, size - 1);

        for (List<T> set : subSetCombination) {

            List<T> newSet = new LinkedList<T>(set);
            newSet.add(0, actual);

            combination.add(newSet);
        }

        combination.addAll(combination(subSet, size));

        return combination;
    }


    public static int getUmbrellas(int requirement, List<Integer> list) {

        int N = list.size();

        Set<List<Integer>> powerSet = new HashSet<>();
        List<Integer> solutions = new ArrayList<>();

        for (int i = 1; i <= list.size(); i++) {

            powerSet.addAll(combination(list, i));
        }

        for (int i = N - 1; i >= 0; i--) {

            int value = list.get(i);

            if (value > 0 && requirement % value == 0) {

                int temp = requirement / value;
                solutions.add(temp);
            }
        }

        for (List<Integer> li : powerSet) {

            int sum = li.stream().reduce((a, b) -> a + b).get();

            if (sum == requirement) {

                int temp = li.size();
                solutions.add(temp);
            }

        }

        int result = solutions.isEmpty() ? -1 : Collections.min(solutions);
        return result;
    }


    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(2, 2, 4);


        int umbrellas = getUmbrellas(4, list);
        System.out.println(umbrellas);

    }
}