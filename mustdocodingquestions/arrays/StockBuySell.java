package mustdocodingquestions.arrays;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/stock-buy-sell/
 * 
 * The cost of a stock on each day is given in an array, find the max profit that 
 * you can make by buying and selling in those days. For example, if the given array 
 * is {100, 180, 260, 310, 40, 535, 695}, the maximum profit can earned by buying on 
 * day 0, selling on day 3. Again buy on day 4 and sell on day 6. If the given array 
 * of prices is sorted in decreasing order, then profit cannot be earned at all.
 * 
 * Sample Output:
 *  865
 */

public class StockBuySell {
    public static void main(String args[]){
        int[] ar = new int[]{100, 180, 260, 310, 40, 535, 695};
        checkResult(greedyApproach(ar),865,"BruteforceApproach",ar);
        ar = new int[]{100, 200,300};
        checkResult(greedyApproach(ar),200,"BruteforceApproach",ar);
        ar = new int[]{500,400,300,200,100};
        checkResult(greedyApproach(ar),0,"BruteforceApproach",ar);
        ar = new int[]{100,200,50,100,200};
        checkResult(greedyApproach(ar),250,"BruteforceApproach",ar);
        ar = new int[]{ 1, 5, 2, 3, 7, 6, 4, 5 };
        checkResult(greedyApproach(ar),10,"BruteforceApproach",ar);
    }

    private static void checkResult(int actual, int expected, String approach, int[] in) {
        String status = actual==expected? "Passed":"Failed";
        System.out.println(approach+": Test with input "+Arrays.toString(in)+" "+status);
        if(actual!=expected){
            System.out.println("Actual: "+actual);
            System.out.println("Expected: "+expected);
        }
    }
    /* Time complexity: O(n) */
    private static int greedyApproach(int[] ar) {
        int i=0,buyDay=-1,profit=0;
        for(;i<ar.length-1;i++){
            if(buyDay==-1 && ar[i]<ar[i+1]){
                buyDay = i;
                continue;
            }
            if(buyDay!=-1 && ar[i]>ar[i+1]){
                profit+=ar[i]-ar[buyDay];
                buyDay=-1;    
            }
        }
        if(buyDay!=-1 && i==ar.length-1){
            profit+=ar[ar.length-1]-ar[buyDay];
        }
        return profit;
    }
}
