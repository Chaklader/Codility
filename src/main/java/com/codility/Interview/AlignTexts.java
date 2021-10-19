/*

You are moderating a newspaper page, and it's your job to align the text on the page properly. The text is provided to you in the following format:

    lines is an array where each element represents a paragraph, in the form of an array of words;
    aligns is an array representing the alignment of each paragraph from lines - each element is either "LEFT" or "RIGHT";
    width represents the maximum number of characters each line of the output can include.
    Your task is to produce a newspaper page according to the following specifications:

    For each lines[i], include all the words lines[i][j] in order, separated by spaces;
    Include as many words as possible per line (the length of the line must be less than or equal to width), and put the next word on a new line if it would exceed the limit.;
    In the case of excess whitespace, words from lines[i] should be aligned according to aligns[i] - if aligns[i] = "LEFT", the line should have trailing spaces, if aligns[i] = RIGHT, it should have leading spaces;
    Include a border of * characters around all the edges of the result (these characters don't count into width, they are just to make output more pretty).
    It is guaranteed that it is possible to justify the given paragraphs to the newspaper. Return the resulting newspaper page as an array of strings.

    Example

    For lines = [["hello", "world"], ["How", "areYou", "doing"], ["Please look", "and align", "to right"]], aligns = ["LEFT", "RIGHT", "RIGHT"], and width = 16, the output should be

    justifyNewspaperText(lines, aligns, width) = ["******************",
    "*hello world     *",
    "*How areYou doing*",
    "*     Please look*",
    "*       and align*",
    "*        to right*",
    "******************"]
    For lines[0] = ["hello", "world"], both words fit on one line with a combined length of 11. Since width = 16, there are 16 - 11 = 5 excess spaces we'll need to add to this line. Because aligns[0] = "LEFT", we align the text to the left by adding the spaces onto the end of the line. So the result for this paragraph is the line "hello world     ".

    For lines[1] = ["How", "areYou", "doing"], all three words fit on one line with a length of 16. There are no excess spaces, so the result is "How areYou doing".

    For lines[2] = ["Please look", "and align", "to right"], the words "Please look" and "and align" are too long to combine (the result would have a length of 21 > 16), so we'll start a new line with "and align"; furthermore, the words "and align" and "to right" would have a combined length of 18 > 16 which is also too long, so "to right" will also be on a new line. Since aligns[2] = "RIGHT", all excess spaces are added to the beginning of each line. Thus, the result of this paragraph is

    "     Please look"
    "       and align"
    "        to right"
    To create the border, we'll add an asterisk (* character) to the beginning and end of each line, and we'll also add a string of width + 2 asterisks before the first paragraph and after the last paragraph.

    Input/Output

    [execution time limit] 3 seconds (java)

    [input] array.array.string lines

    An array of arrays of strings representing text for the newspaper page.

    Guaranteed constraints:
    1 ≤ lines.length ≤ 50,
    1 ≤ lines[i].length ≤ 10,
    1 ≤ lines[i][j].length ≤ width.

    [input] array.string aligns

    An array of strings representing how the paragraphs should be aligned. It is guaranteed that each array element equals either "LEFT" or "RIGHT".

    Guaranteed constraints:
    aligns.length = lines.length.

    [input] integer width

    An integer representing the width of the newspaper page.

    Guaranteed constraints:
    5 ≤ width ≤ 100.

    [output] array.string

    Return the resulting newspaper page as array of strings, in which the ith string represents the ith line of the newspaper page.

    [Java] Syntax Tips

// Prints help message to the console
// Returns a string
// 
// Globals declared here will cause a compilation error,
// declare variables inside the function instead!
String helloWorld(String name) {
        
    System.out.println("This prints to the console when you Run Tests");
    return "Hello, " + name;
}
*/
public class AlignTexts {


    public static void main(String[] args) {
        
    }
    
}