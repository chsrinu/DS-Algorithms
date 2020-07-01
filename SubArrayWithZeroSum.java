import java.util.HashMap;
import java.util.Map;

public class SubArrayWithZeroSum {
    public static void main(String[] args){
        SubArrayWithZeroSum zeroSumFinder = new SubArrayWithZeroSum();
        //positive case
        int[] in = new int[]{0,2,-3,4};
        zeroSumFinder.bruteForceApproach(in);
        zeroSumFinder.prefixSumApproach(in);
        in = new int[]{1,-3,3,4};
        zeroSumFinder.bruteForceApproach(in);
        zeroSumFinder.prefixSumApproach(in);
        in = new int[]{1,2,3,-6};
        zeroSumFinder.bruteForceApproach(in);
        zeroSumFinder.prefixSumApproach(in);
        //negative case
        in = new int[]{1,2,3,4};
        zeroSumFinder.bruteForceApproach(in);
        zeroSumFinder.prefixSumApproach(in);
    }
    /**
     * Time Complexity:
     * Best case: Omega(1)
     * Average case: Theta(n)
     * Worst case: O(n)
     * 
     * Space Complexity:
     * Best case: Omega(1)
     * Average case: Theta(n)
     * Worst case: O(n)
     */
    private void prefixSumApproach(int[] in) {
        Map<Integer,Integer> hm = new HashMap<>();
        hm.put(0,0);
        if(in[0]==0){
            printWithIndices(0, 0);
        }else{
            hm.put(in[0],1);
            for(int i=1;i<in.length;i++){
                in[i]+=in[i-1];
                if(hm.containsKey(in[i])){
                    printWithIndices(hm.get(in[i]), i);
                    return ;
                }
                hm.put(in[i],i+1);
            }
            System.out.println("SubArray with zero sum does not exists");
        }
    }

    /**
     * Time complexity: 
     * Best case : Ω(n^2) 
     * Average case : θ(n^2) 
     * Worst case : O(n^2)
     */
    private void bruteForceApproach(int[] ar) {
        for(int i=0;i<ar.length;i++){
            int sum = 0;
            for(int j=i;j<ar.length ;j++){
                sum+=ar[j];
                if(sum==0){
                    printWithIndices(i, j);
                    return ;
                }
            }            
        }
        System.out.println("SubArray with zero sum does not exists");
    }

    private void printWithIndices(int start, int end){
        System.out.println("SubArray with zero sum exists, starts@"+start+", ends@"+end);
    }

    
}
/**
 * The problem can be extended to return the maxLength subArray if multiple 
 * arrays exists with zero sum.
 * 
 * 
 */