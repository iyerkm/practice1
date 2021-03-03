package main.java.interviews.year_2021;

import java.util.*;

import static main.java.common.Util.print;


public class MicrosoftOA2021 {
    static int max = 0;

    public static void main(String[] args){
        //https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
        print("minDeletions: " + minDeletions("aaabbbcc"));//min deletions to Minimum Deletions to Make Character Frequencies Unique
        //min swaps to make string palindrome
        print("minswaps: " + minswaps("mamad".toCharArray()));
        int[] minStepsInput = new int[] {5,2, 2};
        //make height same
        print("minSteps: " + minSteps(minStepsInput));

        //Largest Num with Neg pair
        int[] arr = {-41,3,2,5,41};
        print("largestNumWithNegPair: " + largestNumWithNegPair(arr));
        //https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/discuss/1036741/clear-java-DFS
        List<String> listArr =  Arrays.asList((new String[] {"cha","r","act","ers"}));

        print("maxLengthOfConcatenatedStringWithUniqueCharacter: " + maxLengthOfConcatenatedStringWithUniqueCharacter(listArr));
        List<List<Integer>> result = partition(new int[] {1,2,3,4,5,6,7,8,9,10}, 3);
        List<List<Integer>> result1 = partition(new int[] {11,12,13,4,5,6,7,8,9,10}, 7);
        print("partition: " + result1);

        int[] nums = {3, 4, 2, 3, 0, 3, 1, 2, 1};
        int start = 7;
        print("canReach0DFS: " + canReach0BFS(nums, start));

    }

    /*
    Write a function that, given an array A of N integers, returns the lagest integer K > 0 such that both values K and -K exist in array A. If there is no such integer, the function should return 0.

Example 1:

Input: [3, 2, -2, 5, -3]
Output: 3
Example 2:

Input: [1, 2, 3, -4]
Output: 0
     */
    private static int largestNumWithNegPair(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int curMax=0;

        for(int i:arr){
            if(set.contains(i*-1)){
                curMax = Math.max(curMax,Math.abs(i));
            } else {
                set.add(i);
            }
        }
        return curMax;
    }

    public static int minDeletions(String s) {
        int deletions = 0;
        int[] letters = new int[26];

        for(char c:s.toCharArray()){
            int index=c-'a';
            letters[index]++;
        }

        HashSet<Integer> set = new HashSet<>();

        for(int i=0;i<letters.length;i++){
            while(letters[i]!=0 && !set.add(letters[i])){
                letters[i]--;
                deletions++;
            }
        }
//        System.out.println(set.toString());
        return deletions;
    }

    /*
    Given a string, what is the minimum number of adjacent swaps required to convert a string into a palindrome. If not possible, return -1.

Example 1:

Input: "mamad"
Output: 3
Example 2:

Input: "asflkj"
Output: -1
Example 3:

Input: "aabb"
Output: 2
Example 4:

Input: "ntiin"
Output: 1
Explanation: swap 't' with 'i' => "nitin"
     */



    public static int minswaps(char[] s1) {
        //mamad
        int result = 0;
        if(!isPalindrome(s1))
            return -1;
        int i = 0;
        int j = s1.length - 1;
        int k = j;
        while(i < j) {
            k = j;
            while(s1[i] != s1[k] && k > i)
                k--;
            if(s1[i] == s1[k] && i != k) {
                while(k < j) {
                    swap(s1,k,k+1);
                    print(String.valueOf(s1));
                    k++;
                    result++;
                }
                i++;
                j--;
            }
            else {
                swap(s1,i,i+1);
                print(String.valueOf(s1));
                result++;
            }
            print(String.valueOf(s1));
        }
        return result;
    }
    private static boolean isPalindrome(char[] s1) {
        HashSet<Character> hs = new HashSet<>();
        for(char c: s1) {
            if(hs.contains(c)) {
                hs.remove(c);
            }
            else {
                hs.add(c);
            }
        }
        return hs.size() < 2;
    }

    private static void swap(char[] s2, int i, int j) {
        char tmp = s2[i];
        s2[i] = s2[j];
        s2[j] = tmp;
    }
    /*
    Alexa is given n piles of equal or unequal heights. In one step, Alexa can remove any number of boxes from the pile which has the maximum height and try to make it equal to the one which is just lower than the maximum height of the stack. Determine the minimum number of steps required to make all of the piles equal in height.

    Example 1:

    Input: piles = [5, 2, 1]
    Output: 3
    Explanation:
    Step 1: reducing 5 -> 2 [2, 2, 1]
    Step 2: reducing 2 -> 1 [2, 1, 1]
    Step 3: reducing 2 -> 1 [1, 1, 1]
    So final number of steps required is 3.
     */
    public static int minSteps(int[] piles) {
        if (piles.length < 2) return 0;
        int len = piles.length;
        Arrays.sort(piles);
        int sum = 0;
        int n = piles.length;
        for (int i = 1; i < n; i++) {
            if (piles[i-1] != piles[i]) {
                sum += i;
            }
        }
        return sum;
    }
//https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/discuss/1036741/clear-java-DFS
    public static int maxLengthOfConcatenatedStringWithUniqueCharacter(List<String> arr) {
        if(arr == null || arr.size() == 0){
            return 0;
        }
        dfs(arr,0,new ArrayList<>());
        return max;
    }
    public static void dfs(List<String> arr,int index,List<String> cur){
        if(index == arr.size()){
            return;
        }
        cur.add(arr.get(index));
        if(isUnique(cur)){
            max = Math.max(max,getLength(cur));
        }
        dfs(arr,index+1,cur);
        cur.remove(cur.size()-1);


        dfs(arr,index+1,cur);

    }
    public static boolean isUnique(List<String> strs){
        Set<Character> set = new HashSet<>();
        for(int i=0;i<strs.size();i++){
            for(int j=0;j<strs.get(i).length();j++){
                if(set.contains(strs.get(i).charAt(j))){
                    return false;
                }
                set.add(strs.get(i).charAt(j));
            }
        }
        return true;
    }
    public static int getLength(List<String> strs){
        int result = 0;
        for(int i=0;i<strs.size();i++){
            result += strs.get(i).length();
        }
        return result;
    }

    public static List<List<Integer>> partition(int[] T, int n) {
        int[] sums = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return sums[a.intValue()] - sums[b.intValue()];
            }
        });
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
            pq.add(i);
        }

        for (int i = T.length - 1; i >= 0; i--) {
            print(pq);
            print(Arrays.toString(sums));
            int c = pq.poll();
            result.get(c).add(T[i]);
            sums[c] += T[i];
            pq.add(c);
        }

        return result;
    }

    private static boolean canReach0BFS(int[] nums, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        Set<Integer> visited = new HashSet<>();
        while(!q.isEmpty()) {
            int cur = q.poll();
            if(nums[cur] == 0)
                return true;
            if(!visited.contains(cur)) {
                visited.add(cur);
                if(cur - nums[cur] >= 0)
                    q.offer(cur - nums[cur]);
                if(cur + nums[cur] < nums.length)
                    q.offer(cur + nums[cur]);
            }
        }
        return false;
    }
    }
