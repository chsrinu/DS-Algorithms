package arrays;
import java.util.Arrays;
/**
 * How do you find the duplicate number on a given integer array
 *  without collection API. Output needs to have elements which are not duplucated
 * 
 * Follow up question: Allow one occurance of repeated elements
 */

public class RemoveDuplicatesInArray {

    public static void main(String[] args){
        int[] in_1 = new int[]{1,1,1,2};
        int[] in_2 = new int[]{2,2,2,1};
        /**Remove elements which are dulplicated */
        removeDuplicates(in_1);
        removeDuplicates(in_2);
        removeDuplicatesUsingPrevious(in_1);
        removeDuplicatesUsingPrevious(in_2);
        /**Remove the duplicate elements alone leaving utmost one occurance of every element*/
        allowOneOccuranceOfDuplicates(in_1);
        allowOneOccuranceOfDuplicates(in_2);
        
    }
    //Using frequency or count array, but this can be used only when
    //the elements in the array are within given range like (1,100)
    public static void removeDuplicates(int[] in){
        System.out.println("Input Array: "+Arrays.toString(in));
        int[] temp = new int[100];
        int outputArraySize = 0;
        for(int i=0;i<in.length;i++){
            temp[in[i]]++;
            if(temp[in[i]]==1)
                outputArraySize++;
            else if(temp[in[i]]==2)
                outputArraySize--;
        }
        int[] outputArray = new int[outputArraySize];
        for(int i=0,j=0;i<outputArraySize;j++){
            if(temp[j]==1){
                outputArray[i]=j;
                i++;
            }
        }
        System.out.println("Output Array: "+Arrays.toString(outputArray));
    }

    //Sort the array and add elements to output array only when 
    //ith element is not equal to (i-1)th or (i+1)th element
    public static void removeDuplicatesUsingPrevious(int[] in){
        Arrays.sort(in);
        int[] outputArray = new int[in.length];
        int j=0;
        if(in[0]!=in[1])
            outputArray[j++]=in[0];
        for(int i=1;i<outputArray.length-1;i++){
            if(in[i]!=in[i-1] && in[i]!=in[i+1]){
                outputArray[j++]=in[i];
            }
        }
        if(in[in.length-1]!=in[in.length-2])
            outputArray[j]=in[in.length-1];

        System.out.println(Arrays.toString(outputArray));
    }
    /**Follow up Question: Allowing one occurance of duplicates */
    public static void allowOneOccuranceOfDuplicates(int[] in){
        Arrays.sort(in);
        int prev = in[0];
        int[] outputArray = new int[in.length];
        int j=0;
        for(int i=1;i<in.length;i++){
            if(in[i]!=prev){
                outputArray[j++]=prev;
                prev=in[i];
            }            
        }
        outputArray[j]=prev;
        System.out.println(Arrays.toString(outputArray));
    }    
}