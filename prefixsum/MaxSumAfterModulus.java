package prefixsum;

import java.util.TreeSet;

/**
 * Given an array of n elements and an integer m. The task is to find the
 * maximum value of the sum of its subarray modulo m i.e find the sum of each
 * subarray mod m and print the maximum value of this modulo operation.
 */
public class MaxSumAfterModulus {
    public static void main(String[] args) {
        bruteForceApproach(new int[]{2,-1}, 6);
        optimizedApproach1(new int[]{2,-1}, 6);
        optimizedApproach2(new int[]{2,-1}, 6);
    }

    /**
     * Best case = Omega(n^2)
     * Average case = Theta(n^2)
     * Worst case = O(n^2)
     */
    public static void bruteForceApproach(int[] ar1,int m){
        int maxSum=0;
        for(int i=0;i<ar1.length;i++){
            int currentSum = 0;
            /**We are calculating the sum again and again
             * 
             * If you know the sum from i to n then why do you need to 
             * calculate again from i+1 to n, you can simply save the sum and subtract the 
             * ith sum from the nth sum to get i+1th sum. You can do this using 
             * a prefix sum array.
             */
            for(int j=i;j<ar1.length;j++){
                currentSum+=ar1[j];
                if((m+currentSum)%m>maxSum)
                    maxSum=(m+currentSum)%m;
            }
        }
        System.out.println(maxSum);
    }
    public static void optimizedApproach1(int[] ar,int m){
        int[] prefixSum = new int[ar.length];
        int maxSum = 0;
        prefixSum[0]=ar[0];
        for(int i=1;i<prefixSum.length;i++){
            prefixSum[i]=prefixSum[i-1]+ar[i-1];
        }
        for(int i=0;i<prefixSum.length;i++){
            int currentSum = 0;
            for(int j=i;j<ar.length;j++){
                currentSum+=ar[j];
                if((currentSum+m)%m>maxSum)
                    maxSum=(m+currentSum)%m;
            }
        }
        System.out.println(maxSum);
    }
    /**
     * Using a greedy approach store the result after processing
     * each and every value. In this way you don't need to go through all the 
     * elements before i to know the subarray start.
     * Time complexity: O(n)
     */
    public static void optimizedApproach2(int[] ar, int m){
        int result=Integer.MIN_VALUE;
        int prefixSum = ar[0];
        TreeSet<Integer> tree = new TreeSet<>();
        tree.add(ar[0]);
        for(int i=1;i<ar.length;i++){
            prefixSum+=ar[i];
            int currentMax = prefixSum%m;
            Integer closesthigh = tree.higher(currentMax);
            if(closesthigh!=null)
                result = Math.max((currentMax-closesthigh+m)%m,result);
            else
                result = Math.max(currentMax,result);
            tree.add(currentMax);
        }
        System.out.println(result);

    }
}