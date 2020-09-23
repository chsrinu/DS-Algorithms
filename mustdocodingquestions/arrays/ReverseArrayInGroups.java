package mustdocodingquestions.arrays;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/reverse-an-array-in-groups-of-given-size/
 * 
 * Given an array, reverse every sub-array formed by consecutive k elements.
 * 
 * 
 * Time complexity: O(n)
 */

public class ReverseArrayInGroups {
    public static void main(String[] args){
        int[] ar = new int[]{1,2,3,4,5,6};
        checkResult(bruteforceApproach(Arrays.stream(ar).toArray(),3),new int[]{3,2,1,6,5,4},"Bruteforce approach",ar,3);
        ar = new int[]{1,2,3,4,5,6,7};
        checkResult(bruteforceApproach(Arrays.stream(ar).toArray(),3),new int[]{3,2,1,6,5,4,7},"Bruteforce approach",ar,3);
        ar = new int[]{1,2,3,4,5,6,7,8};
        checkResult(bruteforceApproach(Arrays.stream(ar).toArray(),3),new int[]{3,2,1,6,5,4,8,7},"Bruteforce approach",ar,3);
        ar = new int[]{1,2,3,4,5,6,7,8,9};
        checkResult(bruteforceApproach(Arrays.stream(ar).toArray(),3),new int[]{3,2,1,6,5,4,9,8,7},"Bruteforce approach",ar,3);
        ar = new int[]{1,2,3};
        checkResult(bruteforceApproach(Arrays.stream(ar).toArray(),1),new int[]{1,2,3},"Bruteforce approach",ar,1);
        ar = new int[]{1,2,3,4,5,6,7};
        checkResult(bruteforceApproach(Arrays.stream(ar).toArray(),2),new int[]{2,1,4,3,6,5,7},"Bruteforce approach",ar,2);
        ar = new int[]{1,2,3,4,5,6,7,8};
        checkResult(bruteforceApproach(Arrays.stream(ar).toArray(),10),new int[]{8,7,6,5,4,3,2,1},"Bruteforce approach",ar,10);

    }
    public static int[] bruteforceApproach(int[] ar,int k){
        for(int s=0;s<ar.length;s+=k){
            for(int i=s,j=s+k-1;i<j;){
                if(j<ar.length){
                    swap(i,j,ar);
                    i++;j--;
                }else{
                    j--;
                }
            }
        }
        return ar;
    }
    public static void swap(int i, int j, int[] ar){
        int src = ar[i];
        ar[i]=ar[j];
        ar[j]=src;
    }
    public static void checkResult(int[] actual, int[] expected,String approach,int[] input,int k){
        boolean failed= false;
        for(int i=0;i<actual.length;i++){
            if(actual[i]!=expected[i]){
                failed = true;
                break;
            }
        }
        String status = failed?"Failed":"Passed";
        System.out.println(approach+": Test with input "+Arrays.toString(input)+" and groupSize:"+k+" "+status);
        if(failed){
            System.out.println("Actual: "+Arrays.toString(actual));
            System.out.println("Expected: "+Arrays.toString(expected));
        }
    }
}
