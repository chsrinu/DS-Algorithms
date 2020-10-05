package mustdocodingquestions.arrays;

import java.util.Arrays;
import java.util.Stack;

/**
 * https://www.geeksforgeeks.org/trapping-rain-water/
 * 
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it is able to trap after raining.
 */

public class TrappingRainWater {
    public static void main(String[] args){
        int[] ar = new int[]{1,0,1,5,0,2};
        checkResult(getStoredWater(ar),3,ar,"Bruteforce approach");
        checkResult(optimizedVersion_usingStack(ar),3,ar,"Optimized approach");
        ar = new int[]{1,1,1};
        checkResult(getStoredWater(ar),0,ar,"Bruteforce approach");
        checkResult(optimizedVersion_usingStack(ar),0,ar,"Optimized approach");

        ar = new int[]{5,0,2};
        checkResult(getStoredWater(ar),2,ar,"Bruteforce approach");
        checkResult(optimizedVersion_usingStack(ar),2,ar,"Optimized approach");

        ar = new int[]{1,0,0,0,1};
        checkResult(getStoredWater(ar),3,ar,"Bruteforce approach");
        checkResult(optimizedVersion_usingStack(ar),3,ar,"Optimized approach");

        ar = new int[]{5,0,3,0,5};
        checkResult(getStoredWater(ar),12,ar,"Bruteforce approach");
        checkResult(optimizedVersion_usingStack(ar),12,ar,"Optimized approach");

        ar = new int[]{1,0,3,0,5};
        checkResult(getStoredWater(ar),4,ar,"Bruteforce approach");
        checkResult(optimizedVersion_usingStack(ar),4,ar,"Optimized approach");

        ar = new int[]{3, 0, 0, 2, 0, 4};
        checkResult(getStoredWater(ar),10,ar,"Bruteforce approach");
        checkResult(optimizedVersion_usingStack(ar),10,ar,"Optimized approach");
    }
    /**Time COmplexity:O(n) but if you take a closer look it would be O(2n) 
     * But a data structure will reduce it to O(n) explicitly which is Stack.
    */
    public static int getStoredWater(int[] ar){
        int maxIndex = 0;
        int tempStorage = 0, maxStored = 0;
        
        for(int i=1;i<ar.length;i++){
            if(ar[i]<ar[maxIndex]){
                tempStorage+=ar[maxIndex]-ar[i];
            }else{
                maxStored+=tempStorage;
                tempStorage=0;
                maxIndex = i;
            }
        }
        if(tempStorage!=0){
            int maxIndex_reverse = ar.length-1;
            tempStorage = 0;
            for(int i=maxIndex_reverse-1;i>=maxIndex;i--){
                if(ar[i]<ar[maxIndex_reverse]){
                    tempStorage+=ar[maxIndex_reverse]-ar[i];
                }else{
                    maxStored+=tempStorage;
                    tempStorage=0;
                    maxIndex_reverse=i;
                }
            }            
        }
        return  maxStored;   
    }

    public static int optimizedVersion_usingStack(int[] ar){
        int waterStored = 0;
        Stack<Integer> stack = new Stack<>(); 
        for(int i=0;i<ar.length;i++){
            while(!stack.isEmpty() && ar[stack.peek()]<ar[i]){
                int poppedElement = ar[stack.peek()];
                stack.pop();
                if(stack.isEmpty())
                    break;
                int leftLimit = ar[stack.peek()];
                int rightLimit = ar[i];
                int distance = i-stack.peek()-1;
                int minHeight = Math.min(leftLimit,rightLimit)-poppedElement;
                waterStored+=distance*minHeight;
            }
            stack.push(i);
        }
        return waterStored;
    }

    public static void checkResult(int actual, int expected,int[] input,String approach){
        String result=actual==expected?"Passed":"Failed";
        System.out.println(approach+": Test with input :"+Arrays.toString(input)+" "+result);
        if(actual!=expected){
            System.out.println("Actual: "+actual);
            System.out.println("Expected: "+expected);
        }

    }
}
