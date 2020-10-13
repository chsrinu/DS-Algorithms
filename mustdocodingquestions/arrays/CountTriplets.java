package mustdocodingquestions.arrays;

import java.util.Arrays;


/**
 * 

Given an array of distinct integers. The task is to count all the triplets such that 
sum of two elements equals the third element.

Input:
The first line of input contains an integer T denoting the number of test cases. 
Then T test cases follow. Each test case consists of two lines. First line of each test
case contains an Integer N denoting size of array and the second line contains N space
 separated elements.

Output:
For each test case, print the count of all triplets, in new line. If no such triplets
 can form, print "-1".

Constraints:
1 <= T <= 100
3 <= N <= 105
1 <= A[i] <= 106

Example:
Input:
2
4
1 5 3 2
3
3 2 7
Output:
2
-1

Explanation:
Testcase 1: There are 2 triplets: 1 + 2 = 3 and 3 +2 = 5
** For More Input/Output Examples Use 'Expected Output' option **

 */
public class CountTriplets {
    public static void main(String[] args){
        int[] ar = new int[]{20,7,8,28,10,13};
        test(ar);
        ar = new int[]{1,5,3,2};
        test(ar);
        ar = new int[]{3,2,7};
        test(ar);
    }    
    public static void test(int[] ar){
        Arrays.sort(ar);
        int targetPos=ar.length-1;
        int count = 0;
        while(targetPos>=2){
            for(int i=0,j=targetPos-1;i<j;){
                int sum = ar[i]+ar[j];
                if(sum==ar[targetPos]){
                    count++;
                    i++;
                    j--;
                }
                else if(sum>ar[targetPos])
                    j--;
                else
                    i++;
            }
            targetPos--;
        }
        System.out.println("Number of triplets "+count);
    }
}