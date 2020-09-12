package mustdocodingquestions;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MergeSortedArrays {
    public static void main(String[] args){
        test(new int[]{8,12,100,300}, new int[]{1,122,144,150,200});
        testWithoutExtraSpace(new int[]{8,12,100,300}, new int[]{1,122,144,150,200});
    }

    private static void test(int[] ar1, int[] ar2) {
        System.out.println("---BruteForce approach---");
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
     * is being allotted for the priorityQ in above test method/solution
     */
    //Using shell sort or implementation of shell sort
    public static void testWithoutExtraSpace(int[] A, int[] B){
        /**
         * new int[]{8,12,100,300}, new int[]{1,122,144,150,200}
         */
        System.out.println("---Shell sort implementation---");
        int n = A.length+B.length;
        for(int gap=n/2;gap>0;gap/=2){
            for(int i=gap;i<n;i++){
                int temp = getElement(i,A,B);
                int j=i;
                for(;j-gap>=0;j-=gap){
                    if(getElement(j-gap, A, B)>temp){
                        setElement(j-gap,A, B, j);
                    }else
                        break;
                }
                setValue(j,temp,A,B);
            }
        }
        System.out.println(Arrays.toString(A));
        System.out.println(Arrays.toString(B));  
    }
    public static int getElement(int i,int[] A,int[] B){
        if(i>=A.length){
            return B[i-A.length];
        }else
            return A[i];
    }
    public static void setElement(int src, int[] A, int[] B, int targetPos){
        if(src>=A.length){
            B[targetPos-A.length] = B[src-A.length];
        }else if(src<A.length && targetPos>=A.length){
            B[targetPos-A.length]=A[src];
        }else{
            A[targetPos] = A[src];
        }
    }
    public static void setValue(int index, int temp, int[] A, int[] B){
        if(index>=A.length)
            B[index-A.length]=temp;
        else
            A[index]=temp;
    }
}