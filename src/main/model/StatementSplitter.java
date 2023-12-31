package model;

import java.util.ArrayList;
import java.util.List;

// Taken from https://www.geeksforgeeks.org/split-parenthesis-sequence-into-maximum-valid-substrings/
// a class containing method for splitting up strings
public class StatementSplitter {

    // EFFECTS: returns a list of strings, split up from input s by pairs of balanced parentheses,
    //          non parentheses-contained strings split up by char
    public static List<String> balancedGroups(String s) {
        List<String> res = new ArrayList<>();

        // To keep the count of opening braces.
        int countOpen = 0;

        // To keep the count of closing braces.
        int countClose = 0;

        // To maintain the current string.
        StringBuilder currStr = new StringBuilder();

        int n = s.length();

        // Loop to find the valid substrings
        for (int i = 0; i < n; i++) {
            currStr.append(s.charAt(i));

            // Incrementing the count of
            // opening braces.
            if (s.charAt(i) == '(') {
                countOpen++;
            }

            // Incrementing the count of
            // closing braces.
            if (s.charAt(i) == ')') {
                countClose++;
            }

            // To check for a balanced group
            // and push it to vector res.
            if (countOpen == countClose) {
                res.add(currStr.toString());
                currStr = new StringBuilder();
            }
        }
        // Returning the vector res.
        return res;
    }
}
