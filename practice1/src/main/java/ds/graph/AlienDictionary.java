package main.java.ds.graph;

import java.util.*;

import static main.java.common.Util.print;

public class AlienDictionary {
    public static void main(String[] args){
        String[] dict = new String[] {"wrt","wrf","er","ett","rftt"};
        print(alienOrder(dict));
    }

    private static String alienOrder(String[] dict) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] order = new int[26];
        buildDependencyGraph(graph,order,dict);
        String resultOrder = topologicalSort(graph,order);
        return resultOrder.length()==graph.size()?resultOrder:"";
    }

    private static String topologicalSort(Map<Character, Set<Character>> graph, int[] order) {
        Queue<Character> queue = new LinkedList<>();
        for(char c: graph.keySet()){
            if(order[c-'a']==0){
                queue.offer(c);
            }
        }
        StringBuilder sb=new StringBuilder();
        while(!queue.isEmpty()){
            char top = queue.remove();
            sb.append(top);

            for(char neighbor:graph.get(top)){
                order[neighbor-'a']--;
                if(order[neighbor-'a']==0){
                    queue.offer(neighbor);
                }
            }
        }
        return sb.toString();
    }

    private static void buildDependencyGraph(Map<Character, Set<Character>> graph, int[] order, String[] dict) {
        for(String word:dict){
            for(char c:word.toCharArray()){
                graph.put(c,new HashSet<>());
            }
        }
        for(int i=1;i< dict.length;i++){
            String first = dict[i-1];
            String second = dict[i];
            int length = Math.min(first.length(),second.length());
            for(int j=0;j<length;j++){
                char parent = first.charAt(j);
                char child = second.charAt(j);
                if(parent!=child){
                    if(!graph.get(parent).contains(child)){
                        graph.get(parent).add(child);
                        order[child-'a']++;
                    }
                    break;
                }
            }
        }
    }
}


/*
// There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
// Example 1:
// Given the following words in dictionary,
// [
//   "wrt",
//   "wrf",
//   "er",
//   "ett",
//   "rftt"
// ]
// The correct order is: "wertf".
// Example 2:
// Given the following words in dictionary,
// [
//   "z",
//   "x"
// ]
// The correct order is: "zx".
// Example 3:
// Given the following words in dictionary,
// [
//   "z",
//   "x",
//   "z"
// ]
// The order is invalid, so return "".
// Note:
// You may assume all letters are in lowercase.
// You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
// If the order is invalid, return an empty string.
// There may be multiple valid order of letters, return any one of them is fine.
 */