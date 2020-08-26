package arrays;

import java.util.Arrays;

/**
 * How do you find the missing number in a given integer array of 1 to 100?
 */

public class MissingNumberInArray {
    public static void main(String[] args){
        int[] in_1 = getInputArrayWithMissingNumber(10,10);
        findMissedNumber_indexApproach(in_1);
        findMissedNumber_overallSumApproach(in_1); 
        int[] in_2 = getInputArrayWithMissingNumber(6,10);
        findMissedNumber_indexApproach(in_2);
        findMissedNumber_overallSumApproach(in_2); 
        int[] in_3 = getInputArrayWithMissingNumber(2,3);
        findMissedNumber_indexApproach(in_3);
        findMissedNumber_overallSumApproach(in_3);
        int[] in_4 = getInputArrayWithMissingNumber(1,1);
        findMissedNumber_indexApproach(in_4);
        findMissedNumber_overallSumApproach(in_4);     
    }

    // Finding missed number based on array Index
    // Problem with this approach: Doesn't work for Unsorted elements  
    private static void findMissedNumber_indexApproach(int[] in) {
        int missingNumber = 0;
        for(int i=0;i<in.length;i++){
            if(in[i]!=i+1){
                missingNumber = i+1;
                break;
            }
        }
        if(missingNumber==0){
            missingNumber = in.length+1;
        }
        System.out.println("Missing number is "+missingNumber);
    }

     //Finding missing number based on Arithmatic Progression
     private static void findMissedNumber_overallSumApproach(int[] in) {
        int n = in.length+1;
        int sumOf_N_numbers = (n*(n+1))/2;
        int currentSum = 0;
        for(int i=0;i<n-1;i++){
            currentSum+=in[i];
        }
        System.out.println("Missing number is "+(sumOf_N_numbers-currentSum));
    }

    private static int[] getInputArrayWithMissingNumber(int numberToBeMissed,int size) {
        int[] in = new int[size-1];
        for(int i=0,j=1;i<in.length;i++,j++){
            if(j==numberToBeMissed){
                in[i]=++j;
            }else{
                in[i]=j;
            }
        }
        System.out.println("Input Array: "+Arrays.toString(in));
        return in;
    }
}