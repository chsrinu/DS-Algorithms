package mustdocodingquestions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.geeksforgeeks.org/print-a-given-matrix-in-spiral-form/
 * 
 * Given a 2D array, print it in spiral form. See the following examples.

Examples: 

Input:  1    2   3   4
        5    6   7   8
        9   10  11  12
        13  14  15  16
Output: 1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10 
Explanation: The output is matrix in spiral format. 

Input:  1   2   3   4  5   6
        7   8   9  10  11  12
        13  14  15 16  17  18
Output: 1 2 3 4 5 6 12 18 17 16 15 14 13 7 8 9 10 11
Explanation :The output is matrix in spiral format.

 */
public class SpiralMatrix {
    public static void main(String[] args){
        int[][] ar = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[] expected = new int[]{1,2,3,6,9,8,7,4,5};
        checkResult(bruteforceApproach(ar),expected,"BruteforceApproach",ar);
        ar = new int[][]{{1,2,3},{4,5,6}};
        expected = new int[]{1,2,3,6,5,4};
        checkResult(bruteforceApproach(ar),expected,"BruteforceApproach",ar);
        ar = new int[][]{{1},{2},{3}};
        expected = new int[]{1,2,3};
        checkResult(bruteforceApproach(ar),expected,"BruteforceApproach",ar);
        ar = new int[][]{{1,2,3}};
        expected = new int[]{1,2,3};
        checkResult(bruteforceApproach(ar),expected,"BruteforceApproach",ar);
        

    }

    private static void checkResult(List<Integer> actual, int[] expected, String approach,int[][] in) {
        boolean failed = false;
        if(actual.size()==expected.length){
            for(int i=0;i<expected.length;i++){
                if(actual.get(i)!=expected[i]){
                    failed=true;
                }
            }
        }else
            failed = false;
        String status = failed?"Failed":"Passed";
        String input = "";
        for(int[] a: in){
            input+=Arrays.toString(a);
        }
        System.out.println(approach+" : Test with input "+input+" "+status);
        if(failed){
            System.out.println("Actual: "+actual.toString());
            System.out.println("Expected: "+Arrays.toString(expected));
        }        

    }
    /**Time complexity O(m*n) */
    private static List<Integer> bruteforceApproach(int[][] ar) {
        int i_min=1,i_max=ar.length;
        int j_min=0,j_max=ar[0].length;
        List<Integer> spiralOrder = new ArrayList<>();
        int m = i_max,n = j_max;
        for(int i=0,j=0;m*n<=spiralOrder.size();){
            while(j<j_max){
                spiralOrder.add(ar[i][j]);
                j++;
            }
            j_max--;
            j--;
            i++;
            while(i<i_max){
                spiralOrder.add(ar[i][j]);
                i++;
            }
            i_max--;
            i--;
            j++;
            while(j>=j_min){
                spiralOrder.add(ar[i][j]);
                j--;
            }
            j_min++;
            j++;
            i--;
            while(i>=i_min){
                spiralOrder.add(ar[i][j]);
                i--;
            }
            i_min++;
            i++;
            j--;
        }
        return spiralOrder;
    }
}
