package main.java.ds.design;

import java.util.HashMap;
import java.util.Map;

import static main.java.common.Util.print;

//https://leetcode.com/problems/lru-cache/

public class LRUCache {
    int capacity;
    Node head=null;
    Node tail=null;
    Map<Integer,Node> map;
    public LRUCache(int capacity){
        this.capacity = capacity;
        map=new HashMap<>();
        print("null");
    }

    public int get(int key){
        if(map.containsKey(key)){
            Node node = map.get(key);
            remove(node);
            add(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int val){
        Node node=null;
        if(map.containsKey(key)){
            node = map.get(key);
            node.val=val;
            remove(node);
            add(node);
        } else {
            if(map.size() >= this.capacity){
                map.remove(head.key);
                remove(head);
            }
            Node newNode = new Node(key,val);
            add(newNode);
            map.put(key,newNode);
        }
        print("null");
    }


    // head: 1 <-->2 <--> 3 <-->4 <--> 5 --> null tail
    public void remove(Node node){
        if(node.prev!=null){
            node.prev.next = node.next;
        } else {
            //node is head since node.prev is null
            head = node.next;
        }
        if(node.next!=null){
            node.next.prev = node.prev;
        } else {
            //node is tail since node.next is null
            tail = node.prev;
        }
    }

    public void add(Node node){
        if(tail!=null){
            tail.next = node;
        }
        node.prev=tail;
        node.next = null;
        tail = node;
        if(head==null){
            head=tail;
        }
    }

    public static void main(String[] args){
//        ["LRUCache","put","put","get","put","get","put","get","get","get"]
//        [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        // ouput expected: [null,null,null,1,null,-1,null,-1,3,4]

        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        print(lruCache.get(1));
        lruCache.put(3,3);
        print(lruCache.get(2));
        lruCache.put(4,4);
        print(lruCache.get(1));
        print(lruCache.get(3));
        print(lruCache.get(4));
    }

}
class Node{
    Node prev;
    Node next;
    int key;
    int val;
    public Node(int key,int val){
        this.key = key;
        this.val=val;
    }
}

/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
Follow up:
Could you do get and put in O(1) time complexity?



Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4


Constraints:

1 <= capacity <= 3000
0 <= key <= 3000
0 <= value <= 104
At most 3 * 104 calls will be made to get and put.

 */
