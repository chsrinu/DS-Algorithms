package mustdocodingquestions.arrays;

import java.util.Arrays;

/*
https://www.geeksforgeeks.org/find-the-element-before-which-all-the-elements-are-smaller-than-it-and-after-which-all-are-greater-than-it/

Given an array, find an element before which all elements are smaller than it, 
and after which all are greater than it. Return the index of the element if there is 
such an element, otherwise, return -1.
Examples:
 

    Input: arr[] = {5, 1, 4, 3, 6, 8, 10, 7, 9}; 
    Output: 4 
    Explanation: All elements on left of arr[4] are smaller than it 
    and all elements on right are greater.
    Input: arr[] = {5, 1, 4, 4}; 
    Output: -1 
    Explanation : No such index exits.


*/
public class BalancingElementInArray {

    public static void main(String[] args){
        int[] ar = new int[]{4,2,5,7};
        checkResult(optimizedApproach_1(ar),2,"optimizedApproach_1",ar);
        checkResult(optimizedApproach_2(ar),2,"optimizedApproach_2",ar);

        ar = new int[]{19,18,20,15};
        checkResult(optimizedApproach_1(ar),-1,"optimizedApproach_1",ar);
        checkResult(optimizedApproach_2(ar),-1,"optimizedApproach_2",ar);

        ar = new int[]{5, 1, 4, 3, 6, 8, 10, 7, 9};
        checkResult(optimizedApproach_1(ar),4,"optimizedApproach_1",ar);
        checkResult(optimizedApproach_2(ar),4,"optimizedApproach_2",ar);

    }

    private static void checkResult(int actual, int expected, String approach, int[] in) {
        String status = actual == expected?"Passed":"Failed";
        System.out.println(approach+": Test with input "+Arrays.toString(in)+" "+status);
        if(actual!=expected){
            System.out.println("Actual: "+actual);
            System.out.println("Expected: "+expected);
        }
    }
    /**Time complexity: O(2n)<=>O(n)
     * Space complexity: O(2n)<=>O(n)
     */
    private static int optimizedApproach_1(int[] ar) {
        int[] leftMax = new int[ar.length];
        int[] rightMin = new int[ar.length];
        leftMax[0] = ar[0];
        rightMin[rightMin.length-1] = ar[ar.length-1];
        for(int i=1,j=ar.length-2;i<ar.length;i++,j--){
            leftMax[i]= Math.max(leftMax[i-1],ar[i]);
            rightMin[j]=Math.min(rightMin[j+1],ar[j]);
        }

        for(int i=1;i<ar.length-1;i++){
            if(ar[i]>=leftMax[i] && ar[i]<=rightMin[i]){
                return i;
            }
        }
        return -1;
    }
    
     /**Time complexity: O(2n)<=>O(n)
     * Space complexity: O(n)
     */
    private static int optimizedApproach_2(int[] ar) {
        
        int[] rightMin = new int[ar.length];
        int leftMax = ar[0];
        rightMin[rightMin.length-1] = ar[ar.length-1];
        for(int i=1,j=ar.length-2;i<ar.length;i++,j--){
            rightMin[j]=Math.min(rightMin[j+1],ar[j]);
        }

        for(int i=1;i<ar.length-1;i++){
            if(ar[i]>=leftMax && ar[i]<=rightMin[i]){
                return i;
            }
            leftMax = Math.max(ar[i],leftMax);
        }
        return -1;
    }
}
