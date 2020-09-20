package mustdocodingquestions;
import java.util.Arrays;
/**
 * https://practice.geeksforgeeks.org/problems/inversion-of-array/0/
 * 
 * Given an array of positive integers. The task is to find inversion count of array.

Inversion Count : For an array, inversion count indicates how far (or close) the 
array is from being sorted. If array is already sorted then inversion count is 0.
 If array is sorted in reverse order that inversion count is the maximum. 
Formally, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j.
Example:
Input:
2 4 1 3 5
Output:
3 

*/

public class InversionCount {
    public static int count = 0;
    public static void main(String[] args){
        bruteForceApproach(new int[]{2,4,1,3,5},3);
        sortAndCount(new int[]{2,4,1,3,5}, 3);
        bruteForceApproach(new int[]{1,2,3,4,5},0);
        sortAndCount(new int[]{1,2,3,4,5}, 0);
        bruteForceApproach(new int[]{5,4,3,2,1},10);
        sortAndCount(new int[]{5,4,3,2,1}, 10);
    }
    public static void bruteForceApproach(int[] ar, int expected){
        int count = 0;
        for(int i=0;i<ar.length-1;i++){
            for(int j=i+1;j<ar.length;j++){
                if(ar[i]>ar[j])
                    count++;
            }
        }
        printTestCaseStatus(count,expected,ar, "Bruteforce approach");
    }

    public static void printTestCaseStatus(int actual, int expected,int[] ar,String approach){
        boolean statusFlag = actual==expected;
        String status = statusFlag?"Passed":"Failed";
        System.out.println(approach+": Test with input "+Arrays.toString(ar)+" "+status);
        if(!statusFlag){
            System.out.println("Expected "+expected);
            System.out.println("Actual "+actual);
        }
    }

    public static void sortAndCount(int[] ar, int expected){
        count = 0;
        int[] sortResult = new int[]{ar.length};
        sortResult = Arrays.stream(ar).toArray();
        mergeSort(sortResult);
        //System.out.println(Arrays.toString(sortResult));
        printTestCaseStatus(count, expected, ar, "Optimized approach using sorting");
    }
    public static int[] mergeSort(int[] ar){
        if(ar.length<=1)
            return ar;
        else{
            int mid = ar.length/2;
            boolean isOddLength = (ar.length)%2 != 0;
            int[] firstHalf = new int[mid];
            int[] secondHalf = new int[isOddLength?mid+1:mid];
            for(int i=0;i<firstHalf.length;i++){
                firstHalf[i]=ar[i];
            }
            for(int i=0,j=mid;i<secondHalf.length;i++){
                secondHalf[i]=ar[j++];
            }
            firstHalf = mergeSort(firstHalf);
            secondHalf = mergeSort(secondHalf);
            for(int i=0,j=0,k=0;i<ar.length;i++){
                if(j<firstHalf.length && k<secondHalf.length){
                    if(firstHalf[j]<secondHalf[k]){
                        ar[i]=firstHalf[j++];
                    }else{
                        ar[i]=secondHalf[k++];
                        count+=(firstHalf.length-j);
                    }
                }else{
                    if(k<secondHalf.length){
                        ar[i]=secondHalf[k++];
                    }else
                        ar[i]=firstHalf[j++];
                }
            }
            return ar;
        }
    }
}
