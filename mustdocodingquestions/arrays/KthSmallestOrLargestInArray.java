package mustdocodingquestions.arrays;

import java.util.Arrays;

import datastructures.MaxHeap;
/**
 * https://www.geeksforgeeks.org/kth-smallestlargest-element-unsorted-array/
 * 
 * Given an array and a number k where k is smaller than size of array, we need to
 *  find the k’th smallest element in the given array. It is given that all array 
 * elements are distinct.
 * 
 *  Input: arr[] = {7, 10, 4, 3, 20, 15}
    k = 3
    Output: 7

    Input: arr[] = {7, 10, 4, 3, 20, 15}
    k = 4
    Output: 10 

   Expected Time Complexity: O(N).
   Expected Auxiliary Space: O(1)
 * 
 */
public class KthSmallestOrLargestInArray {
    public static void main(String[] args){
      int[] ar = new int[]{5,4,1,2,3};
      int k = 3;
      checkResult(bruteforceApproach(Arrays.stream(ar).toArray(),k),3,"Bruteforce approach",ar,k);
      checkResult(optimizedApproachUsingHeap(Arrays.stream(ar).toArray(),k),3,"Optimized Approach using Heap", ar, k); 
      checkResult(optimizedApproachUsingPartitioning(Arrays.stream(ar).toArray(),k),3,"Optimized Approach using Partitioning", ar, k);
      ar = new int[]{5,3};
      k=2;
      checkResult(bruteforceApproach(Arrays.stream(ar).toArray(),k),5,"Bruteforce approach",ar,k);
      checkResult(optimizedApproachUsingHeap(Arrays.stream(ar).toArray(),k),5,"Optimized Approach using Heap", ar, k); 
      checkResult(optimizedApproachUsingPartitioning(Arrays.stream(ar).toArray(),k),5,"Optimized Approach using Partitioning", ar, k); 
      ar = new int[]{100};
      k=1;
      checkResult(bruteforceApproach(Arrays.stream(ar).toArray(),k),100,"Bruteforce approach",ar,k);
      checkResult(optimizedApproachUsingHeap(Arrays.stream(ar).toArray(),k),100,"Optimized Approach using Heap", ar, k); 
      checkResult(optimizedApproachUsingPartitioning(Arrays.stream(ar).toArray(),k),100,"Optimized Approach using Partitioning", ar, k); 

   }
   /* Time complexity: O(nlogn)*/
    public static int bruteforceApproach(int[] ar, int k){
      Arrays.sort(ar);
      return ar[k-1];
    }
   /* Time complexity: O(nlogk)
   but the expected time complexity is O(n) and space is 
   expected to be around O(1) which is a constant. We can achieve this 
   through partitioning.
   */

    public static int optimizedApproachUsingHeap(int[] ar, int k){
      MaxHeap maxHeap = new MaxHeap(k);
      for(int i=0;i<k;i++){
         maxHeap.add(ar[i]);
      }
      for(int i=k;i<ar.length;i++){
         if(maxHeap.getMax()>ar[i]){
            maxHeap.remove();
            maxHeap.add(ar[i]);
         }
      }
      return maxHeap.getMax();
    }
    
    public static int optimizedApproachUsingPartitioning(int[] ar, int k){
        return partition(ar,0,ar.length-1,k)[k-1];
        
    }
    public static int[] partition(int[] ar,int start, int end, int k){
       if(start==end){
          return ar;
       }else{
          int pivot = ar[end];
          int i=start,j=end-1;
          while(i<j){
             if(ar[i]>pivot && ar[j]<pivot){
                swap(i,j,ar);
                i++;
                j--;
             }else{
               if(ar[i]<=pivot){
                  i++;
               }
               if(ar[j]>pivot){
                  j--;
               }
             }
          }
          swap(end,i,ar);
          if(i+1==k){
             return ar;
          }else if(i+1>k){
             //use left part
             return partition(ar,start,i-1,k);
          }else
             return partition(ar,i+1,end,k);
       }
    }

    public static void swap(int i,int j, int[] ar){
      int temp = ar[j];
      ar[j] = ar[i];
      ar[i] = temp;
    }

    public static void checkResult(int actual, int expected, String approach, int[] in_ar, int in_k){
      String status = actual==expected?"Passed":"Failed";
      System.out.println(approach+": with input "+Arrays.toString(in_ar)+" and k:"+in_k+" "+status);
      if(actual!=expected){
         System.out.println("Actual "+actual);
         System.out.println("Expected "+expected);
      }     
    }
    
}
