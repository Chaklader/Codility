/*
**
**
**

question: write an algorithm to separate 
the numbers in an array such that negative 
numbers are at the beginning and positive 
numbers at the end without changing the order. 
The algorithm shouldn't use sorting and perform 
it in O(n) time and O(1) space, both with and 
without additional data-structure

int[] arr = {9, 2, -3, 1, 0, 0, -7, -6, 3, -5, 2}; // test data 
int[] result = {-3, -7, -6, -5, 9, 2, 1, 0, 0, 3, 2};

**/



public  class SeperateNums {



    public static int[] solution(int[] A){

        int val = 0;
        int index = 0;
        int negIndex = 0;

        int N = A.length;
     
        // int[] A = {9, 2, -3, 1, 0, 0, -7, -6, 3, -5, 2}
        for ( int i = 0; i < N; i++) {

            val = A[i];
            index = i;

            /*
             * put the first negative num in the 1st 
             * position, 2nd in the 2nd position and 
             * so on
            */ 
            while(index > negIndex && val < 0){

                A[index] = A[index-1];
                index--;

                if(negIndex == index ){

                    // this will be index we will fill in the next 
                    negIndex++;                    
                }
            }

            A[index] = val; 
        }

        return A; 
    }
    /*END of solution*/



    /*
	* solution - b
    */
    public static int[] solution1(int[] A) {

        int N = A.length;
        int j = 0;

        for (int i = 0; i < N && j < N; i++) {

            int value = A[i];

            if (value < 0) {

                for (int k = i - 1; k >= j; k--) {
                    A[k + 1] = A[k];
                }

                A[j] = value;
                j++;
            }
        }

        return A;
    }



}