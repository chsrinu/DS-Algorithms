package mustdocodingquestions.arrays;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/convert-array-into-zig-zag-fashion/
 * 
 * Given an array of DISTINCT elements, rearrange the elements of array in
 * zig-zag fashion in O(n) time. The converted array should be in form a < b > c
 * < d > e < f. Example:
 * 
 * Input: arr[] = {4, 3, 7, 8, 6, 2, 1} Output: arr[] = {3, 7, 4, 8, 2, 6, 1}
 * 
 * Input: arr[] = {1, 4, 3, 2} Output: arr[] = {1, 4, 2, 3}
 * 
 * 
 */
public class ArrayInZigZagFashion {
    public static void main(String[] args){
        int[] ar = new int[]{4,1,6,5};
        checkResult(bruteforceApproach(ar),new int[]{1,6,4,5},"Bruteforce approach",ar);
        ar = new int[]{4, 3, 7, 8, 6, 2, 1};
        checkResult(bruteforceApproach(ar),new int[]{3,7,4,8,2,6,1},"Bruteforce approach",ar);

    }
    /**Time complexity: O(n) */
    private static int[] bruteforceApproach(int[] ar1) {
        int[] ar = Arrays.stream(ar1).toArray();
        for(int i=0;i<ar.length-1;i++){
            if(i%2==0){
                if(ar[i]<ar[i+1]){
                    continue;
                }else{
                    swap(i,i+1,ar);
                }
            }else{
                if(ar[i]>ar[i+1])
                    continue;
                else
                    swap(i,i+1,ar);
            }
        }
        return ar;
    }

    public static void swap(int i, int j,int[] ar){
        int temp =ar[i];
        ar[i]=ar[j];
        ar[j]=temp;
    }

    private static void checkResult(int[] actual, int[] expected, String approach, int[] in) {
        String status = Arrays.toString(actual).equals(Arrays.toString(expected))?"Passed":"Failed";
        System.out.println(approach+": Test with input "+Arrays.toString(in)+" "+status);
        if(!Arrays.toString(actual).equals(Arrays.toString(expected))){
            System.out.println("Actual: "+Arrays.toString(actual));
            System.out.println("Expected: "+Arrays.toString(expected));
        }
    }
}
