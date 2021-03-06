package main.java.ds.string;

import static main.java.common.Util.print;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args){
        print(longestRepeatingCharacterReplacement("ABAB",1));
    }

    private static int longestRepeatingCharacterReplacement(String input, int k) {
        if(null==input || input.length()==0) return 0;
        int start=0;
        int [] count = new int[26];
        int uniqueCount = 0;
        int replaceCount=0;
        int max=0;
        for(int end=0;end<input.length();end++){
            char c = input.charAt(end);
            count[c-'A']++;
            uniqueCount = Math.max(uniqueCount,count[c-'A']);
            replaceCount = end-start +1 - uniqueCount;

            if(replaceCount > k){
                count[input.charAt(start)-'A']--;
                start++;
            } else {
                max = Math.max(max,end-start+1);
            }
        }
        return max;
    }
}

/*
Given a string s that consists of only uppercase English letters, you can perform at most k operations on that string.

In one operation, you can choose any character of the string and change it to any other uppercase English character.

Find the length of the longest sub-string containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.


Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
 */