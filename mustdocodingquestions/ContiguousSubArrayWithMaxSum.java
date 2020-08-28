package mustdocodingquestions;
/**
 * Given an array arr of N integers. Find the contiguous sub-array with maximum sum.

Input:
The first line of input contains an integer T denoting the number of test cases. 
The description of T test cases follows. The first line of each test case contains a 
single integer N denoting the size of array. The second line contains N space-separated 
integers A1, A2, ..., AN denoting the elements of the array.

Output:
Print the maximum sum of the contiguous sub-array in a separate line for each test 
case.

Constraints:
1 ≤ T ≤ 110
1 ≤ N ≤ 106
-107 ≤ A[i] <= 107

Example:
Input
2
5
1 2 3 -2 5
4
-1 -2 -3 -4
Output
9
-1

Explanation:
Testcase 1: Max subarray sum is 9 of elements (1, 2, 3, -2, 5) which is a contiguous subarray.
 */
public class ContiguousSubArrayWithMaxSum {
    public static void main(String[] args) {
        test(new int[]{8,3,5},16);
        test(new int[]{4,-8,3,5},8);
        test(new int[]{-8,9},9);
        test(new int[]{1,2,3,-2,5},9);
        test(new int[]{-2,-1},-1);
        kadanesTwoStepProcess(new int[]{8,3,5},16);
        kadanesTwoStepProcess(new int[]{4,-8,3,5},8);
        kadanesTwoStepProcess(new int[]{-8,9},9);
        kadanesTwoStepProcess(new int[]{1,2,3,-2,5},9);
        kadanesTwoStepProcess(new int[]{-2,-1},-1);
    }

    private static void test(int[] inputArray, int expectedOutput) {
        int sum=0, maxSum = inputArray[0];
        for(int i=0;i<inputArray.length;i++){
            sum+=inputArray[i];
            if(sum>maxSum){
                maxSum = sum;
            }else if(sum<0){
                sum=0;
            }
        }
        String result = maxSum==expectedOutput? "pass": "failllll";
        System.out.println(result);
    }

    private static void kadanesTwoStepProcess(int[] inputArray,int expectedOutput){
        int left_sum=inputArray[0],maxSum = left_sum;
        for(int i=1;i<inputArray.length;i++){
            left_sum = Math.max(inputArray[i],inputArray[i]+left_sum);
            maxSum = Math.max(left_sum,maxSum);
        }
        String result = maxSum==expectedOutput? "pass": "failllll";
        System.out.println(result); 
    }
}