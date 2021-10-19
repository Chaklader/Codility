//package com.codility.Indeed_Prime_2016_Challenge;
//
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class DwarfsRaftingTest {
//    @Test
//    public void solution() throws Exception {
//        assertEquals(6, new DwarfsRafting.Solution().solution(4, "1B 1C 4B 1D 2A", "3B 2D"));
//    }
//
//    @Test
//    public void solution2() throws Exception {
//        assertEquals(5, new DwarfsRafting.Solution().solution(4, "1B 1C 4B 1D 2A", "3B 2D 3C"));
//    }
//
//    @Test
//    public void solution3() throws Exception {
//        assertEquals(16, new DwarfsRafting.Solution().solution(4, "", ""));
//    }
//
//    @Test
//    public void solution4() throws Exception {
//        assertEquals(-1, new DwarfsRafting.Solution().solution(5, "", ""));
//    }
//
//    @Test
//    public void solution5() throws Exception {
//        assertEquals(4, new DwarfsRafting.Solution().solution(2, "", ""));
//    }
//
//    @Test
//    public void solution6() throws Exception {
//        assertEquals(2, new DwarfsRafting.Solution().solution(2, "1A 2B", ""));
//    }
//
//    @Test
//    public void solution7() throws Exception {
//        assertEquals(2, new DwarfsRafting.Solution().solution(2, "2B", ""));
//    }
//
//    @Test
//    public void solution8() throws Exception {
//        assertEquals(-1, new DwarfsRafting.Solution().solution(1, "2B 1A 1B", ""));
//    }
//
//    @Test
//    public void solution9() throws Exception {
//        assertEquals(34, new DwarfsRafting.Solution().solution(6, "4D", ""));
//    }
//
//    @Test
//    public void solution10() throws Exception {
//        assertEquals(34, new DwarfsRafting.Solution().solution(6, "4D 3C", ""));
//    }
//
//    @Test
//    public void solution11() throws Exception {
//        assertEquals(8, new DwarfsRafting.Solution().solution(4, "1A 1B 2A 2B", ""));
//    }
//
//    @Test
//    public void solution12() throws Exception {
//        assertEquals(3, new DwarfsRafting.Solution().solution(4, "2B 3A 3C 3D 4A 4B 4C", "3B"));
//    }
//
//    @Test
//    public void solution13() throws Exception {
//        assertEquals(3, new DwarfsRafting.Solution().solution(2, "", "1A"));
//    }
//}