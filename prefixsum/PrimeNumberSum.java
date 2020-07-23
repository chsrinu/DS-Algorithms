package prefixsum;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Given an array of limits. For every limit, find the prime number which can be
 * written as the sum of the most consecutive primes smaller than or equal to
 * limit.
 * 
 * The maximum possible value of a limit is 10^4.
 */

public class PrimeNumberSum {
    public static void main(final String[] args) {
        final int[] ar = new int[] {200,1000};
        bruteForceApproach(ar);
        optimizedApproach_1(ar);
        deadEnd(ar);//You cannot get a solution with this approach .Just want you to
        //think before implementing a binarySearch in real interview. Never go for it 
        //if you are not sure on moving the mid element.
    }

    public static void bruteForceApproach(final int[] ar) {
        for (final int limit : ar) {
            final List<Integer> primes = new ArrayList<>();
            /**Optimization technique- don't need to start from beginning 
             * when you have already found the longest consecutive sum prime
             * number for the previous limit, when array of limits is in 
             * ascending order
             * ex:[10,20]- when you already know the longest till 10
             * you don't need to start again from 5 to know the longest
             * for 20 instead you can start from 11
             */
            int longest = 0,longest_consecutive_sum = 0;
            for(int i=2;i<=limit;i++){
                boolean isPrime = true;
                for(int p:primes){
                    if(i%p==0){
                        isPrime = false;
                        break;
                    }
                }
                if(isPrime){
                    primes.add(i);
                    /**
                     * Optimization needed: 
                     * Use prefix sum array to find the subArray
                     */
                    for(int m=0;m<primes.size()-1;m++){
                        int sum = 0;
                        for(int n=m;n<primes.size() && sum<i;n++){
                            sum+=primes.get(n);
                            //System.out.println(sum);
                            if(sum==i && n-m>longest){
                                longest = n-m;
                                longest_consecutive_sum = i;
                            }
                        }
                    }
                }
            if(i==limit){
                if(longest != 0)
                    System.out.println("MaxConsecutivePrimeSum under "+limit+" is "+longest_consecutive_sum);
                else
                    System.out.println("No such sum exists under "+limit);                      
            }
        }
    }
    }
    public static void optimizedApproach_1(int[] ar){
        Arrays.sort(ar);
        List<Integer> primes = new ArrayList<>();
        int previousLimit = 1;
        int longest = 0,longest_consecutive_sum = 0;
        for(int limit:ar){
            for(int i=previousLimit+1;i<=limit;i++){
                boolean isPrime = true;
                for(int prime:primes){
                    if(i%prime == 0)
                        isPrime = false;
                }
                if(isPrime)
                    primes.add(i);
            }
            /**
             * Optimization: Even though we know the max prime till the previous limit
             * we are calculating it again since the primes list is updated.
             * Ex:[20,30]
             * we know the max prime number till 20 is 17 but still we try to
             * find the sum from beginning for 30 too 
             *  
             * Now the problem statement can be revised as 
             * "Finding a MaxSize Subarray whose sum less than K and Prime"
             */
            for(int m=0;m<primes.size()-1;m++){
                int sum = primes.get(m);
                for(int n=m+1;n<primes.size() && sum<limit;n++){
                    sum+=primes.get(n);
                    //System.out.println(sum);
                    if(primes.contains(sum) && n-m>longest){
                        longest = n-m;
                        longest_consecutive_sum = sum;
                    }
                }
            }
            if(longest != 0)
                System.out.println("MaxConsecutivePrimeSum under "+limit+" is "+longest_consecutive_sum);
            else
                System.out.println("No such sum exists under "+limit);

            previousLimit = limit;
        }
    }

    /**
     * 
     * Binary search cannot be applied in this case to find the max consecutive sum
     * as we'll not be sure(no strict condition) to which side the mid position must be 
     * be moved when its not found. 
     */
    public static void deadEnd(int[] ar){
        Arrays.sort(ar);
        List<Integer> primes = new ArrayList<>();
        for(int i=2;i<=ar[ar.length-1];i++){
            boolean isPrime = true;
            for(int prime:primes){
                if(i%prime==0)
                    isPrime = false;
            }
            if(isPrime)
                primes.add(i);
        }
        int[] prefixSum = new int[primes.size()+1];
        for(int i=1;i<prefixSum.length;i++){
            prefixSum[i]=prefixSum[i-1]+primes.get(i-1);
        }
        for(int k:ar){
            int low=0,high=prefixSum.length;
            int longest_sum = 0,longest_size = 0;
            while(low<=high){
                int mid = (low+high)/2;
                int current_sum = 0,i = mid;
                for(;i<prefixSum.length && current_sum<=k;i++){
                    current_sum = prefixSum[i]-prefixSum[i-mid];
                    if(current_sum<=k){
                        if(primes.contains(current_sum)){
                            if(mid>=longest_size){
                                longest_size = mid;
                                longest_sum = current_sum;
                            }
                        }
                    }
                }
                //increase the subArray size since we have covered
                //everything under current size(mid)
                if(current_sum <= k)
                    low=mid+1;
                //we are beyond K before reaching end of array so go for a 
                //size less than current size(mid)
                else
                    high=mid-1;
            }
            if(longest_size>=2)
                System.out.println("MaxConsecutivePrimeSum under "+k+" is "+longest_sum);
            else
                System.out.println("No such sum exists under "+k);
        }
    }
}