package main.java.interviews.year_2021;

import java.util.*;

import static main.java.common.Util.print;

public class IntuitPhone2021 {
    public static void main(String[] args){
        int[] tempInput = new int[] {73, 74, 75, 71, 69, 72, 76, 73};
        print(Arrays.toString(dailyTemperatures(tempInput)));
    }

    private static int[] dailyTemperatures(int[] tempInput) {
        int[] result = new int[tempInput.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = tempInput.length-1;i>=0 ; i--) {
            while(!stack.isEmpty() && tempInput[i] >= tempInput[stack.peek()]){
                stack.pop();
            }
            result[i]=stack.isEmpty()?0:stack.peek()-i;
            stack.push(i);
        }
        return result;
    }
    //stack:        2,6,7
    //result:       1,1,4,2,1,1,0,0
}
