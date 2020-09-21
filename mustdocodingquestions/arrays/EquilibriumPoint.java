package mustdocodingquestions.arrays;

import java.util.Arrays;

/**
 * https://practice.geeksforgeeks.org/problems/equilibrium-point/0
 * 
 * Given an array A of N positive numbers. The task is to find the position 
 * where equilibrium first occurs in the array. Equilibrium position in an array 
 * is a position such that the sum of elements before it is equal to the sum of 
 * elements after it.
 * 
 * Input:
2
1
1
5
1 3 5 2 2

Output:
1
3

Explanation:
Testcase 1: Since its the only element hence its the only equilibrium point.
Testcase 2: For second test case equilibrium point is at position 3 as elements 
below it (1+3) = elements after it (2+2).
 */

public class EquilibriumPoint {
    public static void main(String[] args){
        int[] input = new int[]{1,2,3,4,5};
        checkResult(bruteforceApproach(input),-1,"BruteforceApproach",input);
        input = new int[]{1};
        checkResult(bruteforceApproach(input),1,"BruteforceApproach",input);
        input = new int[]{5,8,2,3,10};
        checkResult(bruteforceApproach(input),3,"BruteforceApproach",input);
        input = new int[]{3,1,3};
        checkResult(bruteforceApproach(input),2,"BruteforceApproach",input);
        input = new int[]{1,2,3,4,6};
        checkResult(bruteforceApproach(input),4,"BruteforceApproach",input);   
    }
    public static int bruteforceApproach(int[] ar){
        if(ar.length==1)
            return 1;
        if(ar.length>2){
            int left = 0,right = 0;
            for(int i:ar){
                right+=i;
            }
            right-=ar[0];
            for(int i=1;i<ar.length-1;i++){
                left+=ar[i-1];
                right-=ar[i];
                if(left==right)
                    return i+1;
            }
        }
        return -1;
    }
    public static void checkResult(int actual, int expected, String approach, int[] input){
        String status = actual!=expected?"Failed":"Passed";
        System.out.println(approach+": Test with input "+Arrays.toString(input)+" "+status);
        if(actual!=expected){
            System.out.println("Actual: "+actual);
            System.out.println("Expected: "+expected);
        }
    }
    
}
