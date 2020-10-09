package mustdocodingquestions.arrays;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 
 * https://www.geeksforgeeks.org/find-pythagorean-triplet-in-an-unsorted-array/
  Given an array of integers, write a function that returns true if there is a triplet
  (a, b, c) that satisfies a2 + b2 = c2.

Example:

    Input: arr[] = {3, 1, 4, 6, 5}
    Output: True
    There is a Pythagorean triplet (3, 4, 5).

    Input: arr[] = {10, 4, 6, 12, 5}
    Output: False
    There is no Pythagorean triplet. 
 * */

public class PythagoreanTriplet {

    public static void main(String[] args){
        int[] ar = new int[]{1,2,3,4,5};
        checkResult(bruteForceApproach(ar),true,ar,"BruteForceApproach");   
        checkResult(optimizedApproach_usingHashing(ar),true,ar,"Hashing technique");   
        checkResult(optimizedApproach_usingSorting(ar),true,ar,"Sorting technique");   
        checkResult(optimizedApproach_counting(ar),true,ar,"Counting technique");   
        checkResult(optimizedApproach_counting(new int[]{5,3,4}),true,new int[]{5,3,4},"Counting technique");   

        ar = new int[]{1,1,1};
        checkResult(bruteForceApproach(ar),false,ar,"BruteForceApproach");
        checkResult(optimizedApproach_usingHashing(ar),false,ar,"Hashing technique");   
        checkResult(optimizedApproach_usingSorting(ar),false,ar,"Sorting technique");
        checkResult(optimizedApproach_counting(ar),false,ar,"Counting technique");   

        ar = new int[]{3,4,1,5};
        checkResult(bruteForceApproach(ar),true,ar,"BruteForceApproach"); 
        checkResult(optimizedApproach_usingHashing(ar),true,ar,"Hashing technique"); 
        checkResult(optimizedApproach_usingSorting(ar),true,ar,"Sorting technique");  
        checkResult(optimizedApproach_counting(ar),true,ar,"Counting technique");   

    }
    /**Time complexity: O(n^3) */
    private static boolean bruteForceApproach(int[] ar) {
        for(int c=0;c<ar.length;c++){
            for(int a=0;a<ar.length;a++){
                if(c!=a){
                    for(int b=0;b<ar.length;b++){
                        if(b!=a && b!=c){
                            if(Math.pow(ar[c],2) == Math.pow(ar[a],2)+Math.pow(ar[b],2))
                                return true;
                        }
                    }
                }
                
            }
        }
        return false;
    }
    /**Time complexity:O(n^2), Space complexity: O(n^2) */
    public static boolean optimizedApproach_usingHashing(int[] ar){
        HashSet<Integer> hs = new HashSet<>();
        for(int i=0;i<ar.length-1;i++){
            for(int j =i+1;j<ar.length;j++){
                hs.add((int) (Math.pow(ar[i], 2) + Math.pow(ar[j], 2)));
            }
        }
        for(int i:ar){
            if(hs.contains((int)Math.pow(i,2))){
                return true;
            }
        }
        return false;
    }
    /**Time complexity: O(n^2) */
    public static boolean optimizedApproach_usingSorting(int[] ar1){
        int[] ar = Arrays.stream(ar1).toArray();
        Arrays.sort(ar);
        for(int i=0;i<ar.length;i++){
            ar[i] = ar[i]*ar[i];
        }
        int target = ar.length-1;
        boolean found = false;
        while(target>1 && !found){
            for(int i=0,j=target-1;i<j;){
                int sum = ar[i]+ar[j];
                if(sum==ar[target]){
                    found=true;
                    break;
                }else if(sum<ar[target]){
                    i++;
                }else{
                    j--;
                }
            }
            target--;
        }
        return found;
    }
    /** Time complexity: O( max * max )*/
    public static boolean optimizedApproach_counting(int[] ar){
        int max = Integer.MIN_VALUE;
        for(int i=0;i<ar.length;i++){
            max= Math.max(max,ar[i]);
        }
        int[] countingArray = new int[max+1];
        for(int i=0;i<ar.length;i++){
            countingArray[ar[i]]++;
        }
        for(int a=0;a<max+1;a++){
            if(countingArray[a]!=0){
                for(int b=a+1;b<max+1;b++){
                    if(countingArray[b]!=0){
                        int val = (int) Math.sqrt(a * a + b * b);
                        if(val*val == a*a+b*b){
                            if(countingArray[val]!=0)
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static void checkResult(boolean actual, boolean expected,int[] in,String approach){
        String result = actual==expected?"Passed":"Failed";
        System.out.println(approach+" :Test with input "+Arrays.toString(in)+" "+result);
        if(actual!=expected){
            System.out.println("Actual: "+actual);
            System.out.println("Expected: "+expected);
        }    
    }
    
}
