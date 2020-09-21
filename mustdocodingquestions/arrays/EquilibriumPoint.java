package mustdocodingquestions.arrays;

import java.util.Arrays;

public class EquilibriumPoint {
    public static void main(String[] args){
        int[] input = new int[]{1,2,3,4,5};
        checkResult(bruteforceApproach(input),-1,"BruteforceApproach",input);
        input = new int[]{1};
        checkResult(bruteforceApproach(input),1,"BruteforceApproach",input);
        input = new int[]{5,8,2,3,10};
        checkResult(bruteforceApproach(input),3,"BruteforceApproach",input);
        input = new int[]{3,1,3};
        checkResult(bruteforceApproach(input),2,"BruteforceApproach",input);
        input = new int[]{1,2,3,4,6};
        checkResult(bruteforceApproach(input),4,"BruteforceApproach",input);   
    }
    public static int bruteforceApproach(int[] ar){
        if(ar.length==1)
            return 1;
        if(ar.length>2){
            int left = 0,right = 0;
            for(int i:ar){
                right+=i;
            }
            right-=ar[0];
            for(int i=1;i<ar.length-1;i++){
                left+=ar[i-1];
                right-=ar[i];
                if(left==right)
                    return i+1;
            }
        }
        return -1;
    }
    public static void checkResult(int actual, int expected, String approach, int[] input){
        String status = actual!=expected?"Failed":"Passed";
        System.out.println(approach+": Test with input "+Arrays.toString(input)+" "+status);
        if(actual!=expected){
            System.out.println("Actual: "+actual);
            System.out.println("Expected: "+expected);
        }
    }
    
}
