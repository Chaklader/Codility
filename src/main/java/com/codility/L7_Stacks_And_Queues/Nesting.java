package com.codility.L7_Stacks_And_Queues;

/*
* A string S consisting of N characters is called properly nested if:

S is empty;
S has the form "(U)" where U is A properly nested string;
S has the form "VW" where V and W are properly nested strings.
For example, string "(()(())())" is properly nested but string "())" isn't.

Write A function:

class Solution { public int solution(String S); }

that, given A string S consisting of N characters, returns 1 if string S is properly nested and 0 otherwise.

For example, given S = "(()(())())", the function should return 1 and given S = "())", the function should return 0, as explained above.

Assume that:

N is an integer within the range [0..1,000,000];
string S consists only of the characters "(" and/or ")".
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
* */


import java.util.Stack;



/**
 * Created by Chaklader on 6/24/18.
 */
public class Nesting {



    /*
     * String "(()(())())" is properly nested but string "())" is not
     * */

    /*
     * solution - A
     */
    public static int solution(String S) {

        if(S.isEmpty()){
            return 1;
        }

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < S.length(); i++) {

            char ch = S.charAt(i);

            if (ch == '(') {
                stack.push('(');
            } 

            else if (ch == ')' && stack.size() > 0) {
                stack.pop();
            }

            else{
                return 0;
            } 
        }

        int value =  stack.size() == 0? 1: 0;
        return value;
    }


    /*
     * solution - B
     */
    public static int solution1(String S) {

        // "(()(())())"

        int count = 0;
        char[] chars = S.toCharArray();

        for (char c : chars) {

            if (c == '(') {
                count++;
            } 

            else if (c == ')') {
                count--;
            }
        
            if (count < 0) {
                return 0;
            }
        }

        int value =  (count == 0)? 1: 0;
        return value;
    }


    /*
     * solution - c
     */
    public int solution2(String S) {


        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < S.length(); i++) {

            switch (S.charAt(i)) {

                case '(':

                    stack.push(S.charAt(i));
                    break;

                case ')':

                    if (stack.isEmpty() || stack.peek() != '(') {
                        return 0;
                    } 

                    // 
                    else {
                        stack.pop();
                    }

                    break;
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }


    /*
     * solution - D
     * */
    public int solution4(String S) {

        char closing = ')';
        int sum = 0;

        for (int i = 0; i < S.length(); i++) {

            char currentSymbol = S.charAt(i);

            if (currentSymbol == closing) {
                sum--;
            } 

            else {
                sum++;
            }

            if (sum < 0) {
                return 0;
            }
        }
        return sum == 0 ? 1 : 0;
    }
}
