import java.util.Arrays;
/**
 * Find the maxSum that can be obtained from given array
 */
public class MaxSumSubArray {
    public static void main(String[] args){
        MaxSumSubArray maxSumFinder = new MaxSumSubArray();
        maxSumFinder.maxSum_BruteForceApproach(new int[]{4,-1,-3,-2});
        maxSumFinder.maxSum_BruteForceApproach(new int[]{-1,-2,4,-3});
        maxSumFinder.maxSum_BruteForceApproach(new int[]{1,2,-3,4,5,-8});

        maxSumFinder.maxSum_SlidingWindowApproach(new int[]{4,-1,-3,-2});
        maxSumFinder.maxSum_SlidingWindowApproach(new int[]{-1,-2,4,-3});
        maxSumFinder.maxSum_SlidingWindowApproach(new int[]{1,2,-3,4,5,-8});

    }
    /**
     * Time complexity analysis:
     * We start at i and find (1 to n sized subarrys) using j
     * 
     * so @i=0 the inner loop(j) will run n times(0->n-1)
     * @i=1 the inner loop(j) will run n-1 times(1->n-1)
     * @i=2 the inner loop(j) will run n-2 times(2->n-1)
     * 
     * Total time complexity would be n+(n-1)+(n-2)+(n-3)+....+(n-(n-1))
     * which would be n+(n-1)+(n-2)+......+1 which can be considered as
     * sum to n terms = n(n+1)/2.
     * 
     * Best case : Ω(n^2) -> [4,-1,-3,-2]
     * Average case : θ(n^2) -> [-1,-2,4,-3]
     * Worst case : O(n^2) -> [1,2,-3,4,5,-8]
     */
    public void maxSum_BruteForceApproach(int[] ar){
        int max=Integer.MIN_VALUE;
        int count = 0,n=ar.length;;
        for(int i=0;i<n;i++){
            int sum=0;
            for(int j=i;j<n;j++,count++){
                sum+=ar[j];
                if(sum>max)
                    max=sum;    
            }
        }
        System.out.println("Input array: "+Arrays.toString(ar) +", MaxSum:"+max);
        System.out.println("Time Complexity evalution: Brute force approach");
        System.out.println("Expected time complexity for array of size "+
        n+" is "+(n*(n+1))/2);
        System.out.println("Actual time complexity:"+count);
    }
    /**
     * Best case : Ω(n) -> [4,-1,-3,-2]
     * Average case : θ(n) -> [-1,-2,4,-3]
     * Worst case : O(n) -> [1,2,-3,4,5,-8]
     */
    public void maxSum_SlidingWindowApproach(int[] ar){
        int sum = 0,count=0,n=ar.length,max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            int tempSum = sum+ar[i];
            /**
            When tempSum is negative we should not be carry forwarding the
            negative sum as it would reduce the overall sum. For example
            ar = [-1,0] if we don't leave the subArray starting with -1 then
            we would end up in maxSum of -1 which is not expected.
            */
            if(tempSum>=0){
                sum=tempSum;
                if(sum>max)
                    max=sum;    
            }else
                sum=0;
            count++;
        }
        System.out.println("Input array: "+Arrays.toString(ar) +", MaxSum:"+max);
        System.out.println("Time Complexity evalution: Sliding window approach");
        System.out.println("Expected time complexity for array of size "+n+" is "+n);
        System.out.println("Actual time complexity:"+count);
    }
}

/**
Input array: [4, -1, -3, -2], MaxSum:4
Time Complexity evalution: Brute force approach
Expected time complexity for array of size 4 is 10
Actual time complexity:10

Input array: [-1, -2, 4, -3], MaxSum:4
Time Complexity evalution: Brute force approach
Expected time complexity for array of size 4 is 10
Actual time complexity:10

Input array: [1, 2, -3, 4, 5, -8], MaxSum:9
Time Complexity evalution: Brute force approach
Expected time complexity for array of size 6 is 21
Actual time complexity:21


Input array: [4, -1, -3, -2], MaxSum:4
Time Complexity evalution: Sliding window approach
Expected time complexity for array of size 4 is 4
Actual time complexity:4

Input array: [-1, -2, 4, -3], MaxSum:4
Time Complexity evalution: Sliding window approach
Expected time complexity for array of size 4 is 4
Actual time complexity:4

Input array: [1, 2, -3, 4, 5, -8], MaxSum:9
Time Complexity evalution: Sliding window approach
Expected time complexity for array of size 6 is 6
Actual time complexity:6
 * 
 */