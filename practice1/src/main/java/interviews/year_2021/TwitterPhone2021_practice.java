package interviews.year_2021;

import java.util.HashMap;
import java.util.Map;
import static main.java.common.Util.print;

public class TwitterPhone2021_practice {
    public static void main(String[] args){
        ConstantTimeDataStructure ds = new ConstantTimeDataStructure();
        ds.add(1);
        ds.add(2);
        ds.add(3);
        ds.remove(2);
        ds.remove(6);
        ds.add(5);
        ds.add(6);
        ds.remove(6);
        print(ds.getLast());
        print(ds.printAll());
    }

}

class ConstantTimeDataStructure{
    Node head;
    Node tail;
    Map<Integer,Node> map = new HashMap<>();

    public boolean add(int value){
        if(!map.containsKey(value)){
            Node newNode = new Node(value);
            map.put(value,newNode);
            addNode(newNode);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(int value){
        if(map.containsKey(value)){
            Node node = map.get(value);
            map.remove(value);
            removeNode(node);
            return true;
        } else {
            return false;
        }

    }

    public int getLast(){
        return map.getOrDefault(tail.val,new Node(0)).val;
    }

    public void addNode(Node node){
        if(tail!=null){
            tail.next=node;
        }
        node.prev=tail;
        node.next=null;
        tail=node;

        if(head==null){
            head=tail;
        }
    }
    public void removeNode(Node node){
        if(node.prev!=null){
            node.prev.next=node.next;
        } else {
            head=node.next;
        }
        if(node.next!=null){
            node.next.prev=node.prev;
        } else {
            tail=node.prev;
        }
    }
    public String printAll(){
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while(current!=null){
            sb.append(current.val + ",");
            current=current.next;
        }
        return sb.toString();
    }

}

class Node{
    public Node prev;
    public Node next;
    public int val;
    public Node(int value){
        this.val=value;
    }
}