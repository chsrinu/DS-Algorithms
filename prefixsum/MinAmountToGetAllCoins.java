package prefixsum;

import java.util.Arrays;
import java.util.TreeSet;

import datastructures.MaxHeap;

/**
 * 
 * Minimum cost for acquiring all coins with k extra coins allowed with every
 * coin
 * 
 * You are given a list of N coins of different denominations. you can pay an
 * amount equivalent to any 1 coin and can acquire that coin. In addition, once
 * you have paid for a coin, we can choose at most K more coins and can acquire
 * those for free. The task is to find the minimum amount required to acquire
 * all the N coins for a given value of K.
 * 
 */
public class MinAmountToGetAllCoins {
    public static void main(String[] args){
        int[] ar = new int[]{4,5,1,3,12,30,21,25,23,28};
        int k = 2;
        bruteForceApproach(ar,k);
        optimizedApproach1(ar,k); 
        optimizedApproach2(ar,k);
        optimizedApproach3(ar,k);        
    }

    /**
     * Sorting entire array would be useless for larger values of k. For instance
     * consider a case where the input array is of size of 100 with k value as 50.
     * So we only need the smallest 2 elements
     */
    private static void bruteForceApproach(int[] ar, int k) {
        Arrays.sort(ar);
        int numberOfTurns = ar.length/(k+1),result = 0;
        if(ar.length%(k+1)!=0)
            numberOfTurns++;
        for(int i=0;i<numberOfTurns;i++){
            result+=ar[i];
        }
        System.out.println(result);
    }
    /**You don't need to sort the entire array to find m small elements if you are using a
     * tree set. But adding an element to treeSet would take log(n) time and for
     * n elements in the array it would be nlogn so no change in the time complexity
     * mentioned earlier
     */
    private static void optimizedApproach1(int[] ar, int k) {
        int numberOfTurns = ar.length/(k+1),result = 0;
        if(ar.length%(k+1)!=0)
            numberOfTurns++;
        TreeSet<Integer> tree = new TreeSet<>();
        int min=Integer.MAX_VALUE;
        for(int i=0;i<ar.length;i++){
            tree.add(ar[i]);
            if(ar[i]<min)
                min=ar[i];
        }
        result+=min;    
        for(int i=0;i<numberOfTurns-1;i++){
            int nextHighest = tree.higher(Integer.valueOf(min));
            min= nextHighest;
            result+=min;
        }
        System.out.println(result);
    }
    /**
     * Using an auxillary array of K smallest elements, we can do it in O(k+(n-k)*k).
     * But the inner loop which takes O(k) time to set the currentMax everytime 
     * from the K elements, can further be optimized to logarithmic terms.
     */
    private static void optimizedApproach2(int[] ar, int k) {
        int m = ar.length/(k+1),result = 0;
        if(ar.length%(k+1)!=0)
            m++;
        int[] temp = new int[m];
        int max = Integer.MIN_VALUE,maxIndex=-1;
        for(int i=0;i<m;i++){
            temp[i]=ar[i];
            if(temp[i]>max)
                max=temp[i];
                maxIndex=i;
        }
        for(int i=m;i<ar.length;i++){
            if(ar[i]<max){
                temp[maxIndex] = ar[i];
                max = ar[i];
                for(int j=0;j<temp.length;j++){
                    if(temp[i]>max){
                        max=temp[i];
                        maxIndex = i;
                    }
                }
            }
        }
        for(int i:temp)
            result+=i;
        System.out.println(result);
            
    }

    /**
     * We can still optimize the code from O((n-k)*k) to O((n-k)*logk)by replacing the 
     * Auxillary array with Max Heap dataStructure in above approach
     */
    private static void optimizedApproach3(int[] ar, int k) {
        int m = ar.length/(k+1);
        int maxAmount = 0;
        if(ar.length%(k+1)!=0)
            m++;
        //System.out.println(m);
        MaxHeap heap = new MaxHeap(m);
        for(int i=0;i<m;i++)
            heap.add(ar[i]);
        //heap.printArray();
        for(int i=m;i<ar.length;i++){
            if(ar[i]<heap.getMax()){
                //replace with the max element
                heap.remove();
                heap.add(ar[i]);
            }
        }
       
        while(!heap.isEmpty()){
            maxAmount+=heap.remove();
        }
        System.out.println(maxAmount);
    }
}