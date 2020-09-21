package mustdocodingquestions.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * https://practice.geeksforgeeks.org/problems/leaders-in-an-array/0
 * 
 * Given an array of positive integers. Your task is to find the leaders in the array.
Note: An element of array is leader if it is greater than or equal to all the elements 
to its right side. Also, the rightmost element is always a leader. 

Example:
Input:
3
6
16 17 4 3 5 2
5
1 2 3 4 0
5
7 4 5 7 3
Output:
17 5 2
4 0
7 7 3

Explanation:
Testcase 3: All elements on the right of 7 (at index 0) are smaller than or equal to 7.
 Also, all the elements of right side of 7 (at index 3) are smaller than 7. 
 And, the last element 3 is itself a leader since no elements are on its right.

 Time complexity of solution: O(n)
 BruteForce complexity would be: O(n^2)
 */

public class LeadersInArray {
    public static void main(String[] args){
        int[] ar = new int[]{16,17,4,3,5,2};
        checkTest(optimizedApproach(ar),Arrays.asList(new Integer[]{17,5,2}),"Optimized Approach",ar);
        ar = new int[]{1,2,3,8};
        checkTest(optimizedApproach(ar),Arrays.asList(new Integer[]{8}),"Optimized Approach",ar);
        ar = new int[]{5,4,3,2,1};
        checkTest(optimizedApproach(ar),Arrays.asList(new Integer[]{5,4,3,2,1}),"Optimized Approach",ar);
        ar = new int[]{4,5};
        checkTest(optimizedApproach(ar),Arrays.asList(new Integer[]{5}),"Optimized Approach",ar);
        ar = new int[]{7,4,5,7,3};
        checkTest(optimizedApproach(ar),Arrays.asList(new Integer[]{7,7,3}),"Optimized Approach",ar);

    }
    public static List<Integer> optimizedApproach(int[] ar){
        List<Integer> leaders = new ArrayList<>();
        int biggest = ar[ar.length-1];
        leaders.add(biggest);
        for(int i=ar.length-2;i>=0;i--){
            if(ar[i]>=biggest){
                biggest = ar[i];
                leaders.add(biggest);
            }
        }
        return leaders;
    }
    /**Result validation */
    public static void checkTest(List<Integer> actual,List<Integer> expected,String approach, int[] input){
        boolean isFailed = false;
        for(Integer i=0,j=actual.size()-1;i<expected.size();i++,j--){
            if(actual.get(j)!=expected.get(i)){
                isFailed = true;
                break;
            }
        }
        String status = isFailed?"Failed":"Passed";
        System.out.println(approach+": Test with input "+Arrays.toString(input)+" "+status);
        if(isFailed){
            System.out.println("Actual "+actual.toString());
            System.out.println("Expected "+expected.toString());
        }
    }
    
}
