package ds.stream;

import java.util.Comparator;
import java.util.PriorityQueue;
import static main.java.common.Util.print;

public class MedianInADataStream {
    public static void main(String[] args){
        //O(log(N)) to insert
        //
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.add(1);
        medianFinder.add(2);
        medianFinder.add(3);
        medianFinder.add(4);
        medianFinder.add(5);
        medianFinder.add(6);
        medianFinder.add(7);
        print(medianFinder.getMedian());
    }

}

class MedianFinder{
    PriorityQueue<Integer> min;
    PriorityQueue<Integer> max;
    public MedianFinder(){
        min=new PriorityQueue<>();
        max=new PriorityQueue<>(Comparator.reverseOrder());
    }
    public void add(int value){
        //1,2,3,4,5
        min.offer(value);
        max.offer(min.poll());

        if(min.size() < max.size()){
            min.offer(max.poll());
        }
    }

    public double getMedian(){
        if(min.size()>max.size()){
            return min.peek();
        } else {
            return (min.peek() + max.peek())/2.0;
        }
    }
}