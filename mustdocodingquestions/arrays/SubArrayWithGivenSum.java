package mustdocodingquestions.arrays;
/**
 * Given an unsorted array A of size N of non-negative integers, 
 * find a continuous sub-array which adds to a given number S.

Input:
The first line of input contains an integer T denoting the number of test cases. 
Then T test cases follow. Each test case consists of two lines. The first line of each 
test case is N and S, where N is the size of array and S is the sum. The second line of 
each test case contains N space separated integers denoting the array elements.

Output:
For each testcase, in a new line, print the starting and ending positions(1 indexing) 
of first such occuring subarray from the left if sum equals to subarray, else print -1.

Constraints:
1 <= T <= 100
1 <= N <= 107
1 <= Ai <= 1010

Example:
Input:
2
5 12
1 2 3 7 5
10 15
1 2 3 4 5 6 7 8 9 10
Output:
2 4
1 5

Explanation :
Testcase1: sum of elements from 2nd position to 4th position is 12
Testcase2: sum of elements from 1st position to 5th position is 15
 */
public class SubArrayWithGivenSum {
    public static void main(String[] args) {
         printResult(runTest(new int[]{1,2,3,8,4,2,3,1},10));

         printResult(runTest(new int[]{11,1,4,2,5,3},14));
         
         printResult(runTest(new int[]{1,2,3,7,5},12));

         printResult(runTest(new int[]{1,2,3,4,5,6,7,8,9,10},15));
    }

    public static void printResult(int[] testResult){
        if(testResult.length==1){
            System.out.println("-1");
        }else{
            System.out.println(testResult[0]+" "+testResult[1]);
        }
    }

    public static int[] runTest(int[] ar,int s){
        int start = 0,end=-1,sum=0;

        for(int i=0;i<ar.length;i++){
            sum+=ar[i];
            if(sum==s){
                end=i;
                return new int[]{start+1,end+1};
            }else if(sum>s){
                sum-=ar[start];
                if(sum==s){
                    end=i;
                    start++;
                    return new int[]{start+1,end+1};
                }
                start++;
            }
        }
        return new int[]{-1};
    }
    
}