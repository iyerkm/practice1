package main.java.ds.graph;

import static main.java.common.Util.print;

public class VerifyAlienDictionary {

    public static void main(String[] args){
        String[] input1 = new String[] {"hello","leetcode"};
        String order1 = "hlabcdefgijkmnopqrstuvwxyz";

        String[] input2 = new String[] {"word","world","row"} ;
        String order2 = "worldabcefghijkmnpqstuvxyz";

        print(verifyAlienDictionary(input1,order1));
        print(verifyAlienDictionary(input2,order2));
     }

    private static boolean verifyAlienDictionary(String[] input, String order) {
        int dict[] = new int[26];
        int index=0;
        for(int i=0;i<order.length();i++){
            dict[order.charAt(i)-'a']=index;
            index++;
        }
        for(int i=1;i< input.length;i++){
            if(!inOrder(input[i-1],input[i],dict)){
                return false;
            }
        }
        return true;
    }

    public static boolean inOrder(String word1, String word2, int[] dict){
        int m=word1.length();
        int n=word2.length();

        for(int i=0;i<m && i<n;i++){
            if(word1.charAt(i)!=word2.charAt(i)){
                return dict[word1.charAt(i)-'a'] < dict[word2.charAt(i)-'a'];
            }
        }
        return n > m;
    }
}


/*
In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographicaly in this alien language.



Example 1:

Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
Output: true
Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
Example 2:

Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
Output: false
Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
Example 3:

Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
Output: false
Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 20
order.length == 26
All characters in words[i] and order are English lowercase letters.
 */