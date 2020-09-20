package mustdocodingquestions.arrays;
import java.util.Arrays;
/**
https://practice.geeksforgeeks.org/problems/sort-an-array-of-0s-1s-and-2s/0#ExpectOP
Given an array A of size N containing 0s, 1s, and 2s; you need to sort the 
array in ascending order.

Example:
Input :
2
5
0 2 1 2 0
3
0 1 0

Output:
0 0 1 2 2
0 0 1

 */
public class SortAnArrayOf012s {
    public static void main(String[] args){
        int[] input = new int[]{1,1,2,1,0,2,1};
        checkTest(input,sort(Arrays.stream(input).toArray()),new int[]{0,1,1,1,1,2,2});
        input = new int[]{0,0,0,0};
        checkTest(input,sort(Arrays.stream(input).toArray()),new int[]{0,0,0,0});
        input = new int[]{2,1,2,1};
        checkTest(input,sort(Arrays.stream(input).toArray()),new int[]{1,1,2,2});
        
    }
    public static int[] sort(int[] input){
        int[] countingArray = new int[3];
        for(int i:input){
            countingArray[i]++;
        }
        for(int i=0,j=0;i<input.length;){
            if(countingArray[j]!=0){
                input[i]=j;
                i++;
                countingArray[j]--;
            }else
                j++;
        }
        return input;
    }

    public static void checkTest(int[] input, int[] actual, int[] expected){
        boolean isFailed = false;
        for(int i=0;i<actual.length;i++){
            if(actual[i]!=expected[i]){
                isFailed = true;
                break;
            }
        }
        String status = isFailed?"Failed":"Passed";
        System.out.println("Test with input: "+Arrays.toString(input)+" "+status);
        if(isFailed){
            System.out.println("Expected: "+Arrays.toString(expected));
            System.out.println("Actual: "+Arrays.toString(actual));
        }
    }
}
