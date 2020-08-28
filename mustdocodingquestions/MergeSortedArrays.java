package mustdocodingquestions;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeSortedArrays {
    public static void main(String[] args){
        test(new int[]{8,12,100,300}, new int[]{1,122,144,150,200});
    }

    private static void test(int[] ar1, int[] ar2) {
        int[] bigArray = ar1.length>=ar2.length?ar1:ar2;
        int[] smallArray = ar1.length<ar2.length?ar1:ar2;
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int i:bigArray){
            q.add(i);
        }
        for(int i=0;i<smallArray.length;i++){
            if(smallArray[i]>q.peek()){
                int temp = smallArray[i];
                smallArray[i]=q.poll();
                q.add(temp);
            }
        }
        for(int i=0;i<bigArray.length;i++){
            bigArray[i]= q.poll();
        }
        System.out.println(Arrays.toString(smallArray));
        System.out.println(Arrays.toString(bigArray));  
    }
    /**Follow up question would be to compute the result without using extra space which
     * is being allotted for the priorityQ in above test solution
     */
    public void testWithoutExtraSpace(){

    }
}