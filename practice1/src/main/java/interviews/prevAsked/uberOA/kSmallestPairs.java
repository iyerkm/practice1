package main.java.interviews.prevAsked.uberOA;

public class kSmallestPairs {
}

/*
https://leetcode.com/discuss/interview-question/490610/Uber-or-OA

1. Given two integer arrays a and b, and an integer value d, your task is to find the comparator value between these arrays.
The comparator value is defined as the number of elements x ∈ a such that there are no elements y ∈ b where |x - y| ≤ d. In other words, it's the number of elements in a that are more than d away from any element of b.
Return the comparator value as an integer.
For eg. a = [2, 9] and b = [16, 13, 8], d = 3 should return 1.
n = 9
9 - 16 = 7 > 3
9 - 13 = 4 > 3
9 - 8 = 1 < 3
n = 2
2 - 16 = 14 > 3
2 - 13 = 11 > 3
2 - 8 = 6 > 3

 */