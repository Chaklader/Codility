

public  class ascendingTree {
	

	/*
		Count the number of ways to make a sequence sorted by deleting one item
	*/

	public static int solution(int[] A) {

        int result = 0;
        int N = A.length;

        for (int k = 1; k < N; k++) {

            if (A[k - 1] > A[k]) {

                if (result != 0) {
                    return 0;
                }

                if (k == 1 || A[k - 2] <= A[k]) {
                    ++result;
                }

                if (k == A.length - 1 || A[k - 1] <= A[k + 1]) {
                    ++result;
                }

                if (result == 0) {
                    return 0;
                }
            }
        }

        return (result == 0) ? N : result;
    }
}