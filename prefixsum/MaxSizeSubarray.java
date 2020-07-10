package prefixsum;

/**
 Given an array of n positive integers and a positive integer k, 
 the task is to find the maximum subarray size such that all subarrays of 
 that size have sum of elements less than k.
 */

public class MaxSizeSubarray{
    public static void main(String[] args){
        //Negative case
        int[] ar = new int[]{3,1,4,5,2};
        int k=5;
        getMaxSize_bruteForce(ar,k);
        getMaxSize_binarySearch(ar, k);
        //Postive case
        ar = new int[]{1,2,0,2,1};
        getMaxSize_bruteForce(ar,k);
        getMaxSize_binarySearch(ar, k);
        ar = new int[]{1,2,1,1,1};
        getMaxSize_bruteForce(ar,100);
        getMaxSize_binarySearch(ar, 100);
    }
/**
 * Check sample subarrays of size 5,4,..1 
 * 1)Check Subarray with 5 elements - If sum<k check all the subArrays with size 5
 * 2)Check subArray with 4 elements - If sum<k check all the subArrays with size 4
 * 3)Check subarray with 3 elements - If sum<k check all the subArrays with size 3
 * Time complexity O(n^2+n^2)=O(n^2) 
 * Cons:
 *  1)Calculating the subArray sum twice for the sample we picked
 *      i)We will be calculating to pick the size
 *      ii)Will check again while checking all the arrays of picked size in step1
 * 
 *  Optimization: Sample collection can be done in O(n) using prefix sum array but still the 
 *  time complexity remains same  O(n+n^2)=O(n^2)
 * 
 * 
 *  2)Calculating the same sum multiple times i.e
 *      for an array of size 5 we will be calculating sum of 0,1,2,3,4
 *      again for size 4 we will start again from 1,2,3,4
 *      again for size 3 we will start from 2,3,4
 *      but this can be obtained from previous sum 
 *      
 *  Optimization: How to optimize the 2nd step ?
 */
    public static void getMaxSize_bruteForce(int[] ar,int k){
        boolean found = false;

        for(int i=0;i<ar.length;i++){
            int sum=0;
            for(int j=i;j<ar.length;j++){
                sum+=ar[j];
                if(sum>=k)
                    break;
            }
            if(sum<k){
                    if(areAllSubArrayWithNSizeSatisfy(i,ar,k)){
                        System.out.println("Max subArray Size"+(ar.length-i));
                        found = true;break;
                    }
            }
        }
        if(!found)
            System.out.println("Such subArray does not exists");   
    }

    private static boolean areAllSubArrayWithNSizeSatisfy(int N, int[] ar,int k) {
        boolean found = true;
        for(int i=0;i<=ar.length-N && found;i++){
            int sum = 0;
            for(int j=i;j<(i+N);j++){
                sum+=ar[j];
            }
            if(sum>=k)
                found=false;
        }
        return found;
    }
    /**
     * Time complexity:
     * Best case : Omega(nlogn)
     * Average case : Theta(nlogn)
     * worst case : O(nlogn)
     * 
     * logn - to find the sample array size
     * n -  we would be going through all the elements in 
     *      the array of size n when subArray size is 1
     * So Time Complexity: O(n*logn)
     */
    public static void getMaxSize_binarySearch(int[] ar, int k){
        int[] prefixSum = new int[ar.length+1];
        for(int i=0;i<ar.length;i++){
            prefixSum[i+1]=prefixSum[i]+ar[i];
        }
        binarySearchForSampleSize(prefixSum,1,ar.length,k);
    }

    private static void binarySearchForSampleSize(int[] prefixSum, int low, int high,int maxSum) {
        int maxSizePossible = 0;
        while(low<=high){
            int mid = (low+high)/2;
            int i=mid;
            for(;i<prefixSum.length;i++){
                int sum = prefixSum[i]-prefixSum[i-mid];
                if(sum>=maxSum)
                    break;    
            }
            if(i==prefixSum.length){
                maxSizePossible = mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        System.out.println("All subArrays of size:"+maxSizePossible+", will have a sum<"+maxSum);
    }
}
