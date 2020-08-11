package prefixsum;

import java.util.HashMap;

/*
https://www.geeksforgeeks.org/maximum-occurred-integer-n-ranges/
Given n ranges of the form L and R, the task is to find the maximum occurred integer 
in all the ranges. If more than one such integer exists, print the smallest one.
0 <= Li, Ri < 1000000.
*/

public class MaxOccuredIntegerInNRanges {
    public static void main(final String[] args) {
        // final int L1 = 1, R1 = 15;
        // final int L2 = 4, R2 = 8;
        // final int L3 = 3, R3 = 5;
        // final int L4 = 1, R4 = 4;
        int[] left1= new int[]{1,4,3,1};
        int[] right1= new int[]{15,8,5,4};
        bruteForceApproach(left1,right1);
        // int L1 = 1, R1 = 15,L2 = 5, R2 = 8,L3 = 9, R3 = 12,L4 = 13,R4 = 20,L5 = 21;
        // int R5 = 30;
        int[] left2 = new int[]{1,5,9,13,21};
        int[] right2 = new int[]{15,8,12,20,30};

        bruteForceApproach(left2,right2);
        //int L1=1,R1=5,L2=6,R2=10;
        int[] left3 = new int[]{1,6};
        int[] right3 = new int[]{5,10};
        bruteForceApproach(left3,right3);
        //int L1=1,R1=2,L2=1,R2=10000;
        int[] left4 = new int[]{1,1};
        int[] right4 = new int[]{2,10000};
        bruteForceApproach(left4,right4);


        bruteForceUsingHashMap(left1,right1);
        // int L1 = 1, R1 = 15,L2 = 5, R2 = 8,L3 = 9, R3 = 12,L4 = 13,R4 = 20,L5 = 21;
        // int R5 = 30;
        bruteForceUsingHashMap(left2,right2);
        //int L1=1,R1=5,L2=6,R2=10;
        bruteForceUsingHashMap(left3,right3);
        //int L1=1,R1=2,L2=1,R2=10000;
        bruteForceUsingHashMap(left4,right4);


        optimizedApproach(left1, right1);
        optimizedApproach(left2, right2);
        optimizedApproach(left3, right3);
        optimizedApproach(left4, right4);
    }
    /*Using counting sort approach*/
    /**
     * But this would be inefficient in space for larger R like 10000 
     */
    private static void bruteForceApproach(int[] left,int[] right) {
        int highest = Integer.MIN_VALUE;
        int maxCount = 0;
        for(int i=0;i<right.length;i++){
            highest = Math.max(right[i],highest);
        }
        int[] countArray = new int[highest+1];
        //System.out.println(countArray.length);
        for(int i=0;i<left.length;i++){
            for(int j=left[i];j<=right[i];j++){
                maxCount=Math.max(maxCount,++countArray[j]);
            }
        }
        for(int i=0;i<countArray.length;i++){
            if(countArray[i]==maxCount){
                System.out.println(i);
                break;
            }
                
        }
    }
    /**
     * With this approach we are saving the memory when compared to 
     * earlier bruteForce approach but we are going though each and 
     * every element in the every single range. 
     */
    private static void bruteForceUsingHashMap(int[] left,int[] right){
        HashMap<Integer,Integer> hm = new HashMap<>();
        int count = 0, maxOccuredElement = Integer.MIN_VALUE;
        for(int i=0;i<left.length;i++){
            for(int j=left[i];j<=right[i];j++){
                hm.putIfAbsent(j, 0);
                hm.put(j, hm.get(j)+1);
                int currentCount = hm.get(j);
                if(currentCount>count){
                    count = currentCount;
                    maxOccuredElement = j;
                }else if(currentCount == count){
                    maxOccuredElement = Math.min(j,maxOccuredElement);
                }
            }
        }
        System.out.println(maxOccuredElement);
    }

    public static void optimizedApproach(int[] left, int[] right){
         int[] aux = new int[1000000];
         int maxRange=Integer.MIN_VALUE;
         int minRange=Integer.MAX_VALUE;
         int maxOccured = 0, maxElement=0;
         for(int i=0;i<left.length;i++){
             aux[left[i]]+=1;
             aux[right[i]+1]-=1;
             maxRange = Math.max(right[i],maxRange);
             minRange = Math.min(left[i],minRange);
         }
         maxOccured=aux[minRange];
         maxElement=minRange;
         for(int i=minRange+1;i<=maxRange;i++){
            aux[i]+=aux[i-1];
            if(aux[i]>maxOccured){
                maxElement=i;
                maxOccured=aux[i];
            }
         }
         System.out.println(maxElement);              
    } 
}