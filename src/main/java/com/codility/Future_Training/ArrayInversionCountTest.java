//package com.codility.Future_Training;
//
//
//import java.util.Random;
//
//
//public class ArrayInversionCountTest {
//
//    @Test
//    public void solution() throws Exception {
//        assertEquals(4, new ArrayInversionCount().solution(new int[]{-1, 6, 3, 4, 7, 4}));
//    }
//
//    @Test
//    public void solution3() throws Exception {
//        assertEquals(30, new ArrayInversionCount.Solution().solution(new int[]{15, 14, 4, 17, 8, 9, 9, 8, 8, 5}));
//    }
//
//    @Test
//    public void solution2() throws Exception {
//        for (int i = 0; i < 10000; i++) {
//            int[] data = getRandomArray(100, 20);
//            int res1 = new ArrayInversionCount.Solution().solution(data.clone());
//            int res2 = new ArrayInversionCount.Solution().nativeSolution(data.clone());
//            assertEquals(res1, res2);
//        }
//    }
//
//    private int[] getRandomArray(int lenght, int scale) {
//        int[] result = new int[lenght];
//        for (int i = 0; i < lenght; i++) {
//            result[i] = new Random().nextInt(scale);
//        }
//        return result;
//    }
//}