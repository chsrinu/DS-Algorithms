package prefixsum;
/**
 * Longest Span with same Sum in two Binary arrays
 */

public class LongestSpanWithSameSum {
    public static void main(String[] args){
        int[] ar1=new int[]{0,1};
        int[] ar2 = new int[]{1,0};
        bruteForceApproach(ar1,ar2);
        optimizedApproach1(ar1,ar2);
        optimizedApproach2(ar1,ar2);
    }

    /**
     * Best case = Omega(n^2)
     * Average case = Theta(n^2)
     * Worst case = O(n^2)
     */
    public static void bruteForceApproach(int[] ar1,int[] ar2){
        int maxSpan=0;
        for(int i=0;i<ar1.length;i++){
            int currentSpan = 0;
            int sum1=0,sum2=0;
            /**We are calculating the sum again and again
             * 
             * If you know the sum from i to n then why do you need to 
             * calculate again from i+1 to n, you can simply save the sum and subtract the 
             * ith sum from the nth sum to get i+1th sum. You can do this using 
             * a prefix sum array.
             */
            for(int j=i;j<ar1.length;j++){
                sum1+=ar1[j];
                sum2+=ar2[j];
                currentSpan = j-i+1;
                if(sum1==sum2 && maxSpan<currentSpan){
                    maxSpan = currentSpan;
                }
            }
        }
        System.out.println(maxSpan);
    }
    /**Prefix sum array with Linear search of sub array size approach */
    public static void optimizedApproach1(int[] ar1, int[] ar2){
        int maxSpan = 0;
        int[] prefixSum1 = new int[ar1.length+1];
        int[] prefixSum2 = new int[ar2.length+1];
        /**
         * This seems to be better than the 
         * Bruteforce approach which we have written earlier as we are saving the 
         * sum in a prefix sum array instead of calculating it again and again.
         * But ideally the worstCase timeComplexity for both the approaches remains
         * O(n^2). This can further be improved by searching in a Binary fashion instead of
         * linearly over the prefixSum array as it would always be in Strictly increasing order
         * 
         * 
         */
        for(int i=1;i<prefixSum1.length;i++){
            prefixSum1[i]=prefixSum1[i-1]+ar1[i-1];
            prefixSum2[i]=prefixSum2[i-1]+ar2[i-1];                            
        }
        int currentSpan=ar1.length;
        while(currentSpan>0 && maxSpan == 0){
            for(int i=currentSpan,j=0;i<prefixSum1.length && maxSpan == 0;i++,j++){
                int sum1=prefixSum1[i]-prefixSum1[j];
                int sum2=prefixSum2[i]-prefixSum2[j];
                if(sum1==sum2)
                    maxSpan = currentSpan;
            }
            currentSpan--;
        }
        System.out.println(maxSpan);
    }
    /**Prefix sum array with Binary search of sub array size approach 
     * The worst case time complexity O(nlogn) is little better than the earlier
     * approaches but this doesn't give us the solution in all the cases,
     * since there is no strict condition to move the mid element. So one cannot apply binary 
     * search to this problem
     * Eg: fails for input ar=[0,1] ar2=[1,0]
     * actual:0
     * expected:2
    */

    public static void optimizedApproach2(int[] ar1, int[] ar2){
        int maxSpan = 0;
        int[] prefixSum1 = new int[ar1.length+1];
        int[] prefixSum2 = new int[ar2.length+1];
        for(int i=1;i<prefixSum1.length;i++){
            prefixSum1[i]=prefixSum1[i-1]+ar1[i-1];
            prefixSum2[i]=prefixSum2[i-1]+ar2[i-1];                            
        }
        int low=0,high=prefixSum1.length;
        while(low<=high){
            System.out.println(low+" "+high);
            int mid = (low+high)/2;
            boolean spanFound = false;
            for(int i= mid; i<prefixSum1.length;i++){
                int sum1 = prefixSum1[i]-prefixSum1[i-mid];
                int sum2 = prefixSum2[i]-prefixSum2[i-mid];
                if(sum1==sum2){
                    spanFound = true;
                    break;
                }   
            }
            if(spanFound){
                low=mid+1;
                if(mid>maxSpan)
                    maxSpan=mid;
            }else
                high=mid-1;
        }
        System.out.println(maxSpan);
    }

    public static void optimizedApproach3(int[] ar1, int[] ar2){
    
    }
}