package main.java.interviews.year_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static main.java.common.Util.print;

public class UberOA2021 {

    public static void main(String[] args) {
        int[] meanInputs = new int[] {2,4,6,6,3};
        int[] peakInputs = new int[] {1,2,1,3,5,6,4};
        String numOfWays="xzxzx";
        int[] findNumOfTriangles = new int[] {1,2,3,3,3,2,4};
        print("numOfElementsEqualToMeanOfNeighbors: " + numOfElementsEqualToMeanOfNeighbors(meanInputs));
        print("numOfWays: " + numOfWays(numOfWays));
        print("findPeakElement: " + findPeakElement(peakInputs));
        print("findPeakElementBinary: " + findPeakElementBinary(peakInputs));
        print("findNumOfTriangles: " + findNumOfTriangles(findNumOfTriangles));
    }

    public static int numOfElementsEqualToMeanOfNeighbors(int[] nums){
        int prev=0;
        int next=0;
        int result=0;
        for (int i = 0; i < nums.length; i++) {
            prev = i==0?0:nums[i-1];
            next = i==nums.length-1? 0:  nums[i+1];
            print( "prev:" + prev + " current:" + nums[i] + " next:"+next);

            if (nums[i] == (prev+next)/2) {
                print("this is mean: " + nums[i]);
                result++;
            }
        }
        return result;
    }

    public static int findPeakElement(int[] nums) {
        /*
        Input: nums = [1,2,1,3,5,6,4]
        Output: 5
         */
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }

    public static int findPeakElementBinary(int[] nums) {
        int left = 0, right = nums.length - 1, mid = 0;
        while(left + 1 < right){
            mid = left + (right - left) / 2;
            if(nums[mid - 1] < nums[mid]){
                left = mid;
            }else{
                right = mid;
            }
        }
        return nums[left] > nums[right] ? left : right;
    }

    public static int numOfWays(String str) {
        int len = str.length();
        int count = 0;
        for (int i = 1; i <= len - 2; i++) {
            for (int j = i + 1; j <= len - 1; j++) {
                String ab = str.substring(0, j); // this is [0, i - 1] + [i, j - 1] = [0, j - 1];
                String bc = str.substring(i, len); // this [i, j - 1] + [j, len - 1] = [i, len - 1];
                String ca = str.substring(j, len) + str.substring(0, i); // this is [j, len - 1] + [0, i - 1]
                if (!(ab.equals(bc) || bc.equals(ca) || ca.equals(ab))) { // check if the three concatenation are the same, if not, add it to the count
                    count++;
                }
            }
        }
        return count;
    }

    public static int[][] rotate_without_extra_space(int[][] matrix) {
        int n = matrix.length;
        // transpose matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i == j || i + j == matrix.length - 1) {
                    //Diagonal
                } else {
                    int tmp = matrix[j][i];
                    matrix[j][i] = matrix[i][j];
                    matrix[i][j] = tmp;
                }
            }
        }
        // reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                if (i == j || i + j == matrix.length - 1) {
                    //Diagonal
                } else {
                    int tmp = matrix[i][j];
                    matrix[i][j] = matrix[i][n - j - 1];
                    matrix[i][n - j - 1] = tmp;
                }
            }
        }
        return matrix;
    }
    public static int findNumOfTriangles(int []arr)
    {
        // If number of elements are
        // less than 3, then no
        // triangle is possible
        int count=0;
        if (arr.length < 3)
            return 0;

        // first sort the array
        //Arrays.sort(arr);

        // loop for all 3
        // consecutive triplets
        for (int i = 0; i < arr.length - 2; i++)

            // If triplet satisfies
            // triangle condition, break
            if (arr[i] + arr[i + 1] > arr[i + 2])
                count++;

        return count;
    }

    /*
    Question 1:
        input1: ababab
        input2: cdcdcd
        output: acbdacbd

    Question 2:
        reverse the number and sum them up
        5000 --> 0005 --> 5000
        123 --> 321

    Question 3:
        Board of characters. Lke the brick game, let them fall.
        * means the shape
        . means empty space
        # means obstacle
        Count of the number of moves to have the shape reach the bottom
    Question 4:
        booleanDeque(int n, String[] operations)
        operations: [L,L, C2, L, C3]
        If L: set the earliest index containing zero to 1.
        If C2: Set the index 2 to zero
        Return the binary string
     */

}

