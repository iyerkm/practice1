package main.java.ds.string;

import java.util.HashSet;
import java.util.Set;

import static main.java.common.Util.print;

public class LengthOfLongestSubstring {
    public static void main(String[] args){
        print(lengthOfLongestSubstring("abcabcbb"));
    }

    private static int lengthOfLongestSubstring(String input) {
        if(null==input||input.length()==0) return 0;
        int maxLength=1;
        Set<Character> set = new HashSet<>();

        for(int i=0;i<input.length();i++){
            for(int j=i;j<input.length();j++){ //O(2n)
                char c = input.charAt(j);
                if(!set.contains(c)){
                    set.add(c);
                    maxLength = Math.max(maxLength,set.size());
                    continue;
                } else {
                    while(i<j){
                        if(input.charAt(i)==c){
                            i++;
                            break;
                        }
                        set.remove(input.charAt(i));
                        i++;
                    }
                }
            }
        }
        return maxLength;
    }
}

/*
Given a string s, find the length of the longest substring without repeating characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
Example 4:

Input: s = ""
Output: 0


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 */