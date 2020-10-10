package mustdocodingquestions.arrays;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/chocolate-distribution-problem/
 * 
 * Given an array of n integers where each value represents the number of
 * chocolates in a packet. Each packet can have a variable number of chocolates.
 * There are m students, the task is to distribute chocolate packets such that:
 * 
 * Each student gets one packet. The difference between the number of chocolates
 * in the packet with maximum chocolates and packet with minimum chocolates
 * given to the students is minimum.
 * 
 * Input : arr[] = {7, 3, 2, 4, 9, 12, 56} , m = 3 Output: Minimum Difference is
 * 2 Explanation: We have seven packets of chocolates and we need to pick three
 * packets for 3 students If we pick 2, 3 and 4, we get the minimum difference
 * between maximum and minimum packet sizes.
 * 
 * Input : arr[] = {3, 4, 1, 9, 56, 7, 9, 12} , m = 5 Output: Minimum Difference
 * is 6 Explanation: The set goes like 3,4,7,9,9 and the output is 9-3 = 6
 * 
 * Input : arr[] = {12, 4, 7, 9, 2, 23, 25, 41, 30, 40, 28, 42, 30, 44, 48, 43,
 * 50} , m = 7 Output: Minimum Difference is 10 Explanation: We need to pick 7
 * packets. We pick 40, 41, 42, 44, 48, 43 and 50 to minimize difference between
 * maximum and minimum.
 * 
 */
public class Choclatedistribution {
    public static void main(String[] args){
        int[] ar = new int[]{5,12,3,1,15};
        int m = 3;
        checkResult(bruteForceApproach(ar,m),4,"Bruteforce Approach",ar, m);
        ar = new int[]{100,5,7,1,5,10,13};
        m =4;
        checkResult(bruteForceApproach(ar,m),5,"Bruteforce Approach",ar, m);
        ar = new int[]{100,5,7,1,5,10,13};
        m =5;
        checkResult(bruteForceApproach(ar,m),8,"Bruteforce Approach",ar, m);
        ar = new int[]{100,5,7,1,5,10,13};
        m =7;
        checkResult(bruteForceApproach(ar,m),99,"Bruteforce Approach",ar, m);
    }
    /**Time complexity: O(nlogn) */
    private static int bruteForceApproach(int[] choclatePacks, int m) {
        int[] in = Arrays.stream(choclatePacks).toArray();
        Arrays.sort(in);
        int minDiff = Integer.MAX_VALUE;
        for(int i=0,j=i+(m-1);i<in.length-(m-1);i++,j++){
            minDiff = Math.min(minDiff,in[j]-in[i]);
        }
        return minDiff;
    }

    private static void checkResult(int actual, int expected, String approach, int[] in, int m) {
        String testStatus = actual == expected ? "Passed" : "Failed";
        System.out.println(approach+" : Test with input "+Arrays.toString(in)+" and m:"+m+" "+testStatus);
        if(actual!=expected){
            System.out.println("Actual: "+actual);
            System.out.println("Expected: "+expected);
        }
    }

    
}
