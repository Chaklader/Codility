package com.codility.L7_Stacks_And_Queues;

/*
* A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:

S is empty;
S has the form "(U)" or "[U]" or "{U}" where U is A properly nested string;
S has the form "VW" where V and W are properly nested strings.
For example, the string "{[()()]}" is properly nested but "([)()]" is not.

Write A function:

class Solution { public int solution(String S); }

that, given A string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.

For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.

Assume that:

N is an integer within the range [0..200,000];
string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
* */


import java.util.*;

/**
 * Created by Chaklader on 6/24/18.
 */
public class Brackets {


    /*
        Name of the brackets
        --------------------
        i.   Parentheses ( )
        ii.  Square brackets [ ]
        iii. Braces { }
        iv.  Angle brackets ⟨ ⟩ 
    */


    /*
     * String "{[()()]}" is properly nested but "([)()]" is not.
     * */
    /*
     * solution - a
     */
    public static int solution(String S) {

        if(S.isEmpty()){
            return 1;
        }

        int N = S.length();

        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < N; i++) {

            char c = S.charAt(i);

            if (stack.size() == 0) {
                stack.push(c);
            } 

            else if (isMatch(stack.peek(), c)) {
                stack.pop();
            } 

            else {
                stack.push(c);
            }
        }

        // BooleanUtils.toInteger(x)
        return stack.size() == 0 ? 1 : 0;
    }

    private static boolean isMatch(char a, char b) {

        switch (a) {

            case '{': {
                return b == '}';
            }

            case '(': {
                return b == ')';
            }

            case '[': {
                return b == ']';
            }

            default:
                return false;
        }
    }


    /*
     * solution - b
     */
    public int solution1(String S) {


        Stack<Character> stack = new Stack<Character>();


        for (int i = 0; i < S.length(); i++) {

            switch (S.charAt(i)) {

                case '(':
                case '[':
                case '{':

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

                case ']':

                    if (stack.isEmpty() || stack.peek() != '[') {
                        return 0;
                    } 

                    else {
                        stack.pop();
                    }

                    break;

                case '}':

                    if (stack.isEmpty() || stack.peek() != '{') {
                        return 0;
                    } 

                    else {
                        stack.pop();
                    }

                    break;
            }
        }

        return stack.isEmpty() ? 1 : 0;
    }


    /*
     * solution - c
     */
    public int solution2(String str) {

        int N = str.length();

        int P = 0;
        int B = 0;
        int S = 0;

        char[] storage = new char[N];
        int index = 0;


        for (char ch : str.toCharArray()) {


            switch (ch) {

                case '(': {
                    P++;
                    storage[index++] = ch;
                    break;
                }

                case '{': {
                    B++;
                    storage[index++] = ch;
                    break;
                }

                case '[': {
                    S++;
                    storage[index++] = ch;
                    break;
                }

                case ')': {

                    if (index > 0 && storage[index - 1] == '(') {
                        P--;
                        index--;
                    } 

                    else {
                        return 0;
                    }

                    break;
                }

                case '}': {

                    if (index > 0 && storage[index - 1] == '{') {
                        B--;
                        index--;
                    } 

                    else {
                        return 0;
                    }
                    break;
                }

                case ']': {

                    if (index > 0 && storage[index - 1] == '[') {
                        S--;
                        index--;
                    } 

                    else {
                        return 0;
                    }

                    break;
                }
            }

            if (P < 0 || B < 0 || S < 0) {
                return 0;
            }
        }

        /*
         * check if the count of all kind of brackets are zero
         * */
        if (P == 0 && B == 0 && S == 0) {
            return 1;
        }

        return 0;
    }


    /*
     * solution - d
     */
    public int solution4(String S) {


        Map<Character, Character> brackets = new HashMap<>();


        brackets.put(')', '(');
        brackets.put(']', '[');
        brackets.put('}', '{');

        Stack<Character> stack = new Stack<>();


        for (int i = 0; i < S.length(); i++) {

            char bracket = S.charAt(i);

            if (isClosingBracket(bracket)) {

                if (stack.size() == 0) {
                    return 0;
                }

                if (stack.lastElement() != brackets.get(bracket)) {
                    return 0;
                } 

                else {
                    stack.pop();
                }
            } 

            else {
                stack.push(bracket);
            }
        }

        if (stack.size() != 0) {
            return 0;
        }

        return 1;
    }

    private boolean isClosingBracket(char bracket) {

        return new ArrayList<>(Arrays.asList(')', ']', '}')).contains(bracket);
    }

    public static void main(String[] args) {

        Stack<Character> stack = new Stack<>();

        stack.push('(');
        stack.push('{');
        stack.push('[');

        // peek and lastElement are the same
        System.out.println(stack.peek());
        System.out.println(stack.lastElement());
    }





}
