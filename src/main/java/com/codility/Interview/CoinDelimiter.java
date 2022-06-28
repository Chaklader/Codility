


/*
Given a value V, if we want to make change for V cents, and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins, what is the minimum number of coins to make the change? 

Examples:  

Input: coins[] = {25, 10, 5}, V = 30
Output: Minimum 2 coins required
We can use one coin of 25 cents and one of 5 cents 

Input: coins[] = {9, 6, 5, 1}, V = 11
Output: Minimum 2 coins required
We can use one coin of 6 cents and one coin of 5 cents
 
This problem is a variation of the problem discussed Coin Change Problem. Here instead of finding total number of possible solutions, we need to find the solution with minimum number of coins.

The minimum number of coins for a value V can be computed using below recursive formula. 
*/


public class CoinDelimiter {
	

	// 16 = 9+7 result 2 
    // 25 = 9+7+9 result 3 
    
    public static int coinDeterminer(int num) {

        int result = Integer.MAX_VALUE;
        int[] coins = {11, 9, 7, 5, 1};

        if (num == 0) {
            return 0;
        }

        for (int coin : coins) {

            if (coin <= num) {
                int sub_res = coinDeterminer(num - coin);

                if (sub_res != Integer.MAX_VALUE && sub_res + 1 < result) {
                    result = sub_res + 1;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
//
//        String s = "never odd or even";
//
//        System.out.println(Palindrome(s));

        System.out.println(coinDeterminer(25));
    }
}