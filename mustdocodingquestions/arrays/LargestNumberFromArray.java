package mustdocodingquestions.arrays;

import java.util.Arrays;
/**
 * https://www.geeksforgeeks.org/given-an-array-of-numbers-arrange-the-numbers-to-form-the-biggest-number/
 * 
 * Given an array of numbers, arrange them in a way that yields the largest
 * value. For example, if the given numbers are {54, 546, 548, 60}, the
 * arrangement 6054854654 gives the largest value. And if the given numbers are
 * {1, 34, 3, 98, 9, 76, 45, 4}, then the arrangement 998764543431 gives the
 * largest value.
 * 
 */

public class LargestNumberFromArray {
    public static void main(String[] args){
        int[] ar = new int[]{32,22,7};
        checkResult(bruteforceApproach(ar),"73222","Bruteforce Approach",ar);
        ar = new int[]{54, 546, 548, 60};
        checkResult(bruteforceApproach(ar),"6054854654","Bruteforce Approach",ar);
        ar = new int[]{1, 34, 3, 98, 9, 76, 45, 4};
        checkResult(bruteforceApproach(ar),"989764543431","Bruteforce Approach",ar);

    }

    private static void checkResult(String actual, String expected, String approach, int[] ar) {
        String status = actual.equals(expected)?"Passed":"Failed";
        System.out.println(approach+": Test with input "+Arrays.toString(ar)+" "+status);        
        if(!actual.equals(expected)){
            System.out.println("Actual: "+actual);
            System.out.println("Expected: "+expected);
        }
    }
    /**Time complexity: O(nlogn) 
     * Space complexity: O(1)
    */
    private static String bruteforceApproach(int[] ar) {
        //String[] in = Arrays.stream(ar).boxed().map(i -> String.valueOf(i)).toArray(String[]::new);
        String[] in = Arrays.stream(ar).mapToObj(String::valueOf).toArray(String[]::new);
        String maxValue = "";
        Arrays.sort(in);
        for(int i=in.length-1;i>=0;i--){
            maxValue+=in[i];
        }
        return maxValue;
    }
}
